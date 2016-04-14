package com.wells.carnet.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.wells.carnet.R;

import butterknife.Bind;

/**
 * Created by Wells on 2016/4/9.
 */
public class MsgFragment extends BaseFragment {

    @Bind(R.id.common_rv)
    RecyclerView commonRv;
    @Bind(R.id.error_msg_lv)
    LinearLayout errorMsgLv;

    @Override
    protected void init() {
        commonRv.setVisibility(View.GONE);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.view_msglist;
    }

}
