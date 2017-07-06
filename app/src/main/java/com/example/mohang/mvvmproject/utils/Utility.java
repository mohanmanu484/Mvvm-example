package com.example.mohang.mvvmproject.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

/**
 * Created by mohang on 5/7/17.
 */

public class Utility {

    public static final String TAG ="Utility";


    private static ProgressDialog progressDialog=null;


    public static void showProgress(Context context){
        Log.d(TAG, "////////////     showProgress: /////////////"
        );
        if(progressDialog!=null){
            progressDialog.dismiss();
            progressDialog=null;
        }
        progressDialog=new ProgressDialog(context);
        progressDialog.show();
    }

    public static void hideProgress(){
        Log.d(TAG, "///////////////// hideProgress: ////////////"
        );
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
        progressDialog=null;
    }

}
