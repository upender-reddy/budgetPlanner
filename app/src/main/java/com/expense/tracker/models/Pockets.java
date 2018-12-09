package com.expense.tracker.models;

import com.orm.dsl.Table;
import com.orm.dsl.Unique;

@Table
public class Pockets {
    @Unique
    String pocket;

    public Pockets(String pocket) {
        this.pocket = pocket;
    }

    public Pockets() {
    }

    public String getPocket() {
        return pocket;
    }

    public void setPocket(String pocket) {
        this.pocket = pocket;
    }
}
