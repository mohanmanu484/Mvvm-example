package com.example.mohang.mvvmproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.mohang.mvvmproject.ui.MvvmFragmment
import com.example.mohang.mvvmproject.viewmodel.BaseViewModel
import com.example.mohang.mvvmproject.viewmodel.MyViewModel
import com.example.mohang.mvvmproject.viewmodel.ViewModelHolder

class MainActivity : AppCompatActivity() {

    val MY_VIEW_MODEL_TAG = "MainActivity"

    var   myViewModel:BaseViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)

        Log.d(MY_VIEW_MODEL_TAG,"oncreate")
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        var mvvmFragment = fragmentManager.findFragmentById(R.id.container) ;

        myViewModel=findOrCreateViewModel();

        if (mvvmFragment == null) {
            mvvmFragment= MvvmFragmment();
            fragmentTransaction.add(R.id.container, mvvmFragment)
            fragmentTransaction.commit()
        }



    }



    override fun onRestart() {
        super.onRestart()
        Log.d(MY_VIEW_MODEL_TAG,"restart")
    }


    override fun onDestroy() {
        super.onDestroy()
        myViewModel?.onDestroy()
    }



    public fun findOrCreateViewModel(): MyViewModel? {

        var retainedViewModel = fragmentManager
                .findFragmentByTag(MY_VIEW_MODEL_TAG) as? ViewModelHolder<MyViewModel>

        if (retainedViewModel != null) {
            return retainedViewModel?.getViewmodel()
        } else {
            val viewModel = MyViewModel()
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.add(ViewModelHolder.createContainer(viewModel), MY_VIEW_MODEL_TAG)
            fragmentTransaction.commit()
            return viewModel
        }
    }


}
