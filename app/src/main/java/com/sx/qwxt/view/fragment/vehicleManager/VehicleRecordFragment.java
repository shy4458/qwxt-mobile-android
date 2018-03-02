package com.sx.qwxt.view.fragment.vehicleManager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sx.baseframework.base.BaseApplication;
import com.sx.qwxt.R;
import com.sx.qwxt.dagger.component.DaggerVehicleRecordComponent;
import com.sx.qwxt.dagger.module.VehicleRecordModule;
import com.sx.qwxt.model.VhehicleModel.VehicleMsgModel;
import com.sx.qwxt.model.VhehicleModel.VehicleRecordModel;
import com.sx.qwxt.presenter.VehicleRecordPresenter;
import com.sx.qwxt.view.activity.MainActivity;
import com.sx.qwxt.view.adapter.LendRecordLvAdapter;

import javax.inject.Inject;

/**
 * 作者：shy
 * 时间：2017/11/30 0030
 * 描述：车辆出借信息
 */
public class VehicleRecordFragment extends Fragment {

    @Inject
    VehicleRecordPresenter presenter;

    private static final int VEHICLEMSG = 160;
    private static final int VEHICLERECORD = 161;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case VEHICLEMSG:

                    VehicleMsgModel vehicleMsgModel = (VehicleMsgModel) msg.obj;
                    tvVehicleCp.setText(vehicleMsgModel.getData().getPlatenumber());
                    tvVehicleCx.setText(vehicleMsgModel.getData().getTypename());
                    tvVehiclePp.setText(vehicleMsgModel.getData().getBrandname());
                    tvVehicleGmnf.setText(sj(vehicleMsgModel.getData().getBuytime()));
                    tvVehicleBm.setText(vehicleMsgModel.getData().getDepartname());
                    tvVehicleBxks.setText(sj(vehicleMsgModel.getData().getInsurstart()));
                    tvVehicleBxjz.setText(sj(vehicleMsgModel.getData().getInsurstop()));
                    break;

                case VEHICLERECORD:
                    VehicleRecordModel vehicleRecordModel = (VehicleRecordModel) msg.obj;
                    LendRecordLvAdapter lendRecordLvAdapter = new LendRecordLvAdapter(VehicleRecordFragment.this, vehicleRecordModel);
                    lvLendRecord.setAdapter(lendRecordLvAdapter);
                    break;

                default:
                    break;
            }
        }
    };
    private TextView tvVehicleCx;
    private TextView tvVehiclePp;
    private TextView tvVehicleGmnf;
    private TextView tvVehicleBm;
    private TextView tvVehicleBxks;
    private TextView tvVehicleBxjz;
    private ListView lvLendRecord;
    private TextView tvVehicleCp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerVehicleRecordComponent.builder().vehicleRecordModule(new VehicleRecordModule(this)).build().in(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehicle_record, null);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        String clsyjl = BaseApplication.get("clsyjl", "");
        presenter.getData(clsyjl);

    }

    private void initView(View view) {
        LinearLayout llVehicleRecordBack = view.findViewById(R.id.ll_vehicle_record_back);
        lvLendRecord = view.findViewById(R.id.lv_lend_record);
        tvVehicleCp = view.findViewById(R.id.tv_vehicle_cp);
        tvVehicleCx = view.findViewById(R.id.tv_vehicle_cx);
        tvVehiclePp = view.findViewById(R.id.tv_vehicle_pp);
        tvVehicleGmnf = view.findViewById(R.id.tv_vehicle_gmnf);
        tvVehicleBm = view.findViewById(R.id.tv_vehicle_bm);
        tvVehicleBxks = view.findViewById(R.id.tv_vehicle_bxks);
        tvVehicleBxjz = view.findViewById(R.id.tv_vehicle_bxjz);

        llVehicleRecordBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                activity.setTabSelection(4);
            }
        });

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {

        } else {
            String clsyjl = BaseApplication.get("clsyjl", "");
            presenter.getData(clsyjl);
        }
    }

    //车辆信息
    public void msgSuccess(VehicleMsgModel vehicleMsgModel) {
        Message msg = Message.obtain();
        msg.what = VEHICLEMSG;
        msg.obj = vehicleMsgModel;
        mHandler.sendMessage(msg);
    }

    //车辆使用记录
    public void recordSuccess(VehicleRecordModel vehicleRecordModel) {
        Message msg = Message.obtain();
        msg.what = VEHICLERECORD;
        msg.obj = vehicleRecordModel;
        mHandler.sendMessage(msg);
    }

    public String sj(String s) {
        if (s != null & !"".equals(s)) {
            String n = s.substring(0, 4);
            String y = s.substring(4, 6);
            String r = s.substring(6, 8);
            return n + "-" + y + "-" + r;
        }else {
            return "";
        }
    }
}
