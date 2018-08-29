package com.example.administrator.expressuserclient.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseFragment;
import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.contract.home.HomeFragmentContract;
import com.example.administrator.expressuserclient.contract.order.TicketFragmentContract;
import com.example.administrator.expressuserclient.gson.BannerGson;
import com.example.administrator.expressuserclient.gson.NewsGson;
import com.example.administrator.expressuserclient.gson.OrderGson;
import com.example.administrator.expressuserclient.gson.WeatherGson;
import com.example.administrator.expressuserclient.http.volley.VolleyRequestCllBack;
import com.example.administrator.expressuserclient.http.volley.VolleyRequestUtil;
import com.example.administrator.expressuserclient.presenter.home.HomeFragmentPresenter;
import com.example.administrator.expressuserclient.presenter.order.TicketFragmentPresenter;
import com.example.administrator.expressuserclient.view.activity.DeliverHistoryActivity;
import com.example.administrator.expressuserclient.view.activity.ExpressSearchActivity;
import com.example.administrator.expressuserclient.view.activity.ExpressSiteListActivity;
import com.example.administrator.expressuserclient.view.activity.NewTaskActivity;
import com.example.administrator.expressuserclient.view.activity.NewsActivity;
import com.example.administrator.expressuserclient.view.activity.PackagePointListActivity;
import com.example.administrator.expressuserclient.view.activity.ScanShiperTraceActivity;
import com.example.administrator.expressuserclient.view.activity.ShiperTraceActivity;
import com.example.administrator.expressuserclient.view.activity.WebActivity;
import com.example.administrator.expressuserclient.weight.VerticalScrollLayout;
import com.example.administrator.expressuserclient.weight.card.CardFragmentPagerAdapter;
import com.example.administrator.expressuserclient.weight.card.CardItem;
import com.example.administrator.expressuserclient.weight.card.CardPagerAdapter;
import com.example.administrator.expressuserclient.weight.card.ShadowTransformer;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.github.xudaojie.qrcodelib.CaptureActivity;

import static android.app.Activity.RESULT_OK;
import static com.example.administrator.expressuserclient.config.SysConfig.REQUEST_QR_CODE;

/**
 * Created by Administrator on 2018/7/29.
 */

public class HomeFragment extends BaseFragment implements AMapLocationListener, TicketFragmentContract.View, HomeFragmentContract.View {

    @InjectView(R.id.tv_weather)
    TextView tvWeather;
    @InjectView(R.id.ll_weather)
    LinearLayout llWeather;
    private HomeFragmentPresenter homeFragmentPresenter;
    @InjectView(R.id.btn_scan)
    ImageView btnScan;
    @InjectView(R.id.frameLayout)
    FrameLayout frameLayout;
    @InjectView(R.id.btn_packet_search)
    TextView btnPacketSearch;
    @InjectView(R.id.btn_packet_history)
    TextView btnPacketHistory;
    @InjectView(R.id.btn_packet_deliver)
    TextView btnPacketDeliver;
    @InjectView(R.id.btn_deliver_points)
    TextView btnDeliverPoints;
    @InjectView(R.id.home_banner)
    Banner homeBanner;
    @InjectView(R.id.scroll_layout)
    VerticalScrollLayout scrollLayout;
    @InjectView(R.id.img_weather)
    ImageView imgWeather;
    @InjectView(R.id.tv_temp)
    TextView tvTemp;
    @InjectView(R.id.tv_air)
    TextView tvAir;
    @InjectView(R.id.tv_province)
    TextView tvProvince;
    @InjectView(R.id.tv_city)
    TextView tvCity;
    @InjectView(R.id.tv_distance)
    TextView tvDistance;
    @InjectView(R.id.smart)
    SmartRefreshLayout smart;
    @InjectView(R.id.red_point)
    View redPoint;
    @InjectView(R.id.tv_search)
    TextView tvSearch;
    @InjectView(R.id.scrollView)
    NestedScrollView scrollView;
    @InjectView(R.id.new_task)
    LinearLayout newTask;
    @InjectView(R.id.tv_sum)
    TextView tvSum;
    private RequestQueue queue;
    //声明mlocationClient对象
    public AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    private TicketFragmentPresenter presenter = new TicketFragmentPresenter(this);
    @InjectView(R.id.ic_box)
    ImageView ic_box;
    @InjectView(R.id.new_task)
    LinearLayout new_task;
    private PopupWindow popupWindow;
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private CardFragmentPagerAdapter mFragmentCardAdapter;
    private ShadowTransformer mFragmentCardShadowTransformer;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void setUpView(View view, Bundle bundle) {


    }


    @Override
    protected void setUpData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        queue = Volley.newRequestQueue(getActivity());
        //高德地图
        homeFragmentPresenter = new HomeFragmentPresenter(this);
        mlocationClient = new AMapLocationClient(getActivity());
        mLocationOption = new AMapLocationClientOption();
        mlocationClient.setLocationListener(this);
        mLocationOption.setOnceLocation(true);
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.startLocation();
        smart.autoRefresh();
        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smart.finishRefresh();
                SharedPreferences sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                int id = sp.getInt("id", 25);
                homeFragmentPresenter.setMainFragmentData();
                presenter.getOrderList(String.valueOf(id));
            }
        });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.ll_weather, R.id.tv_distance, R.id.new_task, R.id.tv_search, R.id.btn_scan, R.id.btn_packet_search, R.id.btn_packet_history, R.id.btn_packet_deliver, R.id.btn_deliver_points})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_weather:
                showPopWindow();
                break;
            case R.id.tv_distance:
                startActivity(new Intent(getContext(), NewsActivity.class));
                break;
            case R.id.new_task:
                startActivity(new Intent(getContext(), NewTaskActivity.class));
                break;
            case R.id.tv_search:
                startActivity(new Intent(getContext(), ExpressSearchActivity.class));
                break;
            case R.id.btn_scan:
                Intent intent = new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_QR_CODE);
                break;
            case R.id.btn_packet_search:
                startActivity(new Intent(getContext(), ShiperTraceActivity.class));
                break;
            case R.id.btn_packet_history:
                startActivity(new Intent(getContext(), DeliverHistoryActivity.class));
                break;
            case R.id.btn_packet_deliver:
                startActivity(new Intent(getContext(), PackagePointListActivity.class));
                break;
            case R.id.btn_deliver_points:
                startActivity(new Intent(getContext(), ExpressSiteListActivity.class));
                break;
            default:
                break;
        }
    }

    private void showPopWindow() {
        final View popupWindow_view = LayoutInflater.from(getActivity()).inflate(R.layout.popwindows_fragment_home_weather, null, false);
        popupWindow = new PopupWindow(popupWindow_view, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setAnimationStyle(R.style.PopWindowAnimStyle);
        popupWindow.showAtLocation(llWeather, Gravity.BOTTOM, 0, 0);
        final ViewPager mViewPager = (ViewPager) popupWindow_view.findViewById(R.id.viewPager);
        ImageView img_close = popupWindow_view.findViewById(R.id.img_close);
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    backgroundAlpha(1f);
                }
            }
        });
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(false);
        popupWindow.setTouchable(false);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
        mCardAdapter = new CardPagerAdapter();
        Map<String, String> map = new HashMap<>();
        map.put("key", "2415af360b224");
        map.put("city", tvCity.getText().toString().replace("市", ""));
        map.put("province", tvProvince.getText().toString().replace("省", ""));
        queue.add(VolleyRequestUtil.RequestWithParams("http://apicloud.mob.com/v1/weather/query", map, new VolleyRequestCllBack() {
            @Override
            public void onSuccess(String result) {
                Log.i(TAG, "onSuccess: " + result);
                Gson gson = new Gson();
                WeatherGson weatherGson = gson.fromJson(result, WeatherGson.class);
                if (weatherGson.getResult() != null) {
                    List<WeatherGson.ResultBean.FutureBean> future = weatherGson.getResult().get(0).getFuture();
                    if (future.size() > 0) {
                        Calendar calendar = Calendar.getInstance();
                        int hour = calendar.get(Calendar.HOUR_OF_DAY);
                        Log.i(TAG, "onSuccess: "+hour);
                        if (hour > 17||hour<6) {
                            mCardAdapter.addCardItem(new CardItem(future.get(0).getNight(), future.get(0).getTemperature(),
                                    future.get(0).getWeek(), future.get(0).getWind()));
                            for (int i = 1; i < future.size(); i++) {
                                mCardAdapter.addCardItem(new CardItem(future.get(i).getNight()==null?"晴":future.get(i).getNight(), future.get(i).getTemperature(),
                                        future.get(i).getWeek(), future.get(i).getWind()));
                            }
                        }else {
                            for (int i = 0; i < future.size(); i++) {
                                mCardAdapter.addCardItem(new CardItem(future.get(i).getDayTime(), future.get(i).getTemperature(),
                                        future.get(i).getWeek(), future.get(i).getWind()));
                            }
                        }

                        mFragmentCardAdapter = new CardFragmentPagerAdapter(getChildFragmentManager(),
                                dpToPixels(2, getActivity()));
                        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
                        mFragmentCardShadowTransformer = new ShadowTransformer(mViewPager, mFragmentCardAdapter);
                        mCardShadowTransformer.enableScaling(true);
                        mFragmentCardShadowTransformer.enableScaling(true);
                        backgroundAlpha(0.5f);
                        mViewPager.setAdapter(mCardAdapter);
                        mViewPager.setPageTransformer(false, mCardShadowTransformer);
                        mViewPager.setOffscreenPageLimit(3);
                    }
                }
            }

            @Override
            public void onError(String error) {

            }
        }));

    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK
                && requestCode == REQUEST_QR_CODE
                && data != null) {
            String result = data.getStringExtra("result");
            Intent intent = new Intent(getContext(), ScanShiperTraceActivity.class);
            intent.putExtra("code", result);
            startActivity(intent);
        }
    }


    @Override
    public void onLocationChanged(final AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                Map<String, String> map = new HashMap<>();
                map.put("key", "2415af360b224");
                map.put("city", aMapLocation.getCity().replace("市", ""));
                map.put("province", aMapLocation.getProvince().replace("省", ""));
                queue.add(VolleyRequestUtil.RequestWithParams("http://apicloud.mob.com/v1/weather/query", map, new VolleyRequestCllBack() {
                    @Override
                    public void onSuccess(String result) {
                        tvCity.setText(aMapLocation.getCity());
                        tvProvince.setText(aMapLocation.getProvince());
                        Gson gson = new Gson();
                        WeatherGson weatherGson = gson.fromJson(result, WeatherGson.class);
                        Log.i(TAG, "onSuccess: " + weatherGson.getMsg());
                        tvAir.setText("空气：" + weatherGson.getResult().get(0).getAirCondition());
                        tvWeather.setText("天气:" + weatherGson.getResult().get(0).getWeather());
                        tvTemp.setText("气温：" + weatherGson.getResult().get(0).getTemperature());
                        if (weatherGson.getResult().get(0).getWeather().contains("晴")) {
                            Glide.with(getActivity()).load(R.mipmap.ic_sun1).asBitmap().into(imgWeather);
                        } else if (weatherGson.getResult().get(0).getWeather().contains("雨")) {
                            Glide.with(getActivity()).load(R.mipmap.ic_ruin_1).asBitmap().into(imgWeather);
                        } else if (weatherGson.getResult().get(0).getWeather().contains("云")) {
                            Glide.with(getActivity()).load(R.mipmap.ic_cloud).asBitmap().into(imgWeather);
                        } else if (weatherGson.getResult().get(0).getWeather().contains("霾")) {
                            Glide.with(getActivity()).load(R.mipmap.ic_mai).asBitmap().into(imgWeather);
                        } else if (weatherGson.getResult().get(0).getWeather().contains("雪")) {
                            Glide.with(getActivity()).load(R.mipmap.ic_snow).asBitmap().into(imgWeather);
                        }
                    }

                    @Override
                    public void onError(String error) {

                    }
                }));
            } else {
                ToastUtil.showToastError("定位出错！");
            }
        }

    }

    private static final String TAG = "HomeFragment";

    @Override
    public void showData(BaseGson<OrderGson> baseGson) {
        Log.i(TAG, "showData: " + baseGson.getData().size());
        if (baseGson.getData().size() > 0) {
            ic_box.setVisibility(View.VISIBLE);
            new_task.setVisibility(View.VISIBLE);
            tvSum.setText("配送快递数量：" + baseGson.getData().size() + "件");
        } else {
            ic_box.setVisibility(View.GONE);
            new_task.setVisibility(View.GONE);

        }
    }

    @Override
    public void setBanner(final List<BannerGson> bannerGsonBaseGson, List<NewsGson> baseGson) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < bannerGsonBaseGson.size(); i++) {
            list.add(bannerGsonBaseGson.get(i).getPic());
        }
        homeBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path.toString()).asBitmap().into(imageView);
            }
        });
        homeBanner.setIndicatorGravity(BannerConfig.LEFT);
        homeBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("url", bannerGsonBaseGson.get(position).getUrl());
                startActivity(intent);
            }
        });
        homeBanner.setImages(list);
        homeBanner.setDelayTime(5000);
        homeBanner.start();
        ScrollLayoutAdapter adapter = new ScrollLayoutAdapter();
        scrollLayout.setAdapter(adapter);
        adapter.setList(baseGson);
    }


    private static class ScrollLayoutAdapter extends BaseAdapter {
        private List<NewsGson> list;

        ScrollLayoutAdapter() {
            this.list = new ArrayList<>();
        }

        void setList(List<NewsGson> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public NewsGson getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ry_item_home_scrolllayout, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            NewsGson item = getItem(position);
            holder.title.setText(item.getTitle());
            holder.text.setText(item.getContent());
            return convertView;
        }
    }

    private static class ViewHolder {
        private TextView title;
        private TextView text;

        ViewHolder(View view) {
            this.title = (TextView) view.findViewById(R.id.tv_news1);
            this.text = (TextView) view.findViewById(R.id.tv_news2);
        }
    }
}
