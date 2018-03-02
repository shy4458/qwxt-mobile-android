package com.sx.qwxt.view.fragment.record;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.sx.qwxt.R;
import com.sx.qwxt.dagger.component.DaggerFifteenForThirtComponent;
import com.sx.qwxt.dagger.module.FifteenForThirtModule;
import com.sx.qwxt.model.recordModel.ReconrdTimeModel;
import com.sx.qwxt.presenter.FifteenForThiryPresenter;
import com.sx.qwxt.view.adapter.RecordLvFifteenAdapter;

import java.util.Calendar;

import javax.inject.Inject;

/**
 * 作者：shy
 * 时间：2017/11/12 0012
 * 描述：
 */
public class FifteenForThirtyFragment extends Fragment {

    private ListView lvFifteenThirty;
    @Inject
    FifteenForThiryPresenter presenter;

    private static final int RECORD = 105;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case RECORD:
                    ReconrdTimeModel reconrdTimeModel = (ReconrdTimeModel) msg.obj;
                    RecordLvFifteenAdapter recordLvFifteenAdapter = new RecordLvFifteenAdapter(getActivity(), reconrdTimeModel);
                    lvFifteenThirty.setAdapter(recordLvFifteenAdapter);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerFifteenForThirtComponent.builder().fifteenForThirtModule(new FifteenForThirtModule(this)).build().in(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fifteen_thirty, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        lvFifteenThirty = view.findViewById(R.id.lv_fifteen_thirty);
        TextView tv31 = view.findViewById(R.id.tv31);
        if (getCurrentMonthLastDay() == 31){
            tv31.setVisibility(View.VISIBLE);
        }
    }

    public static int getCurrentMonthLastDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 错误提示
     * @param msg
     */
    public void requestError(String msg) {

    }

    /**
     * 查询本月记录
     * @param reconrdTimeModel
     */
    public void queryMonthly(ReconrdTimeModel reconrdTimeModel) {
        Message msg = Message.obtain();
        msg.what = RECORD;
        msg.obj = reconrdTimeModel;
        mHandler.sendMessage(msg);
    }
}
