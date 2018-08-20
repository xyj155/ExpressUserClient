package com.example.administrator.expressuserclient.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseActivity;
import com.example.administrator.expressuserclient.contract.home.DeliverHistoryActivityContract;
import com.example.administrator.expressuserclient.gson.DeliverHistory;
import com.example.administrator.expressuserclient.presenter.home.DeliverHistoryActivityPresenter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * @author Administrator
 */
public class DeliverHistoryActivity extends BaseActivity implements DeliverHistoryActivityContract.View {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.ry_history)
    RecyclerView ryHistory;
    private DeliverHistoryActivityPresenter deliverHistoryActivityPresenter;

    @Override
    public int intiLayout() {
        return R.layout.activity_deliver_history;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
        initToolBar().setToolNavigationIco(R.mipmap.ic_back)
                .setToolNavigationIcoOnClickListener(new OnClickListener() {
                    @Override
                    public void OnClickListener() {
                        finish();
                    }
                });
        deliverHistoryActivityPresenter = new DeliverHistoryActivityPresenter(this);
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        deliverHistoryActivityPresenter.getDeliverHistoryByUid(sp.getInt("id", 8));
    }

    @Override
    public void initData() {
        ryHistory.setLayoutManager(new LinearLayoutManager(DeliverHistoryActivity.this));

    }

    private class DeliverAdapter extends BaseQuickAdapter<DeliverHistory, BaseViewHolder> {

        public DeliverAdapter(List<DeliverHistory> data) {
            super(R.layout.ry_histort_item_layout, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, DeliverHistory item) {
            if (helper.getPosition()==0){
                helper.setVisible(R.id.view_red,true);
            }else {
                helper.setVisible(R.id.view_red,false);
            }
            helper.setText(R.id.tv_time,item.getTime());
        }
    }

    @Override
    public void getDeliverHistoryByUid(List<DeliverHistory> deliverHistory) {
        DeliverAdapter adapter=new DeliverAdapter(deliverHistory);
        ryHistory.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
