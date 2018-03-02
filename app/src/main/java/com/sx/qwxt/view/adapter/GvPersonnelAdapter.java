package com.sx.qwxt.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.sx.baseframework.base.AppConfig;
import com.sx.qwxt.R;
import com.sx.qwxt.model.signinModel.SigninSurfaceModel;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：shy
 * 时间：2017/11/9 0009:16:24
 * 描述：
 */
public class GvPersonnelAdapter extends BaseAdapter {

    private Context context;
    private SigninSurfaceModel data;
    //多选选中状态的索引集合 参数是rybh 是否选中
    public HashMap<String, Boolean> map = new HashMap<>();
    private Map<Integer, Boolean> mapState = new HashMap<>();// 存放已被选中的CheckBox
    private boolean isCheck = false;
    /*
      长按多选的CheckBox 标识符
     */
    private int state = 1;

    public GvPersonnelAdapter(Context context, SigninSurfaceModel data) {
        this.context = context;
        this.data = data;
        map.clear();
    }

    @Override
    public int getCount() {
        return data.getData().size();
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public Object getItem(int position) {
        return data.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.gv_personnel_item, null);
            viewHolder = new MyViewHolder();
            viewHolder.ivPersonnel = convertView.findViewById(R.id.iv_personnel);
            viewHolder.tvSigninName = convertView.findViewById(R.id.tv_signin_name);
            viewHolder.tvTime = convertView.findViewById(R.id.tv_time);
            viewHolder.tvSfzz = convertView.findViewById(R.id.tv_sfzz);
            viewHolder.tvQdzt = convertView.findViewById(R.id.tv_qdzt);
            viewHolder.cb = convertView.findViewById(R.id.cb_signin_cb);
            viewHolder.ivSfjc = convertView.findViewById(R.id.iv_sfjc);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }

        /*
        通过外部传进来的state判断CheckBox是否显示
         */

        if (state == 0) {
            viewHolder.cb.setVisibility(View.VISIBLE);
            //全选
            if (isCheck) {
                viewHolder.cb.setChecked(true);
            } else {

            }
        } else {
            viewHolder.cb.setVisibility(View.GONE);
        }

        if ("1".equals(data.getData().get(position).getSfjc())) {
            viewHolder.ivSfjc.setVisibility(View.VISIBLE);
        } else {
            viewHolder.ivSfjc.setVisibility(View.GONE);
        }

        viewHolder.tvSigninName.setText(data.getData().get(position).getXm());

        String rwqssj = data.getData().get(position).getRwqssj();
        String rwjzsj = data.getData().get(position).getRwjzsj();
        String rwdh = data.getData().get(position).getRwdh();
        if (rwdh == null) {
            String time = "昨日值班: 无";
            viewHolder.tvTime.setText(time);
        }
        if (rwqssj != null || rwjzsj != null) {
            String rwqssjSub = rwqssj.substring(0, 2);
            String rwjzsjSub = rwjzsj.substring(0, 2);
            String time = "昨日值班:" + data.getData().get(position).getRwdh() + "\n" + rwqssjSub + ":00" + " - " + rwjzsjSub + ":00";
            viewHolder.tvTime.setText(time);
        }

        String qdzt = data.getData().get(position).getQdzt();
        //默认带考勤
        if ("1".equals(qdzt)) {
            viewHolder.tvQdzt.setText("到岗");
            viewHolder.tvQdzt.setBackgroundResource(R.drawable.shape_corner);
        } else if ("16".equals(qdzt)) {
            viewHolder.tvQdzt.setText("待考勤");
            viewHolder.tvQdzt.setBackgroundResource(R.drawable.shape_corner_chek);
        } else if ("2".equals(qdzt)) {
            viewHolder.tvQdzt.setText("迟到");
            viewHolder.tvQdzt.setBackgroundResource(R.drawable.shape_corner);
        } else if ("3".equals(qdzt)) {
            viewHolder.tvQdzt.setText("休息");
            viewHolder.tvQdzt.setBackgroundResource(R.drawable.shape_corner);
        } else if ("4".equals(qdzt)) {
            viewHolder.tvQdzt.setText("早退");
            viewHolder.tvQdzt.setBackgroundResource(R.drawable.shape_corner);
        } else if ("15".equals(qdzt)) {
            viewHolder.tvQdzt.setText("工伤");
            viewHolder.tvQdzt.setBackgroundResource(R.drawable.shape_corner);
        } else if ("5".equals(qdzt)) {
            viewHolder.tvQdzt.setText("事假");
            viewHolder.tvQdzt.setBackgroundResource(R.drawable.shape_corner);
        } else if ("6".equals(qdzt)) {
            viewHolder.tvQdzt.setText("事假半天");
            viewHolder.tvQdzt.setBackgroundResource(R.drawable.shape_corner);
        } else if ("7".equals(qdzt)) {
            viewHolder.tvQdzt.setText("病假");
            viewHolder.tvQdzt.setBackgroundResource(R.drawable.shape_corner);
        } else if ("8".equals(qdzt)) {
            viewHolder.tvQdzt.setText("病假半天");
            viewHolder.tvQdzt.setBackgroundResource(R.drawable.shape_corner);
        } else if ("9".equals(qdzt)) {
            viewHolder.tvQdzt.setText("年假");
            viewHolder.tvQdzt.setBackgroundResource(R.drawable.shape_corner);
        } else if ("10".equals(qdzt)) {
            viewHolder.tvQdzt.setText("探亲假");
            viewHolder.tvQdzt.setBackgroundResource(R.drawable.shape_corner);
        } else if ("11".equals(qdzt)) {
            viewHolder.tvQdzt.setText("婚假");
            viewHolder.tvQdzt.setBackgroundResource(R.drawable.shape_corner);
        } else if ("12".equals(qdzt)) {
            viewHolder.tvQdzt.setText("产假");
            viewHolder.tvQdzt.setBackgroundResource(R.drawable.shape_corner);
        } else if ("13".equals(qdzt)) {
            viewHolder.tvQdzt.setText("陪产假");
            viewHolder.tvQdzt.setBackgroundResource(R.drawable.shape_corner);
        } else if ("14".equals(qdzt)) {
            viewHolder.tvQdzt.setText("丧假");
            viewHolder.tvQdzt.setBackgroundResource(R.drawable.shape_corner);
        } else {

        }

        Picasso.with(context).load(AppConfig.IP + AppConfig.IM + data.getData().get(position).getZpxx()).placeholder(R.mipmap.ic_people).into(viewHolder.ivPersonnel);
        if ("1".equals(data.getData().get(position).getSfzz())) {
            viewHolder.tvSfzz.setVisibility(View.VISIBLE);
            viewHolder.tvSfzz.setText("组长");
        } else if ("0".equals(data.getData().get(position).getSfzz())) {
            viewHolder.tvSfzz.setVisibility(View.GONE);
        }

        viewHolder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //选中
                    map.put(data.getData().get(position).getRybh(), true);
                    mapState.put(position, true);
                } else {
                    //取消选中
                    map.remove(data.getData().get(position).getRybh());
                    mapState.remove(position);
                }
            }
        });

        if (mapState != null && mapState.containsKey(position)) {
            viewHolder.cb.setChecked(true);
        } else {
            viewHolder.cb.setChecked(false);
        }

        return convertView;
    }

    //全选
    public void changeState() {
        isCheck = true;
        notifyDataSetChanged();
        for (int i = 0; i < data.getData().size(); i++) {
            map.put(data.getData().get(i).getRybh(), true);
        }
    }

    public static class MyViewHolder {
        ImageView ivPersonnel;
        TextView tvSigninName;
        TextView tvTime;
        TextView tvSfzz;
        TextView tvQdzt;
        CheckBox cb;
        ImageView ivSfjc;
    }
}
