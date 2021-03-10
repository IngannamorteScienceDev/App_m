package com.example.comp;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences("AppKey", 0);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public void setLogin(boolean login) {
        editor.putBoolean("KEY_LOGIN", login);
        editor.commit();
    }

    public boolean getLogin() {
        return sharedPreferences.getBoolean("KEY_LOGIN", false);
    }

    public void setNameUser(String txt_name) {
        editor.putString("KEY_USER_NAME", txt_name);
        editor.commit();
    }

    public void setPhoneUser(String txt_phone) {
        editor.putString("KEY_USER_PHONE", txt_phone);
        editor.commit();
    }

    public String getNameUser() {
        return sharedPreferences.getString("KEY_USER_NAME","");
    }

    public String getPhoneUser() {
        return sharedPreferences.getString("KEY_USER_PHONE","");
    }

}
