package com.example.excel.customer.model;

public class Customer {
    private CustomerType type;

    private int years;

    private int discount;

    // Standard getters and setters

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public enum CustomerType {
        INDIVIDUAL,
        BUSINESS;
    }

    public Customer(CustomerType type) {
        this.type = type;
    }

    public Customer(CustomerType type, int discount) {
        this.type = type;
        this.discount = discount;
    }
}
