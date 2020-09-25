package com.example.movieuitemplate;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class Loading_Dialog {
        Activity activity;
        AlertDialog dialog;

        public Loading_Dialog(Activity myActivity) {
            activity=myActivity;
        }

        public void startLoading(){
            AlertDialog.Builder builder=new AlertDialog.Builder(activity);

            LayoutInflater inflater= activity.getLayoutInflater();
            builder.setView(inflater.inflate(R.layout.loading_dialog,null)) ;
            builder.setCancelable(false);

            dialog=builder.create();
            dialog.show();
        }

        public void dismissDialog(){
            dialog.dismiss();
        }
}
