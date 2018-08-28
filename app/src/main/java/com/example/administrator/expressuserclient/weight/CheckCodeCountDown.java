package com.example.administrator.expressuserclient.weight;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;

import com.example.administrator.expressuserclient.commonUtil.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckCodeCountDown extends android.support.v7.widget.AppCompatTextView {
 
    private boolean canSend = true;//默认可以发送 , 等计时结束时方可重新发送
 
    public CheckCodeCountDown(Context context) {
        this(context, null);
    }
 
    public CheckCodeCountDown(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
 
    public CheckCodeCountDown(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
 
    private OnFinishListener mFinishListener;//计时完成的监听
    private OnSendCheckCodeListener mSendCheckCodeListener;//发送验证码的监听
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("ss");//将时间格式设置为 只显示 秒 的格式 eg : 59
 
    long millTime = 60 * 1000; // 倒计时为 1 分钟
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
 
            millTime -= 1000;//每次自减 1000 毫秒
            if (millTime < 1000) {
 
                //倒计时结束时 将handler中的消息及回调移除
                handler.removeCallbacksAndMessages(null);
                if (mFinishListener != null) {
                    mFinishListener.OnFinish();// 接口回调
                }
                canSend = true;//倒计时结束 , 可以重新发送
                return;
            }
            date.setTime(millTime);
            String string = sdf.format(date);//格式化时间
 
            setText(string);//更新文本内容 , 即更新时间
            updateTime();// 递归调用,循环更改时间
        }
    };
 
 
    /**
     * 开始发送验证码 , 这个类最核心的代码
     */
    private void startSend() {
        if (mSendCheckCodeListener == null) {
            return;
        }
        mSendCheckCodeListener.sendCheckCode();
        updateTime();
    }
 
    /**
     * 更新时间的方法,核心是每隔 1000 毫秒 Handler发送一次消息
     */
    private void updateTime() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }, 1000);
    }
 
 
    /**
     * 设置倒计时时间的方法,单位是毫秒
     *
     * @param millisecond 倒计时的时间,eg:倒计时为5000毫秒,则传5000即可
     */
    public void setMillisecond(long millisecond) {
        millTime = millisecond;
    }
 
    /**
     * 设置倒计时结束的监听
     * 在回调的OnFinish()方法中干你想干的事
     */
    public void setOnFinishListener(OnFinishListener listener) {
        mFinishListener = listener;
    }
 
    public interface OnFinishListener {
        void OnFinish();
    }
 
    /**
     * 设置发送验证码操作的回调
     * 在回调的sendCheckCode()方法中执行发送验证码的操作
     */
    public void setOnSendCheckCodeListener(OnSendCheckCodeListener listener) {
        mSendCheckCodeListener = listener;
    }
 
    public interface OnSendCheckCodeListener {
        void sendCheckCode();
    }
 
    /**
     * 在该View的onClick方法中调用
     *
     * @param phoneNumber
     */
    public void performOnClick(boolean phoneNumber) {
        if (!phoneNumber) {
            ToastUtil.showToastError("手机号格式不正确");

            return;
        }
        if (canSend) {
            setMillisecond(60 * 1000); // 每次发送之前 要将倒计时的时间更新为最初的时间
            startSend();//执行发送验证码的逻辑
 
            canSend = false;// 1 分钟之内不能重复发送
        }
    }
 
}