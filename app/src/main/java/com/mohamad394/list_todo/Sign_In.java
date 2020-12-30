package com.mohamad394.list_todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_In extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText emailEt,passwordEt;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__in);
        mAuth=FirebaseAuth.getInstance();
        progressBar =(ProgressBar) findViewById(R.id.progress_bar);
        emailEt = (EditText) findViewById( R.id.edit_text_email_login);
        passwordEt =  (EditText) findViewById(R.id.edit_text_password_login);
    }

    public void dontHaveAccount(View view) {
        Intent intent=new Intent(Sign_In.this,Sign_Up.class);
        startActivity(intent);
        finish();
    }

    public void onClickSignIn(View view) {
        login();
    }

    private void login() {

        String email = emailEt.getText().toString().trim();
        String pass = passwordEt.getText().toString().trim();


        if (email.isEmpty()) {
            emailEt.setError("email is required !");
            emailEt.requestFocus();
            return;
        }
        if (pass.isEmpty()) {
            passwordEt.setError("password is required !");
            passwordEt.requestFocus();
            return;
        }
        if (pass.length()< 6) {
            passwordEt.setError("min password length should be 6 characters !");
            passwordEt.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEt.setError("Please provide valid email !");
            emailEt.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<AuthResult> task) {

                                                                                    if(task.isSuccessful()){
                                                                                        Toast.makeText(Sign_In.this, "login Successfully", Toast.LENGTH_LONG).show();
                                                                                        Intent intent=new Intent(Sign_In.this,Lists.class);
                                                                                        startActivity(intent);
                                                                                        Sign_In.this.finish();
                                                                                    }else{
                                                                                        progressBar.setVisibility(View.GONE);
                                                                                        Toast.makeText(Sign_In.this, "login failed", Toast.LENGTH_LONG).show();

                                                                                    }

                                                                                }
                                                                            }
        );
    }
}