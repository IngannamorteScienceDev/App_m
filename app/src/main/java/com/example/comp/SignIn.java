package com.example.comp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    //объявление и определение компонентов
    private EditText email_address;
    private EditText password_user;
    private CheckBox check;
    private Button back_btn;
    private Button nxt_btn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_signin);

        // Инициализация компонентов экрана
        email_address = findViewById(R.id.editTextEmailAddress);
        password_user = findViewById(R.id.editTextPassword);
        back_btn = findViewById(R.id.back_btn);
        nxt_btn = findViewById(R.id.next_btn);

        auth = FirebaseAuth.getInstance();

        // Поведения класса
        //Функция кнопки "Назад"
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = SignIn.this;
                Class nextActivity = MainActivity.class;

                Intent nextSignAct = new Intent(context, nextActivity);
                startActivity(nextSignAct);
                finish();
            }
        });

        //Функция авторизации
        nxt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String txt_email = email_address.getText().toString();
                final String txt_pass = password_user.getText().toString();

                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_pass)) {
                    Toast.makeText(SignIn.this, "Заполните поля!", Toast.LENGTH_SHORT).show();
                } else if (txt_pass.length() < 6) {
                    Toast.makeText(SignIn.this, "Пароль должен состоять не менее, чем из 6 символов!", Toast.LENGTH_SHORT).show();
                }
                auth.signInWithEmailAndPassword(txt_email, txt_pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(SignIn.this, "Успешно!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignIn.this, PersonalArea.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignIn.this, "Вы ввели неправильные данные!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

}

}