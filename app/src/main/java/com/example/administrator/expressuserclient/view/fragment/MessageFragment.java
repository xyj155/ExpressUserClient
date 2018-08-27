package com.example.administrator.expressuserclient.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseFragment;
import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.contract.push.MessageFragmentContract;
import com.example.administrator.expressuserclient.gson.PushGson;
import com.example.administrator.expressuserclient.presenter.push.MessageFragmentPresenter;
import com.example.administrator.expressuserclient.view.activity.ContactListActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/29.
 */

public class MessageFragment extends BaseFragment implements MessageFragmentContract.View {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.ry_message)
    RecyclerView ryMessage;
    @InjectView(R.id.smart)
    SmartRefreshLayout smart;
    @InjectView(R.id.tv_contact)
    TextView tvContact;
    private MessageFragmentPresenter presenter = new MessageFragmentPresenter(this);

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_message;
    }

    @Override
    protected void setUpView(View view, Bundle bundle) {
        ryMessage.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void setUpData() {
        toolbar.setSubtitle("消息");
        toolbar.setSubtitleTextColor(0xff000000);
        smart.autoRefresh();
        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();//结束刷新
                list.clear();
                presenter.getPushList();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void showDialog(String msg) {
        showFragmentDialog(msg);
    }

    @Override
    public void hideDialog() {
        hideFragmentDialog();
    }

    List<PushGson> list = new ArrayList<>();

    @Override
    public void setPushList(BaseGson<PushGson> pushList) {
        list.addAll(pushList.getData());
        PushMessageAdapter adapter = new PushMessageAdapter(list);
        ryMessage.setAdapter(adapter);
    }

    @OnClick(R.id.tv_contact)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_contact:
                startActivity(new Intent(getContext(), ContactListActivity.class));
                break;
        }
    }

    private class PushMessageAdapter extends BaseQuickAdapter<PushGson, BaseViewHolder> {

        public PushMessageAdapter(List<PushGson> data) {
            super(R.layout.ry_msgpush_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, PushGson item) {
            helper.setText(R.id.tv_title, item.getTitle())
                    .setText(R.id.tv_content, "\t\t" + item.getContent())
                    .setText(R.id.tv_time, item.getDate())
                    .setOnClickListener(R.id.item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
            ImageView view = helper.getView(R.id.img_src);
            Glide.with(getActivity()).load(item.getPic()).asBitmap().into(view);
        }
    }
}
