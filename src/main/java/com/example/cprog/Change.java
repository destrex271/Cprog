package com.example.cprog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Change extends AppCompatActivity {

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        i = getIntent().getIntExtra("id",0);
        DbHelper db = new DbHelper(this);
        ArrayList<String> allSongs = new ArrayList<>();
        allSongs = db.getAll();
        EditText nm = (EditText)findViewById(R.id.editText3);
        EditText cr = (EditText)findViewById(R.id.editText4);
        String song = allSongs.get(i);
        nm.setText(song.substring(song.indexOf("/")+1,song.lastIndexOf("/")));
        cr.setText(song.substring(song.lastIndexOf("/")+1));
    }

    public void ch(View v){

        EditText nm = (EditText)findViewById(R.id.editText3);
        EditText cr = (EditText)findViewById(R.id.editText4);
        DbHelper h = new DbHelper(getApplicationContext());
        ArrayList<String> t = h.getAll();
        String r = t.get(i);
        int z = Integer.valueOf(r.substring(0,r.indexOf("/")).trim());
        h.change(z,nm.getText().toString(),cr.getText().toString());
        Intent m = new Intent(this,MainActivity.class);
        Toast.makeText(this, "Updated!!", Toast.LENGTH_SHORT).show();
        finish();
        startActivity(m);
    }

}
