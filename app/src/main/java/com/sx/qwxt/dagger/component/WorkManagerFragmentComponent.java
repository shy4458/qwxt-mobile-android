package com.sx.qwxt.dagger.component;

import com.sx.qwxt.dagger.module.WorkManagerFragmentModule;
import com.sx.qwxt.view.fragment.workManager.WorkManagerFragment;

import dagger.Component;

/**
 * 作者：shy
 * 时间：2017/11/17 0017
 * 描述：
 */
@Component(modules = WorkManagerFragmentModule.class)
public interface WorkManagerFragmentComponent {
void in(WorkManagerFragment fragment);


}
