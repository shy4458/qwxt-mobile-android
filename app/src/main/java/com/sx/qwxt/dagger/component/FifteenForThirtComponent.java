package com.sx.qwxt.dagger.component;

import com.sx.qwxt.dagger.module.FifteenForThirtModule;
import com.sx.qwxt.view.fragment.record.FifteenForThirtyFragment;

import dagger.Component;

/**
 * 作者：shy
 * 时间：2017/11/19 0019
 * 描述：
 */
@Component(modules = FifteenForThirtModule.class)
public interface FifteenForThirtComponent {
    void in(FifteenForThirtyFragment fifteenForThirtyFragment);
}
