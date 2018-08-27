package com.example.administrator.expressuserclient.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.administrator.expressuserclient.App;

public class CustomEditText extends android.support.v7.widget.AppCompatEditText {
    
    private DrawableLeftListener mLeftListener;
    private DrawableRightListener mRightListener;
 
    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
 
    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
 
    public CustomEditText(Context context) {
        super(context);
    }
 
    public void setDrawableLeftListener(DrawableLeftListener listener) {
        this.mLeftListener = listener;
    }
 
    public void setDrawableRightListener(DrawableRightListener listener) {
        this.mRightListener = listener;
    }
 
    public interface DrawableLeftListener {
        public void onDrawableLeftClick(View view);
    }
 
    public interface DrawableRightListener {
        public void onDrawableRightClick(View view);
    }
 
    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件
     * 当我们按下的位置 在  EditText的宽度 - 图标到控件右边的间距 - 图标的宽度  和
     * EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (mRightListener != null) {
                    if (getCompoundDrawables()[2] != null) {
                        boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight()) && (event.getX() < ((getWidth() - getPaddingRight())));
                        if (touchable) {
                            try {
                                hideSoftInput();
                                mRightListener.onDrawableRightClick(this);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            setFocusable();
                        }
                    }
                }
                if (mLeftListener != null) {
                    if (getCompoundDrawables()[0] != null) {
                        boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight()) && (event.getX() < ((getWidth() - getPaddingRight())));
                        if (touchable) {
                            try {
                                hideSoftInput();
                                mLeftListener.onDrawableLeftClick(this);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            setFocusable();
                        }
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }
 
    /**
     * 设置点击EditText右侧图标EditText失去焦点，防止点击EditText右侧图标EditText获得焦点软键盘弹出
     * 如果软键盘此刻为显示状态则强直性隐藏
     */
    private void hideSoftInput() {
        setFocusableInTouchMode(false);
        setFocusable(false);
        InputMethodManager imm = (InputMethodManager) App.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getWindowToken(), 0);
    }
 
    /**
     * 设置点击EditText输入区域，EditText请求焦点，软键盘弹出，EditText可编辑
     */
    private void setFocusable() {
        setFocusableInTouchMode(true);
        setFocusable(true);
    }
}