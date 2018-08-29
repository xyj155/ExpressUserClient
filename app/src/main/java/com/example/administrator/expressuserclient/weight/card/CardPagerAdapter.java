package com.example.administrator.expressuserclient.weight.card;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.expressuserclient.App;
import com.example.administrator.expressuserclient.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<CardItem> mData;
    private float mBaseElevation;

    public CardPagerAdapter() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void addCardItem(CardItem item) {
        mViews.add(null);
        mData.add(item);
    }

    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.pop_card_adapter_item, container, false);
        container.addView(view);
        bind(mData.get(position), view);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(CardItem item, View view) {
        TextView tem = (TextView) view.findViewById(R.id.tv_temp);
        TextView weather = (TextView) view.findViewById(R.id.tv_weather);
        TextView wind = (TextView) view.findViewById(R.id.tv_wind);
        ImageView imgWeather = (ImageView) view.findViewById(R.id.img_weather);
        TextView week = (TextView) view.findViewById(R.id.tv_week);
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour < 17|| hour > 6) {
            tem.setText(item.getTemperature());
            weather.setText(item.getDayTime());
            wind.setText(item.getWind());
            week.setText(item.getWeek());
            if (item.getDayTime().contains("晴")) {
                Glide.with(App.getInstance()).load(R.mipmap.ic_sun1).asBitmap().into(imgWeather);
            } else if (item.getDayTime().contains("雨")) {
                Glide.with(App.getInstance()).load(R.mipmap.ic_ruin_1).asBitmap().into(imgWeather);
            } else if (item.getDayTime().contains("云")) {
                Glide.with(App.getInstance()).load(R.mipmap.ic_cloud).asBitmap().into(imgWeather);
            } else if (item.getDayTime().contains("霾")) {
                Glide.with(App.getInstance()).load(R.mipmap.ic_mai).asBitmap().into(imgWeather);
            } else if (item.getDayTime().contains("雪")) {
                Glide.with(App.getInstance()).load(R.mipmap.ic_snow).asBitmap().into(imgWeather);
            }
        } else {
            tem.setText(item.getTemperature());
            weather.setText(item.getNight());
            wind.setText(item.getWind());
            week.setText(item.getWeek());
            if (item.getNight().contains("晴")) {
                Glide.with(App.getInstance()).load(R.mipmap.ic_sun1).asBitmap().into(imgWeather);
            } else if (item.getNight().contains("雨")) {
                Glide.with(App.getInstance()).load(R.mipmap.ic_ruin_1).asBitmap().into(imgWeather);
            } else if (item.getNight().contains("云")) {
                Glide.with(App.getInstance()).load(R.mipmap.ic_cloud).asBitmap().into(imgWeather);
            } else if (item.getNight().contains("霾")) {
                Glide.with(App.getInstance()).load(R.mipmap.ic_mai).asBitmap().into(imgWeather);
            } else if (item.getNight().contains("雪")) {
                Glide.with(App.getInstance()).load(R.mipmap.ic_snow).asBitmap().into(imgWeather);
            }


        }


    }

}
