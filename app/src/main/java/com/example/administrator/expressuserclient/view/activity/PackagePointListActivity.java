package com.example.administrator.expressuserclient.view.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
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
import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseActivity;
import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.commonUtil.MarkerOverlay;
import com.example.administrator.expressuserclient.contract.home.PackagePointListActivityContract;
import com.example.administrator.expressuserclient.gson.OrderGson;
import com.example.administrator.expressuserclient.gson.PackageSiteList;
import com.example.administrator.expressuserclient.presenter.home.PackagePointListActivityPresenter;
import com.example.administrator.expressuserclient.weight.CircleImageView;
import com.example.administrator.expressuserclient.weight.FancyAlertDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PackagePointListActivity extends BaseActivity implements PackagePointListActivityContract.View, AMap.OnMapLoadedListener {

    private MapView mMapView = null;
    private AMap aMap;
    private PackagePointListActivityPresenter packagePointListActivityPresenter;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private BitmapDescriptor bitmapDescriptor;
    private Marker currentMarker;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.map_package)
    MapView mapPackage;
    private LatLng centerPoint;
    private MarkerOverlay markerOverlay;
    List<PackageSiteList> packageSiteLists = new ArrayList<>();

    @Override
    public int intiLayout() {
        return R.layout.activity_package_point_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mMapView = (MapView) findViewById(R.id.map_package);
        mMapView.onCreate(savedInstanceState);
        packagePointListActivityPresenter = new PackagePointListActivityPresenter(this);
        SharedPreferences sp = getSharedPreferences("user", Context.MODE_PRIVATE);
        int id = sp.getInt("id", 25);
        packagePointListActivityPresenter.setPackageStation(String.valueOf(id));
        mLocationOption = new AMapLocationClientOption();
        aMap = mMapView.getMap();
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        mlocationClient = new AMapLocationClient(PackagePointListActivity.this);
        MyLocationStyle myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(false);
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromBitmap(convertViewToBitmap(View.inflate(PackagePointListActivity.this, R.layout.map_location_marker, null))));
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.getUiSettings().setLogoBottomMargin(-50);
        mLocationOption.setNeedAddress(true);
        aMap.setOnMapLoadedListener(this); //地图加载完成监听
        mLocationOption.setOnceLocation(true);
        mLocationOption.setOnceLocationLatest(true);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.startLocation();//启动定位
        mlocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                centerPoint = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
            }
        });
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                showMapDialog(marker);
                return true;
            }
        });
        initToolBar().setToolNavigationIco(R.mipmap.ic_back)
                .setToolNavigationIcoOnClickListener(new OnClickListener() {
                    @Override
                    public void OnClickListener() {
                        finish();
                    }
                });
    }

    private void showMapDialog(Marker marker) {
        double latitude = marker.getPosition().latitude;
        double longitude = marker.getPosition().longitude;
        System.out.println(longitude + "---" + latitude);
        for (int i = 0; i < packageSiteLists.size(); i++) {
            System.out.println(packageSiteLists.get(i).getLatitude() + "--" + packageSiteLists.get(i).getLongitude());
            if (packageSiteLists.get(i).getLatitude() == latitude && packageSiteLists.get(i).getLongitude() == longitude) {
                FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(this)
                        .setimageResource(R.mipmap.ic_package)
                        .setTextTitle("快递信息")
                        .setTextSubTitle("用户名：" + packageSiteLists.get(i).getUsername())
                        .setBody("地址：" + packageSiteLists.get(i).getEndlocation())
                        .setNegativeColor(R.color.colorAccent)
                        .setNegativeButtonText("我知道了")
                        .setOnNegativeClicked(new FancyAlertDialog.OnNegativeClicked() {
                            @Override
                            public void OnClick(View view, Dialog dialog) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButtonText("查看详情")
                        .setPositiveColor(R.color.blue)
                        .setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
                            @Override
                            public void OnClick(View view, Dialog dialog) {

                            }
                        })
                        .setBodyGravity(FancyAlertDialog.TextGravity.LEFT)
                        .setTitleGravity(FancyAlertDialog.TextGravity.LEFT)
                        .setSubtitleGravity(FancyAlertDialog.TextGravity.LEFT)
                        .setCancelable(false)
                        .build();
                alert.show();
            }
        }

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);

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
        final View markerView = LayoutInflater.from(PackagePointListActivity.this).inflate(R.layout.map_location_marker, null);
        final CircleImageView icon = (CircleImageView) markerView.findViewById(R.id.img_user_head);
        Glide.with(PackagePointListActivity.this)
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

    @Override
    public void showDialog(String msg) {
        showmDialog(msg);
    }

    @Override
    public void hideDialog() {
        hidemDialog();
    }

    private static final String TAG = "PackagePointListActivit";
    private List<LatLng> pointList = new ArrayList<>();

    @Override
    public void showData(BaseGson<OrderGson> packageStation) {
        for (int i = 0; i < packageStation.getData().size(); i++) {
            System.out.println(packageStation.getData().get(i).getLatintude() + "packageStation");
        }

        Log.i(TAG, "showData: " + packageStation);
        for (int i = 0; i < packageStation.getData().size(); i++) {
            packageSiteLists.add(new PackageSiteList(
                    packageStation.getData().get(i).getUsername(),
                    packageStation.getData().get(i).getLongtitude(),
                    packageStation.getData().get(i).getLatintude(),
                    packageStation.getData().get(i).getEndlocation(),
                    packageStation.getData().get(i).getTel()));
            pointList.add(new LatLng(packageStation.getData().get(i).getLatintude(), packageStation.getData().get(i).getLongtitude()));
            addMarker(new LatLng(packageStation.getData().get(i).getLatintude(), packageStation.getData().get(i).getLongtitude()));
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


    @Override
    public void onMapLoaded() {
        markerOverlay = new MarkerOverlay(aMap, pointList, centerPoint, PackagePointListActivity.this);
        markerOverlay.zoomToSpanWithCenter();
        markerOverlay.zoomToSpanWithCenter();
    }


}
