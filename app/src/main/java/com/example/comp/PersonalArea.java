package com.example.comp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PersonalArea extends AppCompatActivity {

    TextView userName;
    TextView exitBtn;
    TextView phoneUser;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_area);

        userName = findViewById(R.id.nameUser);
        exitBtn = findViewById(R.id.btnExit);
        phoneUser = findViewById(R.id.phoneUser);

        sessionManager = new SessionManager(getApplicationContext());
        String txt_name = sessionManager.getNameUser();
        userName.setText(txt_name);
        String txt_phone = sessionManager.getPhoneUser();
        phoneUser.setText(txt_phone);

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("");
                builder.setMessage("Вы действительно хотите выйти из учетной записи?");
                builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sessionManager.setLogin(false);
                        sessionManager.setNameUser("");
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                });
                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}