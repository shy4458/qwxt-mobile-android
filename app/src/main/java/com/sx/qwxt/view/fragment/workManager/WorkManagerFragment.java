package com.sx.qwxt.view.fragment.workManager;


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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sx.baseframework.util.UIUtils;
import com.sx.qwxt.R;
import com.sx.qwxt.dagger.component.DaggerWorkManagerFragmentComponent;
import com.sx.qwxt.dagger.module.WorkManagerFragmentModule;
import com.sx.qwxt.model.workManagerModel.WorkManagerAddToModel;
import com.sx.qwxt.model.workManagerModel.WorkManagerRwdhModel;
import com.sx.qwxt.model.workManagerModel.WorkManagerZgryModel;
import com.sx.qwxt.presenter.WorkFragmentPresenter;
import com.sx.qwxt.view.widget.MyDialog;
import com.sx.qwxt.view.adapter.LvWorkAddToAdapter;
import com.sx.qwxt.view.adapter.LvWorkRemoveToAdapter;
import com.sx.qwxt.view.adapter.WorkManagerInPositionLvAdapter;
import com.sx.qwxt.view.adapter.WorkManagerLvAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * 作者：shy
 * 时间：2017/11/10 0010
 * 描述：勤务安排
 */
public class WorkManagerFragment extends Fragment {

    private ListView lvWorkManager;
    private RadioGroup rgWorkManager;
    private RadioButton rbtnWorkManagerWhole;
    private RadioButton rbtnWorkManagerOne;
    private RadioButton rbtnWorkManagerTwo;
    private RadioButton rbtnWorkManagerThree;
    private RadioButton rbtnWorkManagerFour;
    private ListView lvWorkManagerInPosition;
    private WorkManagerLvAdapter lvAdapter;
    private PopupWindow mPopWindow;

    @Inject
    WorkFragmentPresenter presenter;
    private static final int RWDH = 103;
    private static final int ZGRY = 104;
    private static final int ADDTO = 106;
    private static final int SUBMITT = 107;
    private static final int REMOVE = 108;

    private LayoutInflater inflater;
    private LinearLayout heanView;
    private LinearLayout tailView;

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
                    lvAdapter = new WorkManagerLvAdapter(getActivity(), workManagerRwdhModel, WorkManagerFragment.this);
                    lvWorkManager.setAdapter(lvAdapter);
                    tvWorkOther = tailView.findViewById(R.id.tv_work_lv_other);
                    int size0 = workManagerRwdhModel.getData().get(workManagerRwdhModel.getData().size() - 1).get(0).getRylist().size();
                    int size1 = workManagerRwdhModel.getData().get(workManagerRwdhModel.getData().size() - 1).get(1).getRylist().size();
                    int size = size0 + size1;
                    tvWorkOther.setText("其他勤务安排(" + size + ")人");
                    progressDialog.dismiss();
                    break;
                //获取右侧表数据
                case ZGRY:
                    WorkManagerZgryModel zgryModel = (WorkManagerZgryModel) msg.obj;
                    lvWorkManagerInPosition.setAdapter(new WorkManagerInPositionLvAdapter(getActivity(), zgryModel));
                    progressDialog.dismiss();
                    break;
                //添加人员
                case ADDTO:
                    WorkManagerAddToModel addToModel = (WorkManagerAddToModel) msg.obj;
                    if (addToModel.getData().size() == 0) {
                        UIUtils.showToast(getActivity(), "该组无人员可安排");
                        mPopWindow.dismiss();
                    } else {
                        mPopWindow.dismiss();
                        showAddToDialog(addToModel);
                    }
                    if (lvWorkAddToAdapter != null) {
                        lvWorkAddToAdapter.notifyDataSetChanged();
                    }
                    presenter.getDataRwdh();
                    progressDialog.dismiss();
                    break;
                //添加人员成功
                case SUBMITT:
                    String message = (String) msg.obj;
                    mPopWindow.dismiss();
                    dialog.dismiss();
//                    UIUtils.showToast(getActivity(), message);
                    UIUtils.showToast(getActivity(), "添加人员成功");
                    presenter.getDataRwdh();
                    progressDialog.dismiss();
                    break;
                //删除人员
                case REMOVE:
                    String removeMessage = (String) msg.obj;
                    dialogRemove.dismiss();
//                    UIUtils.showToast(getActivity(), removeMessage);
                    UIUtils.showToast(getActivity(), "删除人员成功");
                    presenter.getDataRwdh();
                    progressDialog.dismiss();
                    break;
                default:
                    break;
            }
        }
    };
    private ListView lvWorkAddto;
    private LvWorkAddToAdapter lvWorkAddToAdapter;
    private View view;
    private MyDialog dialog;
    private LvWorkRemoveToAdapter removeToAdapter;
    private MyDialog dialogRemove;
    private TextView tvWorkOther;
    private ProgressDialog progressDialog;
    private Button btnWorkYes;
    private int popWidth;
    private int popHeight;
    private int btnWidth;
    private int btnHeight;
    private View v1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerWorkManagerFragmentComponent.builder().workManagerFragmentModule(new WorkManagerFragmentModule(this)).build().in(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        progressDialog = ProgressDialog.show(getActivity(), "请稍等...", "正在获取数据中... 请稍后", true);
        progressDialog.setCanceledOnTouchOutside(true);
        presenter.getDataRwdh();
        presenter.ObtainZgry("");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workmanager, container, false);
        initView(view);
        initLisener();
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {
        } else {
            this.onResume();
        }
    }

    private void initView(View view) {
        lvWorkManager = view.findViewById(R.id.lv_work_maneger);
        rgWorkManager = view.findViewById(R.id.rg_work_manager);
        rbtnWorkManagerWhole = view.findViewById(R.id.rbtn_work_manager_whole);
        rbtnWorkManagerOne = view.findViewById(R.id.rbtn_work_manager_one);
        rbtnWorkManagerTwo = view.findViewById(R.id.rbtn_work_manager_two);
        rbtnWorkManagerThree = view.findViewById(R.id.rbtn_work_manager_three);
        rbtnWorkManagerFour = view.findViewById(R.id.rbtn_work_manager_four);
        lvWorkManagerInPosition = view.findViewById(R.id.lv_work_maneger_in_position);

        inflater = LayoutInflater.from(getActivity());
        //添加头部
        heanView = (LinearLayout) inflater.inflate(R.layout.lv_item_head, null);
        lvWorkManager.addHeaderView(heanView);
        tailView = (LinearLayout) inflater.inflate(R.layout.lv_item_tail, null);
        lvWorkManager.addFooterView(tailView);


    }

    private void initLisener() {
        rgWorkManager.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtn_work_manager_whole:
                        presenter.ObtainZgry("");
                        break;
                    case R.id.rbtn_work_manager_one:
                        presenter.ObtainZgry("1");
                        break;
                    case R.id.rbtn_work_manager_two:
                        presenter.ObtainZgry("2");
                        break;
                    case R.id.rbtn_work_manager_three:
                        presenter.ObtainZgry("3");
                        break;
                    case R.id.rbtn_work_manager_four:
                        presenter.ObtainZgry("4");
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 加号点击 请求列表数据 携带待添加的任务信息
     */
    public void addTo(final String clickRwdh, final String clickQssj, final String clickJzsj, View v) {

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
                presenter.addToData(clickQssj, clickRwdh, clickJzsj, 1);
            }
        });
        tvGroup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addToData(clickQssj, clickRwdh, clickJzsj, 2);
            }
        });
        tvGroup3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addToData(clickQssj, clickRwdh, clickJzsj, 3);
            }
        });
        tvGroup4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addToData(clickQssj, clickRwdh, clickJzsj, 4);
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
        btnWidth = v.getMeasuredWidth();
        btnHeight = v.getMeasuredHeight();
        popWidth = mPopWindow.getContentView().getMeasuredWidth();
        popHeight = mPopWindow.getContentView().getMeasuredHeight();
        //PopupWindow的x偏移值
        int xoff = (int) ((float) (btnWidth - popWidth) / 2);
        //因为相对于按钮的上方，所以该值为负值
        int yoff = popHeight + btnHeight;
        v1 = v;
        mPopWindow.showAsDropDown(v, xoff, -yoff);
    }

    private void showAddToDialog(final WorkManagerAddToModel addToModel) {
        dialog = new MyDialog(getActivity(), R.style.myDialog);
        final View view = View.inflate(getActivity(), R.layout.work_dialog_addto, null);
        dialog.setView(view);
        dialog.setProperty(0, 0, 400, 800);//设置坐标和宽高
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        ListView lvWorkAddTo = view.findViewById(R.id.lv_work_addto);
        btnWorkYes = view.findViewById(R.id.btn_work_yes);
        lvWorkAddToAdapter = new LvWorkAddToAdapter(getActivity(), addToModel);
        lvWorkAddTo.setAdapter(lvWorkAddToAdapter);
        //从适配器中拿到点击的position的集合
        final HashMap<Integer, String> map = lvWorkAddToAdapter.map;
        //选择的人员编号的集合
        final List<String> listRybhAll = new ArrayList<>();
        btnWorkYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (String value : map.values()) {
                    listRybhAll.add(value);
                }
                presenter.taskSubmission(listRybhAll, rwdh, qssj, jzsj);
            }
        });
    }

    /**
     * 删除的人员信息列表展示
     * @param rylist
     */
    public void removeTo(List<WorkManagerRwdhModel.DataBean.RylistBean> rylist) {
        dialogRemove = new MyDialog(getActivity(), R.style.myDialog);
        final View view = View.inflate(getActivity(), R.layout.work_dialog_remove, null);
        dialogRemove.setView(view);
        dialogRemove.setProperty(0, 0, 400, 800);//设置坐标和宽高
        dialogRemove.setCanceledOnTouchOutside(true);
        dialogRemove.show();
        ListView lvWorkAddTo = view.findViewById(R.id.lv_work_addto);
        Button bunWorkYes = view.findViewById(R.id.btn_work_yes);
        removeToAdapter = new LvWorkRemoveToAdapter(getActivity(), rylist);
        lvWorkAddTo.setAdapter(removeToAdapter);
        //获得这个时间段的所有在岗人员
        final HashMap<Integer, String> mapRemove = removeToAdapter.mapRemove;
        //选择要删除的人员编号的集合
        final List<String> listRenoveRybhAll = new ArrayList<>();
        bunWorkYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (String value : mapRemove.values()) {
                    listRenoveRybhAll.add(value);
                }
                presenter.removeToSubmitted(listRenoveRybhAll);
            }
        });
    }

    /**
     * 错误提示
     *
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

    public void showDialogData(WorkManagerAddToModel addToModel) {
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
}
