package com.sx.qwxt.dagger.module;

import com.sx.qwxt.presenter.VehicleFragmentPresenter;
import com.sx.qwxt.view.fragment.vehicleManager.VehicleFragment;

import dagger.Module;
import dagger.Provides;

/**
 * 作者：shy
 * 时间：2017/12/4 0004
 * 描述：
 */
@Module
public class VehicleFragmentModule {

    VehicleFragment fragment;

    public VehicleFragmentModule(VehicleFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    VehicleFragmentPresenter providesVehicleFragmentPresenter(){
        return new VehicleFragmentPresenter(fragment);
    }
}
