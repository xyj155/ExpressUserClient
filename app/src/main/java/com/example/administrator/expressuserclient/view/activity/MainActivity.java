package com.example.administrator.expressuserclient.view.activity;


import android.app.Notification;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseActivity;
import com.example.administrator.expressuserclient.view.fragment.HomeFragment;
import com.example.administrator.expressuserclient.view.fragment.MessageFragment;
import com.example.administrator.expressuserclient.view.fragment.TicketFragment;
import com.example.administrator.expressuserclient.view.fragment.UserFragment;
import com.example.administrator.expressuserclient.weight.bottom.BottomBarLayout;
import com.example.administrator.expressuserclient.weight.bottom.TabEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.MultiActionsNotificationBuilder;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.framlayout)
    FrameLayout framlayout;
    BottomBarLayout bottom;
    private List<TabEntity> tabEntityList;
    private String[] tabText = {"主页", "派单", "消息", "用户"};
    private int[] normalIcon = {R.mipmap.ic_bottom_mainpage_unselet, R.mipmap.ic_bottom_ticket_unselet, R.mipmap.ic_bottom_message_unselet, R.mipmap.ic_bottom_user_unselet};
    private int[] selectIcon = {R.mipmap.ic_bottom_mainpage_selet, R.mipmap.ic_bottom_ticket_select, R.mipmap.ic_bottom_message_selet, R.mipmap.ic_bottom_user_select};
    private FragmentManager fragmentManager;
    //fragment 列表
    private HomeFragment fragmentHome;
    private MessageFragment fragmentMessage;
    private TicketFragment fragmentTicket;
    private UserFragment fragmentUser;

    @Override
    public int intiLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        bottom = (BottomBarLayout) findViewById(R.id.bottom);
    }

    @Override
    public void initData() {
        //fragment切换
        fragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        fragmentHome = new HomeFragment();
        transaction.add(R.id.framlayout, fragmentHome);
        transaction.commit();
        //底部导航栏
        tabEntityList = new ArrayList<>();
        for (int i = 0; i < tabText.length; i++) {
            TabEntity item = new TabEntity();
            item.setText(tabText[i]);
            item.setNormalIconId(normalIcon[i]);
            item.setSelectIconId(selectIcon[i]);
            tabEntityList.add(item);
            if (i == 2) {
                item.setNewsCount(8);
            }
        }
        bottom.setNormalTextColor(0xff8a8a8a);
        bottom.setSelectTextColor(0xff000000);
        bottom.setTabList(tabEntityList);
        bottom.setOnItemClickListener(new BottomBarLayout.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                hideAllFragment(transaction);
                switch (position) {
                    case 0:
                        if (fragmentHome == null) {
                            fragmentHome = new HomeFragment();
                            transaction.add(R.id.framlayout, fragmentHome);
                        } else {
                            transaction.show(fragmentHome);
                        }
                        break;
                    case 1:
                        if (fragmentTicket == null) {
                            fragmentTicket = new TicketFragment();
                            transaction.add(R.id.framlayout, fragmentTicket);
                        } else {
                            transaction.show(fragmentTicket);
                        }
                        break;
                    case 2:
                        if (fragmentMessage == null) {
                            fragmentMessage = new MessageFragment();
                            transaction.add(R.id.framlayout, fragmentMessage);
                        } else {
                            transaction.show(fragmentMessage);
                        }
                        break;
                    case 3:
                        if (fragmentUser == null) {
                            fragmentUser = new UserFragment();
                            transaction.add(R.id.framlayout, fragmentUser);
                        } else {
                            transaction.show(fragmentUser);
                        }
                        break;
                }
                transaction.commit();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
    //隐藏所有Fragment

    public void hideAllFragment(FragmentTransaction transaction) {
        if (fragmentHome != null) {
            transaction.hide(fragmentHome);
        }
        if (fragmentTicket != null) {
            transaction.hide(fragmentTicket);
        }
        if (fragmentMessage != null) {
            transaction.hide(fragmentMessage);
        }
        if (fragmentUser != null) {
            transaction.hide(fragmentUser);
        }
    }
}
