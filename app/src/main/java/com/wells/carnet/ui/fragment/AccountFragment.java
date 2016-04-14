package com.wells.carnet.ui.fragment;

import android.view.View;
import android.widget.RelativeLayout;

import com.wells.carnet.R;
import com.wells.carnet.ui.act.LoginAct;
import com.wells.carnet.ui.act.ModifyPwdAct;
import com.wells.carnet.utils.AlerDialogUtils;
import com.wells.carnet.utils.PreferenceUtils;

import butterknife.Bind;

/**
 * Created by Wells on 2016/4/9.
 */
public class AccountFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.logout_ll)
    RelativeLayout logoutLl;
    @Bind(R.id.modify_pwd_ll)
    RelativeLayout modifyPwdLl;

    @Override
    protected void init() {
        logoutLl.setOnClickListener(this);
        modifyPwdLl.setOnClickListener(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.view_settings;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.modify_pwd_ll:
                readyGo(ModifyPwdAct.class);
                break;
            case R.id.logout_ll:
                logout();
                break;
        }
    }

    private void logout() {
        AlerDialogUtils.showDialog(getActivity(), "提示", "是否确定退出登录?", "取消", "确定", new AlerDialogUtils.DialogBtnClickListener() {
            @Override
            public void onClick(boolean isRight) {
                if (isRight) {
                    PreferenceUtils.clearPreference(getActivity().getApplicationContext());
                    PreferenceUtils.putString(getActivity().getApplicationContext(), PreferenceUtils.Key.OPEN_STATE, "OPEN");
                    readyGo(LoginAct.class);
                    getActivity().finish();
                }
            }
        });
    }
}
