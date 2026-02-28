package com.example.codefesttest;

public class Products{

    private String prodID;
    private String prodName;
    private String price;
    private String prodDescription;

    public Products(String id, String name, String price, String desc){
        this.prodID = id;
        this.prodName = name;
        this.price = price;
        this.prodDescription = desc;
    }

    public String getProdID(){return prodID;}
    public String getProdName(){return prodName;}
    public String getPrice(){return price;}
    public String getProdDesc(){return prodDescription;}
}