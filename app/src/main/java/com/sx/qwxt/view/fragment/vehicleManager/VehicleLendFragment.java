package com.sx.qwxt.view.fragment.vehicleManager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sx.baseframework.base.BaseApplication;
import com.sx.baseframework.util.UIUtils;
import com.sx.qwxt.R;
import com.sx.qwxt.dagger.component.DaggerVehicleLendComponent;
import com.sx.qwxt.dagger.module.VehicleLendModule;
import com.sx.qwxt.presenter.VehicleLendPresenter;
import com.sx.qwxt.view.activity.MainActivity;
import com.sx.qwxt.view.adapter.VehicleVpAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

/**
 * 作者：shy
 * 时间：2017/11/29 0029
 * 描述：借出Fragment
 */
public class VehicleLendFragment extends Fragment {

    private LinearLayout llBack;
    private SimpleDateFormat test;
    private static List<Fragment> fragmentList = new ArrayList<>();
    private VehicleVpAdapter vehicleVpAdapter;
    private ViewPager vpVehicleLend;
    private RadioButton rbtnVehice4;
    private RadioButton rbtnVehice3;
    private RadioButton rbtnVehice2;
    private RadioButton rbtnVehice1;

    private int checkd = 0;

    @Inject
    VehicleLendPresenter presenter;
    private static final int LENDSUCCESS = 152;
    private static final int MODIFYSUCCESS = 153;
    private static final int MODIFYNO = 154;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = (MainActivity) getActivity();
            super.handleMessage(msg);
            switch (msg.what) {

                case LENDSUCCESS:
                    String str = (String) msg.obj;
                    UIUtils.showToast(getActivity(), "车辆已成功出借");
                    activity.setTabSelection(4);

                    break;
                case MODIFYSUCCESS:
                    String strModify = (String) msg.obj;
                    UIUtils.showToast(getActivity(), "车辆出借信息修改成功");
                    activity.setTabSelection(4);
                    break;
                case MODIFYNO:
                    String s = (String) msg.obj;
                    UIUtils.showToast(getActivity(), s);
                    break;
                default:
                    break;
            }
        }
    };
    private TextView tvTitle;
    private LendGvFragment lendGvFragment;
    private EditText etDometer;
    private String dometer;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerVehicleLendComponent.builder().vehicleLendModule(new VehicleLendModule(this)).build().in(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehicle_lend, null);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        String state = BaseApplication.get("addAndModify", "");
        if ("0".equals(state)) {
            tvTitle.setText("选择借车的人员和时间信息");
        } else if ("1".equals(state)) {
            tvTitle.setText("修改借车的人员和时间信息");
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {

        } else {
            //editext复原
            etDometer.setFocusable(true);
            etDometer.setFocusableInTouchMode(true);
            etDometer.requestFocus();
            etDometer.setText("");

            String state = BaseApplication.get("addAndModify", "");
            if ("0".equals(state)) {
                tvTitle.setText("选择借车的人员和时间信息");

            } else if ("1".equals(state)) {
                tvTitle.setText("修改借车的人员和时间信息");
            }

            lendGvFragment = (LendGvFragment) getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:" + vpVehicleLend.getId() + ":" + vpVehicleLend.getCurrentItem());
            lendGvFragment.onHiddenChanged(false);
        }
    }

    private void initView(View view) {
        test = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        llBack = view.findViewById(R.id.ll_vehicle_back);
        vpVehicleLend = view.findViewById(R.id.vp_vehicle_lend);
        RadioGroup rgVehiceNav = view.findViewById(R.id.rg_vehicle_nav);
        rbtnVehice4 = view.findViewById(R.id.rbtn_vehice_4);
        rbtnVehice3 = view.findViewById(R.id.rbtn_vehice_3);
        rbtnVehice2 = view.findViewById(R.id.rbtn_vehice_2);
        rbtnVehice1 = view.findViewById(R.id.rbtn_vehice_1);
        Button bCancel = view.findViewById(R.id.b_cancel);
        Button bYes = view.findViewById(R.id.b_yes);
        tvTitle = view.findViewById(R.id.tv_title);
        etDometer = view.findViewById(R.id.et_dometer);
        etDometer.setFocusable(true);
        etDometer.setFocusableInTouchMode(true);
        etDometer.requestFocus();

        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                activity.setTabSelection(4);
            }
        });

        for (int i = 1; i < 5; i++) {
            if (i == 1) {
                fragmentList.add(newInstance(i + ""));
            } else if (i == 2) {
                fragmentList.add(newInstance(i + ""));
            } else if (i == 3) {
                fragmentList.add(newInstance(i + ""));
            } else if (i == 4) {
                fragmentList.add(newInstance(i + ""));
            }
        }
        vpVehicleLend.setOffscreenPageLimit(0);
        vehicleVpAdapter = new VehicleVpAdapter(getFragmentManager(), getActivity(), fragmentList);
        vpVehicleLend.setAdapter(vehicleVpAdapter);

        rgVehiceNav.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int item = 0;
                switch (checkedId) {
                    case R.id.rbtn_vehice_1:
                        item = 0;
                        break;
                    case R.id.rbtn_vehice_2:
                        item = 1;
                        break;
                    case R.id.rbtn_vehice_3:
                        item = 2;
                        break;
                    case R.id.rbtn_vehice_4:
                        item = 3;
                        break;
                }

                //ViewPager切换到对应的页面
                vpVehicleLend.setCurrentItem(item, false);//false 不需要Viewpager页面切换的时候有滑动的动画
            }
        });

        vpVehicleLend.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //每次滑动刷新guidview选中的人员
                BaseApplication.set("ryid", "");
                BaseApplication.set("ryidstate", "");
                lendGvFragment = (LendGvFragment) getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:" + vpVehicleLend.getId() + ":" + vpVehicleLend.getCurrentItem());
                lendGvFragment.onHiddenChanged(false);

                checkd = position;
                switch (position) {
                    case 0:
                        rbtnVehice1.setChecked(true);
                        break;
                    case 1:
                        rbtnVehice2.setChecked(true);
                        break;
                    case 2:
                        rbtnVehice3.setChecked(true);
                        break;
                    case 3:
                        rbtnVehice4.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                activity.setTabSelection(4);
            }
        });

        bYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dometer = etDometer.getText().toString();
                if (dometer.length() == 0) {
                    dometer = "";
                }
                String ryid = BaseApplication.get("ryid", "");
                String carid = BaseApplication.get("carid", "");
                /** modify 为空时借车的接口
                    ryidstate 为空表示未选中人员
                 */
                String modify = BaseApplication.get("modify", "");
                if (modify == "") { //借车
                    if ("".equals(BaseApplication.get("ryidstate", ""))) {
                        //为空是没选择人员
                        UIUtils.showToast(getActivity(), "请选择借车的人员...");
                    } else {
                        presenter.lendSubmit(ryid, carid, dometer);
                        BaseApplication.set("ryidstate", "");
                    }
                } else {            //修改借车信息
                    if ("".equals(BaseApplication.get("ryidstate", ""))) {
                        //为空是没选择人员
                        UIUtils.showToast(getActivity(), "请选择借车的人员...");
                    } else {
                        presenter.lendModifySubmit(ryid, dometer);
                        BaseApplication.set("modify", "");
                        BaseApplication.set("ryidstate", "");
                    }
                }
            }
        });
    }

    //时间截取拼接
    public String getdate(String str) {
        String s = str.substring(0, 4) + str.substring(5, 7) + str.substring(8, 10) + str.substring(11, 13)
                + str.substring(14, 16);
        return s;
    }

    //获取时间
    public String getTime() {
        int y, m, d, h, mi, s;
        Calendar cal = Calendar.getInstance();
        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH) + 1;
        d = cal.get(Calendar.DATE);
        h = cal.get(Calendar.HOUR_OF_DAY);
        mi = cal.get(Calendar.MINUTE);
//        System.out.println("现在时刻是" + y + "年" + m + "月" + d + "日" + h + "时" + mi + "分" + s + "秒");
        return y + "-" + m + "-" + d + " " + h + ":" + mi;
    }

    //创建Fragmen并传递小组参数
    public static LendGvFragment newInstance(String doctor_id) {
        LendGvFragment fragment = new LendGvFragment();
        Bundle bundle = new Bundle();
        bundle.putString("grouping_xz", doctor_id);
        fragment.setArguments(bundle);
        return fragment;
    }

    //借出车辆成功的信息
    public void lendSuccess(String str) {
        Message msg = Message.obtain();
        msg.what = LENDSUCCESS;
        msg.obj = str;
        mHandler.sendMessage(msg);
    }

    //修改信息成功信息
    public void lendModifySuccess(String str) {
        Message msg = Message.obtain();
        msg.what = MODIFYSUCCESS;
        msg.obj = str;
        mHandler.sendMessage(msg);
    }

    //修改借车人员失败
    public void lendModifyNo(String s) {
        Message msg = Message.obtain();
        msg.what = MODIFYNO;
        msg.obj = s;
        mHandler.sendMessage(msg);
    }
}
