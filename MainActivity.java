package com.example.lenovo.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
 private MapView mapView;
    private BaiduMap map;
    private MapStatusUpdate mapStatusUpdate;
    private MapStatusUpdate newLatLng;
    LatLng qh=new LatLng(40.009424,116.332556);
    LatLng bd=new LatLng(39.997743,116.316176);
    LatLng tam=new LatLng(39.915112,116.403963);
    private View v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        initView();
       mapView.showZoomControls(true);
        mapView.showScaleControl(true);
        float maxZoomLevel = map.getMaxZoomLevel();
        System.out.println("地图的最大缩放级别 maxZoomLevel"+maxZoomLevel);
        float minZoomLevel = map.getMinZoomLevel();
        System.out.println("地图的最小缩放级别 minZoomLevel"+minZoomLevel);
        newLatLng = MapStatusUpdateFactory.newLatLng(qh);
map.setMapStatus(newLatLng);
        MapStatusUpdate ZoomTo = MapStatusUpdateFactory.zoomTo(15);
        map.setMapStatus(ZoomTo);

    }



    /**
     * @param v
     */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ZoomOut_bt:
                MapStatusUpdate zoomOut = MapStatusUpdateFactory.zoomOut();
                map.setMapStatus(zoomOut);
                break;
            case R.id.ZoomIn_bt:
                MapStatusUpdate zoomIn = MapStatusUpdateFactory.zoomIn();
                map.setMapStatus(zoomIn);
                break;
            case R.id.rotate_bt:
                MapStatus mapStatus = map.getMapStatus();
                float rotate = mapStatus.rotate;
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.rotate(rotate+30);
                MapStatus build = builder.build();
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(build);
                map.setMapStatus(mapStatusUpdate);
                break;
            case R.id.overlook_bt:
                MapStatus mapStatus1 = map.getMapStatus();
                float overlook = mapStatus1.overlook;
                MapStatus.Builder builder1 = new MapStatus.Builder();
                builder1.overlook(overlook-5);
                MapStatus build12 = builder1.build();
                MapStatusUpdate mapStatusUpdate1 = MapStatusUpdateFactory.newMapStatus(build12);
                map.setMapStatus(mapStatusUpdate1);
                break;
            case R.id.newlatLng_bt:
                MapStatusUpdate mapStatusUpdate2 = MapStatusUpdateFactory.newLatLng(tam);
                map.animateMapStatus(mapStatusUpdate2,5000);
                break;
            case R.id.setCompassEnable:
                UiSettings uiSettings = map.getUiSettings();
                uiSettings.setCompassEnabled(false);
                break;
        }

    }

    private void initView() {
        mapView=(MapView)findViewById(R.id.bmapView);
         map = mapView.getMap();
        Button ZoomOut_bt=(Button) findViewById(R.id.ZoomOut_bt);
        Button ZoomIn_bt=(Button) findViewById(R.id.ZoomIn_bt);
        Button rotate_bt=(Button) findViewById(R.id.rotate_bt);
        Button overlook_bt=(Button) findViewById(R.id.overlook_bt);
        Button newlatLng_bt=(Button) findViewById(R.id.newlatLng_bt);
        Button setCompassEnable=(Button) findViewById(R.id.setCompassEnable);
        ZoomOut_bt.setOnClickListener(this);
        ZoomIn_bt.setOnClickListener(this);
        rotate_bt.setOnClickListener(this);
        overlook_bt.setOnClickListener(this);
        newlatLng_bt.setOnClickListener(this);
        setCompassEnable.setOnClickListener(this);
    }

    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
    }



}
