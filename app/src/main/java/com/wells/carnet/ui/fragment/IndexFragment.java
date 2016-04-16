package com.wells.carnet.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.wells.carnet.R;
import com.wells.carnet.contract.CarShowContract;
import com.wells.carnet.data.entity.Car;
import com.wells.carnet.data.entity.Location;
import com.wells.carnet.utils.AlerDialogUtils;
import com.wells.carnet.utils.LocationUtils;

import java.util.List;

/**
 * Created by Wells on 2016/4/9.
 */
public class IndexFragment extends Fragment implements CarShowContract.View {
    MapView bmapView;
    private CarShowContract.Presenter mPresenter;
    private Handler mHandler;
    private BaiduMap mBaiduMap;
    private boolean loaded = false;
    private List<Car> mCars = null;
    private final String DRIVER = "司机: ";
    private final String STATE = "状态: ";
    private final String SPEED = "车速: ";
    private final String DISTANCE = "总里程: ";
    private final String ELECTRIC = "电瓶: ";
    private final String OWNER = "拥有人: ";
    private final String DETAIL = "车型: ";
    private final String OTHER = "其他: ";
    private final String CARNUM = "车牌号: ";
    private final String POSITION = "位置: ";

    protected void init(View view) {
        setHasOptionsMenu(true);
        mHandler = new Handler();
        mBaiduMap.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                loaded = true;
                if (mCars != null) {
                    setMapOverlay(LocationUtils.transfer(mCars));
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getAllInfos();
    }


    @Override
    public void showLocations(List<Car> carList) {
        mCars = carList;
        if (loaded) {
            setMapOverlay(LocationUtils.transfer(carList));
        }
    }

    private void setMapOverlay(List<Location> locations) {
        resetMapState();
        for (int i = 0; i < locations.size(); i++) {
            LatLng point = new LatLng(locations.get(i).getLatitude(), locations.get(i).getLongitude());
//构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.mediumcaricon);
//构建MarkerOption，用于在地图上添加Marker
            MarkerOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap);
//在地图上添加Marker，并显示
            mBaiduMap.addOverlay(option);
        }
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                LatLng latLng = marker.getPosition();
                Car car = LocationUtils.findCarByPos(mCars, new Location(latLng.longitude, latLng.latitude));
                Log.i("info", car.toString());
                showDetailInfo(car);
                return true;
            }
        });

    }

    @Override
    public void showNoCarInfo() {
        AlerDialogUtils.showDialog(getActivity(), "", "暂无相关车辆，请稍后重新获取", null, "确定", null);
    }

    @Override
    public void showDetailInfo(Car car) {
        View detailView = LayoutInflater.from(getActivity()).inflate(R.layout.car_detail_view, null);
        TextView driverTv = (TextView) detailView.findViewById(R.id.driver_tv);
        TextView speedTv = (TextView) detailView.findViewById(R.id.speed_tv);
        TextView eleTv = (TextView) detailView.findViewById(R.id.electric_tv);
        TextView ownerTv = (TextView) detailView.findViewById(R.id.owner_tv);
        TextView distanceTv = (TextView) detailView.findViewById(R.id.totaldistance_tv);
        TextView stateTv = (TextView) detailView.findViewById(R.id.state_tv);
        TextView detailTv = (TextView) detailView.findViewById(R.id.cartype_tv);
        TextView otherTv = (TextView) detailView.findViewById(R.id.other_tv);
        TextView carnumTv = (TextView) detailView.findViewById(R.id.carnum_tv);
        TextView posTv = (TextView) detailView.findViewById(R.id.position_tv);
        driverTv.setText(DRIVER + car.getDriver());
        if (car.getState() == Car.STATE_IDLE) {
            speedTv.setVisibility(View.GONE);
            stateTv.setText(STATE + "停靠中");
        } else {
            speedTv.setVisibility(View.VISIBLE);
            stateTv.setText(STATE + "运行中");
            speedTv.setText(SPEED + car.getSpeed() + "km/h");
        }
        eleTv.setText(ELECTRIC + car.getElectric() + "v");
        ownerTv.setText(OWNER + car.getOwner());
        distanceTv.setText(DISTANCE + car.getTotalDistance() + "km");
        detailTv.setText(DETAIL + car.getDetail());
        otherTv.setText(OTHER + car.getOther());
        carnumTv.setText(CARNUM + car.getCarNum());
        posTv.setText(POSITION + car.getPosition());
        AlerDialogUtils.showCustomDialog(getActivity(), detailView, "车辆信息", null, "确定", null);
    }

    public void resetMapState() {
        LocationUtils.Range range = LocationUtils.getMapRange(mCars);
        double aveLat = (range.maxLatitude + range.minLatitude) / 2;
        double aveLin = (range.maxLongitude + range.minLongitude) / 2;
        LatLng ll = new LatLng(aveLat,
                aveLin);
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(ll).zoom(11.0f);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }

    @Override
    public void setPresenter(CarShowContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view_showcarpos, container, false);
        bmapView = (MapView) v.findViewById(R.id.bmapView);
        mBaiduMap = bmapView.getMap();
        init(v);
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        bmapView.onDestroy();
    }


    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        bmapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        bmapView.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refresh_menu) {
            resetMapState();
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.index_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
