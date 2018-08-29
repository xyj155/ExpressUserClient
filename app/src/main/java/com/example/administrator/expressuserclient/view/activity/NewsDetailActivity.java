package com.example.administrator.expressuserclient.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Administrator
 */
public class NewsDetailActivity extends BaseActivity {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.ry_news)
    RecyclerView ryNews;
    @InjectView(R.id.tv_time)
    TextView tvTime;
    @InjectView(R.id.divider)
    View divider;

    @Override
    public int intiLayout() {
        return R.layout.activity_news_detail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        showmDialog("新闻加载中...");
        ryNews.setNestedScrollingEnabled(false);
        initToolBar().setToolNavigationIco(R.mipmap.ic_back)
                .setToolNavigationIcoOnClickListener(new OnClickListener() {
                    @Override
                    public void OnClickListener() {
                        finish();
                    }
                });
        ryNews.setLayoutManager(new LinearLayoutManager(NewsDetailActivity.this));
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final List<String> strings = new ArrayList<String>();
                    Document utl = Jsoup.connect(getIntent().getStringExtra("url")).get();
                    Elements select1 = utl.select("div.nssj");
                    final Element time = select1.select("span").get(1);
                    final String text = utl.select("div.nsbt").text();
                    Elements select = utl.select("div.nsnr");
                    Elements p = select.select("p");
                    for (Element element : p) {
                        strings.add("\t\t\t" + element.text());
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            NewsDetailAdapter adapter = new NewsDetailAdapter(strings);
                            ryNews.setAdapter(adapter);
                            tvTitle.setText(text);
                            tvTitle.setBackgroundColor(0xffffffff);
                            tvTime.setBackgroundColor(0xffffffff);
                            tvTime.setText(time.text());
                            divider.setVisibility(View.VISIBLE);
                            hidemDialog();
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }


    private class NewsDetailAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public NewsDetailAdapter(@Nullable List<String> data) {
            super(R.layout.ry_item_newsdetail_layout, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_content, item);
        }
    }

}
