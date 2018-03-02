package com.sx.qwxt.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者：shy
 * 时间：2017/11/10 0010
 * 描述：
 */
public class SigninGroupingVpAdapter extends FragmentPagerAdapter {


    private List<String> tabTitleList;
    private List<Fragment> fragmentList;
    public SigninGroupingVpAdapter(FragmentManager fm,List<String> tabTitleList,List<Fragment> fragmentList) {
        super(fm);
        this.tabTitleList=tabTitleList;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitleList.get(position);
    }
}
