package com.example.administrator.expressuserclient.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseActivity;
import com.example.administrator.expressuserclient.commonUtil.ActivityCollectorUtil;
import com.example.administrator.expressuserclient.commonUtil.DataCleanManager;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.http.util.RetrofitUtil;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @InjectView(R.id.tv_clean_cache)
    TextView tvCleanCache;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tv_about_us)
    TextView tvAboutUs;
    @InjectView(R.id.tv_our_service)
    TextView tvOurService;
    @InjectView(R.id.tv_update)
    TextView tvUpdate;
    @InjectView(R.id.tv_login_out)
    TextView tvLoginOut;

    @Override
    public int intiLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

        initToolBar().setToolNavigationIco(R.mipmap.ic_back)
                .setToolNavigationIcoOnClickListener(new OnClickListener() {
                    @Override
                    public void OnClickListener() {
                        finish();
                    }
                });
    }

    private void cleanCache() {
        File file = new File(this.getCacheDir().getPath());
        try {
            tvCleanCache.setText("清理缓存   " + DataCleanManager.getCacheSize(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
        cleanCache();
    }

    @OnClick({R.id.tv_clean_cache, R.id.tv_about_us, R.id.tv_our_service, R.id.tv_update, R.id.tv_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_clean_cache:
                try {
                    DataCleanManager.cleanApplicationData(getApplicationContext());
                    tvCleanCache.setText("清理缓存   0.00 KB");
                    ToastUtil.showToastSuccess("缓存清理成功！");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_about_us:
                Intent intent = new Intent(SettingActivity.this, WebActivity.class);
                intent.putExtra("url", RetrofitUtil.BASE_URL + "/CurrierBrother/public/index.php/index/Index/aboutUs");
                startActivity(intent);
                break;
            case R.id.tv_our_service:
                Intent about = new Intent(SettingActivity.this, WebActivity.class);
                about.putExtra("url", RetrofitUtil.BASE_URL + "/CurrierBrother/public/index.php/index/Index/introduce");
                startActivity(about);
                break;
            case R.id.tv_update:
                Beta.checkUpgrade();
                UpgradeInfo upgradeInfo = Beta.getUpgradeInfo();
                if (upgradeInfo == null) {
                    ToastUtil.showToastInfor("你当前的版本是最新的！");
                }
                break;
            case R.id.tv_login_out:
                ActivityCollectorUtil.finishAllActivity();
                SharedPreferences userSettings = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor editor = userSettings.edit();
                editor.clear();
                editor.apply();
                Intent intent1=new Intent(SettingActivity.this,SplashActivity.class);
                startActivity(intent1);
                break;
        }
    }


}
