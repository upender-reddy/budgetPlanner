package com.expense.tracker.constants;

import java.util.ArrayList;
import java.util.Arrays;

public class Constants {
    public enum TransactionType
    {
        Expense, MoneyIn, MoneyOut, Transfer;

        public static ArrayList<String> transactionTypes() {
            TransactionType[] transactionTypes = values();
            ArrayList<String> names = new ArrayList<>(transactionTypes.length);

            for (int i = 0; i < transactionTypes.length; i++) {
                names.add( transactionTypes[i].name());
            }

            return names;
        }
    }



    public enum Target
    {
        Categories,Pockets;
    }
}
