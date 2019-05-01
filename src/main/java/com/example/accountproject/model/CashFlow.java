package com.example.accountproject.model;

import java.text.DateFormat;
import java.util.Date;

public class CashFlow {

    public static int CREDIT = 1;
    public static int DEBIT = 2;

    private Date mvtDate;
    private double amount;
    private int type;
    private long accountNo;

    public Date getMvtDate() {
        return mvtDate;
    }

    public void setMvtDate(Date mvtDate) {
        this.mvtDate = mvtDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        StringBuffer buff = new StringBuffer();
        buff.append("-----CashFlow-----)\n");
        buff.append("Account no=" + this.accountNo + "\n");
        if (this.mvtDate != null) {
            buff.append("Mouvement Date= "
                    + DateFormat.getDateInstance().format(this.mvtDate)
                    + "\n");
        } else {
            buff.append("No Mouvement date was set\n");
        }
        buff.append("Mouvement Amount=" + this.amount + "\n");
        buff.append("-----CashFlow end--)");
        return buff.toString();
    }
}
