package com.example.mohang.mvvmproject;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.mohang.mvvmproject.ui.MvvmFragmment;
import com.example.mohang.mvvmproject.ui.adapters.ActionHandler;

public class MainActivity extends AppCompatActivity implements ActionHandler{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm);
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container,new MvvmFragmment());
        fragmentTransaction.commit();



    }
}
