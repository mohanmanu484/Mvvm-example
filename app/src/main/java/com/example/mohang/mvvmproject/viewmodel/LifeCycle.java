package com.example.mohang.mvvmproject.viewmodel;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by mohang on 5/7/17.
 */

public interface LifeCycle {

    interface View{
        Context getActivityContext();
        void showProgress();
        void hideProgress();
        void showError(Exception e);
        void showError(Throwable t);
        void showMessage(String message);
    }

    interface ViewModel{
        void onCreate();
        void onViewResumed();
        void onViewAttached(@NonNull LifeCycle.View viewCallback);
        void onViewDetached();
        void onDestroy();
    }
}
