package com.example.administrator.expressuserclient.model.home;

import com.example.administrator.expressuserclient.contract.home.NewsActivityContract;
import com.example.administrator.expressuserclient.entity.NewsEntity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/22/022.
 */

public class NewsActivityModel implements NewsActivityContract.Model {

    @Override
    public List<NewsEntity> getListNews(final int page) {
        return  getNewsList(page);
    }

    private List<NewsEntity> getNewsList(int page) {
        final List<NewsEntity> list = new ArrayList<NewsEntity>();
        try {
            Document document = Jsoup.connect("http://www.cecss.com/index.aspx?cat_code=qyxw&page="+page).get();
            Elements select = document.select("ul.list_n5");
            Elements li = select.select("li");
            System.out.println(select + "select");
            for (Element element : li) {
                NewsEntity entity = new NewsEntity();
                entity.setImg("http://www.cecss.com/" + element.select("div.imgk").select("img").attr("src"));
                entity.setTitle(element.select("a").text());
                entity.setContent(element.select("p").text());
                entity.setUrl("http://www.cecss.com/" + element.select("a").attr("href"));
                list.add(entity);
                System.out.println(list.size() + "cecss");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
