package com.sx.qwxt.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.sx.baseframework.base.AppConfig;
import com.sx.qwxt.R;
import com.sx.qwxt.model.workManagerModel.WorkManagerRwdhModel;
import com.sx.qwxt.view.fragment.record.RecordFragment;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：shy
 * 时间：2017/11/12 0012
 * 描述：
 */
public class RecordWorkLvAdapter extends BaseAdapter {

    private Context context;
    private WorkManagerRwdhModel workManagerRwdhModel;
    private RecordFragment fragment;
    private String rwdh;
    private TextView tvWorkManagerNumber;
    private TextView tvWorkWanager_9_12;
    private TextView tvWorkManager_12_18;
    private TextView tvWorkManager_18_22;
    private LinearLayout llWorkTime1;
    private LinearLayout llWorkTime2;
    private LinearLayout llWorkTime3;
    private LinearLayout llWorkTime1More;
    private LinearLayout llWorkTime2More;
    private LinearLayout llWorkTime3More;
//    private TextView tvWorkLvOther;
    private int size;

    public RecordWorkLvAdapter(Context context, WorkManagerRwdhModel workManagerRwdhModel, RecordFragment fragment) {
        this.context = context;
        this.workManagerRwdhModel = workManagerRwdhModel;
        this.fragment = fragment;
    }


    @Override
    public int getCount() {
        return workManagerRwdhModel.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return workManagerRwdhModel.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

//        if (convertView == null) {
        convertView = LayoutInflater.from(context).inflate(R.layout.lv_record_manager_item, null);
//        }
        tvWorkManagerNumber = convertView.findViewById(R.id.tv_work_manager_number);
        tvWorkWanager_9_12 = convertView.findViewById(R.id.tv_work_manager_9_12);
        tvWorkManager_12_18 = convertView.findViewById(R.id.tv_work_manager_12_18);
        tvWorkManager_18_22 = convertView.findViewById(R.id.tv_work_manager_18_22);

        CircleImageView ivWorkTime11 = convertView.findViewById(R.id.iv_work_time_1_1);
        CircleImageView ivWorkTime12 = convertView.findViewById(R.id.iv_work_time_1_2);
        CircleImageView ivWorkTime13 = convertView.findViewById(R.id.iv_work_time_1_3);
        CircleImageView ivWorkTime21 = convertView.findViewById(R.id.iv_work_time_2_1);
        CircleImageView ivWorkTime22 = convertView.findViewById(R.id.iv_work_time_2_2);
        CircleImageView ivWorkTime23 = convertView.findViewById(R.id.iv_work_time_2_3);
        CircleImageView ivWorkTime31 = convertView.findViewById(R.id.iv_work_time_3_1);
        CircleImageView ivWorkTime32 = convertView.findViewById(R.id.iv_work_time_3_2);
        CircleImageView ivWorkTime33 = convertView.findViewById(R.id.iv_work_time_3_3);

        TextView tvWorkTime11 = convertView.findViewById(R.id.tv_work_time_1_1);
        TextView tvWorkTime12 = convertView.findViewById(R.id.tv_work_time_1_2);
        TextView tvWorkTime13 = convertView.findViewById(R.id.tv_work_time_1_3);
        TextView tvWorkTime21 = convertView.findViewById(R.id.tv_work_time_2_1);
        TextView tvWorkTime22 = convertView.findViewById(R.id.tv_work_time_2_2);
        TextView tvWorkTime23 = convertView.findViewById(R.id.tv_work_time_2_3);
        TextView tvWorkTime31 = convertView.findViewById(R.id.tv_work_time_3_1);
        TextView tvWorkTime32 = convertView.findViewById(R.id.tv_work_time_3_2);
        TextView tvWorkTime33 = convertView.findViewById(R.id.tv_work_time_3_3);
        LinearLayout ll3 = convertView.findViewById(R.id.ll3);

        //人数大于3的时候显示***
        llWorkTime1More = convertView.findViewById(R.id.ll_work_time_1_More);
        llWorkTime2More = convertView.findViewById(R.id.ll_work_time_2_more);
        llWorkTime3More = convertView.findViewById(R.id.ll_work_time_3_more);

        //每个时间段的人数列表夫布局
        llWorkTime1 = convertView.findViewById(R.id.ll_work_time_1);
        llWorkTime2 = convertView.findViewById(R.id.ll_work_time_2);
        llWorkTime3 = convertView.findViewById(R.id.ll_work_time_3);
        //每个时间段人数子布局
        LinearLayout llWorkTime11 = convertView.findViewById(R.id.ll_work_time_1_1);
        LinearLayout llWorkTime12 = convertView.findViewById(R.id.ll_work_time_1_2);
        LinearLayout llWorkTime13 = convertView.findViewById(R.id.ll_work_time_1_3);
        LinearLayout llWorkTime21 = convertView.findViewById(R.id.ll_work_time_2_1);
        LinearLayout llWorkTime22 = convertView.findViewById(R.id.ll_work_time_2_2);
        LinearLayout llWorkTime23 = convertView.findViewById(R.id.ll_work_time_2_3);
        LinearLayout llWorkTime31 = convertView.findViewById(R.id.ll_work_time_3_1);
        LinearLayout llWorkTime32 = convertView.findViewById(R.id.ll_work_time_3_2);
        LinearLayout llWorkTime33 = convertView.findViewById(R.id.ll_work_time_3_3);

        //任务代号
        String rwdh = workManagerRwdhModel.getData().get(position).get(0).getRwdh();
        //每个任务代号下有几个时间段
        size = workManagerRwdhModel.getData().get(position).size();
        //第一行时间段下的人数
        int size0 = workManagerRwdhModel.getData().get(position).get(0).getRylist().size();
        //第二行
        int size1 = workManagerRwdhModel.getData().get(position).get(1).getRylist().size();


        if (size == 2) {
            tvWorkWanager_9_12.setText(workManagerRwdhModel.getData().get(position).get(0).getRwqssj().substring(0, 2) + ":00 - " + workManagerRwdhModel.getData().get(position).get(0).getRwjzsj().substring(0, 2) + ":00");
            tvWorkManager_12_18.setText(workManagerRwdhModel.getData().get(position).get(1).getRwqssj().substring(0, 2) + ":00 - " + workManagerRwdhModel.getData().get(position).get(1).getRwjzsj().substring(0, 2) + ":00");
            tvWorkManager_18_22.setVisibility(View.GONE);
            llWorkTime3.setVisibility(View.GONE);
            ll3.setVisibility(View.GONE);

            //第一行人物控件
            if (size0 == 0) {
                llWorkTime1.setVisibility(View.INVISIBLE);
                llWorkTime1More.setVisibility(View.INVISIBLE);
            } else if (size0 == 1) {
                llWorkTime12.setVisibility(View.GONE);
                llWorkTime13.setVisibility(View.GONE);

                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(0).getRylist().get(0).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime11);
                tvWorkTime11.setText(workManagerRwdhModel.getData().get(position).get(0).getRylist().get(0).getXm());
            } else if (size0 == 2) {
                llWorkTime13.setVisibility(View.GONE);
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(0).getRylist().get(0).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime11);
                tvWorkTime11.setText(workManagerRwdhModel.getData().get(position).get(0).getRylist().get(0).getXm());
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(0).getRylist().get(1).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime12);
                tvWorkTime12.setText(workManagerRwdhModel.getData().get(position).get(0).getRylist().get(1).getXm());

            } else if (size0 == 3) {
                llWorkTime11.setVisibility(View.VISIBLE);
                llWorkTime12.setVisibility(View.VISIBLE);
                llWorkTime13.setVisibility(View.VISIBLE);
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(0).getRylist().get(0).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime11);
                tvWorkTime11.setText(workManagerRwdhModel.getData().get(position).get(0).getRylist().get(0).getXm());
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(0).getRylist().get(1).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime12);
                tvWorkTime12.setText(workManagerRwdhModel.getData().get(position).get(0).getRylist().get(1).getXm());
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(0).getRylist().get(2).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime13);
                tvWorkTime13.setText(workManagerRwdhModel.getData().get(position).get(0).getRylist().get(2).getXm());

            } else if (size0 > 3) {
                llWorkTime11.setVisibility(View.VISIBLE);
                llWorkTime12.setVisibility(View.VISIBLE);
                llWorkTime13.setVisibility(View.VISIBLE);
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(0).getRylist().get(0).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime11);
                tvWorkTime11.setText(workManagerRwdhModel.getData().get(position).get(0).getRylist().get(0).getXm());
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(0).getRylist().get(1).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime12);
                tvWorkTime12.setText(workManagerRwdhModel.getData().get(position).get(0).getRylist().get(1).getXm());
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(0).getRylist().get(2).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime13);
                tvWorkTime13.setText(workManagerRwdhModel.getData().get(position).get(0).getRylist().get(2).getXm());

                llWorkTime1More.setVisibility(View.VISIBLE);
            }

            //第二行人物控件
            if (size1 == 0) {
                llWorkTime2.setVisibility(View.INVISIBLE);
                llWorkTime2More.setVisibility(View.INVISIBLE);
            } else if (size1 == 1) {
                llWorkTime22.setVisibility(View.GONE);
                llWorkTime23.setVisibility(View.GONE);

                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(1).getRylist().get(0).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime21);
                tvWorkTime21.setText(workManagerRwdhModel.getData().get(position).get(1).getRylist().get(0).getXm());

            } else if (size1 == 2) {
                llWorkTime23.setVisibility(View.GONE);
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(1).getRylist().get(0).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime21);
                tvWorkTime21.setText(workManagerRwdhModel.getData().get(position).get(1).getRylist().get(0).getXm());

                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(1).getRylist().get(1).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime22);
                tvWorkTime22.setText(workManagerRwdhModel.getData().get(position).get(1).getRylist().get(1).getXm());

            } else if (size1 == 3) {
                llWorkTime21.setVisibility(View.VISIBLE);
                llWorkTime22.setVisibility(View.VISIBLE);
                llWorkTime23.setVisibility(View.VISIBLE);
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(1).getRylist().get(0).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime21);
                tvWorkTime21.setText(workManagerRwdhModel.getData().get(position).get(1).getRylist().get(0).getXm());
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(1).getRylist().get(1).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime22);
                tvWorkTime22.setText(workManagerRwdhModel.getData().get(position).get(1).getRylist().get(1).getXm());
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(1).getRylist().get(2).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime23);
                tvWorkTime23.setText(workManagerRwdhModel.getData().get(position).get(1).getRylist().get(2).getXm());

            } else if (size1 > 3) {
                llWorkTime21.setVisibility(View.VISIBLE);
                llWorkTime22.setVisibility(View.VISIBLE);
                llWorkTime23.setVisibility(View.VISIBLE);
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(1).getRylist().get(0).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime21);
                tvWorkTime21.setText(workManagerRwdhModel.getData().get(position).get(1).getRylist().get(0).getXm());
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(1).getRylist().get(1).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime22);
                tvWorkTime22.setText(workManagerRwdhModel.getData().get(position).get(1).getRylist().get(1).getXm());
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(1).getRylist().get(2).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime23);
                tvWorkTime23.setText(workManagerRwdhModel.getData().get(position).get(1).getRylist().get(2).getXm());

                llWorkTime2More.setVisibility(View.VISIBLE);
            }


            //三个时间段
        } else if (size == 3) {

            int size2 = workManagerRwdhModel.getData().get(position).get(2).getRylist().size();

            tvWorkWanager_9_12.setText(workManagerRwdhModel.getData().get(position).get(0).getRwqssj().substring(0, 2) + ":00 - " + workManagerRwdhModel.getData().get(position).get(0).getRwjzsj().substring(0, 2) + ":00");
            tvWorkManager_12_18.setText(workManagerRwdhModel.getData().get(position).get(1).getRwqssj().substring(0, 2) + ":00 - " + workManagerRwdhModel.getData().get(position).get(1).getRwjzsj().substring(0, 2) + ":00");
            tvWorkManager_18_22.setText(workManagerRwdhModel.getData().get(position).get(2).getRwqssj().substring(0, 2) + ":00 - " + workManagerRwdhModel.getData().get(position).get(2).getRwjzsj().substring(0, 2) + ":00");

            //第一行人物控件
            if (size0 == 0) {
                llWorkTime1.setVisibility(View.INVISIBLE);
                llWorkTime1More.setVisibility(View.INVISIBLE);

            } else if (size0 == 1) {
                llWorkTime12.setVisibility(View.GONE);
                llWorkTime13.setVisibility(View.GONE);

                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(0).getRylist().get(0).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime11);
                tvWorkTime11.setText(workManagerRwdhModel.getData().get(position).get(0).getRylist().get(0).getXm());
            } else if (size0 == 2) {
                llWorkTime13.setVisibility(View.GONE);
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(0).getRylist().get(0).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime11);
                tvWorkTime11.setText(workManagerRwdhModel.getData().get(position).get(0).getRylist().get(0).getXm());
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(0).getRylist().get(1).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime12);
                tvWorkTime12.setText(workManagerRwdhModel.getData().get(position).get(0).getRylist().get(1).getXm());

            } else if (size0 == 3) {
                llWorkTime11.setVisibility(View.VISIBLE);
                llWorkTime12.setVisibility(View.VISIBLE);
                llWorkTime13.setVisibility(View.VISIBLE);
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(0).getRylist().get(0).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime11);
                tvWorkTime11.setText(workManagerRwdhModel.getData().get(position).get(0).getRylist().get(0).getXm());
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(0).getRylist().get(1).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime12);
                tvWorkTime12.setText(workManagerRwdhModel.getData().get(position).get(0).getRylist().get(1).getXm());
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(0).getRylist().get(2).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime13);
                tvWorkTime13.setText(workManagerRwdhModel.getData().get(position).get(0).getRylist().get(2).getXm());

            } else if (size0 > 3) {
                llWorkTime11.setVisibility(View.VISIBLE);
                llWorkTime12.setVisibility(View.VISIBLE);
                llWorkTime13.setVisibility(View.VISIBLE);
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(0).getRylist().get(0).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime11);
                tvWorkTime11.setText(workManagerRwdhModel.getData().get(position).get(0).getRylist().get(0).getXm());
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(0).getRylist().get(1).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime12);
                tvWorkTime12.setText(workManagerRwdhModel.getData().get(position).get(0).getRylist().get(1).getXm());
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(0).getRylist().get(2).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime13);
                tvWorkTime13.setText(workManagerRwdhModel.getData().get(position).get(0).getRylist().get(2).getXm());

                llWorkTime1More.setVisibility(View.VISIBLE);
            }

            //第二行人物控件
            if (size1 == 0) {
                llWorkTime2.setVisibility(View.INVISIBLE);
                llWorkTime2More.setVisibility(View.INVISIBLE);
            } else if (size1 == 1) {
                llWorkTime22.setVisibility(View.GONE);
                llWorkTime23.setVisibility(View.GONE);

                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(1).getRylist().get(0).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime21);
                tvWorkTime21.setText(workManagerRwdhModel.getData().get(position).get(1).getRylist().get(0).getXm());

            } else if (size1 == 2) {
                llWorkTime23.setVisibility(View.GONE);
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(1).getRylist().get(0).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime21);
                tvWorkTime21.setText(workManagerRwdhModel.getData().get(position).get(1).getRylist().get(0).getXm());

                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(1).getRylist().get(1).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime22);
                tvWorkTime22.setText(workManagerRwdhModel.getData().get(position).get(1).getRylist().get(1).getXm());

            } else if (size1 == 3) {
                llWorkTime21.setVisibility(View.VISIBLE);
                llWorkTime22.setVisibility(View.VISIBLE);
                llWorkTime23.setVisibility(View.VISIBLE);
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(1).getRylist().get(0).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime21);
                tvWorkTime21.setText(workManagerRwdhModel.getData().get(position).get(1).getRylist().get(0).getXm());
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(1).getRylist().get(1).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime22);
                tvWorkTime22.setText(workManagerRwdhModel.getData().get(position).get(1).getRylist().get(1).getXm());
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(1).getRylist().get(2).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime23);
                tvWorkTime23.setText(workManagerRwdhModel.getData().get(position).get(1).getRylist().get(2).getXm());

            } else if (size1 > 3) {
                llWorkTime21.setVisibility(View.VISIBLE);
                llWorkTime22.setVisibility(View.VISIBLE);
                llWorkTime23.setVisibility(View.VISIBLE);
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(1).getRylist().get(0).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime21);
                tvWorkTime21.setText(workManagerRwdhModel.getData().get(position).get(1).getRylist().get(0).getXm());
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(1).getRylist().get(1).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime22);
                tvWorkTime22.setText(workManagerRwdhModel.getData().get(position).get(1).getRylist().get(1).getXm());
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(1).getRylist().get(2).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime23);
                tvWorkTime23.setText(workManagerRwdhModel.getData().get(position).get(1).getRylist().get(2).getXm());

                llWorkTime2More.setVisibility(View.VISIBLE);
            }
            //第三行
            if (size2 == 0) {
                llWorkTime3.setVisibility(View.INVISIBLE);
                llWorkTime3More.setVisibility(View.INVISIBLE);
            } else if (size2 == 1) {
                llWorkTime32.setVisibility(View.GONE);
                llWorkTime33.setVisibility(View.GONE);

                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(2).getRylist().get(0).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime31);
                tvWorkTime31.setText(workManagerRwdhModel.getData().get(position).get(2).getRylist().get(0).getXm());


            } else if (size2 == 2) {
                llWorkTime33.setVisibility(View.GONE);
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(2).getRylist().get(0).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime31);
                tvWorkTime31.setText(workManagerRwdhModel.getData().get(position).get(2).getRylist().get(0).getXm());

                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(2).getRylist().get(1).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime32);
                tvWorkTime32.setText(workManagerRwdhModel.getData().get(position).get(2).getRylist().get(1).getXm());

            } else if (size2 == 3) {


                llWorkTime31.setVisibility(View.VISIBLE);
                llWorkTime32.setVisibility(View.VISIBLE);
                llWorkTime33.setVisibility(View.VISIBLE);

                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(2).getRylist().get(0).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime31);
                tvWorkTime31.setText(workManagerRwdhModel.getData().get(position).get(2).getRylist().get(0).getXm());
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(2).getRylist().get(1).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime32);
                tvWorkTime32.setText(workManagerRwdhModel.getData().get(position).get(2).getRylist().get(1).getXm());
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(2).getRylist().get(2).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime33);
                tvWorkTime33.setText(workManagerRwdhModel.getData().get(position).get(2).getRylist().get(2).getXm());

            } else if (size2 > 3) {
                llWorkTime31.setVisibility(View.VISIBLE);
                llWorkTime32.setVisibility(View.VISIBLE);
                llWorkTime33.setVisibility(View.VISIBLE);

                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(2).getRylist().get(0).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime31);
                tvWorkTime31.setText(workManagerRwdhModel.getData().get(position).get(2).getRylist().get(0).getXm());
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(2).getRylist().get(1).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime32);
                tvWorkTime32.setText(workManagerRwdhModel.getData().get(position).get(2).getRylist().get(1).getXm());
                Picasso.with(context).load(AppConfig.IP + AppConfig.IM + workManagerRwdhModel.getData().get(position).get(2).getRylist().get(2).getZpxx()).placeholder(R.mipmap.ic_people).into(ivWorkTime33);
                tvWorkTime33.setText(workManagerRwdhModel.getData().get(position).get(2).getRylist().get(2).getXm());

                llWorkTime3More.setVisibility(View.VISIBLE);
            }
        }
        if ("9999".equals(rwdh)) {
            tvWorkManagerNumber.setText("社区");
        } else {
            tvWorkManagerNumber.setText(rwdh + "");
        }

        //更多
        llWorkTime1More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<WorkManagerRwdhModel.DataBean.RylistBean> rylist1 = workManagerRwdhModel.getData().get(position).get(0).getRylist();
                fragment.removeTo(rylist1);

            }
        });

        llWorkTime2More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<WorkManagerRwdhModel.DataBean.RylistBean> rylist2 = workManagerRwdhModel.getData().get(position).get(1).getRylist();
                fragment.removeTo(rylist2);
            }
        });

        llWorkTime3More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<WorkManagerRwdhModel.DataBean.RylistBean> rylist3 = workManagerRwdhModel.getData().get(position).get(2).getRylist();
                fragment.removeTo(rylist3);
            }
        });
        return convertView;
    }
}
