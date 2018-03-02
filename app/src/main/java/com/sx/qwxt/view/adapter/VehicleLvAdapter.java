package com.sx.qwxt.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sx.baseframework.base.BaseApplication;
import com.sx.baseframework.util.UIUtils;
import com.sx.qwxt.R;
import com.sx.qwxt.model.VhehicleModel.VhehicleFragmentModel;
import com.sx.qwxt.view.activity.MainActivity;
import com.sx.qwxt.view.fragment.vehicleManager.VehicleFragment;

/**
 * 作者：shy
 * 时间：2017/11/29 0029
 * 描述：
 */
public class VehicleLvAdapter extends BaseAdapter {

    private Context context;
    private VehicleFragment vehicleFragment;
    private VhehicleFragmentModel vhehicleFragmentModel;


    public VehicleLvAdapter(Context context, VehicleFragment vehicleFragment, VhehicleFragmentModel vhehicleFragmentModel) {
        this.context = context;
        this.vehicleFragment = vehicleFragment;
        this.vhehicleFragmentModel = vhehicleFragmentModel;

    }

    @Override
    public int getCount() {
        return vhehicleFragmentModel.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return vhehicleFragmentModel.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

//        if (convertView == null) {
        convertView = LayoutInflater.from(context).inflate(R.layout.lv_vehicle_item, null);
//        }
        LinearLayout llVehicleItem = convertView.findViewById(R.id.ll_vehicle_lv_item);
        TextView tvNumber = convertView.findViewById(R.id.tv_vehicle_number);
        TextView tvVehicleXh = convertView.findViewById(R.id.tv_vehicle_xh);
        LinearLayout llLend = convertView.findViewById(R.id.ll_vehicle_lend);
        TextView tvLendName = convertView.findViewById(R.id.tv_vehicle_lend_name);
        TextView tvLendTime = convertView.findViewById(R.id.tv_vehicle_lend_time);
        TextView tvLendDometer = convertView.findViewById(R.id.tv_vehicle_lend_dometer);
        LinearLayout llReturn = convertView.findViewById(R.id.ll_vehicle_return);
        TextView tvReturnName = convertView.findViewById(R.id.tv_vehicle_return_name);
        TextView tvReturnTime = convertView.findViewById(R.id.tv_vehicle_return_time);
        TextView tvReturnDometer = convertView.findViewById(R.id.tv_vehicle_return_dometer);

        ImageView ivVehicleAddLend = convertView.findViewById(R.id.iv_vehicle_addlend);
        ImageView ivVehicleModify = convertView.findViewById(R.id.iv_vehicle_modify);

        tvVehicleXh.setText(position + 1 + "");
        tvNumber.setText(vhehicleFragmentModel.getData().get(position).getPlatenumber());
        tvLendName.setText(vhehicleFragmentModel.getData().get(position).getXm());

        //根据不同状态添加背景色
//        if ("1".equals(vhehicleFragmentModel.getData().get(position).getState())){
//            //未借出  可用
//            int color = context.getResources().getColor(R.color.in_circulation_bg);
//            llVehicleItem.setBackgroundColor(color);
//        }else {
//            //已借出  不可用
//            int color = context.getResources().getColor(R.color.on_loan_bg);
//            llVehicleItem.setBackgroundColor(color);
//        }

        //添加
        ivVehicleAddLend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存一个区分借车和修改的值
                BaseApplication.set("addAndModify", "0");


                if ("0".equals(vhehicleFragmentModel.getData().get(position).getState())) {
                    UIUtils.showToast(context, "此车辆已出借...");
                } else {
                    //保存点击的车牌号
                    BaseApplication.set("carid", vhehicleFragmentModel.getData().get(position).getCarid());
                    MainActivity activity = (MainActivity) context;
                    activity.setTabSelection(5);

                }
            }
        });
        //修改
        ivVehicleModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存一个区分借车和修改的值
                BaseApplication.set("addAndModify", "1");

                if ("1".equals(vhehicleFragmentModel.getData().get(position).getState())) {
                    UIUtils.showToast(context, "此车辆未借出...");
                } else {
                    BaseApplication.set("modify", vhehicleFragmentModel.getData().get(position).getCarid());
                    BaseApplication.set("recordid", vhehicleFragmentModel.getData().get(position).getRecordid());
                    BaseApplication.set("carid", vhehicleFragmentModel.getData().get(position).getCarid());
                    MainActivity activity = (MainActivity) context;
                    activity.setTabSelection(5);
                }
            }
        });

        //借出时间
        if (vhehicleFragmentModel.getData().get(position).getLoantime() == null) {
            tvLendTime.setText("");
        } else {
            tvLendTime.setText("借出时间:" + transformation(vhehicleFragmentModel.getData().get(position).getLoantime()));
        }
        //借出里程数
        if (vhehicleFragmentModel.getData().get(position).getLoanodometer() == null) {
            tvLendDometer.setText("");
        } else {
            tvLendDometer.setText("里程:" + vhehicleFragmentModel.getData().get(position).getLoanodometer());
        }

        //归还
        if ("0".equals(vhehicleFragmentModel.getData().get(position).getState())) {
            tvReturnName.setText("         未归还");
            tvReturnName.setTextColor(Color.RED);
        } else if ("1".equals(vhehicleFragmentModel.getData().get(position).getState())) {
            if (vhehicleFragmentModel.getData().get(position).getXm() == null) {

            } else {
                tvReturnName.setText(vhehicleFragmentModel.getData().get(position).getXm() + "     已归还");
                tvReturnTime.setText("归还时间:" + transformation(vhehicleFragmentModel.getData().get(position).getReturntime()));
            }
//            tvReturnTime.setText("归还时间:" + transformation(vhehicleFragmentModel.getData().get(position).getReturntime()));
        }

        if (vhehicleFragmentModel.getData().get(position).getReturnodometer() == null) {
            tvReturnDometer.setText("");
        } else {
            tvReturnDometer.setText("里程:" + vhehicleFragmentModel.getData().get(position).getReturnodometer());
        }

        //车辆归还信息点击事件
        llReturn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if ("0".equals(vhehicleFragmentModel.getData().get(position).getState())) {
                    String carid = vhehicleFragmentModel.getData().get(position).getCarid();
                    String recordid = vhehicleFragmentModel.getData().get(position).getRecordid();
                    vehicleFragment.showYesForNo(carid, recordid);
                } else {
                    UIUtils.showToast(context, "此车辆未借出使用,无法完成归还操作...");
                }
            }
        });

        //车牌点击事件
        tvNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseApplication.set("clsyjl", vhehicleFragmentModel.getData().get(position).getCarid());
                MainActivity activity = (MainActivity) context;
                activity.setTabSelection(6);
            }
        });
        return convertView;
    }

    //日期加冒号 :
    public String transformation(String str) {
        if (str == null) {
            return "";
        } else {
            String n = str.substring(0, 4); //年
            String y = str.substring(4, 6); //月
            String r = str.substring(6, 8); //日
            String h = str.substring(8, 10); //时
            String m = str.substring(10, 12); //分
            return n + "-" + y + "-" + r + " " + h + ":" + m;
        }
    }
}
