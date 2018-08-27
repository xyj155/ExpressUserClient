package com.example.administrator.expressuserclient.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.weight.AppleDialog;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2018/7/9.
 */

public abstract class BaseActivity extends AppCompatActivity {
    /***是否显示标题栏*/
    private boolean isshowtitle = true;
    /***是否显示标题栏*/
    private boolean isshowstate = true;
    /***封装toast对象**/
    private static Toast toast;
    /***获取TAG的activity名称**/
    protected final String TAG = this.getClass().getSimpleName();
    private Toolbar toolbar;
    public Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(intiLayout());
        //初始化控件
        initView(savedInstanceState);
        //设置数据
        initData();
    }

    public void showmDialog(String msg) {
        dialog = AppleDialog.createLoadingDialog(BaseActivity.this, msg);
        dialog.show();
    }

    public void hidemDialog() {
        dialog.dismiss();
    }

    public BaseActivity initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        return this;
    }

    public BaseActivity setToolBarSubTitle(String var) {
        toolbar.setSubtitle(var);
        return this;
    }

    public BaseActivity setToolBarTitle(String var) {
        toolbar.setTitle(var);
        return this;
    }

    public BaseActivity setToolNavigationIco(int var) {
        toolbar.setNavigationIcon(var);
        return this;
    }

    public BaseActivity setToolNavigationIcoOnClickListener(final OnClickListener onClickListener) {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.OnClickListener();
            }
        });
        return this;
    }

    public interface OnClickListener {
        void OnClickListener();
    }

    /**
     * 设置布局
     *
     * @return
     */
    public abstract int intiLayout();

    /**
     * 初始化布局
     */
    public abstract void initView(Bundle savedInstanceState) ;

    /**
     * 设置数据
     */
    public abstract void initData();

    /**
     * 是否设置标题栏
     *
     * @return
     */
    public void setTitle(boolean ishow) {
        isshowtitle = ishow;
    }

    /**
     * 设置是否显示状态栏
     *
     * @param ishow
     */
    public void setState(boolean ishow) {
        isshowstate = ishow;
    }


}