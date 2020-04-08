package com.lven.immooc;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.lven.immooc.http.CarCallback;
import com.lven.immooc.imrc.IMCloudService;
import com.lven.immooc.presenter.CarPresenter;
import com.lven.lib.bmob.BmobBean;
import com.lven.lib.bmob.BmobUtils;
import com.lven.lib.bmob.IMUser;
import com.lven.lib.frame.bmob.IMTokenUser;
import com.lven.lib.frame.http.BeanCallback;
import com.lven.immooc.presenter.BmobPresenter;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.carhouse.base.ui.AppActivity;
import cn.carhouse.titlebar.DefTitleBar;
import cn.carhouse.utils.LogUtils;
import cn.carhouse.utils.TSUtils;

public class MainActivity extends AppActivity {

    @Override
    protected Object getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initTitle(DefTitleBar titleBar) {
        titleBar.setTitle("测试");
        titleBar.clearBackImage();
    }

    public void add(View view) {
        BmobBean bmobBean = new BmobBean("Lven", 1);
        bmobBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    // ad93cfbdd5
                    LogUtils.e("添加成功：" + s);
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    public void delete(View view) {
        BmobBean bmobBean = new BmobBean();
        bmobBean.setObjectId("ad93cfbdd5");
        bmobBean.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    // ad93cfbdd5
                    LogUtils.e("删除成功");
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    public void update(View view) {
        BmobBean bmobBean = new BmobBean();
        bmobBean.setName("Kven");
        bmobBean.update("eeae2da4f4", new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e != null) {
                    e.printStackTrace();
                    return;
                }
                LogUtils.e("修改成功");
            }
        });
    }

    public void query(View view) {
        BmobQuery<BmobBean> query = new BmobQuery<>();
        query.addWhereEqualTo("name", "Lven");
        query.findObjects(new FindListener<BmobBean>() {
            @Override
            public void done(List<BmobBean> list, BmobException e) {
                if (e != null) {
                    e.printStackTrace();
                    return;
                }
                LogUtils.e(list.toString());
            }
        });
    }

    public void login(View view) {

        // 1. 如果没登录
        if (!BmobUtils.isLogin()) {
            startActivity(new Intent(this, LoginActivity.class));
            return;
        }
        // 获取用户Token
        IMUser imUser = BmobUtils.getIMUser();
        if (imUser == null) {
            return;
        }
        String token = imUser.getToken();
        if (!TextUtils.isEmpty(token)) {
            connectIM(token);
            return;
        }
        // 2. 获取融云Token
        BmobPresenter.getToken(this, imUser, new BeanCallback<IMTokenUser>() {
            @Override
            public void onSucceed(IMTokenUser user) {
                imUser.setToken(user.token);
                imUser.update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e != null) {
                            return;
                        }
                        connectIM(user.token);
                    }
                });
            }
        });

    }

    /**
     * 根据Token去连接IM
     *
     * @param token
     */
    private void connectIM(String token) {
        if (TextUtils.isEmpty(token)) {
            return;
        }
        startService(new Intent(this, IMCloudService.class));

    }

    /**
     * 测试
     * @param view
     */
    public void test(View view) {
        CarPresenter.getMsgCode(this, "13556285219", new CarCallback<Object>() {
            @Override
            public void onSucceed(Object data) {
                TSUtils.show("获取验证码成功");
            }
        });
    }
}
