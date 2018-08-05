package com.example.administrator.expressuserclient.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseActivity;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.contract.home.SiteListActivityContract;
import com.example.administrator.expressuserclient.gson.POIGson;
import com.example.administrator.expressuserclient.presenter.home.SiteListActivityPresenter;
import com.example.administrator.expressuserclient.weight.CircleImageView;

import java.util.ArrayList;
import java.util.List;


public class ExpressSiteListActivity extends BaseActivity implements SiteListActivityContract.View, AMap.OnMarkerClickListener, AMap.OnMapClickListener {

    private RecyclerView ry_map;
    private MapView mMapView = null;
    private AMap aMap;

    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;
    private NestedScrollView scrollview;


    private BitmapDescriptor bitmapDescriptor;
    private SiteListActivityPresenter siteListActivityPresenter;
    private Marker currentMarker;


    @Override
    public int intiLayout() {
        return R.layout.activity_site_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        scrollview = (NestedScrollView) findViewById(R.id.scrollView);
        ry_map = (RecyclerView) findViewById(R.id.ry_map);
        siteListActivityPresenter = new SiteListActivityPresenter(this);
        initToolBar().setToolNavigationIco(R.mipmap.ic_back).setToolNavigationIcoOnClickListener(new OnClickListener() {
            @Override
            public void OnClickListener() {
                finish();
            }
        });
    }

    @Override
    public void initData() {
        BottomSheetBehavior behavior = BottomSheetBehavior.from(scrollview);
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        ry_map.setLayoutManager(new LinearLayoutManager(ExpressSiteListActivity.this));
        if (ActivityCompat.checkSelfPermission(ExpressSiteListActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(ExpressSiteListActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
        mLocationOption = new AMapLocationClientOption();
        aMap = mMapView.getMap();
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        mlocationClient = new AMapLocationClient(ExpressSiteListActivity.this);
        MyLocationStyle myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(false);
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromBitmap(convertViewToBitmap(View.inflate(ExpressSiteListActivity.this, R.layout.map_location_marker, null))));
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.getUiSettings().setLogoBottomMargin(-50);
        mLocationOption.setNeedAddress(true);
        mLocationOption.setOnceLocation(true);
        mLocationOption.setOnceLocationLatest(true);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation == null) {
                    ToastUtil.showToastWarning("定位错误");
                } else {
                    CameraUpdate cameraUpdate = CameraUpdateFactory.zoomTo(15);
                    mMapView.getMap().moveCamera(cameraUpdate);
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                    aMap.setMyLocationEnabled(true);
                    siteListActivityPresenter.loadList(ExpressSiteListActivity.this, aMapLocation.getLatitude(), aMapLocation.getLongitude());
                }
            }
        });
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.startLocation();//启动定位
        CameraUpdate cameraUpdate = CameraUpdateFactory.zoomTo(19);
        mMapView.getMap().moveCamera(cameraUpdate);
        aMap.setOnMarkerClickListener(this);
        aMap.setOnMapClickListener(this);
        aMap.setInfoWindowAdapter(new AMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View infoContent = getLayoutInflater().inflate(
                        R.layout.sitelist_mapoverlay_bg, null);
                render(marker, infoContent);
                return infoContent;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }


    /**
     * func:view转bitmap
     *
     * @param view
     * @return
     */
    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    /**
     * func:定制化marker的图标
     *
     * @param url
     * @param listener
     */
    private void customizeMarkerIcon(int url, final OnMarkerIconLoadListener listener) {
        final View markerView = LayoutInflater.from(ExpressSiteListActivity.this).inflate(R.layout.map_location_marker, null);
        final CircleImageView icon = (CircleImageView) markerView.findViewById(R.id.img_user_head);
        Glide.with(ExpressSiteListActivity.this)
                .load(url)
                .asBitmap()
                .thumbnail(0.2f)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                        //待图片加载完毕后再设置bitmapDes
                        icon.setImageBitmap(bitmap);
                        bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(convertViewToBitmap(markerView));
                        listener.markerIconLoadingFinished(markerView);
                    }
                });
    }

    private List<POIGson.PoisBean> poiList = new ArrayList<>();

    /**
     * 数据加载presentor
     *
     * @param poiGson
     */
    @Override
    public void loadSitelist(List<POIGson.PoisBean> poiGson) {
        ry_map.setLayoutManager(new LinearLayoutManager(ExpressSiteListActivity.this));
        ry_map.setNestedScrollingEnabled(false);
        SiteListAdapter adapter = new SiteListAdapter(ExpressSiteListActivity.this, poiGson);
        ry_map.setAdapter(adapter);
        for (int i = 0; i < poiGson.size(); i++) {
            poiList.add(poiGson.get(i));
            addMarker(poiGson.get(i));
        }
    }

    /**
     * 解析数据添加到地图上面
     * <p>
     * //
     */

    private void addMarker(POIGson.PoisBean poisBean) {
        String location = poisBean.getLocation();
        String[] split = location.split(",");
        double lat = Double.valueOf(split[1]);
        double lot = Double.valueOf(split[0]);
        Log.i(TAG, "addMarker: " + lat + "--" + lot);
        LatLng latLng = new LatLng(lat, lot);

        final MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        customizeMarkerIcon(R.mipmap.ic_sitelist_station, new OnMarkerIconLoadListener() {
            @Override
            public void markerIconLoadingFinished(View view) {
                markerOptions.icon(bitmapDescriptor);
                aMap.addMarker(markerOptions);
            }
        });
    }

    /**
     * maker点击事件
     *
     * @param marker
     * @return
     */
    @Override
    public boolean onMarkerClick(Marker marker) {
        currentMarker = marker;
        for (int i = 0; i < poiList.size(); i++) {
            String location = poiList.get(i).getLocation();
            String[] split = location.split(",");
            double lat = Double.valueOf(split[1]);
            double lot = Double.valueOf(split[0]);
            if (marker.getPosition().longitude == lot && marker.getPosition().latitude == lat) {
                marker.setTitle(poiList.get(i).getName());
                marker.setSnippet(poiList.get(i).getAddress());
            }
        }

        marker.showInfoWindow();
        return true;
    }

    /**
     * 自定义infowinfow窗口
     */
    public void render(Marker marker, View view) {
        String title = marker.getTitle();
        String snippet = marker.getSnippet();
        TextView titleUi = ((TextView) view.findViewById(R.id.tv_name));
        TextView address = ((TextView) view.findViewById(R.id.tv_address));
        titleUi.setText(title);
        address.setText(snippet);
    }

    /**
     * 地图点击事件
     *
     * @param latLng
     */
    @Override
    public void onMapClick(LatLng latLng) {
        if (currentMarker.isInfoWindowShown()) {
            currentMarker.hideInfoWindow();//这个是隐藏infowindow窗口的方法
        }
    }

    /**
     * 列表适配器
     */
    public static class SiteListAdapter extends BaseQuickAdapter<POIGson.PoisBean, BaseViewHolder> {
        private Context context;

        public SiteListAdapter(Context context, List<POIGson.PoisBean> data) {
            super(R.layout.ry_item_sitelist_layout, data);
            this.context = context;
        }

        @Override
        protected void convert(final BaseViewHolder helper, final POIGson.PoisBean item) {
            helper.setText(R.id.tv_name, item.getName())
                    .setText(R.id.tv_address, item.getAddress())
                    .setOnClickListener(R.id.tv_station, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView view = helper.getView(R.id.tv_distance);
                            Intent intent = new Intent(context, ExpressStationDetailActivity.class);
                            intent.putExtra("distance", view.getText().toString());
                            intent.putExtra("name", item.getName());
                            intent.putExtra("location", item.getLocation());
                            intent.putExtra("type", item.getType());
                            intent.putExtra("address", item.getAddress());
                            context.startActivity(intent);
                        }
                    });
            int integer = Integer.valueOf(item.getDistance());
            if (integer > 1000) {
                helper.setText(R.id.tv_distance, String.format("%.1fkm", integer * 1.0 / 1000));
            } else {
                helper.setText(R.id.tv_distance, item.getDistance() + "m");
            }
        }
    }

    /**
     * 、
     * <p>
     * 加载框
     *
     * @param str
     */
    @Override
    public void showLoading(String str) {
        showmDialog(str);
    }

    @Override
    public void hideLoading() {
        dialog.dismiss();
    }

    public interface OnMarkerIconLoadListener {
        void markerIconLoadingFinished(View view);
    }


}
