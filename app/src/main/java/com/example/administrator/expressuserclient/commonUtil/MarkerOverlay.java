package com.example.administrator.expressuserclient.commonUtil;

import android.content.Context;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.example.administrator.expressuserclient.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.expressuserclient.view.activity.PackagePointListActivity.convertViewToBitmap;

public class MarkerOverlay {
    private List<LatLng> pointList = new ArrayList<LatLng>();
    private AMap aMap;
    private LatLng centerPoint;
    private Marker centerMarker;
    private ArrayList<Marker> mMarkers = new ArrayList<Marker>();
    private Context context;

    public MarkerOverlay(AMap amap, List<LatLng> points, LatLng centerpoint, Context context) {
        this.aMap = amap;
        this.context = context;
        this.centerPoint = centerpoint;
        initPointList(points);
        initCenterMarker();
    }

    //初始化list
    private void initPointList(List<LatLng> points) {
        if (points != null && points.size() > 0) {
            for (LatLng point : points) {
                pointList.add(point);
            }
        }
    }

    //初始化中心点Marker
    private void initCenterMarker() {
        this.centerMarker = aMap.addMarker(new MarkerOptions()
                .anchor(0.5f, 0.5f)
                .icon(BitmapDescriptorFactory.fromBitmap(convertViewToBitmap(View.inflate(context, R.layout.map_location_marker, null))))
                        .position(centerPoint));
        centerMarker.showInfoWindow();
    }


    /**
     * 缩放移动地图，保证所有自定义marker在可视范围中，且地图中心点不变。
     */
    public void zoomToSpanWithCenter() {
        if (pointList != null && pointList.size() > 0) {
            if (aMap == null)
                return;
            centerMarker.setVisible(true);
            centerMarker.showInfoWindow();
            LatLngBounds bounds = getLatLngBounds(centerPoint, pointList);
            aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
        }
    }

    //根据中心点和自定义内容获取缩放bounds
    private LatLngBounds getLatLngBounds(LatLng centerpoint, List<LatLng> pointList) {
        LatLngBounds.Builder b = LatLngBounds.builder();
        if (centerpoint != null) {
            for (int i = 0; i < pointList.size(); i++) {
                LatLng p = pointList.get(i);
                LatLng p1 = new LatLng((centerpoint.latitude * 2) - p.latitude, (centerpoint.longitude * 2) - p.longitude);
                b.include(p);
                b.include(p1);
            }
        }
        return b.build();
    }

    /**
     * 缩放移动地图，保证所有自定义marker在可视范围中。
     */
    public void zoomToSpan() {
        if (pointList != null && pointList.size() > 0) {
            if (aMap == null)
                return;
            centerMarker.setVisible(false);
            LatLngBounds bounds = getLatLngBounds(pointList);
            aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
        }
    }

    /**
     * 根据自定义内容获取缩放bounds
     */
    private LatLngBounds getLatLngBounds(List<LatLng> pointList) {
        LatLngBounds.Builder b = LatLngBounds.builder();
        for (int i = 0; i < pointList.size(); i++) {
            LatLng p = pointList.get(i);
            b.include(p);
        }
        return b.build();
    }


}