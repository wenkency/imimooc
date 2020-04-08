package com.lven.immooc;

import android.view.View;

import com.lven.lib.bmob.BmobUtils;
import com.lven.lib.imrc.IMCloudUtils;

import butterknife.OnClick;
import cn.carhouse.base.ui.AppActivity;
import cn.carhouse.titlebar.DefTitleBar;

public class ConversationActivity extends AppActivity {
    @Override
    protected void initTitle(DefTitleBar titleBar) {

    }

    @Override
    protected Object getContentLayout() {
        return R.layout.app_activity_converstaion;
    }

    @OnClick(R.id.tv_msg)
    public void message(View view) {
        String targetId = "88866268ce";
        String objectId = BmobUtils.getIMUser().getObjectId();
        if (targetId.equals(objectId)) {
            targetId = "bf64549b07";
        }
        IMCloudUtils.sendTextMassage(objectId + " send IM message to" + targetId, targetId);
    }
}
