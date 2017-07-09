package com.example.mohang.mvvmproject.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohang.mvvmproject.R;

/**
 * Created by mohang on 7/7/17.
 */

public class TestFragment extends Fragment {


    public static final String TAG ="TestFragment";

    public TestFragment() {
        Log.d(TAG, "TestFragment: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_test,container,false);
    }
}
