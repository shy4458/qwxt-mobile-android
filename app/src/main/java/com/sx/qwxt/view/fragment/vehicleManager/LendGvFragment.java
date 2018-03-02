package com.sx.qwxt.view.fragment.vehicleManager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.sx.baseframework.base.BaseApplication;
import com.sx.qwxt.R;
import com.sx.qwxt.dagger.component.DaggerLendFragmentComponent;
import com.sx.qwxt.dagger.module.LendVpFragmentModule;
import com.sx.qwxt.model.workManagerModel.WorkManagerZgryModel;
import com.sx.qwxt.presenter.LendGvPresenter;
import com.sx.qwxt.view.adapter.LendGvAdapter;

import javax.inject.Inject;

/**
 * 作者：shy
 * 时间：2017/11/30 0030
 * 描述：借出界面左右滑动 Fragment
 */
public class LendGvFragment extends Fragment {
    private LendGvAdapter lendGvAdapter;
    public int selectorPosition = -1;
    @Inject
    LendGvPresenter presenter;
    private String grouping_xz;
    private WorkManagerZgryModel zgryModel;
    private static final int ZGRY = 200;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ZGRY:
                    zgryModel = (WorkManagerZgryModel) msg.obj;
                    lendGvAdapter = new LendGvAdapter(getActivity(), zgryModel);
                    gvLend.setAdapter(lendGvAdapter);
                    break;

                default:
                    break;
            }
        }
    };
    private GridView gvLend;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerLendFragmentComponent.builder().lendVpFragmentModule(new LendVpFragmentModule(this)).build().in(this);
        Bundle bundle = getArguments();
        grouping_xz = bundle.getString("grouping_xz");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lend_vp, null);
        initView(view);
        initListener();
        return view;
    }

    private void initListener() {
        gvLend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lendGvAdapter.changeState(position);
                selectorPosition = position;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        String state = BaseApplication.get("addAndModify", "");
        if ("0".equals(state)){
            //选择借车的人员和时间信息
            //这个借口去除了已添加的人员信息
            presenter.getData(grouping_xz);
            if (lendGvAdapter != null) {
                lendGvAdapter.notifyDataSetChanged();
            }

        }else if ("1".equals(state)){
            //"修改借车的人员和时间信息
            presenter.getDataXg(grouping_xz);
            if (lendGvAdapter != null) {
                lendGvAdapter.notifyDataSetChanged();
            }
        }


    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        String state = BaseApplication.get("addAndModify", "");
        if (hidden) {
        } else {
            if ("0".equals(state)){
                //选择借车的人员和时间信息
                //这个借口去除了已添加的人员信息
                presenter.getData(grouping_xz);
                if (lendGvAdapter != null) {
                    lendGvAdapter.notifyDataSetChanged();
                }

            }else if ("1".equals(state)){
                //"修改借车的人员和时间信息
                presenter.getDataXg(grouping_xz);
                if (lendGvAdapter != null) {
                    lendGvAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    private void initView(View view) {
        gvLend = view.findViewById(R.id.gv_lend);
    }

    public void success(WorkManagerZgryModel zgryModel) {
        Message msg = Message.obtain();
        msg.what = ZGRY;
        msg.obj = zgryModel;
        mHandler.sendMessage(msg);
    }
}
