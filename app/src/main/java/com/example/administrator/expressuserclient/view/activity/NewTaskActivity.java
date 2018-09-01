package com.example.administrator.expressuserclient.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseActivity;
import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.contract.order.TicketFragmentContract;
import com.example.administrator.expressuserclient.gson.OrderGson;
import com.example.administrator.expressuserclient.presenter.order.TicketFragmentPresenter;
import com.example.administrator.expressuserclient.weight.CircleImageView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.amap.api.services.route.RouteSearch.DRIVING_SINGLE_SHORTEST;

public class NewTaskActivity extends BaseActivity implements TicketFragmentContract.View {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.map)
    MapView map;
    @InjectView(R.id.ry_package)
    RecyclerView ryPackage;

    DrawerLayout dlLayout;
    @InjectView(R.id.tv_see)
    TextView tvSee;
    private BitmapDescriptor bitmapDescriptor;
    private MapView mMapView = null;
    private AMap aMap;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;
    private TicketFragmentPresenter presenter = new TicketFragmentPresenter(this);

    @Override
    public int intiLayout() {
        return R.layout.activity_new_task;
    }


    @Override
    public void initView(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        ryPackage.setLayoutManager(new LinearLayoutManager(NewTaskActivity.this));
        SharedPreferences sp = getSharedPreferences("user", Context.MODE_PRIVATE);
        int id = sp.getInt("id", 25);
        presenter.getOrderList(String.valueOf(id));

        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mLocationOption = new AMapLocationClientOption();
        aMap = mMapView.getMap();
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        mlocationClient = new AMapLocationClient(NewTaskActivity.this);
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        //初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        aMap.setMyLocationStyle(myLocationStyle);
        //设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(false);
        aMap.getUiSettings().setZoomControlsEnabled(false);
        // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setMyLocationEnabled(true);
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
        if (activity == null || drawerLayout == null) {
            return;
        }
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
    public void showData(final BaseGson<OrderGson> baseGson) {
        mlocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation == null) {
                    ToastUtil.showToastWarning("定位错误");
                } else {
                    RouteSearch routeSearch = new RouteSearch(NewTaskActivity.this);
                    final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(new LatLonPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude()),
                            new LatLonPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude()));
                    List<LatLonPoint> lists = new ArrayList<LatLonPoint>();
                    for (int i = 0; i < baseGson.getData().size(); i++) {
                        addMarker(new LatLng(baseGson.getData().get(i).getLatintude(), baseGson.getData().get(i).getLongtitude()));
                        lists.add(new LatLonPoint(baseGson.getData().get(i).getLatintude(), baseGson.getData().get(i).getLongtitude()));
                    }
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
                                ToastUtil.showToastError("规划出错,错误代码："+errorCode);
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
        ExpressSearchAdapter adapter = new ExpressSearchAdapter(NewTaskActivity.this, baseGson.getData());
        ryPackage.setAdapter(adapter);
    }

    private class ExpressSearchAdapter extends BaseQuickAdapter<OrderGson, BaseViewHolder> {
        private Context context;

        public ExpressSearchAdapter(Context context, List<OrderGson> data) {
            super(R.layout.ry_item_express_search, data);
            this.context = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, final OrderGson item) {
            helper.setText(R.id.tv_num, "订单号：" + item.getOrdernum())
                    .setText(R.id.tv_username, "收件人：" + item.getUsername())
                    .setText(R.id.tv_address, "地址：" + item.getEndlocation())
                    .setOnClickListener(R.id.tv_detail, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, UserExpressDetailActivity.class);
                            intent.putExtra("location", item.getLatintude() + "," + item.getLongtitude());
                            intent.putExtra("num", item.getOrdernum());
                            intent.putExtra("tel", item.getTel());
                            intent.putExtra("username", item.getUsername());
                            intent.putExtra("address", item.getEndlocation());
                            intent.putExtra("id", item.getId());
                            context.startActivity(intent);
                        }
                    });

        }
    }

    private void addMarker(LatLng latLng) {
        final MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        customizeMarkerText(R.mipmap.ic_package, new ExpressSiteListActivity.OnMarkerIconLoadListener() {
            @Override
            public void markerIconLoadingFinished(View view) {
                markerOptions.icon(bitmapDescriptor);
                aMap.addMarker(markerOptions);
            }
        });
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
     * @param listener
     */
    private void customizeMarkerText(int url, final ExpressSiteListActivity.OnMarkerIconLoadListener listener) {
        final View markerView = LayoutInflater.from(NewTaskActivity.this).inflate(R.layout.map_location_marker, null);
        final CircleImageView icon = (CircleImageView) markerView.findViewById(R.id.img_user_head);
        Glide.with(NewTaskActivity.this)
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

}
