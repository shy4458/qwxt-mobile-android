package com.sx.qwxt.dagger.module;

import com.sx.qwxt.presenter.FifteenForThiryPresenter;
import com.sx.qwxt.view.fragment.record.FifteenForThirtyFragment;

import dagger.Module;
import dagger.Provides;

/**
 * 作者：shy
 * 时间：2017/11/19 0019
 * 描述：
 */
@Module
public class FifteenForThirtModule {

    FifteenForThirtyFragment fifteenForThirtyFragment;

    public FifteenForThirtModule(FifteenForThirtyFragment fifteenForThirtyFragment) {
        this.fifteenForThirtyFragment = fifteenForThirtyFragment;
    }

    @Provides
    FifteenForThiryPresenter providesFifteenForThiryPresenter(){
        return new FifteenForThiryPresenter(fifteenForThirtyFragment);
    }
}
