package com.sx.qwxt.dagger.module;

import com.sx.qwxt.presenter.WorkFragmentPresenter;
import com.sx.qwxt.view.fragment.workManager.WorkManagerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * 作者：shy
 * 时间：2017/11/17 0017
 * 描述：
 */
@Module
public class WorkManagerFragmentModule {
    WorkManagerFragment fragment;

    public WorkManagerFragmentModule(WorkManagerFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    WorkFragmentPresenter providesWorkFragmentPresenter(){
        return new WorkFragmentPresenter(fragment);
    }
}
