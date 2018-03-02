package com.sx.qwxt.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.sx.baseframework.base.AppConfig;
import com.sx.qwxt.R;
import com.sx.qwxt.model.workManagerModel.WorkManagerZgryModel;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：shy
 * 时间：2017/11/12 0012
 * 描述：
 */
public class WorkManagerInPositionLvAdapter extends BaseAdapter {

    private Context context;
    private  WorkManagerZgryModel zgryModel;
    private String qssjSub;
    private String jzsjSub;

    public WorkManagerInPositionLvAdapter(Context context, WorkManagerZgryModel zgryModel) {
        this.context = context;
        this.zgryModel = zgryModel;
    }

    @Override
    public int getCount() {
        return zgryModel.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return zgryModel.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_work_manager_in_position_item, null);
            viewHolder = new MyViewHolder();
            viewHolder.civManagerinPosition = convertView.findViewById(R.id.civ_work_manager_in_position);
            viewHolder.tvWorkManagerInName = convertView.findViewById(R.id.tv_work_manager_in_name);
            viewHolder.tvWorkManagerInTime = convertView.findViewById(R.id.tv_work_manager_in_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }



        Picasso.with(context).load(AppConfig.IP + AppConfig.IM + zgryModel.getData().get(position).getZpxx()).placeholder(R.mipmap.ic_people).into(viewHolder.civManagerinPosition);
        viewHolder.tvWorkManagerInName.setText(zgryModel.getData().get(position).getXm());

        if (zgryModel.getData().get(position).getRwqssj() == null || zgryModel.getData().get(position).getRwjzsj() == null){

            viewHolder.tvWorkManagerInTime.setText("无");

        }else {

            qssjSub = zgryModel.getData().get(position).getRwqssj().substring(0, 2);
            jzsjSub = zgryModel.getData().get(position).getRwjzsj().substring(0, 2);

            viewHolder.tvWorkManagerInTime.setText("昨日值班: " + zgryModel.getData().get(position).getRwdh()+ " \n" + qssjSub + ":00 - " + jzsjSub + ":00");
        }

        return convertView;
    }
    public static class MyViewHolder{
        CircleImageView civManagerinPosition;
        TextView tvWorkManagerInName;
        TextView tvWorkManagerInTime;
    }
}
