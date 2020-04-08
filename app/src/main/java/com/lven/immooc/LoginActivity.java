package com.lven.immooc;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lven.lib.bmob.BmobUtils;
import com.lven.lib.bmob.IMUser;

import butterknife.BindView;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.carhouse.base.ui.AppActivity;
import cn.carhouse.titlebar.DefTitleBar;
import cn.carhouse.utils.CountdownHandler;
import cn.carhouse.utils.TSUtils;

/**
 * 登录页面
 */
public class LoginActivity extends AppActivity {

    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.btn_send)
    Button mBtnSend;

    private CountdownHandler mHandler;


    @Override
    protected void initTitle(DefTitleBar titleBar) {
        titleBar.setTitle("登录");
    }

    @Override
    protected Object getContentLayout() {
        return R.layout.app_activity_login;
    }

    @Override
    protected void initViews(View view) {
        mHandler = new CountdownHandler();
        mHandler.setOnCountdownListener(new CountdownHandler.OnCountdownListener() {
            @Override
            public void start() {
                mBtnSend.setEnabled(false);
            }

            @Override
            public void onCountdown(int time) {
                mBtnSend.setText(time + "s");
            }

            @Override
            public void onEnd() {
                mBtnSend.setEnabled(true);
                mBtnSend.setText("发送");
            }
        });
    }

    /**
     * 发送
     */
    public void send(View view) {
        String phone = mEtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            TSUtils.show(mEtPhone.getHint().toString());
            mEtPhone.requestFocus();
            return;
        }
        BmobUtils.requestSMSCode(phone, new QueryListener<Integer>() {
            @Override
            public void done(Integer integer, BmobException e) {
                if (e != null) {
                    TSUtils.show("获取验证码失败：" + e.getMessage());
                    return;
                }
                TSUtils.show("获取验证码成功");
                mHandler.start(60);
            }
        });
    }

    /**
     * 登录
     *
     * @param view
     */
    public void login(View view) {
        String phone = mEtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            TSUtils.show(mEtPhone.getHint().toString());
            mEtPhone.requestFocus();
            return;
        }
        String code = mEtCode.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            TSUtils.show(mEtCode.getHint().toString());
            mEtCode.requestFocus();
            return;
        }
        BmobUtils.signOrLoginByMobilePhone(phone, code, new LogInListener<IMUser>() {
            @Override
            public void done(IMUser user, BmobException e) {
                if (user != null) {
                    // 登录成功
                    loginSucceed();
                } else if (e != null) {
                    TSUtils.show("登录失败:" + e.getMessage());
                }
            }
        });
    }

    private void loginSucceed() {
        TSUtils.show("登录成功");
        IMUser imUser = BmobUtils.getIMUser();

        // 更新
        if (imUser != null) {
            imUser.setName("Lven");
            imUser.setAvatar("https://img.car-house.cn/Upload/goods/20181023/source/6d96a0f883de42b38fa1229926975ec6.jpg");
            imUser.update(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        TSUtils.show("更新成功");
                    } else {
                        TSUtils.show("更新失败:" + e.getMessage());
                    }
                }
            });

        }
    }

    @Override
    protected void onDestroy() {
        if (mHandler != null) {
            mHandler.stop();
        }
        super.onDestroy();
    }
}
