package com.expense.tracker.models;

import com.orm.dsl.Table;
import com.orm.dsl.Unique;

@Table
public class Categories{
    @Unique
    String category;

    public Categories() {
    }

    public Categories(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
