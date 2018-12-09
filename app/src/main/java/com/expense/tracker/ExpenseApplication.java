package com.expense.tracker;

import android.app.Application;

import com.orm.SugarApp;
import com.orm.SugarContext;

public class ExpenseApplication extends SugarApp {
    private static ExpenseApplication expenseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        expenseApplication=this;

    }


    public static synchronized ExpenseApplication getInstance(){
        return expenseApplication;
    }
}
