package com.sx.qwxt.dagger.component;

import com.sx.qwxt.dagger.module.OneForFifteenModule;
import com.sx.qwxt.view.fragment.record.OneForFifteenFragment;

import dagger.Component;

/**
 * 作者：shy
 * 时间：2017/11/19 0019
 * 描述：
 */
@Component(modules = OneForFifteenModule.class)
public interface OneForFifteenComponent {
    void in(OneForFifteenFragment fragment);
}
