package com.example.administrator.expressuserclient.presenter.home;

import com.example.administrator.expressuserclient.contract.home.NewsActivityContract;
import com.example.administrator.expressuserclient.entity.NewsEntity;
import com.example.administrator.expressuserclient.model.home.NewsActivityModel;

import java.util.List;

/**
 * Created by Administrator on 2018/8/22/022.
 */

public class NewsActivityPresenter implements NewsActivityContract.Presenter {
    private NewsActivityContract.View view;
    private NewsActivityModel model = new NewsActivityModel();

    public NewsActivityPresenter(NewsActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void loadNews(int page) {

        List<NewsEntity> listNews = model.getListNews(page);
        if (listNews.size()>0){
            view.loadNews(listNews);
        }

    }

    @Override
    public void loadMore(int index) {
        List<NewsEntity> listNews = model.getListNews(index);
        if (listNews.size()>0){
            view.loadMore(listNews);
        }
    }
}
