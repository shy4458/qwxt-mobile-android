package com.sx.qwxt.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.sx.qwxt.R;
import com.sx.qwxt.model.workManagerModel.WorkManagerAddToModel;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：shy
 * 时间：2017/11/20 0020
 * 描述：
 */
public class LvWorkAddToAdapter extends BaseAdapter {
    private Context context;
    private WorkManagerAddToModel addToModel;
    public HashMap<Integer, String> map = new HashMap<>();
    private Map<Integer, Boolean> mapState = new HashMap<>();// 存放已被选中的CheckBox

    public LvWorkAddToAdapter(Context context, WorkManagerAddToModel addToModel) {
        this.context = context;
        this.addToModel = addToModel;
    }

    @Override
    public int getCount() {
        return addToModel.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return addToModel.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.work_dialog_addto_item, parent, false);
            viewHolder = new MyViewHolder();
            viewHolder.name = convertView.findViewById(R.id.tv_work_addto_name);
            viewHolder.cbItem = convertView.findViewById(R.id.cb_work_addto_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(addToModel.getData().get(position).getXm());
        viewHolder.cbItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    map.put(position, addToModel.getData().get(position).getRybh());
                    mapState.put(position, true);
                } else {
                    map.remove(position);
                    mapState.remove(position);
                }
            }

        });
        if (mapState != null && mapState.containsKey(position)) {
            viewHolder.cbItem.setChecked(true);
        } else {
            viewHolder.cbItem.setChecked(false);
        }

        return convertView;
    }

    public static class MyViewHolder {
        TextView name;
        CheckBox cbItem;
    }
}