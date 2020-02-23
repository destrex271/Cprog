package com.example.cprog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Song extends AppCompatActivity {

    public int y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        Intent x = this.getIntent();
        int m = x.getIntExtra("position",0);
        y = m;
        DbHelper db = new DbHelper(this);
        ArrayList<String> allSongs = new ArrayList<>();
        allSongs = db.getAll();
        String song = allSongs.get(y);
        TextView nm = (TextView)findViewById(R.id.textView2);
        TextView crd = (TextView)findViewById(R.id.textView3);
        nm.setText(song.substring(song.indexOf("/")+1,song.lastIndexOf("/")));
        crd.setText(song.substring(song.lastIndexOf("/")+1));
    }


}
