package com.sx.qwxt.dagger.component;

import com.sx.qwxt.dagger.module.SigninFragmentModule;
import com.sx.qwxt.view.fragment.signin.SigninFragment;

import dagger.Component;

/**
 * 作者：shy
 * 时间：2017/11/15 0015
 * 描述：
 */
@Component(modules = SigninFragmentModule.class)
public interface SigninFragmentComponent {
    void in(SigninFragment fragment);
}
