package com.example.mohang.mvvmproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.mohang.mvvmproject.ui.MvvmFragmment
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.container, MvvmFragmment())
        fragmentTransaction.commit()


    }


    private fun <T> applySchedulers(): ObservableTransformer<T, T> {
        return mm as ObservableTransformer<T, T>
    }


    var mm: ObservableTransformer<*, *> =  object : ObservableTransformer<Any, Any> {

        override fun apply(upstream: Observable<Any>): ObservableSource<Any> {
            return upstream.apply { upstream.subscribeOn(Schedulers.io())
            upstream.observeOn(AndroidSchedulers.mainThread())
            }
        }



    }
}
