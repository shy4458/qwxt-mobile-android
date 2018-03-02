package com.sx.qwxt.view.fragment.record;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sx.qwxt.R;
import com.sx.qwxt.dagger.component.DaggerMonthRecordFragmentComponent;
import com.sx.qwxt.dagger.module.MonthRecordFragmentModule;
import com.sx.qwxt.model.recordModel.RecordMonthModel;
import com.sx.qwxt.presenter.MonthRecordFragmentPresenter;
import com.sx.qwxt.view.activity.MainActivity;
import com.sx.qwxt.view.adapter.RecordHalfMonthVpAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

/**
 * 作者：shy
 * 时间：2017/11/12 0012
 * 描述：查询记录中 查看本月考勤记录页面
 */
public class MonthRecordFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private ViewPager vpRecordHalfMonth;
    private List<Fragment> fragments = new ArrayList<>();
    private LinearLayout llMonthBack;

    @Inject
    MonthRecordFragmentPresenter presenter;
    private static final int RECORD = 105;
    private int position = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case RECORD:
                    RecordMonthModel recordMonthModel = (RecordMonthModel) msg.obj;

                    break;

                default:
                    break;
            }
        }
    };
    private RadioButton rbtnRecordEnd;
    private RadioGroup rgRecordNavigation;
    private RadioButton rbtnRecordStart;
    private OneForFifteenFragment oneForFifteenFragment;
    private FifteenForThirtyFragment fifteenForThirtyFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMonthRecordFragmentComponent.builder().monthRecordFragmentModule(new MonthRecordFragmentModule(this)).build().in(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.month_record, container, false);
        initView(view);
        initListener();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initView(View view) {
        vpRecordHalfMonth = view.findViewById(R.id.vp_record_half_month);
        rgRecordNavigation = view.findViewById(R.id.rg_record_navigation);
        llMonthBack = view.findViewById(R.id.ll_month_back);
        rbtnRecordStart = view.findViewById(R.id.rbtn_record_start);
        rbtnRecordEnd = view.findViewById(R.id.rbtn_record_end);
        rbtnRecordEnd.setText("16 - " + getCurrentMonthLastDay() + " 日");

        oneForFifteenFragment = new OneForFifteenFragment();
        fifteenForThirtyFragment = new FifteenForThirtyFragment();

        fragments.add(oneForFifteenFragment);
        fragments.add(fifteenForThirtyFragment);
        vpRecordHalfMonth.setAdapter(new RecordHalfMonthVpAdapter(getActivity().getSupportFragmentManager(), fragments));
    }

    private void initListener() {
        //返回键
        llMonthBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity activity = (MainActivity) getActivity();
                activity.setTabSelection(2);
            }
        });

        rgRecordNavigation.setOnCheckedChangeListener(this);
        vpRecordHalfMonth.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int item = 0;
        switch (checkedId){
            case R.id.rbtn_record_start:
                item = 0;
                break;
            case R.id.rbtn_record_end:
                item = 1;
                break;
        }
        vpRecordHalfMonth.setCurrentItem(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                rbtnRecordStart.setChecked(true);
                break;
            case 1:
                rbtnRecordEnd.setChecked(true);
                break;

        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    /**
     * 获取本月多少天
     *
     * @return
     */
    public static int getCurrentMonthLastDay() {
        Calendar a = Calendar.getInstance();
        int maxDate = a.getActualMaximum(Calendar.DAY_OF_MONTH);
        return maxDate;
    }

    /**
     * 错误提示
     *
     * @param msg
     */
    public void requestError(String msg) {

    }

    /**
     * 查询本月记录
     *
     * @param recordMonthModel
     */
    public void queryMonthly(RecordMonthModel recordMonthModel) {
        Message msg = Message.obtain();
        msg.what = RECORD;
        msg.obj = recordMonthModel;
        mHandler.sendMessage(msg);
    }



}
