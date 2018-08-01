package com.example.administrator.expressuserclient.view.activity;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseActivity;
import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.contract.MessageFragmentContract;
import com.example.administrator.expressuserclient.gson.PushGson;
import com.example.administrator.expressuserclient.presenter.MessageFragmentPresenter;
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

public class MainActivity extends BaseActivity implements MessageFragmentContract.View {

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
    private final static int MESSAGE_SIZE = 8;
    private MessageFragmentPresenter presenter = new MessageFragmentPresenter(this);
    private int size;

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
        presenter.getPushList();
        fragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        fragmentHome = new HomeFragment();
        transaction.add(R.id.framlayout, fragmentHome);
        transaction.commit();
        //底部导航栏
//        setBottomBar();
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

    private void setBottomBar(int size) {
        tabEntityList = new ArrayList<>();
        TabEntity item = new TabEntity();
        item.setText(tabText[0]);
        item.setNormalIconId(normalIcon[0]);
        item.setSelectIconId(selectIcon[0]);
        tabEntityList.add(item);
        TabEntity item1 = new TabEntity();
        item1.setText(tabText[1]);
        item1.setNormalIconId(normalIcon[1]);
        item1.setSelectIconId(selectIcon[1]);
        tabEntityList.add(item1);
        final TabEntity item3 = new TabEntity();
        item3.setText(tabText[2]);
        item3.setNormalIconId(normalIcon[2]);
        item3.setSelectIconId(selectIcon[2]);

        item3.setNewsCount(size);
        tabEntityList.add(item3);
        Log.i(TAG, "initData: " + size);

        TabEntity item4 = new TabEntity();
        item4.setText(tabText[3]);
        item4.setNormalIconId(normalIcon[3]);
        item4.setSelectIconId(selectIcon[3]);
        tabEntityList.add(item4);
        bottom.setNormalTextColor(0xff8a8a8a);
        bottom.setSelectTextColor(0xff000000);
        bottom.setTabList(tabEntityList);
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

    @Override
    public void showDialog(String msg) {

    }

    @Override
    public void hideDialog() {

    }


    @Override
    public void setPushList(BaseGson<PushGson> pushList) {
        final int size = pushList.getData().size();
        setBottomBar(size);
    }

}
