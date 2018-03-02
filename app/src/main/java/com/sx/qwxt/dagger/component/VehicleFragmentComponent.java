package com.sx.qwxt.dagger.component;

import com.sx.qwxt.dagger.module.VehicleFragmentModule;
import com.sx.qwxt.view.fragment.vehicleManager.VehicleFragment;

import dagger.Component;

/**
 * 作者：shy
 * 时间：2017/12/4 0004
 * 描述：
 */
@Component(modules = VehicleFragmentModule.class)
public interface VehicleFragmentComponent {

    void in(VehicleFragment fragment);

}
