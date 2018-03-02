package com.sx.qwxt.dagger.module;

import com.sx.qwxt.presenter.MonthRecordFragmentPresenter;
import com.sx.qwxt.view.fragment.record.MonthRecordFragment;

import dagger.Module;
import dagger.Provides;

/**
 * 作者：shy
 * 时间：2017/11/19 0019
 * 描述：
 */
@Module
public class MonthRecordFragmentModule {

    MonthRecordFragment fragment;

    public MonthRecordFragmentModule(MonthRecordFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    MonthRecordFragmentPresenter providesMonthRecordFragmentPresenter(){
        return new MonthRecordFragmentPresenter(fragment);
    }
}
