package com.example.mohang.mvvmproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.mohang.mvvmproject.ui.MvvmFragmment
import com.example.mohang.mvvmproject.viewmodel.BaseViewModel

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




}
