package com.example.comp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {

    //объявление и определение компонентов
    private EditText email_address;
    private EditText first_lastName;
    private EditText phone_user;
    private EditText password_user;
    private CheckBox check;
    private Button back_btn;
    private Button nxt_btn;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_register);

        // Инициализация компонентов экрана
        email_address = findViewById(R.id.editTextEmailAddress);
        first_lastName = findViewById(R.id.first_last_name);
        phone_user = findViewById(R.id.editTextTextPhone);
        password_user = findViewById(R.id.editTextPassword);
        check = findViewById(R.id.checkBox);
        back_btn = findViewById(R.id.back_btn);
        nxt_btn = findViewById(R.id.next_btn);
        check = findViewById(R.id.checkBox);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        sessionManager = new SessionManager(getApplicationContext());


        // Поведения класса
        //Функция кнопки "Назад"
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = Registration.this;
                Class nextActivity = MainActivity.class;

                Intent nextSignAct = new Intent(context, nextActivity);
                startActivity(nextSignAct);
                finish();
            }
        });

        //Функция регистрации
        nxt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String txt_email = email_address.getText().toString();
                final String txt_name = first_lastName.getText().toString();
                final String txt_phone = phone_user.getText().toString();
                final String txt_pass = password_user.getText().toString();

                if (validateEmailAddress(email_address) && TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_pass)
                        || TextUtils.isEmpty(txt_phone) || TextUtils.isEmpty(txt_name)) {
                    Toast.makeText(Registration.this, "Заполните поля!", Toast.LENGTH_SHORT).show();

                } else if (txt_pass.length() < 6) {
                    Toast.makeText(Registration.this, "Пароль должен состоять не менее, чем из 6 символов!", Toast.LENGTH_SHORT).show();
                } else if (!check.isChecked()) {
                    Toast.makeText(Registration.this, "Вы не приняли соглашение!", Toast.LENGTH_SHORT).show();
                } else {
                    auth.createUserWithEmailAndPassword(txt_email, txt_pass)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    User user = new User();
                                    user.setEmail(txt_email);
                                    user.setName(txt_name);
                                    user.setPass(txt_pass);
                                    user.setPhone(txt_phone);

                                    users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(Registration.this, "Успешно", Toast.LENGTH_SHORT).show();
                                            sessionManager.setLogin(true);
                                            sessionManager.setNameUser(txt_name);
                                            sessionManager.setPhoneUser(txt_phone);
                                            startActivity(new Intent(getApplicationContext(), PersonalArea.class));
                                            finish();
                                        }
                                    });
                                }
                            });
                }
            }
        });
        if (sessionManager.getLogin()) {
            startActivity(new Intent(getApplicationContext(), PersonalArea.class));
        }
    }

    private boolean validateEmailAddress(EditText email_address) {
        String emailInput = email_address.getText().toString();
        if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            return true;
        } else {
            Toast.makeText(this, "invalid email!", Toast.LENGTH_SHORT).show(); return false;
        }
    }
}