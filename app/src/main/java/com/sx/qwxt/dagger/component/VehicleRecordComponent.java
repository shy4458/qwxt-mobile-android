package com.sx.qwxt.dagger.component;

import com.sx.qwxt.dagger.module.VehicleRecordModule;
import com.sx.qwxt.view.fragment.vehicleManager.VehicleRecordFragment;

import dagger.Component;

/**
 * 作者：shy
 * 时间：2017/12/4 0004
 * 描述：
 */
@Component(modules = VehicleRecordModule.class)
public interface VehicleRecordComponent {
    void in(VehicleRecordFragment fragment);
}
