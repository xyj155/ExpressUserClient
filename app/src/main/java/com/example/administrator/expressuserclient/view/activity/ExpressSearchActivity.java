package com.example.administrator.expressuserclient.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseActivity;
import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.contract.home.ExpressSearchActivityContract;
import com.example.administrator.expressuserclient.gson.OrderGson;
import com.example.administrator.expressuserclient.presenter.home.ExpressSearchActivityPresenter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ExpressSearchActivity extends BaseActivity implements ExpressSearchActivityContract.View {

    @InjectView(R.id.btn_scan)
    ImageView btnScan;
    @InjectView(R.id.et_search)
    EditText etSearch;
    @InjectView(R.id.frameLayout)
    FrameLayout frameLayout;
    @InjectView(R.id.ry_search)
    RecyclerView rySearch;
    private ExpressSearchActivityPresenter presenter = new ExpressSearchActivityPresenter(this);

    @Override
    public int intiLayout() {
        return R.layout.activity_express_search;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (etSearch.getText().toString().isEmpty()) {
                    rySearch.removeAllViews();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!etSearch.getText().toString().isEmpty()) {
                    presenter.expressSearch(etSearch.getText().toString());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
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
    public void expressSearch(BaseGson<OrderGson> packageSiteListBaseGson) {
        rySearch.setLayoutManager(new LinearLayoutManager(ExpressSearchActivity.this));
        rySearch.setAdapter(new ExpressSearchAdapter(ExpressSearchActivity.this, packageSiteListBaseGson.getData()));
    }

    @OnClick(R.id.btn_scan)
    public void onViewClicked() {
        finish();
    }

    public static class ExpressSearchAdapter extends BaseQuickAdapter<OrderGson, BaseViewHolder> {
        private Context context;

        public ExpressSearchAdapter(Context context, List<OrderGson> data) {
            super(R.layout.ry_item_express_search, data);
            this.context = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, final OrderGson item) {
            helper.setText(R.id.tv_num, "订单号：" + item.getOrdernum())
                    .setText(R.id.tv_username, "收件人：" + item.getUsername())
                    .setText(R.id.tv_address, "地址：" + item.getEndlocation())
                    .setOnClickListener(R.id.tv_detail, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, UserExpressDetailActivity.class);
                            intent.putExtra("location", item.getLatintude() + "," + item.getLongtitude());
                            intent.putExtra("num", item.getOrdernum());
                            intent.putExtra("tel", item.getTel());
                            intent.putExtra("username", item.getUsername());
                            intent.putExtra("address", item.getEndlocation());
                            intent.putExtra("id", item.getId());
                            context.startActivity(intent);
                        }
                    });

        }
    }
}
