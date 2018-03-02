package com.sx.qwxt.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者：shy
 * 时间：2017/11/30 0030
 * 描述：
 */
public class VehicleVpAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<Fragment> list;

    public VehicleVpAdapter(FragmentManager fm, Context context, List<Fragment> list) {
        super(fm);
        this.context = context;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
