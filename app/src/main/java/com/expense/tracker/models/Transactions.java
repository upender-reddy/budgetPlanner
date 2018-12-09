package com.expense.tracker.models;

import com.orm.SugarRecord;

public class Transactions extends SugarRecord {
   private Integer transactionType;
   private Integer target;
   private String  source ;
   private String targetCategory;
   private String date;
   private Integer amount;
   private String remarks;
}
