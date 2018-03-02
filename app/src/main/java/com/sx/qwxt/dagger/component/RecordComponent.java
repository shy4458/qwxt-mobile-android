package com.sx.qwxt.dagger.component;

import com.sx.qwxt.dagger.module.RecordModule;
import com.sx.qwxt.view.fragment.record.RecordFragment;

import dagger.Component;

/**
 * 作者：shy
 * 时间：2017/11/21 0021
 * 描述：
 */
@Component(modules = RecordModule.class)
public interface RecordComponent {
    void in(RecordFragment fragment);
}
