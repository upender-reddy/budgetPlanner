package com.expense.tracker.models;

import com.orm.SugarRecord;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;

public class Pockets extends SugarRecord {
    @Unique
    private String pocket;
    private boolean spendable;

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

    public boolean isSpendable() {
        return spendable;
    }

    public void setSpendable(boolean spendable) {
        this.spendable = spendable;
    }
}
