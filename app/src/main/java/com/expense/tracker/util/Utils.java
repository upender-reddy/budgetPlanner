package com.expense.tracker.util;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.expense.tracker.ExpenseApplication;
import com.orm.SugarApp;

public class Utils {

    public static boolean isNonEmpty(String str){
        return str!=null&&!str.isEmpty();
    }


}
