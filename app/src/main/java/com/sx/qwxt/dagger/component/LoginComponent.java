package com.sx.qwxt.dagger.component;

import com.sx.qwxt.dagger.module.LoginModule;
import com.sx.qwxt.view.activity.LoginActivity;

import dagger.Component;

/**
 * 作者：shy
 * 时间：2017/11/23 0023
 * 描述：
 */
@Component(modules = LoginModule.class)
public interface LoginComponent {

    void in(LoginActivity activity);

}
