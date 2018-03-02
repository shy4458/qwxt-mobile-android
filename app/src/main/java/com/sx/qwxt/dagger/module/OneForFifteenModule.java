package com.sx.qwxt.dagger.module;

import com.sx.qwxt.presenter.OneForFifteenPersenter;
import com.sx.qwxt.view.fragment.record.OneForFifteenFragment;

import dagger.Module;
import dagger.Provides;

/**
 * 作者：shy
 * 时间：2017/11/19 0019
 * 描述：
 */
@Module
public class OneForFifteenModule {

    OneForFifteenFragment fragment;

    public OneForFifteenModule(OneForFifteenFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    OneForFifteenPersenter persenter(){
        return new OneForFifteenPersenter(fragment);
    }
}
