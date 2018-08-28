package com.example.administrator.expressuserclient.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseActivity;
import com.example.administrator.expressuserclient.view.fragment.login.LoginFragment;
import com.example.administrator.expressuserclient.view.fragment.login.RegisterFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginAndRegisterActivity extends BaseActivity {


    @InjectView(R.id.vp_login)
    ViewPager vpLogin;
    @InjectView(R.id.img_finish)
    ImageView imgFinish;

    @Override
    public int intiLayout() {
        return R.layout.activity_login_and_register;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        List<Fragment> list = new ArrayList<>();
        list.add(new LoginFragment());
        list.add(new RegisterFragment());
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), list);
        vpLogin.setAdapter(adapter);
    }

    @Override
    public void initData() {

    }

    private class FragmentAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public FragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }


        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


    @OnClick(R.id.img_finish)
    public void onViewClicked() {
        finish();
    }
}
