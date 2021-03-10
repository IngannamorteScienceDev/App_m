package com.example.comp;

public class User {
    private String id;
    private String txt_name, txt_email, txt_pass, txt_phone;

    public User(){}

    public User(String id, String name, String email, String pass, String phone) {

        this.id = id;
        this.txt_name = name;
        this.txt_email = email;
        this.txt_pass = pass;
        this.txt_phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return txt_name;
    }

    public void setName(String name) {
        this.txt_name = name;
    }

    public String getEmail() {
        return txt_email;
    }

    public void setEmail(String email) {
        this.txt_email = email;
    }

    public String getPass() {
        return txt_pass;
    }

    public void setPass(String pass) {
        this.txt_pass = pass;
    }

    public String getPhone() {
        return txt_phone;
    }

    public void setPhone(String phone) {
        this.txt_phone = phone;
    }
}
