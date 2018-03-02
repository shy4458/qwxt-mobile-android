package com.sx.qwxt.dagger.module;

import com.sx.qwxt.presenter.WholeFragmentPresenter;
import com.sx.qwxt.view.fragment.signin.WholeFragment;

import dagger.Module;
import dagger.Provides;

/**
 * 作者：shy
 * 时间：2017/11/15 0015
 * 描述：
 */

@Module
public class WholeFragmentModule {

    private WholeFragment fragment;

    public WholeFragmentModule(WholeFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    WholeFragmentPresenter provideWholeFragmentPresenter(){
        return new WholeFragmentPresenter(fragment);
    }

}
