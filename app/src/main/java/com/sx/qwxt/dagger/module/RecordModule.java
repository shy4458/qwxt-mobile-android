package com.sx.qwxt.dagger.module;

import com.sx.qwxt.presenter.RecordPresenter;
import com.sx.qwxt.view.fragment.record.RecordFragment;

import dagger.Module;
import dagger.Provides;

/**
 * 作者：shy
 * 时间：2017/11/21 0021
 * 描述：
 */
@Module
public class RecordModule {

    RecordFragment fragment;

    public RecordModule(RecordFragment fragment) {
        this.fragment = fragment;
    }
    @Provides
    RecordPresenter providesRecordPresenter(){
        return new RecordPresenter(fragment);
    }
}
