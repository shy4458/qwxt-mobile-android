package com.sx.qwxt.view.fragment.record;

import android.app.ProgressDialog;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.sx.baseframework.util.UIUtils;
import com.sx.qwxt.R;
import com.sx.qwxt.dagger.component.DaggerRecordComponent;
import com.sx.qwxt.dagger.module.RecordModule;
import com.sx.qwxt.model.workManagerModel.WorkManagerAddToModel;
import com.sx.qwxt.model.workManagerModel.WorkManagerRwdhModel;
import com.sx.qwxt.model.workManagerModel.WorkManagerZgryModel;
import com.sx.qwxt.presenter.RecordPresenter;
import com.sx.qwxt.view.activity.MainActivity;
import com.sx.qwxt.view.adapter.LvWorkAddToAdapter;
import com.sx.qwxt.view.adapter.RecordRemoveToAdapter;
import com.sx.qwxt.view.adapter.RecordWorkLvAdapter;
import com.sx.qwxt.view.widget.MyDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * 作者：shy
 * 时间：2017/11/10 0010
 * 描述：记录查询
 */
public class RecordFragment extends Fragment {

    private RecordWorkLvAdapter lvAdapter;
    private PopupWindow mPopWindow;

    private ListView lvRecord;
    private TextView tvMonthRecord;
    private MaterialCalendarView mcvCalenarView;

    @Inject
    RecordPresenter presenter;
    private LvWorkAddToAdapter lvWorkAddToAdapter;
    private MyDialog dialog;
    private RecordRemoveToAdapter removeToAdapter;
    private MyDialog dialogRemove;

    private static final int RWDH = 110;
    private static final int ZGRY = 111;
    private static final int ADDTO = 112;
    private static final int SUBMITT = 113;
    private static final int REMOVE = 114;

    private LayoutInflater inflater;
    private LinearLayout heanView;
    private LinearLayout tailView;
    private ProgressDialog progressDialog;
    private TextView tvWorkLvOther;
    private TextView tvCurrentDate;



    private String qssj;
    private String jzsj;
    private String rwdh;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                //获取任务单号
                case RWDH:
                    WorkManagerRwdhModel workManagerRwdhModel = (WorkManagerRwdhModel) msg.obj;
                    lvAdapter = new RecordWorkLvAdapter(getActivity(),workManagerRwdhModel,RecordFragment.this);
                    lvRecord.setAdapter(lvAdapter);
                    tvWorkLvOther = tailView.findViewById(R.id.tv_work_lv_other);
                    int size0 = workManagerRwdhModel.getData().get(workManagerRwdhModel.getData().size() - 1).get(0).getRylist().size();
                    int size1 = workManagerRwdhModel.getData().get(workManagerRwdhModel.getData().size() - 1).get(1).getRylist().size();
                    int size = size0 + size1;
                    tvWorkLvOther.setText("其他勤务安排(" + size + ")人");
                    progressDialog.dismiss();
                    break;
                //添加人员
                case ADDTO:
                    WorkManagerAddToModel addToModel = (WorkManagerAddToModel) msg.obj;
                    if(addToModel.getData().size() == 0){
                        UIUtils.showToast(getActivity(),"该组无人员可安排");
                    }else {
                        showAddToDialog(addToModel);
                        lvWorkAddToAdapter.notifyDataSetChanged();
                    }

                    progressDialog.dismiss();
                    break;
                //添加人员成功
                case SUBMITT:
                    String message = (String) msg.obj;
                    mPopWindow.dismiss();
                    dialog.dismiss();
                    UIUtils.showToast(getActivity(),message);
                    presenter.getDataRwdh();
                    lvAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                    break;
                //删除人员
                case REMOVE:
                    String removeMessage = (String) msg.obj;
                    dialogRemove.dismiss();
                    UIUtils.showToast(getActivity(),removeMessage);
                    presenter.getDataRwdh();
                    lvAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                    break;
                default:
                    break;
            }
        }
    };
    private Button btnWorkAddtoYes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerRecordComponent.builder().recordModule(new RecordModule(this)).build().in(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        progressDialog = ProgressDialog.show(getActivity(), "请稍等...", "正在获取数据中... 请稍后", true);
        progressDialog.setCanceledOnTouchOutside(true);
        presenter.getDataRwdh();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        initView(view);
        initLiserner();
        return view;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden){

        }else {
            this.onResume();
        }
    }

    private void initView(View view) {
        lvRecord = view.findViewById(R.id.lv_record);
        tvMonthRecord = view.findViewById(R.id.tv_month_record);
        tvCurrentDate = view.findViewById(R.id.tv_current_date);
        mcvCalenarView = view.findViewById(R.id.calendarView);
        mcvCalenarView.setSelectedDate(new Date());
        mcvCalenarView.setShowOtherDates(MaterialCalendarView.SHOW_ALL);
        mcvCalenarView.setTopbarVisible(false);

        tvCurrentDate.setText(getDate());

        inflater = LayoutInflater.from(getActivity());
        //添加头部
        heanView = (LinearLayout) inflater.inflate(R.layout.lv_item_head, null);
        lvRecord.addHeaderView(heanView);
        tailView = (LinearLayout) inflater.inflate(R.layout.lv_item_tail, null);
        lvRecord.addFooterView(tailView);
    }

    private void initLiserner() {
        tvMonthRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                activity.setTabSelection(3);
            }
        });
    }
    /**
     * 加号点击 请求列表数据 携带待添加的任务信息
     */
    public void addTo(final String clickRwdh, final String clickQssj, final String clickJzsj, View v){

        qssj = clickQssj;
        jzsj = clickJzsj;
        rwdh = clickRwdh;

        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_group, null);
        TextView tvGroup1 = contentView.findViewById(R.id.tv_group_1);
        TextView tvGroup2 = contentView.findViewById(R.id.tv_group_2);
        TextView tvGroup3 = contentView.findViewById(R.id.tv_group_3);
        TextView tvGroup4 = contentView.findViewById(R.id.tv_group_4);

        tvGroup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addToData(clickQssj,clickRwdh,clickJzsj,1);
            }
        });
        tvGroup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addToData(clickQssj,clickRwdh,clickJzsj,2);
            }
        });
        tvGroup3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addToData(clickQssj,clickRwdh,clickJzsj,3);
            }
        });
        tvGroup4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addToData(clickQssj,clickRwdh,clickJzsj,4);
            }
        });

        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //点击外侧关闭窗口
        mPopWindow.setOutsideTouchable(true);
        //获取焦点
        mPopWindow.setFocusable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        //点击在按钮的中上方弹出popupWindow
        int btnWidth = v.getMeasuredWidth();
        int btnHeight = v.getMeasuredHeight();
        int popWidth = mPopWindow.getContentView().getMeasuredWidth();
        int popHeight = mPopWindow.getContentView().getMeasuredHeight();
        //PopupWindow的x偏移值
        int xoff = (int) ((float) (btnWidth - popWidth) / 2);
        //因为相对于按钮的上方，所以该值为负值
        int yoff = popHeight + btnHeight;
        mPopWindow.showAsDropDown(v, xoff, -yoff);

    }

    private void showAddToDialog(final WorkManagerAddToModel addToModel) {

        dialog = new MyDialog(getActivity(), R.style.myDialog);
        final View view = View.inflate(getActivity(), R.layout.work_dialog_addto,null);
        dialog.setView(view);
        dialog.setProperty(0,0, 400, 800);//设置坐标和宽高
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        ListView lvWorkAddTo = view.findViewById(R.id.lv_work_addto);
        btnWorkAddtoYes = view.findViewById(R.id.btn_work_yes);
        lvWorkAddToAdapter = new LvWorkAddToAdapter(getActivity(), addToModel);
        lvWorkAddTo.setAdapter(lvWorkAddToAdapter);
        //从适配器中拿到点击的position的集合
        final HashMap<Integer, String> map = lvWorkAddToAdapter.map;
        //选择的人员编号的集合
        final List<String> listRybhAll = new ArrayList<>();

        btnWorkAddtoYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (String value : map.values()) {
                    listRybhAll.add(value);
                }
                presenter.taskSubmission(listRybhAll,rwdh,qssj,jzsj);
            }
        });
    }
    /**
     * 删除的人员信息列表展示
     * @param rylist
     */
    public void removeTo(List<WorkManagerRwdhModel.DataBean.RylistBean> rylist) {
        dialogRemove = new MyDialog(getActivity(), R.style.myDialog);
        final View view = View.inflate(getActivity(), R.layout.record_dialog_remove,null);
        dialogRemove.setView(view);
        dialogRemove.setProperty(0,0, 400, 800);//设置坐标和宽高
        dialogRemove.setCanceledOnTouchOutside(true);
        dialogRemove.show();
        ListView lvWorkAddTo = view.findViewById(R.id.lv_work_addto);
        removeToAdapter = new RecordRemoveToAdapter(getActivity(), rylist);
        lvWorkAddTo.setAdapter(removeToAdapter);
    }

    /**
     * 错误提示
     * @param msg
     */
    public void requestError(String msg) {

    }

    public void obtainRwdh(WorkManagerRwdhModel workManagerRwdhModel) {
        Message msg = Message.obtain();
        msg.what = RWDH;
        msg.obj = workManagerRwdhModel;
        mHandler.sendMessage(msg);
    }

    public void obtainZgry(WorkManagerZgryModel zgryModel) {
        Message msg = Message.obtain();
        msg.what = ZGRY;
        msg.obj = zgryModel;
        mHandler.sendMessage(msg);
    }
    public void showDialogData(WorkManagerAddToModel addToModel){
        Message msg = Message.obtain();
        msg.what = ADDTO;
        msg.obj = addToModel;
        mHandler.sendMessage(msg);
    }


    public void submittedSuccess(String message) {
        Message msg = Message.obtain();
        msg.what = SUBMITT;
        msg.obj = message;
        mHandler.sendMessage(msg);
    }


    /**
     * 移除人员成功
     */
    public void removeSuccess(String message) {
        Message msg = Message.obtain();
        msg.what = REMOVE;
        msg.obj = message;
        mHandler.sendMessage(msg);
    }

    public static String getDate() {
        Calendar c = Calendar.getInstance();//
        int mYear = c.get(Calendar.YEAR); // 获取当前年份
        int mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        int mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当日期

        int mWay = c.get(Calendar.DAY_OF_WEEK);// 获取当前日期的星期
        int mHour = c.get(Calendar.HOUR_OF_DAY);//时
        int  mMinute = c.get(Calendar.MINUTE);//分

        return mYear + " - " + mMonth + " - " + mDay + "";

    }
}
