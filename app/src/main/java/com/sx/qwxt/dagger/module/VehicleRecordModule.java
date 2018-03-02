package com.sx.qwxt.dagger.module;

import com.sx.qwxt.presenter.VehicleRecordPresenter;
import com.sx.qwxt.view.fragment.vehicleManager.VehicleRecordFragment;

import dagger.Module;
import dagger.Provides;

/**
 * 作者：shy
 * 时间：2017/12/4 0004
 * 描述：
 */
@Module
public class VehicleRecordModule {

    VehicleRecordFragment fragment;

    public VehicleRecordModule(VehicleRecordFragment fragment) {
        this.fragment = fragment;
    }
    @Provides
    VehicleRecordPresenter provides(){
        return new VehicleRecordPresenter(fragment);
    }
}
