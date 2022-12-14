package com.example.addressbook;

public class Name {
    String key = "";
    String name = "";
    String email = "";
    String phone = "";
    String address = "";

    public Name(String key, String name, String email, String phone, String address){
        this.key = key;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
    public String getKey(){
        return key;
    }
}