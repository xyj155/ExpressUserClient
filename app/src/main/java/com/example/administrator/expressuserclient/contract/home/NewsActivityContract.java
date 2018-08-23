package com.example.administrator.expressuserclient.contract.home;

import com.example.administrator.expressuserclient.entity.NewsEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/8/22/022.
 */

public interface NewsActivityContract {
    interface Model {
        List<NewsEntity> getListNews(int page);

    }

    interface View {
        void loadNews(List<NewsEntity> newsEntities);

        void loadMore(List<NewsEntity> newsEntities);
    }

    interface Presenter {
        void loadNews(int page);
        void loadMore(int index);
    }
}
