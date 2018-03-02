package com.sx.qwxt.view.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.sx.qwxt.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 作者：shy
 * 时间：2017/11/23 0023
 * 描述：
 */
public class leaveAdapter extends BaseAdapter {

    private Activity fragment;
    private ArrayList<String> list = new ArrayList();
    public HashMap<String, String> map = new HashMap<>();

    public leaveAdapter(Activity fragment, ArrayList<String> list) {
        this.fragment = fragment;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(fragment, R.layout.dialog_leave_item, null);
        } else {
            view = convertView;
        }

        TextView tvLeaveName = view.findViewById(R.id.tv_leave_name);
        final CheckBox cbWorkAddToItem = view.findViewById(R.id.cb_work_addto_item);

        tvLeaveName.setText(list.get(position));
        cbWorkAddToItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                } else {
                }
            }
        });

        return view;
    }


}
