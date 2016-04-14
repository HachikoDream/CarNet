package com.wells.carnet.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.wells.carnet.R;
import com.wells.carnet.ui.act.OrderListAct;

import butterknife.Bind;

/**
 * Created by Wells on 2016/4/9.
 */
public class CarControlFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.apply_ll)
    RelativeLayout applyLl;
    @Bind(R.id.verify_ll)
    RelativeLayout verifyLl;

    @Override
    protected void init() {
        applyLl.setOnClickListener(this);
        verifyLl.setOnClickListener(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.view_operatecar;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.apply_ll:
                Bundle applyBundle = new Bundle();
                applyBundle.putInt(OrderListAct.SOURCE, OrderListAct.APPLY_LIST);
                readyGo(OrderListAct.class, applyBundle);
                break;
            case R.id.verify_ll:
                Bundle verifyBundle = new Bundle();
                verifyBundle.putInt(OrderListAct.SOURCE, OrderListAct.VERIFY_LIST);
                readyGo(OrderListAct.class, verifyBundle);
                break;
        }
    }
}
