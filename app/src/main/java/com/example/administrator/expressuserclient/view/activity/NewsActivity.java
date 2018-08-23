package com.example.administrator.expressuserclient.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseActivity;
import com.example.administrator.expressuserclient.contract.home.NewsActivityContract;
import com.example.administrator.expressuserclient.entity.NewsEntity;
import com.example.administrator.expressuserclient.presenter.home.NewsActivityPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NewsActivity extends BaseActivity implements NewsActivityContract.View {

    @InjectView(R.id.ry_news)
    RecyclerView ryNews;
    @InjectView(R.id.smart_layout)
    SmartRefreshLayout smartLayout;
    private NewsActivityPresenter presenter = new NewsActivityPresenter(this);
    private int index = 2;
    List<NewsEntity> list = new ArrayList<>();
    NewsAdapter adapter;
    @Override
    public int intiLayout() {
        return R.layout.activity_news;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        ryNews.setLayoutManager(new LinearLayoutManager(NewsActivity.this));
        smartLayout.autoRefresh();
        smartLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        presenter.loadNews(index);
                    }
                }).start();
                smartLayout.finishRefresh(1000);
            }
        });
        smartLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Log.i(TAG, "onLoadMore: " + index++);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        presenter.loadMore(index++);
                    }
                }).start();
                smartLayout.finishLoadMore(500);
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
    public void loadNews(final List<NewsEntity> newsEntities) {
        Log.i(TAG, "loadNews: " + newsEntities.size());
        if (newsEntities.size() > 0) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    list.addAll(newsEntities);
                    adapter = new NewsAdapter(list);
                    ryNews.setAdapter(adapter);
                }
            });
        }


    }

    @Override
    public void loadMore(final List<NewsEntity> newsEntities) {
        Log.i(TAG, "loadNews: " + newsEntities.size());
        if (newsEntities.size() > 0) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    list.addAll(newsEntities);
                    adapter.notifyDataSetChanged();
                    adapter.addData(newsEntities);
                }
            });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    private class NewsAdapter extends BaseQuickAdapter<NewsEntity, BaseViewHolder> {

        public NewsAdapter(List<NewsEntity> data) {
            super(R.layout.ry_item_news_layout, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, NewsEntity item) {
            helper.setText(R.id.tv_title, item.getTitle())
                    .setText(R.id.tv_content, item.getContent());
            ImageView view = helper.getView(R.id.img_news);
            Glide.with(NewsActivity.this).load(item.getImg()).asBitmap().into(view);
        }
    }
}
