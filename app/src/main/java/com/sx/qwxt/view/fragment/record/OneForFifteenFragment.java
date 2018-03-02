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

import com.sx.qwxt.R;
import com.sx.qwxt.dagger.component.DaggerOneForFifteenComponent;
import com.sx.qwxt.dagger.module.OneForFifteenModule;
import com.sx.qwxt.model.recordModel.ReconrdTimeModel;
import com.sx.qwxt.presenter.OneForFifteenPersenter;
import com.sx.qwxt.view.adapter.RecordLvAdapter;

import javax.inject.Inject;

/**
 * 作者：shy
 * 时间：2017/11/12 0012
 * 描述：
 */
public class OneForFifteenFragment extends Fragment {

    private ListView lvOneFifteen;
    @Inject
    OneForFifteenPersenter persenter;

    private static final int RECORD = 105;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case RECORD:
                    ReconrdTimeModel reconrdTimeModel = (ReconrdTimeModel) msg.obj;
                    lvOneFifteen.setAdapter(new RecordLvAdapter(getActivity(), reconrdTimeModel));
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerOneForFifteenComponent.builder().oneForFifteenModule(new OneForFifteenModule(this)).build().in(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        persenter.getData();
    }

    //ViewPager 左右滑动是 fragment显示和隐藏时调用
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {

        } else {

        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one_fifteen, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {
        lvOneFifteen = view.findViewById(R.id.lv_one_fifteen);

    }

    /**
     * 错误提示
     *
     * @param msg
     */
    public void requestError(String msg) {

    }

    /**
     * 查询本月记录
     *
     * @param recordTimeModel
     */
    public void queryMonthly(ReconrdTimeModel recordTimeModel) {
        Message msg = Message.obtain();
        msg.what = RECORD;
        msg.obj = recordTimeModel;
        mHandler.sendMessage(msg);
    }
}
