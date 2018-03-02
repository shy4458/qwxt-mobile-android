package com.sx.qwxt.view.fragment.signin;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sx.baseframework.util.UIUtils;
import com.sx.qwxt.R;
import com.sx.qwxt.dagger.component.DaggerWholeFragmentComponent;
import com.sx.qwxt.dagger.module.WholeFragmentModule;
import com.sx.qwxt.model.signinModel.SigninPopupModel;
import com.sx.qwxt.model.signinModel.SigninSurfaceModel;
import com.sx.qwxt.presenter.WholeFragmentPresenter;
import com.sx.qwxt.view.adapter.GvPersonnelAdapter;
import com.sx.qwxt.view.adapter.leaveAdapter;
import com.sx.qwxt.view.widget.MyDialog;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

/**
 * 作者：shy
 * 时间：2017/11/10 0010
 * 描述：全部
 */

public class WholeFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private GridView gvPersonnel;
    public GvPersonnelAdapter adapter;
    private PopupWindow mPopWindow;

    @Inject
    WholeFragmentPresenter presenter;

    private static final int MSG = 100;
    private static final int POPUP = 101;
    private static final int CS = 102;

    private String groupingXz;
    private ArrayList<String> list = new ArrayList<>();

    @SuppressLint("HandlerLeak")
    //@SuppressLint标注忽略指定的警告。
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FragmentManager fragmentManager = getFragmentManager();
            Fragment signinFragment = fragmentManager.findFragmentByTag("signinFragment");
            switch (msg.what) {
                case MSG:
                    progressDialog.dismiss();
                    data = (SigninSurfaceModel) msg.obj;
//                    UIUtils.showToast(getActivity(),data.getMsg());
                    adapter = new GvPersonnelAdapter(getActivity(), data);
                    gvPersonnel.setAdapter(adapter);
                    tvDefaultPersonnel.setText("默认" + data.getData().size() + "人待考勤,请点名");
                    signinFragment.onResume();
                    break;
                case POPUP:
                    popupModel = (SigninPopupModel) msg.obj;
                    break;
                case CS:
                    mPopWindow.dismiss();
                    presenter.getDataCxkqb(groupingXz);
                    UIUtils.showToast(getActivity(), "修改成功");
                    signinFragment.onResume();
                    if (myDialog != null) {
                        myDialog.dismiss();
                    }

                    break;
                default:
                    break;
            }
        }
    };

    private SigninSurfaceModel data;
    private String rybh;
    private SigninPopupModel popupModel;
    private ProgressDialog progressDialog;
    private TextView tvDefaultPersonnel;
    private com.sx.qwxt.view.adapter.leaveAdapter leaveAdapter;
    private MyDialog myDialog;
    private MyAdapter myAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerWholeFragmentComponent.builder().wholeFragmentModule(new WholeFragmentModule(this)).build().in(this);
        list.add("事假");
        list.add("事假半天");
        list.add("病假");
        list.add("病假半天");
        list.add("年假");
        list.add("探亲假");
        list.add("婚假");
        list.add("产假");
        list.add("陪产假");
        list.add("丧假");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grouping_whole_fragment, container, false);
        Bundle bundle = getArguments();
        groupingXz = bundle.getString("grouping_xz");
        initView(view);
        initListener();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        progressDialog = ProgressDialog.show(getActivity(), "请稍等...", "正在获取数据中... 请稍后...", true);
        progressDialog.setCanceledOnTouchOutside(true);
        presenter.getDataCxkqb(groupingXz);
        presenter.getPopupData();
    }

    private void initView(View view) {
        gvPersonnel = view.findViewById(R.id.gv_personnel);
        tvDefaultPersonnel = view.findViewById(R.id.tv_default_personnel);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {

        } else {
            presenter.getDataCxkqb(groupingXz);
            presenter.getPopupData();
        }
    }

    private void initListener() {
        gvPersonnel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rybh = data.getData().get(position).getRybh();
                showPopupWindow(view);
            }
        });

        gvPersonnel.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setState(0);
                int count = gvPersonnel.getChildCount();
                for (int i = 0; i < count; i++) {
                    //返回指定位置的视图
                    View childView = gvPersonnel.getChildAt(i);
                    CheckBox cb = childView.findViewById(R.id.cb_signin_cb);
                    cb.setVisibility(View.VISIBLE);
                }
                FragmentManager fm = getActivity().getSupportFragmentManager();
                SigninFragment signinFragment = (SigninFragment) fm.findFragmentByTag("signinFragment");
                signinFragment.controlView(1);
                return true;
            }
        });
    }

    private void showPopupWindow(View v) {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_layout, null);
        FrameLayout flClickLeave = contentView.findViewById(R.id.fl_click_leave);
        TextView tvPopup1 = contentView.findViewById(R.id.tv_popup_1);
        TextView tvPopup2 = contentView.findViewById(R.id.tv_popup_2);
        TextView tvPopup3 = contentView.findViewById(R.id.tv_popup_3);
        TextView tvPopup4 = contentView.findViewById(R.id.tv_popup_4);
        TextView tvPopup5 = contentView.findViewById(R.id.tv_popup_5);
        TextView tvPopup6 = contentView.findViewById(R.id.tv_popup_6);
        TextView tvZz = contentView.findViewById(R.id.tv_zz);
        TextView tvQxzz = contentView.findViewById(R.id.tv_qxzz);

        tvPopup1.setText(popupModel.getData().get(0).getCd_name());
        tvPopup2.setText(popupModel.getData().get(1).getCd_name());
        tvPopup3.setText(popupModel.getData().get(2).getCd_name());
        tvPopup4.setText(popupModel.getData().get(3).getCd_name());
        tvPopup5.setText(popupModel.getData().get(4).getCd_name());
        tvPopup6.setText(popupModel.getData().get(5).getCd_name());

        tvPopup1.setOnClickListener(this);
        tvPopup2.setOnClickListener(this);
        tvPopup3.setOnClickListener(this);
        tvPopup4.setOnClickListener(this);
        tvPopup5.setOnClickListener(this);
        tvPopup6.setOnClickListener(this);
        tvZz.setOnClickListener(this);
        tvQxzz.setOnClickListener(this);

        flClickLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSingleChoiceDialog();
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

    /**
     * 请假分类dialog
     */
    public void showSingleChoiceDialog() {
        myDialog = new MyDialog(getActivity(), R.style.myDialog);
        final View view = View.inflate(getActivity(), R.layout.work_dialog_addto, null);
        myDialog.setView(view);
        myDialog.setProperty(0, 0, 400, 800);//设置坐标和宽高
        myDialog.setCanceledOnTouchOutside(true);
        myDialog.show();
        ListView lvWorkAddTo = view.findViewById(R.id.lv_work_addto);
        Button btnWorkYes = view.findViewById(R.id.btn_work_yes);
        leaveAdapter = new leaveAdapter(getActivity(), list);
        btnWorkYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hashMap.get("id") == null) {
                    UIUtils.showToast(getActivity(), "请勾选请假类型");
                } else {
                    presenter.askleave(hashMap.get("id") + 5, rybh);
                }
            }
        });

        for (int j = 0; j < list.size(); j++) {
            checkList.add(false);
        }
        myAdapter = new MyAdapter(getActivity(), list);
        lvWorkAddTo.setAdapter(myAdapter);
    }

    private HashMap<String, Integer> hashMap = new HashMap<>();
    private ArrayList<Boolean> checkList = new ArrayList<Boolean>(); // 判断listview单选位置

    private class MyAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        ArrayList<String> myList;

        public MyAdapter(Context context, ArrayList<String> myList) {
            this.inflater = LayoutInflater.from(context);
            this.myList = myList;
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
            Log.i("aaa", "getview");
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.dialog_leave_item, null);
                holder = new ViewHolder();
                holder.txt = (TextView) convertView.findViewById(R.id.tv_leave_name);
                holder.checkBox = (CheckBox) convertView
                        .findViewById(R.id.cb_work_addto_item);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.txt.setText(myList.get(position));
            holder.checkBox.setChecked(checkList.get(position));
            holder.checkBox
                    .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {//单击checkbox实现单选，根据状态变换进行单选设置
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView,
                                                     boolean isChecked) {
                            if (isChecked) {
                                checkPosition(position);
                            } else {
                                checkList.set(position, false);//将已选择的位置设为选择
                            }
                        }
                    });
            convertView.setOnClickListener(new View.OnClickListener() {//item单击进行单选设置
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    checkPosition(position);
                }
            });
            return convertView;
        }
    }

    public final class ViewHolder {
        public TextView txt;
        public CheckBox checkBox;
    }

    //设置选中的位置，将其他位置设置为未选
    public void checkPosition(int position) {
        for (int i = 0; i < checkList.size(); i++) {
            if (position == i) {// 设置已选位置
                checkList.set(i, true);
                hashMap.put("id", position);
            } else {
                checkList.set(i, false);
            }
        }
        myAdapter.notifyDataSetChanged();
    }

    private int chk;

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        chk = checkedId;
    }

    /**
     * 错误提示
     *
     * @param msg
     */
    public void requestError(String msg) {
        progressDialog.dismiss();
    }

    /**
     * 得到全部的考勤人员数据
     * @param data
     */
    public void success(SigninSurfaceModel data) {
        Message msg = Message.obtain();
        msg.what = MSG;
        msg.obj = data;
        mHandler.sendMessage(msg);
    }

    /**
     * pppwindows 展示数据
     *
     * @param popupModel
     */
    public void obtainPopup(SigninPopupModel popupModel) {
        Message msg = Message.obtain();
        msg.what = POPUP;
        msg.obj = popupModel;
        mHandler.sendMessage(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_zz:
                presenter.signinPopup("zz", rybh);
                break;
            case R.id.tv_qxzz:
                presenter.signinPopup("cancelzz", rybh);
                break;
            case R.id.tv_popup_1:
                presenter.signinPopup("1", rybh);
                break;
            case R.id.tv_popup_2:
                presenter.signinPopup("16", rybh);
                break;
            case R.id.tv_popup_3:
                presenter.signinPopup("2", rybh);
                break;
            case R.id.tv_popup_4:
                presenter.signinPopup("3", rybh);
                break;
            case R.id.tv_popup_5:
                presenter.signinPopup("4", rybh);
                break;
            case R.id.tv_popup_6:
                presenter.signinPopup("15", rybh);
                break;
        }
    }

    /**
     * 状态改变成功的回调
     */
    public void notifyView() {
        Message msg = Message.obtain();
        msg.what = CS;
        mHandler.sendMessage(msg);
    }

    /*
    多选确定按钮
     */
    public void checkdYes() {
        String multipleRYBH = "";
        HashMap<String, Boolean> map = adapter.map;
        //1、获取所有键值对对象的集合
        Set<Map.Entry<String, Boolean>> set = map.entrySet();
        //2、获取Iterator对象，并用此对象的方法遍历键值对对象
        Iterator<Map.Entry<String, Boolean>> iter = set.iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Boolean> me = iter.next();
            //3、根据键值对对象获取键和值
            multipleRYBH = multipleRYBH + me.getKey() + ",";
            Log.d("/////", me.getKey());
        }
        rybh = multipleRYBH;

        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_layout, null);
        FrameLayout flClickLeave = contentView.findViewById(R.id.fl_click_leave);
        TextView tvPopup1 = contentView.findViewById(R.id.tv_popup_1);
        TextView tvPopup2 = contentView.findViewById(R.id.tv_popup_2);
        TextView tvPopup3 = contentView.findViewById(R.id.tv_popup_3);
        TextView tvPopup4 = contentView.findViewById(R.id.tv_popup_4);
        TextView tvPopup5 = contentView.findViewById(R.id.tv_popup_5);
        TextView tvPopup6 = contentView.findViewById(R.id.tv_popup_6);
        TextView tvZz = contentView.findViewById(R.id.tv_zz);
        TextView tvQxzz = contentView.findViewById(R.id.tv_qxzz);

        tvPopup1.setText(popupModel.getData().get(0).getCd_name());
        tvPopup2.setText(popupModel.getData().get(1).getCd_name());
        tvPopup3.setText(popupModel.getData().get(2).getCd_name());
        tvPopup4.setText(popupModel.getData().get(3).getCd_name());
        tvPopup5.setText(popupModel.getData().get(4).getCd_name());
        tvPopup6.setText(popupModel.getData().get(5).getCd_name());

        tvPopup1.setOnClickListener(this);
        tvPopup2.setOnClickListener(this);
        tvPopup3.setOnClickListener(this);
        tvPopup4.setOnClickListener(this);
        tvPopup5.setOnClickListener(this);
        tvPopup6.setOnClickListener(this);
        tvZz.setOnClickListener(this);
        tvQxzz.setOnClickListener(this);

        flClickLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSingleChoiceDialog();
            }
        });

        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //点击外侧关闭窗口
        mPopWindow.setOutsideTouchable(true);
        //获取焦点
        mPopWindow.setFocusable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        if (rybh.length() == 0) {
            //多选没有选择人员就点击了确定
            onResume();
        } else {
            mPopWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        }
    }

    //全选操作
    public void checkdAll() {
        adapter.changeState();
    }
}
