package com.lven.immooc;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.lven.lib.bmob.BmobUtils;
import com.lven.lib.bmob.IMUser;

import butterknife.BindView;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.carhouse.base.ui.AppActivity;
import cn.carhouse.titlebar.DefTitleBar;
import cn.carhouse.utils.LogUtils;
import cn.carhouse.utils.TSUtils;

public class LoginActivity extends AppActivity {
    private static final int CODE_SUCCEED = 1;
    // 倒计时
    private int time = 60;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.btn_send)
    Button mBtnSend;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case CODE_SUCCEED:
                    time--;
                    // 获取验证码发送成功
                    if (time <= 0) {
                        mHandler.removeMessages(CODE_SUCCEED);
                        mBtnSend.setEnabled(true);
                        mBtnSend.setText("发送");
                        break;
                    }
                    mBtnSend.setText(time + "s");
                    mHandler.sendEmptyMessageDelayed(CODE_SUCCEED, 1000);
                    break;
            }
            return true;
        }
    });

    @Override
    protected void initTitle(DefTitleBar titleBar) {
        titleBar.setTitle("登录");
    }

    @Override
    protected Object getContentLayout() {
        return R.layout.app_activity_login;
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
                mBtnSend.setEnabled(false);
                mHandler.sendEmptyMessage(CODE_SUCCEED);
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
        if (imUser != null) {
            LogUtils.e("IMUser:" + imUser.getUsername() + " " + imUser.getMobilePhoneNumber());
        }
    }
}
