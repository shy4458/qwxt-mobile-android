package com.sx.qwxt.dagger.module;

import com.sx.qwxt.presenter.LendGvPresenter;
import com.sx.qwxt.view.fragment.vehicleManager.LendGvFragment;

import dagger.Module;
import dagger.Provides;

/**
 * 作者：shy
 * 时间：2017/11/30 0030
 * 描述：
 */
@Module
public class LendVpFragmentModule {

    LendGvFragment fragment;

    public LendVpFragmentModule(LendGvFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    LendGvPresenter providesLendPresenter(){
        return new LendGvPresenter(fragment);
    }
}
