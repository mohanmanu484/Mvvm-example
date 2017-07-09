package com.example.mohang.mvvmproject.viewmodel.contract;

import com.example.mohang.mvvmproject.viewmodel.LifeCycle;

/**
 * Created by mohan on 16/02/17.
 */

public interface MovieContract {

    interface View extends LifeCycle.View {

        String getUserId();
    }

    interface ViewModel extends LifeCycle.ViewModel{

    }
}
