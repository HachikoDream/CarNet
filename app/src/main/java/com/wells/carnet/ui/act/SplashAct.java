package com.wells.carnet.ui.act;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.wells.carnet.R;
import com.wells.carnet.utils.PreferenceUtils;

/**
 * Created by Wells on 2016/4/12.
 */
public class SplashAct extends AppCompatActivity {

    private Handler mHandler;
    private boolean mIsInterrupted = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.view_splash);
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!mIsInterrupted)
                    checkIsLogin();
            }


        }, 3000);
    }

    private void checkIsLogin() {
        if (PreferenceUtils.hasKey(getApplicationContext(), PreferenceUtils.Key.LOGIN_STATE)) {
            Intent intent = new Intent(this, MainAct.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, LoginAct.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mIsInterrupted = true;
        if (mHandler != null) {

            mHandler.removeCallbacksAndMessages(null);
        }
    }
}
