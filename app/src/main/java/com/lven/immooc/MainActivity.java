package com.lven.immooc;

import android.view.View;

import com.lven.lib.bmob.BmobBean;
import com.lven.lib.bmob.BmobUtils;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.carhouse.base.ui.AppActivity;
import cn.carhouse.titlebar.DefTitleBar;
import cn.carhouse.utils.ActivityUtils;
import cn.carhouse.utils.LogUtils;

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
        // 如果登录了
        if (BmobUtils.isLogin()){

            return;
        }
        ActivityUtils.startActivity(this, LoginActivity.class);
    }


}
