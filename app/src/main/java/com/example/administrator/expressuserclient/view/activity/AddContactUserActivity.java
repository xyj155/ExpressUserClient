package com.example.administrator.expressuserclient.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseActivity;
import com.example.administrator.expressuserclient.contract.message.AddContactUserActivityContract;
import com.example.administrator.expressuserclient.gson.UserGson;
import com.example.administrator.expressuserclient.presenter.message.AddContactUserActivityPresenter;
import com.example.administrator.expressuserclient.weight.CustomEditText;
import com.example.administrator.expressuserclient.weight.iosDialog.AlertDialog;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AddContactUserActivity extends BaseActivity implements AddContactUserActivityContract.View {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.et_search)
    CustomEditText etSearch;
    @InjectView(R.id.ry_add)
    RecyclerView ryAdd;
    private AddContactUserActivityPresenter presenter = new AddContactUserActivityPresenter(this);

    @Override
    public int intiLayout() {
        return R.layout.activity_add_contact_user;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        ryAdd.setLayoutManager(new LinearLayoutManager(AddContactUserActivity.this));
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (etSearch.getText().toString().isEmpty()) {
                    ryAdd.removeAllViews();
                    ryAdd.removeAllViewsInLayout();
                } else {
                    presenter.queryContactByUid(etSearch.getText().toString());
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        etSearch.addTextChangedListener(textWatcher);
        initToolBar().setToolNavigationIco(R.mipmap.ic_back)
                .setToolNavigationIcoOnClickListener(new OnClickListener() {
                    @Override
                    public void OnClickListener() {
                        Intent intent = new Intent(AddContactUserActivity.this, ContactListActivity.class);
                        setResult(0xff, intent);
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

    @Override
    public void loadUserList(List<UserGson> userGsonBaseGson) {
        AddUserAdapter adapter = new AddUserAdapter(userGsonBaseGson);
        ryAdd.setAdapter(adapter);
    }

    private class AddUserAdapter extends BaseQuickAdapter<UserGson, BaseViewHolder> {

        public AddUserAdapter(@Nullable List<UserGson> data) {
            super(R.layout.ry_adduser_layout, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final UserGson item) {
            helper.setText(R.id.tv_username, item.getUsername())
                    .setText(R.id.tv_identity, item.getIdentity())
                    .setOnClickListener(R.id.img_add, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new AlertDialog(AddContactUserActivity.this).builder().setTitle("添加好友")
                                    .setMsg("确认添加对方为你的快递好友？")
                                    .setPositiveButton("添加", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
                                            presenter.setNewContact(String.valueOf(sp.getInt("id", 85)), String.valueOf(item.getId()));
                                        }
                                    }).setNegativeButton("取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            }).show();
                        }
                    });
            ImageView view = helper.getView(R.id.img_head);
            Glide.with(AddContactUserActivity.this).load(item.getHead()).asBitmap().error(R.mipmap.ic_user_head).into(view);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(AddContactUserActivity.this, ContactListActivity.class);
            setResult(0xff, intent);
            finish();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }
}
