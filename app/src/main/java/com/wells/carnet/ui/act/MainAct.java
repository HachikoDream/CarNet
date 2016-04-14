package com.wells.carnet.ui.act;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;

import com.wells.carnet.R;
import com.wells.carnet.contract.CarSearchContract;
import com.wells.carnet.data.CarDataSource;
import com.wells.carnet.data.CarDataSourceImpl;
import com.wells.carnet.presenter.CarSearchPresenter;
import com.wells.carnet.ui.adapter.VPFragmentAdapter;
import com.wells.carnet.ui.fragment.AccountFragment;
import com.wells.carnet.ui.fragment.BaseFragment;
import com.wells.carnet.ui.fragment.CarControlFragment;
import com.wells.carnet.ui.fragment.CarSearchFragment;
import com.wells.carnet.ui.fragment.IndexFragment;
import com.wells.carnet.ui.fragment.MsgFragment;
import com.wells.carnet.ui.widget.XViewPager;
import com.wells.carnet.utils.InputUtils;

import butterknife.Bind;

/**
 * Created by Wells on 2016/4/9.
 */
public class MainAct extends BaseAct implements NavigationView.OnNavigationItemSelectedListener {
    @Bind(R.id.fragment_container)
    XViewPager mContainerVp;
    @Bind(R.id.id_navigation)
    NavigationView mNavigation;
    @Bind(R.id.dl_left)
    DrawerLayout mDl;
    private ActionBarDrawerToggle mDrawerToggle;
    private BaseFragment[] fragments = {
            new IndexFragment(),
            new AccountFragment(),
            new MsgFragment(),
            new CarSearchFragment(),
            new CarControlFragment()
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    protected void init() {
        mNavigation.setNavigationItemSelectedListener(this);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDl, 0, 0) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                InputUtils.closeInputMethod(MainAct.this);
            }
        };
        mDl.addDrawerListener(mDrawerToggle);
        if (null != fragments) {
            mContainerVp.setEnableScroll(false);
            mContainerVp.setOffscreenPageLimit(fragments.length);
            mContainerVp.setAdapter(new VPFragmentAdapter(getSupportFragmentManager(), fragments));
        }
        CarDataSource carDataSource = CarDataSourceImpl.getInstance(getApplicationContext());
        CarSearchContract.Presenter carSearchPresenter = new CarSearchPresenter((CarSearchContract.View) fragments[3], carDataSource);

    }

    @Override
    public int getLayoutId() {
        return R.layout.view_main;
    }

    private void setFragmentTitle(int id) {
        getSupportActionBar().setTitle(getString(id));
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        InputUtils.closeInputMethod(this);
        mDl.closeDrawer(mNavigation);
        if (!getSupportActionBar().isShowing()) {
            getSupportActionBar().show();
        }
        switch (item.getItemId()) {
            case R.id.mn_home:
                mContainerVp.setCurrentItem(0, false);
                setFragmentTitle(R.string.home);
                break;
            case R.id.mn_account:
                mContainerVp.setCurrentItem(1, false);
                setFragmentTitle(R.string.myaccount);
                break;
            case R.id.mn_msg:
                mContainerVp.setCurrentItem(2, false);
                setFragmentTitle(R.string.mymsg);
                break;
            case R.id.mn_search:
                mContainerVp.setCurrentItem(3, false);
                getSupportActionBar().hide();
                setFragmentTitle(R.string.carsearch);
                break;
            case R.id.mn_control:
                mContainerVp.setCurrentItem(4, false);
                setFragmentTitle(R.string.carcontrol);
                break;
        }
        return true;
    }
}
