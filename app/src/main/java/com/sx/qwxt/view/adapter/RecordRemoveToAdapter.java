package com.sx.qwxt.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sx.qwxt.R;
import com.sx.qwxt.model.workManagerModel.WorkManagerRwdhModel;

import java.util.List;

/**
 * 作者：shy
 * 时间：2017/11/20 0020
 * 描述：
 */
public class RecordRemoveToAdapter extends BaseAdapter {
    private Context context;
    private List<WorkManagerRwdhModel.DataBean.RylistBean> rylist;

    public RecordRemoveToAdapter(Context context, List<WorkManagerRwdhModel.DataBean.RylistBean> rylist) {
        this.context = context;
        this.rylist = rylist;
    }

    @Override
    public int getCount() {
        return rylist.size();
    }

    @Override
    public Object getItem(int position) {
        return rylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        MyViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.record_dialog_remove_item, parent, false);
            viewHolder = new MyViewHolder();
            viewHolder.name = convertView.findViewById(R.id.tv_work_addto_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(rylist.get(position).getXm());

        return convertView;
    }
    public static class MyViewHolder{
        TextView name;

    }
}
