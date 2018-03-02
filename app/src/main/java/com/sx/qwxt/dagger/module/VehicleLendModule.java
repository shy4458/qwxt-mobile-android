package com.sx.qwxt.dagger.module;

import com.sx.qwxt.presenter.VehicleLendPresenter;
import com.sx.qwxt.view.fragment.vehicleManager.VehicleLendFragment;

import dagger.Module;
import dagger.Provides;

/**
 * 作者：shy
 * 时间：2017/12/4 0004
 * 描述：
 */
@Module
public class VehicleLendModule {

    VehicleLendFragment fragment;

    public VehicleLendModule(VehicleLendFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    VehicleLendPresenter provides(){
        return new VehicleLendPresenter(fragment);
    }
}
