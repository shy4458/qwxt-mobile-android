package com.sx.qwxt.dagger.component;

import com.sx.qwxt.dagger.module.MonthRecordFragmentModule;
import com.sx.qwxt.view.fragment.record.MonthRecordFragment;

import dagger.Component;

/**
 * 作者：shy
 * 时间：2017/11/19 0019
 * 描述：
 */
@Component(modules = MonthRecordFragmentModule.class)
public interface MonthRecordFragmentComponent {
    void in(MonthRecordFragment fragment);
}
