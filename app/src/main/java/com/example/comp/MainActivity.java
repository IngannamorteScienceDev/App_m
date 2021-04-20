package com.example.comp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    //объявление и определение компонентов
    private TextView reg_btn;
    private Button sign_btn;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //callback func
        checkSessionUser();

        // Инициализация компонентов экрана
        reg_btn = findViewById(R.id.reg_btn);
        sign_btn = findViewById(R.id.sign_btn);



        // Поведения класса
        //Функция кнопки "Входа"
        sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = MainActivity.this;
                Class nextActivity = SignIn.class;

                Intent nextRegAct = new Intent(context, nextActivity);
                startActivity(nextRegAct);
                finish();
            }
        });
        //Функция кнопки "Регистрации"
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = MainActivity.this;
                Class nextActivity = Registration.class;

                Intent nextRegAct = new Intent(context, nextActivity);
                startActivity(nextRegAct);
                finish();
            }
        });
    }
    public void checkSessionUser(){
        sessionManager = new SessionManager(getApplicationContext());
        if(FirebaseAuth.getInstance().getCurrentUser() != null && (sessionManager.getLogin())){
            startActivity(new Intent(getApplicationContext(), MainFragment.class));
            finish();
        }else {sessionManager.setLogin(false);}
    }
}