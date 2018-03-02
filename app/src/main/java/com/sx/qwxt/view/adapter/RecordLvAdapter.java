package com.sx.qwxt.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sx.qwxt.R;
import com.sx.qwxt.model.recordModel.ReconrdTimeModel;

/**
 * 作者：shy
 * 时间：2017/11/12 0012
 * 描述：
 */
public class RecordLvAdapter extends BaseAdapter {

    private Context context;
    private ReconrdTimeModel reconrdTimeModel;

    public RecordLvAdapter(Context context, ReconrdTimeModel reconrdTimeModel) {
        this.context = context;
        this.reconrdTimeModel = reconrdTimeModel;
    }

    final static int WEIGHT = 16;

    @Override
    public int getCount() {
        return reconrdTimeModel.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return reconrdTimeModel.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
//        if (convertView == null) {
        view = LayoutInflater.from(context).inflate(R.layout.lv_record_item, null);
//        } else {
//            view = convertView;
//        }

        LinearLayout llRecord = view.findViewById(R.id.ll_record);
        LinearLayout rowLayout = null;
        for (int i = 0; i < 16; i++) {
            rowLayout = createImageLayout();
            llRecord.addView(rowLayout);
            View columnLinearLayout = createText(WEIGHT, position, i);
            rowLayout.addView(columnLinearLayout);
        }


        return view;
    }

    public LinearLayout createImageLayout() {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        return linearLayout;
    }

    public View createText(int weight, int position, int i) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        // 获取屏幕宽度
        int dm2 = context.getResources().getDisplayMetrics().widthPixels;
        // 根据每行个数设置布局大小
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams((dm2 - 302) / weight, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setPadding(0, 16, 0, 16);
        linearLayout.setGravity(Gravity.CENTER);
        TextView textView = new TextView(context);

        if (i == 0) {
            textView.setText(reconrdTimeModel.getData().get(position).get(i).getXm() + "");
        } else {
            if (reconrdTimeModel.getData().get(position).get(i - 1).getQdmc() == null) {
                textView.setText("");
            } else {

//                if ("到岗".equals(reconrdTimeModel.getData().get(position).get(i - 1).getQdmc() + "")) {
//                    textView.setText("");
//                }else {
                    textView.setText(reconrdTimeModel.getData().get(position).get(i - 1).getQdmc() + "");
//                }
            }
        }

        textView.setTextSize(14);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setSingleLine();
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        linearLayout.setTag(textView.getText());
        linearLayout.addView(textView);
        return linearLayout;
    }
}
