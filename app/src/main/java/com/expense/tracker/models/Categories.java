package com.expense.tracker.models;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;

public class Categories extends SugarRecord {
    @Unique
    String category;
    @Ignore
    Integer amount;

    public Categories() {
    }

    public Categories(String category) {
        this.category = category;
    }

    public Categories(String category, Integer amount) {
        this.category = category;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
