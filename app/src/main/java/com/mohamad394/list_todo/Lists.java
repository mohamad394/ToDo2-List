package com.mohamad394.list_todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Lists extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
    }

    public void back(View view) {
        Intent intent=new Intent(Lists.this,Sign_In.class);
        startActivity(intent);
        finish();
    }

    public void OnClickCreateNewList(View view) {

    }

    public void logout(View view) {
        Intent intent=new Intent(Lists.this,Sign_In.class);
        startActivity(intent);
        finish();
    }
}