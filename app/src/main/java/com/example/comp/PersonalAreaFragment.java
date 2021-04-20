package com.example.comp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class PersonalAreaFragment extends Fragment {
    TextView userName;
    TextView exitBtn;
    TextView phoneUser;

    SessionManager sessionManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_area, container, false);
        userName = view.findViewById(R.id.nameUser);
        exitBtn = view.findViewById(R.id.btnExit);
        phoneUser = view.findViewById(R.id.phoneUser);
        setDataU();

        sessionManager = new SessionManager(getContext());

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                exitAcc(builder);
            }
        });

        return view;

    }
    //Функция выхода из учетной записи
    public void exitAcc(AlertDialog.Builder builder){
        builder.setTitle("");
        builder.setMessage(Html.fromHtml("<font color='#D4D4BD'>Вы действительно хотите выйти из учетной записи?</font>"));
        builder.setPositiveButton(Html.fromHtml("<font color='#D4D4BD'>Да</font>"), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sessionManager.setLogin(false);
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });
        builder.setNegativeButton(Html.fromHtml("<font color='#D4D4BD'>Отмена</font>"), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void setDataU(){
        FirebaseFirestore mFF = FirebaseFirestore.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String uid = user.getUid();
        mFF.collection("Users").document(uid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                userName.setText(document.getString("name"));
                                phoneUser.setText(document.getString("phone"));
                            }
                        }
                    }
                });
    }

}