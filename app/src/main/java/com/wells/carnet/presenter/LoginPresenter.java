package com.wells.carnet.presenter;

import com.wells.carnet.contract.LoginContract;
import com.wells.carnet.data.TempData;
import com.wells.carnet.ui.act.LoginAct;
import com.wells.carnet.utils.PreferenceUtils;
import com.wells.carnet.utils.StringUtils;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Wells on 2016/4/12.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View mView) {
        checkNotNull(mView);
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void login(String userName, String pwd) {
        if (StringUtils.isEmpty(userName)) {
            mView.showErrorInfo("用户名不能为空");
            return;
        }
        if (StringUtils.isEmpty(pwd)) {
            mView.showErrorInfo("密码不能为空");
            return;
        }
        if (userName.equals(TempData.userName) && pwd.equals(TempData.pwd)) {
            PreferenceUtils.putString(((LoginAct) mView).getApplicationContext(), PreferenceUtils.Key.LOGIN_STATE, "LOGIN");
            mView.showMainPage();
        } else {
            mView.showErrorInfo("用户名密码不匹配");
        }

    }

    @Override
    public void start() {

    }
}
