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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sx.baseframework.util.UIUtils;
import com.sx.qwxt.R;
import com.sx.qwxt.dagger.component.DaggerVehicleFragmentComponent;
import com.sx.qwxt.dagger.module.VehicleFragmentModule;
import com.sx.qwxt.model.VhehicleModel.VhehicleFragmentModel;
import com.sx.qwxt.presenter.VehicleFragmentPresenter;
import com.sx.qwxt.view.adapter.VehicleLvAdapter;
import com.sx.qwxt.view.widget.MyDialog;

import javax.inject.Inject;

/**
 * 作者：shy
 * 时间：2017/11/29 0029
 * 描述：车辆管理
 */

public class VehicleFragment extends Fragment {
    private LayoutInflater inflater;
    private ListView lvVehicle;
    private VehicleLvAdapter vehicleLvAdapter;

    @Inject
    VehicleFragmentPresenter presenter;

    private static final int VEHICLEDATA = 150;
    private static final int RETU = 151;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case VEHICLEDATA:
                    VhehicleFragmentModel vhehicleFragmentModel= (VhehicleFragmentModel) msg.obj;
                    vehicleLvAdapter = new VehicleLvAdapter(getActivity(),VehicleFragment.this,vhehicleFragmentModel);
                    lvVehicle.setAdapter(vehicleLvAdapter);
                    break;
                case RETU:
                    String str= (String) msg.obj;
                    UIUtils.showToast(getActivity(),"车辆已成功归还");
                    onResume();
                default:
                    break;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerVehicleFragmentComponent.builder().vehicleFragmentModule(new VehicleFragmentModule(this)).build().in(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehice, container, false);
        intView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden){
            //隐藏
        }else {
            //显示
            presenter.getData();
        }
    }

    private void intView(View view) {
        lvVehicle = view.findViewById(R.id.lv_vehicle);
        inflater = LayoutInflater.from(getActivity());
        LinearLayout heanView = (LinearLayout) inflater.inflate(R.layout.vehicle_lv_head, null);
        lvVehicle.addHeaderView(heanView);
    }

    public void showYesForNo(final String recordid, final String carid) {
        View view = View.inflate(getActivity(), R.layout.mydialog_yesforno, null);
        final MyDialog myDialog = new MyDialog(getActivity(), R.style.myDialog);
        myDialog.setView(view);
        myDialog.setProperty(0, 0, 600, 500);//设置坐标和宽高
        myDialog.setCanceledOnTouchOutside(true);
        myDialog.show();
        Button bReturnCancel = view.findViewById(R.id.b_return_cancel);
        Button bReturnYes = view.findViewById(R.id.b_return_yes);
        final EditText etReturnDometer = view.findViewById(R.id.et_return_dometer);

        bReturnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        bReturnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //确定归还的数据提交
                final String returnDomoter = etReturnDometer.getText().toString();
                presenter.submit(recordid,carid, returnDomoter);
                myDialog.dismiss();
            }
        });
    }

    public void success(VhehicleFragmentModel vhehicleFragmentModel) {
        Message msg = Message.obtain();
        msg.what = VEHICLEDATA;
        msg.obj = vhehicleFragmentModel;
        mHandler.sendMessage(msg);
    }
    //归还提交成功
    public void submitSuccess(String str) {
        Message msg = Message.obtain();
        msg.what = RETU;
        msg.obj = str;
        mHandler.sendMessage(msg);
    }
}
