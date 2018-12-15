package com.expense.tracker.models;

import com.orm.SugarRecord;

public class Transactions extends SugarRecord {
   private Integer transactionType;
   private String target;
   private String  source ;
   private String date;
   private Integer amount;
   private String remarks;

   public Transactions() {
   }

   public Transactions(Integer transactionType, String target, String source, String date, Integer amount, String remarks) {
      this.transactionType = transactionType;
      this.target = target;
      this.source = source;
      this.date = date;
      this.amount = amount;
      this.remarks = remarks;
   }

   public Integer getTransactionType() {
      return transactionType;
   }

   public void setTransactionType(Integer transactionType) {
      this.transactionType = transactionType;
   }

   public String getTarget() {
      return target;
   }

   public void setTarget(String target) {
      this.target = target;
   }

   public String getSource() {
      return source;
   }

   public void setSource(String source) {
      this.source = source;
   }

   public String getDate() {
      return date;
   }

   public void setDate(String date) {
      this.date = date;
   }

   public Integer getAmount() {
      return amount;
   }

   public void setAmount(Integer amount) {
      this.amount = amount;
   }

   public String getRemarks() {
      return remarks;
   }

   public void setRemarks(String remarks) {
      this.remarks = remarks;
   }
}
