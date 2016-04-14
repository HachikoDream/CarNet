package com.wells.carnet.ui.act;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wells.carnet.R;

import butterknife.Bind;

/**
 * Created by Wells on 2016/4/9.
 */
public class ModifyPwdAct extends BaseAct {
    @Bind(R.id.oldpwd_ev)
    EditText oldpwdEv;
    @Bind(R.id.newpwd_ev)
    EditText newpwdEv;
    @Bind(R.id.newpwd2_ev)
    EditText newpwd2Ev;
    @Bind(R.id.modify_btn)
    Button modifyBtn;

    @Override
    protected void init() {
        modifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.view_modifypwd;
    }


}
