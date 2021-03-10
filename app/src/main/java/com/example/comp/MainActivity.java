package com.example.comp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //объявление и определение компонентов
    private TextView reg_btn;
    private Button sign_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация компонентов экрана
        reg_btn = findViewById(R.id.create_acc);
        sign_btn = findViewById(R.id.sign_btn);

        // Поведения класса
        //Функция кнопки "Входа"
        sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = MainActivity.this;
                Class nextActivity = SignIn.class;

                Intent nextSignAct = new Intent(context, nextActivity);
                startActivity(nextSignAct);
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
    
}