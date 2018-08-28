package com.example.administrator.expressuserclient.view.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.DriveStep;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseActivity;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.gson.DistanceGson;
import com.example.administrator.expressuserclient.http.volley.VolleyRequestCllBack;
import com.example.administrator.expressuserclient.http.volley.VolleyRequestUtil;
import com.example.administrator.expressuserclient.weight.iosDialog.AlertDialog;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.amap.api.services.route.RouteSearch.DRIVING_SINGLE_SHORTEST;
import static com.example.administrator.expressuserclient.view.activity.PackagePointListActivity.convertViewToBitmap;

public class UserExpressDetailActivity extends BaseActivity {

    private RequestQueue queue;
    @InjectView(R.id.map)
    MapView map;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tv_num)
    TextView tvNum;
    @InjectView(R.id.tv_username)
    TextView tvUsername;
    @InjectView(R.id.tv_tel)
    TextView tvTel;
    @InjectView(R.id.tv_address)
    TextView tvAddress;
    @InjectView(R.id.tv_detail)
    TextView tvDetail;
    private MapView mMapView = null;
    private AMap aMap;

    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;

    private AMap.OnMarkerClickListener markerClickListener;

    @Override
    public int intiLayout() {
        return R.layout.activity_user_express_detail;
    }


    @Override
    public void initView(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        queue = Volley.newRequestQueue(UserExpressDetailActivity.this);

        Intent intent = getIntent();
        final String stringExtra = intent.getStringExtra("location");
        final String[] split = stringExtra.split(",");
        tvAddress.setText("收件地址：" + intent.getStringExtra("address"));
        tvUsername.setText("收件人：" + intent.getStringExtra("username"));
        tvTel.setText("联系方式：" + intent.getStringExtra("tel"));
        tvNum.setText("订单编号：" + intent.getStringExtra("num"));
        Log.i(TAG, "initView: " + stringExtra);

        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mLocationOption = new AMapLocationClientOption();
        aMap = mMapView.getMap();
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        LatLng latLng = new LatLng(Double.valueOf(split[0]), Double.valueOf(split[1]));
        MarkerOptions position = new MarkerOptions().position(latLng);
        position.icon(BitmapDescriptorFactory.fromBitmap(convertViewToBitmap(View.inflate(UserExpressDetailActivity.this, R.layout.map_location_end_marker, null))));
        aMap.addMarker(position);
        mlocationClient = new AMapLocationClient(UserExpressDetailActivity.this);
        MyLocationStyle myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(false);
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromBitmap(convertViewToBitmap(View.inflate(UserExpressDetailActivity.this, R.layout.map_location_marker, null))));
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.getUiSettings().setLogoBottomMargin(-50);
        mLocationOption.setNeedAddress(true);
        mLocationOption.setOnceLocation(true);
        mLocationOption.setOnceLocationLatest(true);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mlocationClient.setLocationOption(mLocationOption);
        CameraUpdate cameraUpdate = CameraUpdateFactory.zoomTo(15);
        mMapView.getMap().moveCamera(cameraUpdate);
        mlocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(final AMapLocation aMapLocation) {
                if (aMapLocation == null) {
                    ToastUtil.showToastWarning("定位错误");
                } else {
                    Map<String, String> map = new HashMap<>();
                    map.put("key", "4e0f85e120108994af79bfebb175b85b");
                    map.put("destination", split[1]+","+split[0]);
                    map.put("origins", aMapLocation.getLongitude() + "," + aMapLocation.getLatitude());
                    queue.add(VolleyRequestUtil.RequestWithParams("https://restapi.amap.com/v3/distance", map, new VolleyRequestCllBack() {
                        @Override
                        public void onSuccess(String result) {
                            System.out.println(result + "result");
                            Gson gson = new Gson();
                            DistanceGson distanceGson = gson.fromJson(result, DistanceGson.class);
                            if (distanceGson.getStatus().equals("1")){
                                new AlertDialog(UserExpressDetailActivity.this).builder().setTitle("距离目的地")
                                        .setMsg(String.format("%.1fkm", (Integer.valueOf(distanceGson.getResults().get(0).getDistance())) * 1.0 / 1000)+"\n\n"+"耗时："+String.format("%.2f小时", (Integer.valueOf(distanceGson.getResults().get(0).getDuration())) * 1.0 / 3600))
                                        .setNegativeButton("确定", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                            }
                                        }).show();
                            }

                        }

                        @Override
                        public void onError(String error) {

                        }
                    }));
                    RouteSearch routeSearch = new RouteSearch(UserExpressDetailActivity.this);
                    final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(new LatLonPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude()),
                            new LatLonPoint(Double.valueOf(split[0]), Double.valueOf(split[1])));
                    RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, DRIVING_SINGLE_SHORTEST, null, null, "");
                    routeSearch.calculateDriveRouteAsyn(query);
                    routeSearch.setRouteSearchListener(new RouteSearch.OnRouteSearchListener() {
                        @Override
                        public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

                        }

                        @Override
                        public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int errorCode) {
                            if (errorCode == 1000) {
                                List<LatLng> latLonPoints = new ArrayList<LatLng>();
                                for (DrivePath drivePath : driveRouteResult.getPaths()) {
                                    for (DriveStep driveStep : drivePath.getSteps()) {
                                        for (LatLonPoint latLng : driveStep.getPolyline()) {
                                            double latitude = latLng.getLatitude();
                                            double longitude = latLng.getLongitude();
                                            latLonPoints.add(new LatLng(latitude, longitude));
                                        }
                                    }
                                }
                                PolylineOptions mPolylineOptions = new PolylineOptions();
                                mPolylineOptions.setDottedLine(false);
                                mPolylineOptions.color(0xff1296db);
                                mPolylineOptions.width(8);
                                mPolylineOptions.geodesic(false);
                                mPolylineOptions.visible(true);
                                mPolylineOptions.useGradient(false);
                                mPolylineOptions.setPoints(latLonPoints);
                                aMap.addPolyline(mPolylineOptions);
                            } else {
                                ToastUtil.showToastError("规划出错");
                            }

                        }

                        @Override
                        public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

                        }

                        @Override
                        public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

                        }
                    });
                }
            }
        });
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.startLocation();//启动定位


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

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @OnClick({R.id.tv_detail, R.id.tv_num, R.id.tv_username, R.id.tv_tel, R.id.tv_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_detail:
                new AlertDialog(UserExpressDetailActivity.this).builder().setTitle("距离目的地")
                        .setMsg("")
                        .setNegativeButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
                break;
            case R.id.tv_num:
                break;
            case R.id.tv_username:
                break;
            case R.id.tv_tel:
                break;
            case R.id.tv_address:
                break;
        }
    }
}
