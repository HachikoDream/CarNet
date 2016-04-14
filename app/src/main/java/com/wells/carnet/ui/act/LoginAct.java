package com.wells.carnet.ui.act;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.wells.carnet.R;
import com.wells.carnet.contract.LoginContract;
import com.wells.carnet.presenter.LoginPresenter;
import com.wells.carnet.utils.StringUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wells on 2016/4/9.
 */
public class LoginAct extends BaseAct implements LoginContract.View {
    @Bind(R.id.username_ev)
    EditText usernameEv;
    @Bind(R.id.userpwd_ev)
    EditText userpwdEv;
    @Bind(R.id.login_btn)
    Button loginBtn;
    LoginContract.Presenter mPresenter;
    ProgressDialog mPd;

    @Override
    protected void init() {
        mPresenter = new LoginPresenter(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = usernameEv.getText().toString();
                String pwd = userpwdEv.getText().toString();
                mPresenter.login(userName, pwd);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.view_login;
    }

    @Override
    public void showMainPage() {
        readyGoThenKill(MainAct.class);

    }

    @Override
    public void showErrorInfo(String info) {
        showToast(info);
    }

    @Override
    public void setProgressLoading() {
        if (mPd == null)
            mPd = ProgressDialog.show(this, "", getString(R.string.loading_msg), true, false);
        else {
            if (!mPd.isShowing())
                mPd.show();
        }
    }

    @Override
    public void stopProgressLoading() {
        if (mPd != null && mPd.isShowing()) {
            mPd.dismiss();
        }
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

    }
}
