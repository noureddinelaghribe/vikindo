package com.example.aslsignlanguage.Model;

public class Subscription {

    String tital;
    String dital;
    double price;
    String linkPay;
    int discount;

    public Subscription(String tital, String dital, double price, String linkPay, int discount) {
        this.tital = tital;
        this.dital = dital;
        this.price = price;
        this.linkPay = linkPay;
        this.discount = discount;
    }


    public Subscription() {}

    public String getTital() {
        return tital;
    }

    public void setTital(String tital) {
        this.tital = tital;
    }

    public String getDital() {
        return dital;
    }

    public void setDital(String dital) {
        this.dital = dital;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLinkPay() {
        return linkPay;
    }

    public void setLinkPay(String linkPay) {
        this.linkPay = linkPay;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
