package com.wells.carnet.contract;

/**
 * Created by Wells on 2016/4/12.
 */
public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void showMainPage();

        void showErrorInfo(String info);

        void setProgressLoading();

        void stopProgressLoading();

    }

    interface Presenter extends BasePresenter {
        void login(String userName, String pwd);
    }
}
