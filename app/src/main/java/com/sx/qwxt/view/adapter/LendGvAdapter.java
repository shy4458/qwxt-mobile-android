package com.sx.qwxt.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.sx.baseframework.base.AppConfig;
import com.sx.baseframework.base.BaseApplication;
import com.sx.qwxt.R;
import com.sx.qwxt.model.workManagerModel.WorkManagerZgryModel;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：shy
 * 时间：2017/11/30 0030
 * 描述：
 */
public class LendGvAdapter extends BaseAdapter {

    private Context context;
    private WorkManagerZgryModel zgryModel;
    private LinearLayout llGvItemBg;


    public LendGvAdapter(Context context, WorkManagerZgryModel zgryModel) {
        this.context = context;
        this.zgryModel = zgryModel;
    }

    private int selectorPosition = -1;

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
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lend_gv_item, null);
        } else {
            view = convertView;
        }
        CircleImageView civLend = view.findViewById(R.id.civ_lend);
        TextView tvLendName = view.findViewById(R.id.tv_lend_name);
        llGvItemBg = view.findViewById(R.id.ll_gv_item_bg);
        Picasso.with(context).load(AppConfig.IP + AppConfig.IM + zgryModel.getData().get(position).getZpxx()).placeholder(R.mipmap.ic_people).into(civLend);
        tvLendName.setText(zgryModel.getData().get(position).getXm());

        if (selectorPosition == position) {
            llGvItemBg.setBackgroundResource(R.drawable.shape_corner);
        } else {
            llGvItemBg.setBackgroundColor(Color.WHITE);
        }

        return view;
    }

    public void changeState(int pos) {
        if (pos == selectorPosition) {
            selectorPosition = -1;
            notifyDataSetChanged();

            //未选中了状态为0
            BaseApplication.set("ryidstate","");
            //再次点击已选中的人员时删除人员id
            BaseApplication.set("ryid","");
        } else {
            selectorPosition = pos;
            notifyDataSetChanged();

            //选中了状态为1
            BaseApplication.set("ryidstate","1");
            //点击时保存选中的人员id
            BaseApplication.set("ryid",zgryModel.getData().get(pos).getRybh()+"");
        }
    }
}
