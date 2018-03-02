package com.sx.qwxt.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sx.qwxt.R;
import com.sx.qwxt.model.VhehicleModel.VehicleRecordModel;
import com.sx.qwxt.view.fragment.vehicleManager.VehicleRecordFragment;

/**
 * 作者：shy
 * 时间：2017/11/30 0030
 * 描述：
 */
public class LendRecordLvAdapter extends BaseAdapter {
    private VehicleRecordFragment vehicleRecordFragment;
    private VehicleRecordModel vehicleRecordModel;

    public LendRecordLvAdapter(VehicleRecordFragment vehicleRecordFragment, VehicleRecordModel vehicleRecordModel) {
        this.vehicleRecordFragment = vehicleRecordFragment;
        this.vehicleRecordModel = vehicleRecordModel;
    }

    @Override
    public int getCount() {
        return vehicleRecordModel.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return vehicleRecordModel.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(vehicleRecordFragment.getActivity()).inflate(R.layout.lend_record_item, null);
            viewHolder = new MyViewHolder();
            viewHolder.name = convertView.findViewById(R.id.tv_record_lv_item_name);
            viewHolder.time = convertView.findViewById(R.id.tv_record_lv_item_time);
            viewHolder.sendtime = convertView.findViewById(R.id.tv_record_lv_item_sendtime);
            viewHolder.tvLendRecordLvLc = convertView.findViewById(R.id.tv_lend_record_lv_lc);
            viewHolder.tvRecordLvLc = convertView.findViewById(R.id.tv_record_lv_lc);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }

        String starttime = sj(vehicleRecordModel.getData().get(position).getLoantime());
        viewHolder.name.setText(vehicleRecordModel.getData().get(position).getXm());
        viewHolder.time.setText(starttime);
        viewHolder.sendtime.setText(sj(vehicleRecordModel.getData().get(position).getReturntime()));

        //出借信息里程
        if (vehicleRecordModel.getData().get(position).getLoantime() == null) {

        } else {
            if (vehicleRecordModel.getData().get(position).getLoanodometer() == null) {
                viewHolder.tvLendRecordLvLc.setText("里程:");
            } else {
                viewHolder.tvLendRecordLvLc.setText("里程:" + vehicleRecordModel.getData().get(position).getLoanodometer());
            }
        }

        //归还信息里程
        if (vehicleRecordModel.getData().get(position).getReturntime() == null) {

        } else {
            if (vehicleRecordModel.getData().get(position).getReturnodometer() == null){
                viewHolder.tvRecordLvLc.setVisibility(View.INVISIBLE);
            }else {
                viewHolder.tvRecordLvLc.setText("里程:");

            }
            viewHolder.tvRecordLvLc.setText("里程:" + vehicleRecordModel.getData().get(position).getReturnodometer());
        }

        return convertView;
    }

    public static class MyViewHolder {
        TextView name;
        TextView time;
        TextView sendtime;
        TextView tvLendRecordLvLc;
        TextView tvRecordLvLc;
    }


    public String sj(String s) {
        if (s != null & !"".equals(s)) {
            String n = s.substring(0, 4);
            String y = s.substring(4, 6);
            String r = s.substring(6, 8);
            String h = s.substring(8, 10);
            String m = s.substring(10, 12);
            return n + "-" + y + "-" + r + " " + h + ":" + m;
        } else {
            return "";
        }

    }
}
