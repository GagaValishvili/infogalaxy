package com.example.davaleba4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText name, surname, editEmail1, editPassword1;
    Button register, goBack;
    private FirebaseAuth myAuth2;

    public void goToLogin(View view){
        Intent goLogin = new Intent(Register.this, Login.class);
        startActivity(goLogin);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        myAuth2 = FirebaseAuth.getInstance();

        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        editEmail1 = findViewById(R.id.editEmail1);
        editPassword1 = findViewById(R.id.editPassword1);
        register = findViewById(R.id.register);
        goBack = findViewById(R.id.goback);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_back = new Intent(Register.this, Login.class);
                startActivity(go_back);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterOnApp();
            }
        });
    }
    private void RegisterOnApp() {
        String nameField = name.getText().toString();
        String surnameField = surname.getText().toString();
        String edit_email = editEmail1.getText().toString();
        String edit_password = editPassword1.getText().toString();


        if(TextUtils.isEmpty(nameField)) {
            Toast.makeText(Register.this, "გთხოვთ შეავსოთ ყველა ველი",Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(surnameField)) {
            Toast.makeText(Register.this, "გთხოვთ შეავსოთ ყველა ველი",Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(edit_email)) {
            Toast.makeText(Register.this, "გთხოვთ შეავსოთ ყველა ველი",Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(edit_password)){
            Toast.makeText(Register.this, "გთხოვთ შეავსოთ ყველა ველი",Toast.LENGTH_SHORT).show();
            return;
        } else if (edit_password.length() < 6) {
            Toast.makeText(Register.this, "პაროლის სიგრძე უნდა აღემატებოდეს 6-ს!",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Register.this,"გთხოვთ მოიცადოთ...",Toast.LENGTH_SHORT).show();
        }
        myAuth2.createUserWithEmailAndPassword(edit_email, edit_password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "თქვენ წარმატებით გაიარეთ რეგისტრაცია!", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(Register.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "თქვენ ვერ დარეგისტრირდით!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}