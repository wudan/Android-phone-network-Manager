package com.wenhuabin.database;

import android.app.DatePickerDialog;
import android.content.Context;

public class MydatePickerDialog extends DatePickerDialog {

    public MydatePickerDialog(Context context, int theme,
                    OnDateSetListener callBack, int year, int monthOfYear,
                    int dayOfMonth) {
            super(context, theme, callBack, year, monthOfYear, dayOfMonth);
            
    }
     public MydatePickerDialog(Context context,
                OnDateSetListener callBack,
                int year,
                int monthOfYear,
                int dayOfMonth) {
             super(context, callBack, year, monthOfYear, dayOfMonth);
             setButton("ȷ��", this);
        }
}