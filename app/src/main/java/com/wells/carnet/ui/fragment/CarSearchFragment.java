package com.wells.carnet.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wells.carnet.R;
import com.wells.carnet.contract.CarSearchContract;
import com.wells.carnet.data.CarDataSource;
import com.wells.carnet.data.entity.Car;
import com.wells.carnet.ui.adapter.CarAdapter;
import com.wells.carnet.utils.AlerDialogUtils;
import com.wells.carnet.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wells on 2016/4/9.
 */
public class CarSearchFragment extends BaseFragment implements CarSearchContract.View {
    @Bind(R.id.common_rv)
    RecyclerView commonRv;
    @Bind(R.id.error_msg_lv)
    LinearLayout errorMsgLv;
    @Bind(R.id.search_ev)
    EditText searchEv;
    private CarAdapter mAdapter;
    private CarSearchContract.Presenter mPresenter;
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

    @Override
    protected void init() {
        mAdapter = new CarAdapter(new ArrayList<Car>(), getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        commonRv.setAdapter(mAdapter);
        commonRv.setLayoutManager(manager);
        searchEv.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String carNum = searchEv.getText().toString();
                    if (!StringUtils.isEmpty(carNum))
                        mPresenter.search(carNum);
                    return true;
                }
                return false;
            }
        });
        mAdapter.setListener(new CarAdapter.onCarItemClick() {
            @Override
            public void onClick(View v, Car car) {
                showDetailInfo(car);
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.search(null);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.view_searchresult;
    }

    @Override
    public void showSearchResult(List<Car> cars) {
        commonRv.setVisibility(View.VISIBLE);
        errorMsgLv.setVisibility(View.GONE);
        mAdapter.setmCars(cars);
    }

    @Override
    public void showEmptyMsg() {
        commonRv.setVisibility(View.INVISIBLE);
        errorMsgLv.setVisibility(View.VISIBLE);
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

    @Override
    public void setPresenter(CarSearchContract.Presenter presenter) {
        mPresenter = presenter;
    }


}
