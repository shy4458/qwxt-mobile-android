package com.sx.qwxt.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sx.baseframework.http.HttpUtils;
import com.sx.qwxt.R;
import com.sx.qwxt.view.fragment.record.MonthRecordFragment;
import com.sx.qwxt.view.fragment.record.RecordFragment;
import com.sx.qwxt.view.fragment.signin.SigninFragment;
import com.sx.qwxt.view.fragment.vehicleManager.VehicleFragment;
import com.sx.qwxt.view.fragment.vehicleManager.VehicleLendFragment;
import com.sx.qwxt.view.fragment.vehicleManager.VehicleRecordFragment;
import com.sx.qwxt.view.fragment.workManager.WorkManagerFragment;


public class MainActivity extends AppCompatActivity {

    private RadioGroup rg_navigation;
    private RadioButton rbtn_sign;
    private RadioButton rbtn_workManager;
    private RadioButton rbtn_record;
    private FrameLayout frameLayout;

    private int position = 0;
    private SigninFragment signinFragment;
    private WorkManagerFragment workManagerFragment;
    private RecordFragment recordFragment;
    private MonthRecordFragment monthRecordFragment;

    private PopupWindow mPopWindow;
    private TextView tvBackSign;
    private AlertDialog.Builder normalDialog;
    private RadioButton rbtn_vehice;
    private VehicleFragment vehicleFragment;
    private VehicleLendFragment vehicleLendFragment;
    private VehicleRecordFragment vehicleRecordFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setTabSelection(0);
        initListener();
    }

    private void initView() {
        frameLayout = findViewById(R.id.fl);
        rg_navigation = findViewById(R.id.rg_navigation);
        rbtn_sign = findViewById(R.id.rbtn_sign);
        rbtn_workManager = findViewById(R.id.rbtn_work_manager);
        rbtn_record = findViewById(R.id.rbtn_record);
        rbtn_vehice = findViewById(R.id.rbtn_vehice);
        tvBackSign = findViewById(R.id.tv_backsign);
    }

    private void initListener() {
        rg_navigation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtn_sign:
                        setTabSelection(0);
                        break;
                    case R.id.rbtn_work_manager:
                        setTabSelection(1);
                        break;
                    case R.id.rbtn_record:
                        setTabSelection(2);
                        break;
                    //3为记录查询
                    case R.id.rbtn_vehice:
                        setTabSelection(4);
                        break;
                    default:
                        break;
                }
            }
        });

        tvBackSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog();
            }
        });
    }

    private void showNormalDialog() {
        normalDialog = new AlertDialog.Builder(MainActivity.this);
        normalDialog.setTitle("您确定退出此程序么?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        normalDialog.show();
    }

    /*
       Fragment
        控制显示和隐藏
     */

    public void setTabSelection(int position) {
        //记录position
        this.position = position;
        //更改导航栏按钮状态
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                rbtn_sign.setChecked(true);
                if (signinFragment == null) {
                    signinFragment = new SigninFragment();
                    transaction.add(R.id.fl, signinFragment, "signinFragment");
                } else {
                    transaction.show(signinFragment);
                }
                break;
            case 1:
                rbtn_workManager.setChecked(true);
                if (workManagerFragment == null) {
                    workManagerFragment = new WorkManagerFragment();
                    transaction.add(R.id.fl, workManagerFragment, "workManagerFragment");
                } else {
                    transaction.show(workManagerFragment);
                }
                break;
            case 2:
                rbtn_record.setChecked(true);
                if (recordFragment == null) {
                    recordFragment = new RecordFragment();
                    transaction.add(R.id.fl, recordFragment, "recordFragment");
                } else {
                    transaction.show(recordFragment);
                }
                break;
            case 3:
                if (monthRecordFragment == null) {
                    monthRecordFragment = new MonthRecordFragment();
                    transaction.add(R.id.fl, monthRecordFragment, "monthRecordFragment");
                } else {
                    transaction.show(monthRecordFragment);
                }
                break;
            case 4:
                if (vehicleFragment == null) {
                    vehicleFragment = new VehicleFragment();
                    transaction.add(R.id.fl, vehicleFragment, "vehicleFragment");
                } else {
                    transaction.show(vehicleFragment);
                }
                break;
            case 5:
                if (vehicleLendFragment == null) {
                    vehicleLendFragment = new VehicleLendFragment();
                    transaction.add(R.id.fl, vehicleLendFragment, "vehicleLendFragment");
                } else {
                    transaction.show(vehicleLendFragment);
                }
                break;
            case 6:
                if (vehicleRecordFragment == null) {
                    vehicleRecordFragment = new VehicleRecordFragment();
                    transaction.add(R.id.fl, vehicleRecordFragment, "vehicleRecordFragment");
                } else {
                    transaction.show(vehicleRecordFragment);
                }
                break;


        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (signinFragment != null)
            transaction.hide(signinFragment);
        if (workManagerFragment != null)
            transaction.hide(workManagerFragment);
        if (recordFragment != null)
            transaction.hide(recordFragment);
        if (monthRecordFragment != null)
            transaction.hide(monthRecordFragment);
        if (vehicleFragment != null)
            transaction.hide(vehicleFragment);
        if (vehicleLendFragment != null)
            transaction.hide(vehicleLendFragment);
        if (vehicleRecordFragment != null){
            transaction.hide(vehicleRecordFragment);
        }

        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        HttpUtils.cancleAllCall(this);
        super.onDestroy();
    }

    //返回键应用不退出 到后台 点击App图标显示
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
