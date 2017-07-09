package com.example.mohang.mvvmproject.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohang.mvvmproject.R;
import com.example.mohang.mvvmproject.databinding.FragmentMvvmBinding;
import com.example.mohang.mvvmproject.viewmodel.BaseViewModel;
import com.example.mohang.mvvmproject.viewmodel.LifeCycle;
import com.example.mohang.mvvmproject.viewmodel.MyViewModel;
import com.example.mohang.mvvmproject.viewmodel.contract.MovieContract;

/**
 * Created by mohang on 5/7/17.
 */

public class DifferentFragmment extends BaseFragment implements MovieContract.View{


    MyViewModel myViewModel=new MyViewModel();


    public DifferentFragmment() {

        Log.d(TAG, "MvvmFragmment: fgragment construtor");
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);

      //  myViewModel=((MainActivity)getActivity()).findOrCreateViewModel();



        FragmentMvvmBinding fragmentMvvmBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_mvvm,container,false);
        fragmentMvvmBinding.setModel(myViewModel);
       // myViewModel.onCreate();
        return fragmentMvvmBinding.getRoot();
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    @Override
    public LifeCycle.ViewModel getViewModel() {
        return myViewModel;
    }

    public void setViewModel(BaseViewModel myViewModel){
       // this.myViewModel=myViewModel;
    }

    @Override
    public String getUserId() {
        return null;
    }
}
