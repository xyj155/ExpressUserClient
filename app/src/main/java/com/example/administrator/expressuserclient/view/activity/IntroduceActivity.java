package com.example.administrator.expressuserclient.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class IntroduceActivity extends BaseActivity {

    @InjectView(R.id.vp_intro)
    ViewPager vpIntro;

    @Override
    public int intiLayout() {
        return R.layout.activity_intro_duce;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initData() {
        ButterKnife.inject(this);
        List<View> integers = new ArrayList<>();
        View inflate1 = LayoutInflater.from(IntroduceActivity.this).inflate(R.layout.guidance_item_1, null);
        View inflate2 = LayoutInflater.from(IntroduceActivity.this).inflate(R.layout.guidance_item_2, null);
        View inflate3 = LayoutInflater.from(IntroduceActivity.this).inflate(R.layout.guidance_item_3, null);
        TextView viewById = inflate3.findViewById(R.id.tv_ignore);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroduceActivity.this, LoginAndRegisterActivity.class));
                finish();
            }
        });
        integers.add(inflate1);
        integers.add(inflate2);
        integers.add(inflate3);
        ViewPagerIntroAdapter adapter = new ViewPagerIntroAdapter(integers);
        vpIntro.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }

    private class ViewPagerIntroAdapter extends PagerAdapter {
        List<View> list = new ArrayList<>();

        public ViewPagerIntroAdapter(List<View> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager) container).addView(list.get(position));
            return list.get(position);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(list.get(position));
        }
    }
}
