package com.sx.qwxt.dagger.component;

import com.sx.qwxt.dagger.module.LendVpFragmentModule;
import com.sx.qwxt.view.fragment.vehicleManager.LendGvFragment;

import dagger.Component;

/**
 * 作者：shy
 * 时间：2017/11/30 0030
 * 描述：
 */
@Component (modules = LendVpFragmentModule.class)
public interface LendFragmentComponent {
    void in(LendGvFragment fragment);

}
