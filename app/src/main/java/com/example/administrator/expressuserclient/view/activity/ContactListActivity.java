package com.example.administrator.expressuserclient.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseActivity;
import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.contract.message.ContactListActivityContract;
import com.example.administrator.expressuserclient.gson.UserGson;
import com.example.administrator.expressuserclient.presenter.message.ContactListActivityPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ContactListActivity extends BaseActivity implements ContactListActivityContract.View {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.ry_contact)
    RecyclerView ryContact;
    @InjectView(R.id.refresh_contact)
    SmartRefreshLayout refreshContact;
    @InjectView(R.id.tv_add)
    TextView tvAdd;
    @InjectView(R.id.ry_visible)
    LinearLayout ryVisible;
    private ContactListActivityPresenter presenter;

    @Override
    public int intiLayout() {
        return R.layout.activity_contactlist;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        presenter = new ContactListActivityPresenter(this);
        refreshContact.autoRefresh();
        refreshContact.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
                int id = sp.getInt("id", 85);
                presenter.getContactList(String.valueOf(id));
                refreshLayout.finishRefresh(300);
            }
        });
        initToolBar().setToolNavigationIco(R.mipmap.ic_back)
                .setToolNavigationIcoOnClickListener(new OnClickListener() {
                    @Override
                    public void OnClickListener() {
                        finish();
                    }
                });
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick(R.id.tv_add)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_add:
                Intent intent = new Intent(ContactListActivity.this, AddContactUserActivity.class);
                startActivityForResult(intent, 0xff);
                break;
        }

    }

    @Override
    public void loadContactList(BaseGson<UserGson> userGsonBaseGson) {
        if (userGsonBaseGson.isSuccess()) {
            ryContact.setLayoutManager(new LinearLayoutManager(ContactListActivity.this));
            ContactAdapter adapter = new ContactAdapter(userGsonBaseGson.getData());
            ryContact.setAdapter(adapter);
            ryVisible.setVisibility(View.GONE);
            ryContact.setVisibility(View.VISIBLE);
        } else {
            if (userGsonBaseGson.getCode() == 203) {
                ToastUtil.showToastInfor("你还没有好友哦！");
                ryVisible.setVisibility(View.VISIBLE);
                ryContact.setVisibility(View.GONE);
            } else {
                ToastUtil.showToastError("好友列表获取失败！");
            }

        }

    }

    private class ContactAdapter extends BaseQuickAdapter<UserGson, BaseViewHolder> {

        public ContactAdapter(List<UserGson> data) {
            super(R.layout.ry_item_contactlist_layout, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final UserGson item) {

            helper.setText(R.id.tv_username, item.getUsername()==null?"":item.getUsername())
                    .setOnClickListener(R.id.ll_contact, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(ContactListActivity.this, ConversationActivity.class);
                            intent.putExtra("username",item.getUsername());
                            startActivity(intent);
                        }
                    });
            ;
            ImageView view = helper.getView(R.id.img_head);

            Glide.with(ContactListActivity.this).load(item.getHead()).asBitmap().error(R.mipmap.ic_user_head).into(view);


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0xff) {
            refreshContact.autoRefresh();
        }
    }
}
