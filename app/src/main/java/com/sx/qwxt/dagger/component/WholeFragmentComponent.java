package com.sx.qwxt.dagger.component;

import com.sx.qwxt.dagger.module.WholeFragmentModule;
import com.sx.qwxt.view.fragment.signin.WholeFragment;

import dagger.Component;

/**
 * 作者：shy
 * 时间：2017/11/15 0015
 * 描述：业务对象设置给目标
 */

@Component(modules = WholeFragmentModule.class)
public interface WholeFragmentComponent {
    void in(WholeFragment fragment);
}
