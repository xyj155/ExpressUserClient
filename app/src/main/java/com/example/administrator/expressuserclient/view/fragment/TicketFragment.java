package com.example.administrator.expressuserclient.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseFragment;
import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.contract.order.TicketFragmentContract;
import com.example.administrator.expressuserclient.gson.OrderGson;
import com.example.administrator.expressuserclient.presenter.order.TicketFragmentPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2018/7/29.
 */

public class TicketFragment extends BaseFragment implements TicketFragmentContract.View {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.ry_ticket)
    RecyclerView ryTicket;
    @InjectView(R.id.smart)
    SmartRefreshLayout smart;
    @InjectView(R.id.ry_visible)
    LinearLayout ryVisible;
    private TicketFragmentPresenter presenter = new TicketFragmentPresenter(this);

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_ticket;
    }

    @Override
    protected void setUpView(View view, Bundle bundle) {
        ryTicket.setLayoutManager(new LinearLayoutManager(getActivity()));
        smart.autoRefresh();
        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smart.finishRefresh();
                presenter.getOrderList("0");
            }
        });
    }

    @Override
    protected void setUpData() {
        toolbar.setSubtitle("派单");
        toolbar.setSubtitleTextColor(0xff000000);
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
    public void showData(BaseGson<OrderGson> baseGson) {
        System.out.println(baseGson.getData().size() + "size");
        if (baseGson.getData().size()< 1) {
            ryVisible.setVisibility(View.VISIBLE);
            ryTicket.setVisibility(View.GONE);
            ToastUtil.showToastUsual("今天可以休息哦！");
        } else {
            ryVisible.setVisibility(View.GONE);
            ryTicket.setVisibility(View.VISIBLE);
            ToastUtil.showToastSuccess("今天有订单哦！要辛苦你了");
            TicketAdapter adapter = new TicketAdapter(baseGson.getData());
            ryTicket.setAdapter(adapter);
        }

    }

    private class TicketAdapter extends BaseQuickAdapter<OrderGson, BaseViewHolder> {

        public TicketAdapter(List<OrderGson> data) {
            super(R.layout.ry_orderitem_layout, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, OrderGson item) {
            helper.setText(R.id.tv_username, "收件人：" + item.getUsername())
                    .setText(R.id.tv_num, "订单号：" + item.getOrdernum())
                    .setText(R.id.tv_address, "派送地址：" + item.getEndlocation())
                    .setText(R.id.tv_tel, "联系方式：" + item.getUsertel());
        }
    }
}
