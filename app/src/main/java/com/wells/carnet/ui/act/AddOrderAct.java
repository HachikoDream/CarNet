package com.wells.carnet.ui.act;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.wells.carnet.R;
import com.wells.carnet.contract.AddOrderContract;
import com.wells.carnet.data.CarDataSource;
import com.wells.carnet.data.CarDataSourceImpl;
import com.wells.carnet.data.OrderDataSource;
import com.wells.carnet.data.OrderDataSourceImpl;
import com.wells.carnet.data.entity.Car;
import com.wells.carnet.data.entity.Order;
import com.wells.carnet.presenter.AddOrderPresenter;
import com.wells.carnet.utils.AlerDialogUtils;
import com.wells.carnet.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.utils.DataUtils;
import cn.aigestudio.datepicker.views.DatePicker;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Wells on 2016/4/9.
 */
public class AddOrderAct extends BaseAct implements AddOrderContract.View {

    @Bind(R.id.orderman_ev)
    EditText ordermanEv;
    @Bind(R.id.phonenum_ev)
    EditText phonenumEv;
    @Bind(R.id.srcplace_ev)
    EditText srcplaceEv;
    @Bind(R.id.destplace_ev)
    EditText destplaceEv;
    @Bind(R.id.startDate_ev)
    EditText startDateEv;
    @Bind(R.id.enddate_ev)
    EditText enddateEv;
    @Bind(R.id.usename_ev)
    EditText usenameEv;
    @Bind(R.id.triptype_ev)
    EditText triptypeEv;
    @Bind(R.id.reason_ev)
    EditText reasonEv;
    @Bind(R.id.apply_btn)
    Button applyBtn;
    @Bind(R.id.carname_ev)
    EditText carNameEv;
    String orderman;
    String phoneNum;
    String srcPlace;
    String destPlace;
    String startDate;
    String endDate;
    String userName;
    String tripType;
    String reason;
    int state = Order.STATE_APPLY;
    String carId;
    String[] carNames;
    String[] carIds;
    AddOrderContract.Presenter mPresenter;
    public static String[] types = {"短途", "长途"};

    @Override
    protected void init() {
        CarDataSource carDataSource = CarDataSourceImpl.getInstance(getApplicationContext());
        OrderDataSource orderDataSource = OrderDataSourceImpl.getInstance(getApplicationContext());
        AddOrderContract.Presenter presenter = new AddOrderPresenter(this, carDataSource, orderDataSource);
        setDateEditStyle(startDateEv);
        setDateEditStyle(enddateEv);
        carNameEv.setInputType(InputType.TYPE_NULL);
        carNameEv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    mPresenter.getAllCars();
                }
                return true;
            }
        });
        triptypeEv.setInputType(InputType.TYPE_NULL);
        triptypeEv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    showTypesDialog();
                }
                return true;
            }
        });
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValue();
                mPresenter.addOrder(state, orderman, phoneNum, srcPlace, destPlace, startDate, endDate, userName, tripType, reason, carId);
            }
        });
    }

    private void showTypesDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("路途性质")
                .setItems(types, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        triptypeEv.setText(types[which]);
                        dialog.dismiss();
                    }
                }).create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

    }

    private void getValue() {
        orderman = ordermanEv.getText().toString();
        phoneNum = phonenumEv.getText().toString();
        srcPlace = srcplaceEv.getText().toString();
        destPlace = destplaceEv.getText().toString();
        startDate = startDateEv.getText().toString();
        endDate = enddateEv.getText().toString();
        userName = usenameEv.getText().toString();
        tripType = triptypeEv.getText().toString();
        reason = reasonEv.getText().toString();
    }

    private void setDateEditStyle(final EditText enddateEv) {
        enddateEv.setInputType(InputType.TYPE_NULL);
        enddateEv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    showDataPickerDialog(enddateEv);
                }
                return true;
            }
        });
    }

    public void showCarListDialog(List<Car> cars) {
        List<String> carLists = new ArrayList<String>();
        List<String> carIdLists = new ArrayList<String>();
        for (Car car : cars) {
            carIdLists.add(car.getId());
            carLists.add(car.getCarNum() + "," + car.getDetail());
        }
        carIds = carIdLists.toArray(new String[carIdLists.size()]);
        carNames = carLists.toArray(new String[carLists.size()]);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("选择车辆")
                .setItems(carNames, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        carId = carIds[which];
                        carNameEv.setText(carNames[which]);
                        dialog.dismiss();
                    }
                }).create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    @Override
    public void showNoCarUsed() {
        AlerDialogUtils.showDialog(this, "", "暂时没有可用车辆，请稍后再试", null, "确定", null);
    }

    private void showDataPickerDialog(final EditText editText) {
        checkNotNull(editText);
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.show();
        DatePicker picker = new DatePicker(this);
        picker.setDate(DataUtils.getCurrentYear(), DataUtils.getCurrentMonth());
        picker.setMode(DPMode.SINGLE);
        picker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
            @Override
            public void onDatePicked(String date) {
                dialog.dismiss();
                if (!StringUtils.isEmpty(date)) {
                    editText.setText(date);
                }
            }
        });
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setContentView(picker, params);
        dialog.getWindow().setGravity(Gravity.CENTER);
    }


    @Override
    public int getLayoutId() {
        return R.layout.view_addorder;
    }

    @Override
    public void showError(String info) {
        showToast(info);
    }

    @Override
    public void showSuccessDialog(final Order order) {
        AlerDialogUtils.showDialog(this, "", "申请成功，请等待审核.", null, "确定", new AlerDialogUtils.DialogBtnClickListener() {
            @Override
            public void onClick(boolean isRight) {
                showOrderList(order);
            }
        });
    }

    @Override
    public void showOrderList(Order order) {
        Intent intent = new Intent();
        intent.putExtra(OrderListAct.NEED_REFRESH, order);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void setPresenter(AddOrderContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
