package com.example.administrator.expressuserclient.view.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseActivity;
import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.contract.home.ExpressSearchActivityContract;
import com.example.administrator.expressuserclient.gson.OrderGson;
import com.example.administrator.expressuserclient.presenter.home.ExpressSearchActivityPresenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.amap.api.services.route.RouteSearch.DRIVING_SINGLE_SHORTEST;
import static com.example.administrator.expressuserclient.view.activity.PackagePointListActivity.convertViewToBitmap;

public class NewTaskActivity extends BaseActivity implements ExpressSearchActivityContract.View {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.map)
    MapView map;
    @InjectView(R.id.ry_package)
    RecyclerView ryPackage;

    DrawerLayout dlLayout;
    @InjectView(R.id.tv_see)
    TextView tvSee;

    private MapView mMapView = null;
    private AMap aMap;

    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;
    private ExpressSearchActivityPresenter presenter = new ExpressSearchActivityPresenter(this);

    @Override
    public int intiLayout() {
        return R.layout.activity_new_task;
    }


    @Override
    public void initView(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        ryPackage.setLayoutManager(new LinearLayoutManager(NewTaskActivity.this));
        presenter.expressSearch("1");

        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mLocationOption = new AMapLocationClientOption();
        aMap = mMapView.getMap();
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        mlocationClient = new AMapLocationClient(NewTaskActivity.this);
        MyLocationStyle myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(false);
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromBitmap(convertViewToBitmap(View.inflate(NewTaskActivity.this, R.layout.map_location_marker, null))));
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
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation == null) {
                    ToastUtil.showToastWarning("定位错误");
                } else {
                    RouteSearch routeSearch = new RouteSearch(NewTaskActivity.this);
                    final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(new LatLonPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude()),
                            new LatLonPoint(30.746083, 120.717651));
                    List<LatLonPoint> lists = new ArrayList<LatLonPoint>();
                    lists.add(new LatLonPoint(30.744368, 120.730555));
                    lists.add(new LatLonPoint(30.584568, 120.750255));
                    lists.add(new LatLonPoint(30.654568, 120.760985));
                    RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, DRIVING_SINGLE_SHORTEST, lists, null, "");
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
                                Toast.makeText(NewTaskActivity.this, "规划出错", Toast.LENGTH_SHORT).show();
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
        dlLayout = (DrawerLayout) findViewById(R.id.dl_layout);
        setDrawerLeftEdgeSize(NewTaskActivity.this, dlLayout, 1);
    }

    public static void setDrawerLeftEdgeSize(Activity activity, DrawerLayout drawerLayout, float displayWidthPercentage) {
        if (activity == null || drawerLayout == null) return;
        try {
            Field leftDraggerField = drawerLayout.getClass().getDeclaredField("mLeftDragger");
            leftDraggerField.setAccessible(true);
            ViewDragHelper leftDragger = (ViewDragHelper) leftDraggerField.get(drawerLayout);
            Field edgeSizeField = leftDragger.getClass().getDeclaredField("mEdgeSize");
            edgeSizeField.setAccessible(true);
            int edgeSize = edgeSizeField.getInt(leftDragger);
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            edgeSizeField.setInt(leftDragger, Math.max(edgeSize, (int) (dm.widthPixels * displayWidthPercentage)));
        } catch (Exception e) {
        }
    }

    @OnClick({R.id.tv_see, R.id.dl_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dl_layout:
                break;
            case R.id.tv_see:
                if (dlLayout.isDrawerOpen(Gravity.RIGHT)) {
                    dlLayout.closeDrawer(Gravity.RIGHT);
                } else {
                    dlLayout.openDrawer(Gravity.RIGHT);
                }
                break;
        }
    }


    @Override
    public void expressSearch(BaseGson<OrderGson> packageSiteListBaseGson) {
        ExpressSearchActivity.ExpressSearchAdapter adapter = new ExpressSearchActivity.ExpressSearchAdapter(NewTaskActivity.this, packageSiteListBaseGson.getData());
        ryPackage.setAdapter(adapter);
    }
}
