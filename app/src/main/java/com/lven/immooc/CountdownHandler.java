package com.lven.immooc;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

/**
 * 倒计时封装
 */
public class CountdownHandler {
    private static final int COUNTDOWN = 1;
    private int mTime;
    private int mDelayTime = 1000;
    private OnCountdownListener onCountdownListener;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case COUNTDOWN:
                    if (mTime <= 0) {
                        stop();
                        if (onCountdownListener != null) {
                            onCountdownListener.onEnd();
                        }
                        break;
                    }
                    mTime--;
                    // 回调
                    if (onCountdownListener != null) {
                        onCountdownListener.onCountdown(mTime);
                    }
                    mHandler.sendEmptyMessageDelayed(COUNTDOWN, mDelayTime);
                    break;
            }
            return true;
        }
    });


    public void start(int time) {
        this.mTime = time;
        mHandler.sendEmptyMessage(COUNTDOWN);
        if (onCountdownListener != null) {
            onCountdownListener.start();
        }
    }

    public void stop() {
        mHandler.removeMessages(COUNTDOWN);
    }

    public void setDelayTime(int delayTime) {
        this.mDelayTime = delayTime;
        if (mDelayTime < 0) {
            mDelayTime = 0;
        }
    }


    public interface OnCountdownListener {
        /**
         * 开始
         */
        void start();

        /**
         * 倒计时
         *
         * @param time 剩下时间
         */
        void onCountdown(int time);

        /**
         * 倒计完成
         */
        void onEnd();
    }

    public void setOnCountdownListener(OnCountdownListener onCountdownListener) {
        this.onCountdownListener = onCountdownListener;
    }
}
