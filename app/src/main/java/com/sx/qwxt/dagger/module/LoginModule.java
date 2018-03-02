package com.sx.qwxt.dagger.module;

import com.sx.qwxt.presenter.LoginPresenter;
import com.sx.qwxt.view.activity.LoginActivity;

import dagger.Module;
import dagger.Provides;

/**
 * 作者：shy
 * 时间：2017/11/23 0023
 * 描述：
 */
@Module
public class LoginModule {

    LoginActivity activity;

    public LoginModule(LoginActivity activity) {
        this.activity = activity;
    }

    @Provides
    LoginPresenter providesLoginPresenter (){
        return new LoginPresenter(activity);
    }


}
