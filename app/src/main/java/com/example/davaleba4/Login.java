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

public class Login extends AppCompatActivity {
    EditText editEmail, editPassword;
    Button login, goToRegister;
    private FirebaseAuth myAuth;


    public void openRegisterPage(View view) {
        Intent openNextPage = new Intent(Login.this, Register.class);
        startActivity(openNextPage);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        myAuth = FirebaseAuth.getInstance();
        
        editEmail = findViewById(R.id.editEmail1);
        editPassword = findViewById(R.id.editPassword1);
        login = findViewById(R.id.login);
        goToRegister = findViewById(R.id.goRegister);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logInToAcc();
            }
        });
    }

    private void logInToAcc(){
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();


        if(TextUtils.isEmpty(email)){
            Toast.makeText(Login.this, "გთხოვთ შეავსოთ ყველა ველი",Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(Login.this, "გთხოვთ შეავსოთ ყველა ველი",Toast.LENGTH_SHORT).show();
            return;
        } else if(password.length() < 6) {
            Toast.makeText(Login.this, "პაროლის სიგრძე უნდა აღემატებოდეს 6-ს!",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Login.this,"გთხოვთ მოიცადოთ...",Toast.LENGTH_SHORT).show();
        }
        myAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(Login.this,"თქვენ წარმატებით გაიარეთ ავტორიზაცია!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this,"თქვენ ვერ გაიარეთ ავტორიზაცია!",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}
