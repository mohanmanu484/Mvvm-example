package com.example.mohang.mvvm

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView

/**
 * Created by mohang on 3/7/17.
 */

interface RecyclerConfiguration {


    fun layoutManager(): RecyclerView.LayoutManager

    fun hasFixedSize(): Boolean{
        return true
    }

    fun itemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }

    fun itemAnimator(): RecyclerView.ItemAnimator{
        return DefaultItemAnimator()
    }
}
