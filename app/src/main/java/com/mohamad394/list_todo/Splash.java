package com.mohamad394.list_todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    //Change UI according to user data.
    public void updateUI(FirebaseUser account){

        if(account != null){

            startActivity(new Intent(this,Lists.class));

        }

    }


    public void OnClickNext(View view) {
        Intent intent=new Intent(Splash.this,Sign_Up.class);
        startActivity(intent);
        finish();
    }
    }
