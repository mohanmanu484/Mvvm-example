package com.example.mohang.mvvmproject.ui.adapters;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mohang.mvvm.RecyclerConfiguration;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by mohang on 3/7/17.
 */

public class RecyclerConfigurationImpl implements RecyclerConfiguration {

    private Context context;
    public RecyclerConfigurationImpl(Context context) {
        this.context=context;
    }



    @Override
    public boolean hasFixedSize() {
        return true;
    }



    @NotNull
    @Override
    public RecyclerView.LayoutManager layoutManager() {
        return new LinearLayoutManager(context);
    }

    @Nullable
    @Override
    public RecyclerView.ItemDecoration itemDecoration() {
        return null;
    }

    @NotNull
    @Override
    public RecyclerView.ItemAnimator itemAnimator() {
        return new DefaultItemAnimator();
    }
}
