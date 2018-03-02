package com.sx.qwxt.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者：shy
 * 时间：2017/11/12 0012
 * 描述：
 */
public class RecordHalfMonthVpAdapter extends FragmentPagerAdapter {


    private List<Fragment> fragments;
    public RecordHalfMonthVpAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
