package com.example.cprog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void go(View v){
        Intent i = new Intent(this,Add.class);
        startActivity(i);
    }


    public void show(View v){
        Intent i = new Intent(this,Show.class);
        startActivity(i);
    }

}
