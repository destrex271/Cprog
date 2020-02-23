package com.example.cprog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void add(View v){
        EditText ar = (EditText)findViewById(R.id.editText2);
        EditText tr = (EditText)findViewById(R.id.editText);
        String dt = tr.getText().toString().trim();
        String dr = ar.getText().toString().trim();
        DbHelper c = new DbHelper(this);
        if(!c.addChord(dt,dr)){
            Toast.makeText(this, "Ah Sry!!", Toast.LENGTH_SHORT).show();
        }else{
            ar.setText("");
            tr.setText("");
            Toast.makeText(this, "Rocking!!", Toast.LENGTH_SHORT).show();
        }
    }

}
