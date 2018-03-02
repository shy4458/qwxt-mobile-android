package com.sx.qwxt.dagger.component;

import com.sx.qwxt.dagger.module.VehicleLendModule;
import com.sx.qwxt.view.fragment.vehicleManager.VehicleLendFragment;

import dagger.Component;

/**
 * 作者：shy
 * 时间：2017/12/4 0004
 * 描述：
 */
@Component(modules = VehicleLendModule.class)
public interface VehicleLendComponent {
    void in(VehicleLendFragment fragment);
}
