package com.sx.qwxt.view.fragment.signin;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sx.baseframework.util.UIUtils;
import com.sx.qwxt.R;
import com.sx.qwxt.dagger.component.DaggerSigninFragmentComponent;
import com.sx.qwxt.dagger.module.SigninFragmentModule;
import com.sx.qwxt.model.signinModel.SigninLeaveReasonNumber;
import com.sx.qwxt.presenter.SigninFragmentPresenter;
import com.sx.qwxt.view.adapter.SigninGroupingVpAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * 作者：shy
 * 时间：2017/11/9 0009:15:40
 * 描述：点名考勤
 */
public class SigninFragment extends Fragment {

    private ViewPager signinGroupingVp;

    /**
     * tab导航个数集合
     */
    private List<String> tabTitleList = new ArrayList<>();
    /**
     * 根据小组数加载fragment页面
     */
    private static List<Fragment> fragmentList = new ArrayList<>();

    private SigninGroupingVpAdapter signinGroupingVpAdapter;

    private static final int NUMBER = 101;
    @Inject
    SigninFragmentPresenter presenter;
    private TabLayout tlSigninGrouping;
    private static final int SIZE = 90;
    private static final int MSG = 95;
    private int size;

    private LinearLayout ll1, ll2, ll3, ll4, ll5, ll6, ll7, ll8, ll9, ll10, ll11, ll12, ll13, ll14, ll15, ll16;
    private TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10, tv11, tv12, tv13, tv14, tv15, tv16;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case SIZE:
                    size = (int) msg.obj;
                    for (int i = 0; i < size; i++) {
                        if (i == 0) {
                            tabTitleList.add("全部");
                            fragmentList.add(newInstance(""));
                        } else if (i == 1) {
                            tabTitleList.add("一组");
                            fragmentList.add(newInstance(i + ""));
                        } else if (i == 2) {
                            tabTitleList.add("二组");
                            fragmentList.add(newInstance(i + ""));
                        } else if (i == 3) {
                            tabTitleList.add("三组");
                            fragmentList.add(newInstance(i + ""));
                        } else if (i == 4) {
                            tabTitleList.add("四组");
                            fragmentList.add(newInstance(i + ""));
                        }
                    }

                    break;
                case NUMBER:
                    SigninLeaveReasonNumber number = (SigninLeaveReasonNumber) msg.obj;
                    tv1.setText(number.getData().get(0).getSl() + "");
                    tv2.setText(number.getData().get(1).getSl() + "");
                    tv3.setText(number.getData().get(2).getSl() + "");
                    tv4.setText(number.getData().get(3).getSl() + "");
                    tv5.setText(number.getData().get(4).getSl() + "");
                    tv6.setText(number.getData().get(5).getSl() + "");
                    tv7.setText(number.getData().get(6).getSl() + "");
                    tv8.setText(number.getData().get(7).getSl() + "");
                    tv9.setText(number.getData().get(8).getSl() + "");
                    tv10.setText(number.getData().get(9).getSl() + "");
                    tv11.setText(number.getData().get(10).getSl() + "");
                    tv12.setText(number.getData().get(11).getSl() + "");
                    tv13.setText(number.getData().get(12).getSl() + "");
                    tv14.setText(number.getData().get(13).getSl() + "");
                    tv15.setText(number.getData().get(14).getSl() + "");
                    tv16.setText(number.getData().get(15).getSl() + "");
                    break;
                case MSG:
                    String str = (String) msg.obj;
                    UIUtils.showToast(getActivity(), str);

                    break;
                default:
                    break;
            }
        }
    };
    private TextView tvDefaultPersonnel;
    private LinearLayout llAllChecd;
    private TextView tvAllChecd;
    private TextView tvAllCancel;
    private TextView tvAllYes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerSigninFragmentComponent.builder().signinFragmentModule(new SigninFragmentModule(this)).build().in(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //获取小组数量
        presenter.getData();
        presenter.getLeaveData();
    }

    //每次界面显示或隐藏调用 f为显示
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            llAllChecd.setVisibility(View.INVISIBLE);
        } else {
            //刷新数据
            WholeFragment fragment = (WholeFragment) getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:" + signinGroupingVp.getId() + ":" + signinGroupingVp.getCurrentItem());
            fragment.onHiddenChanged(false);
        }
    }

    public void controlView(int i) {
        if (i == 0) {
            llAllChecd.setVisibility(View.INVISIBLE);
        } else if (i == 1) {
            llAllChecd.setVisibility(View.VISIBLE);
        }
    }

    private void initView(View view) {

        ll1 = view.findViewById(R.id.ll1);
        ll2 = view.findViewById(R.id.ll2);
        ll3 = view.findViewById(R.id.ll3);
        ll4 = view.findViewById(R.id.ll4);
        ll5 = view.findViewById(R.id.ll5);
        ll6 = view.findViewById(R.id.ll6);
        ll7 = view.findViewById(R.id.ll7);
        ll8 = view.findViewById(R.id.ll8);
        ll9 = view.findViewById(R.id.ll9);
        ll10 = view.findViewById(R.id.ll10);
        ll11 = view.findViewById(R.id.ll11);
        ll12 = view.findViewById(R.id.ll12);
        ll13 = view.findViewById(R.id.ll13);
        ll14 = view.findViewById(R.id.ll14);
        ll15 = view.findViewById(R.id.ll15);
        ll16 = view.findViewById(R.id.ll16);

        tv1 = view.findViewById(R.id.tv1);
        tv2 = view.findViewById(R.id.tv2);
        tv3 = view.findViewById(R.id.tv3);
        tv4 = view.findViewById(R.id.tv4);
        tv5 = view.findViewById(R.id.tv5);
        tv6 = view.findViewById(R.id.tv6);
        tv7 = view.findViewById(R.id.tv7);
        tv8 = view.findViewById(R.id.tv8);
        tv9 = view.findViewById(R.id.tv9);
        tv10 = view.findViewById(R.id.tv10);
        tv11 = view.findViewById(R.id.tv11);
        tv12 = view.findViewById(R.id.tv12);
        tv13 = view.findViewById(R.id.tv13);
        tv14 = view.findViewById(R.id.tv14);
        tv15 = view.findViewById(R.id.tv15);
        tv16 = view.findViewById(R.id.tv16);

        llAllChecd = view.findViewById(R.id.ll_All_checd);
        tvAllChecd = view.findViewById(R.id.tv_All_checd);
        tvAllCancel = view.findViewById(R.id.tv_All_cancel);
        tvAllYes = view.findViewById(R.id.tv_All_yes);

        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                tabTitleList.add("全部");
                fragmentList.add(newInstance(""));
            } else if (i == 1) {
                tabTitleList.add("一组");
                fragmentList.add(newInstance(i + ""));
            } else if (i == 2) {
                tabTitleList.add("二组");
                fragmentList.add(newInstance(i + ""));
            } else if (i == 3) {
                tabTitleList.add("三组");
                fragmentList.add(newInstance(i + ""));
            } else if (i == 4) {
                tabTitleList.add("四组");
                fragmentList.add(newInstance(i + ""));
            }
        }

        tlSigninGrouping = view.findViewById(R.id.tl_signin_grouping);
        signinGroupingVp = view.findViewById(R.id.vp_signin_grouping);
        tvDefaultPersonnel = view.findViewById(R.id.tv_default_personnel);
        signinGroupingVp.setOffscreenPageLimit(0);

        for (int i = 0; i < tabTitleList.size(); i++) {
            tlSigninGrouping.addTab(tlSigninGrouping.newTab().setText(tabTitleList.get(i)));
        }
        tlSigninGrouping.setTabGravity(TabLayout.GRAVITY_FILL);
        //设置顶部标签指示条的颜色和选中页面时标签字体颜色
        tlSigninGrouping.setSelectedTabIndicatorColor(Color.parseColor("#03a9f3"));
        tlSigninGrouping.setTabTextColors(Color.GRAY, Color.parseColor("#000000"));
        signinGroupingVpAdapter = new SigninGroupingVpAdapter(getFragmentManager(), tabTitleList, fragmentList);
        signinGroupingVp.setAdapter(signinGroupingVpAdapter);
        //在设置viewpager页面滑动监听时，创建TabLayout的滑动监听
        signinGroupingVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlSigninGrouping));
        tlSigninGrouping.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //在选中的顶部标签时，为viewpager设置currentitem
                signinGroupingVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        signinGroupingVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                llAllChecd.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 2) {
                    WholeFragment fragment = (WholeFragment) getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:" + signinGroupingVp.getId() + ":" + signinGroupingVp.getCurrentItem());
                    fragment.onHiddenChanged(false);
                }
            }
        });
        //多选全选
        tvAllChecd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WholeFragment fragment = (WholeFragment) getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:" + signinGroupingVp.getId() + ":" + signinGroupingVp.getCurrentItem());
                fragment.checkdAll();
            }
        });
        //多选取消
        tvAllCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlView(0);
                WholeFragment fragment = (WholeFragment) getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:" + signinGroupingVp.getId() + ":" + signinGroupingVp.getCurrentItem());
                fragment.onResume();
            }
        });
        //多选确定
        tvAllYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WholeFragment fragment = (WholeFragment) getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:" + signinGroupingVp.getId() + ":" + signinGroupingVp.getCurrentItem());
                fragment.checkdYes();
                llAllChecd.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void set(String string) {
        tvDefaultPersonnel.setText("默认" + string + "人全部在岗,请点名考勤");
    }

    public static WholeFragment newInstance(String doctor_id) {
        WholeFragment fragment = new WholeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("grouping_xz", doctor_id);
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * 错误信息
     *
     * @param str
     */
    public void requestError(String str) {
        Message msg = Message.obtain();
        msg.what = MSG;
        msg.obj = str;
        mHandler.sendMessage(msg);
    }

    /**
     * 返回的小组数
     *
     * @param size
     */
    public void success(int size) {
        Message msg = Message.obtain();
        msg.what = SIZE;
        msg.obj = size;
        mHandler.sendMessage(msg);
    }

    /**
     * 每个理由下的人数
     *
     * @param number
     */

    public void leaveAllSize(SigninLeaveReasonNumber number) {
        Message msg = Message.obtain();
        msg.what = NUMBER;
        msg.obj = number;
        mHandler.sendMessage(msg);
    }
}
