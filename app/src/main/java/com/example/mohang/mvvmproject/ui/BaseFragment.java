package com.example.mohang.mvvmproject.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mohang.mvvmproject.utils.Utility;
import com.example.mohang.mvvmproject.viewmodel.LifeCycle;

public abstract class BaseFragment extends Fragment implements LifeCycle.View {

    public static final String TAG = "BaseFragment";

    public abstract LifeCycle.ViewModel getViewModel();

   // private static Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Log.d(TAG, "onCreate: "
        );
    }





    @Override
    public void onResume() {
        super.onResume();
        getViewModel().onViewResumed();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        getViewModel().onViewAttached(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getViewModel().onViewDetached();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getViewModel().onDestroy();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       // getViewModel().onDestroy();

        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void showError(Exception e) {
        Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        Log.d(TAG, "showError: ");
    }

    @Override
    public void showProgress() {
        Utility.showProgress(getActivity());
        Log.d(TAG, "showProgress: ");
    }

    @Override
    public void hideProgress() {
       Utility.hideProgress();
        Log.d(TAG, "hideProgress: ");
    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

        Log.d(TAG, "showMessage: ");
    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        Log.d(TAG, "showError: ");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}