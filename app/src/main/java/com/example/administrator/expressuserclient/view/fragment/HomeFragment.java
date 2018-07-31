package com.example.administrator.expressuserclient.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseFragment;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.gson.NewsGson;
import com.example.administrator.expressuserclient.gson.WeatherGson;
import com.example.administrator.expressuserclient.http.volley.VolleyRequestCllBack;
import com.example.administrator.expressuserclient.http.volley.VolleyRequestUtil;
import com.example.administrator.expressuserclient.view.activity.SiteListActivity;
import com.example.administrator.expressuserclient.weight.VerticalScrollLayout;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.github.xudaojie.qrcodelib.CaptureActivity;

import static android.app.Activity.RESULT_OK;
import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;
import static com.example.administrator.expressuserclient.R.id.map;
import static com.example.administrator.expressuserclient.config.SysConfig.REQUEST_QR_CODE;

/**
 * Created by Administrator on 2018/7/29.
 */

public class HomeFragment extends BaseFragment implements AMapLocationListener {


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
    private RequestQueue queue;
    //声明mlocationClient对象
    public AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void setUpView(View view, Bundle bundle) {
        initVScrollLayout();
        setBanner();
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
        mlocationClient = new AMapLocationClient(getActivity());
        mLocationOption = new AMapLocationClientOption();
        mlocationClient.setLocationListener(this);
        mLocationOption.setOnceLocation(true);
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.startLocation();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btn_scan, R.id.btn_packet_search, R.id.btn_packet_history, R.id.btn_packet_deliver, R.id.btn_deliver_points})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_scan:
                Intent intent = new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_QR_CODE);
                break;
            case R.id.btn_packet_search:
                break;
            case R.id.btn_packet_history:
                break;
            case R.id.btn_packet_deliver:
                break;
            case R.id.btn_deliver_points:
                startActivity(new Intent(getContext(), SiteListActivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK
                && requestCode == REQUEST_QR_CODE
                && data != null) {
            String result = data.getStringExtra("result");
            Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
        }
    }

    private void initVScrollLayout() {
        List<NewsGson> items = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            NewsGson item = new NewsGson();
            item.setTitle("标签" + i);
            item.setTitle("应该显示的内容标题" + i);
            items.add(item);
        }
        ScrollLayoutAdapter adapter = new ScrollLayoutAdapter();
        scrollLayout.setAdapter(adapter);
        adapter.setList(items);
    }

    private void setBanner() {
        List<String> list = new ArrayList<>();
        list.add("http://img.zcool.cn/community/01ed0a5a38d390a80121db808813d6.jpg@2o.jpg");
        list.add("http://img.zcool.cn/community/01497856f108896ac7257d2053f0d4.jpg@1280w_1l_2o_100sh.png");
        list.add("http://img.zcool.cn/community/0186185973f3c8a8012193a3a25adc.jpg");
        list.add("http://img.zcool.cn/community/0122eb5973f3e3a8012193a329ce2c.jpg");
        homeBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path.toString()).asBitmap().into(imageView);
            }
        });
        homeBanner.setIndicatorGravity(BannerConfig.CENTER);
        homeBanner.setImages(list);
        homeBanner.setDelayTime(5000);
        homeBanner.start();

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
                        tvTemp.setText("气温：" + weatherGson.getResult().get(0).getTemperature());
                        if (weatherGson.getResult().get(0).getWeather().contains("晴")) {
                            Glide.with(getActivity()).load(R.mipmap.ic_sun1).asBitmap().into(imgWeather);
                        } else if (weatherGson.getResult().get(0).getWeather().contains("雨")) {
                            Glide.with(getActivity()).load(R.mipmap.ic_ruin_1).asBitmap().into(imgWeather);
                        }else if (weatherGson.getResult().get(0).getWeather().contains("云")) {
                            Glide.with(getActivity()).load(R.mipmap.ic_cloud).asBitmap().into(imgWeather);
                        }else if (weatherGson.getResult().get(0).getWeather().contains("霾")) {
                            Glide.with(getActivity()).load(R.mipmap.ic_mai).asBitmap().into(imgWeather);
                        }else if (weatherGson.getResult().get(0).getWeather().contains("雪")) {
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
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_scrolllayout, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            NewsGson item = getItem(position);
            holder.title.setText(item.getTitle() + "1");
            holder.text.setText(item.getTitle() + "2");
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
