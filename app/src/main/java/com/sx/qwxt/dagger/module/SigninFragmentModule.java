package com.sx.qwxt.dagger.module;

import com.sx.qwxt.presenter.SigninFragmentPresenter;
import com.sx.qwxt.view.fragment.signin.SigninFragment;

import dagger.Module;
import dagger.Provides;

/**
 * 作者：shy
 * 时间：2017/11/15 0015
 * 描述：
 */
@Module
public class SigninFragmentModule {
    private SigninFragment signinFragment;

    public SigninFragmentModule(SigninFragment signinFragment) {
        this.signinFragment = signinFragment;
    }

    @Provides
    SigninFragmentPresenter provideSigninFragmentPresenter(){
        return new SigninFragmentPresenter(signinFragment);
    }
}
