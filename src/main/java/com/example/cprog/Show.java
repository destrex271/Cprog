package com.example.cprog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.DialogCompat;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Show extends AppCompatActivity {

    ArrayList<String> ar = new ArrayList<>();
    ArrayAdapter<String> at;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ListView vr = (ListView)findViewById(R.id.dc);
        at = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,ar);
        DbHelper db = new DbHelper(this);
        ArrayList<String> st = db.getAll();
        for(int i = 0;i<st.size();i++){
            ar.add(st.get(i).substring(st.get(i).indexOf("/")+1,st.get(i).lastIndexOf("/")).replace("/"," "));
        }
        vr.setAdapter(at);
        vr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i  = new Intent(Show.this,Song.class);
                i.putExtra("position",position);
                startActivity(i);
            }
        });
        vr.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder am = new AlertDialog.Builder(Show.this);
                final int pos = position;
                am.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DbHelper dh = new DbHelper(getApplicationContext());
                        ArrayList<String> t = dh.getAll();
                        String r = t.get(pos);
                        dh.del(Integer.valueOf(r.substring(0,r.indexOf("/")).trim()));
                        Toast.makeText(Show.this, "Deleted!", Toast.LENGTH_SHORT).show();
                        Intent i = Show.this.getIntent();
                        finish();
                        startActivity(i);
                    }
                });
                am.setNegativeButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Show.this,Change.class);
                        i.putExtra("id",pos);
                        finish();
                        startActivity(i);
                    }
                });
                am.setTitle("Choose an option");
                AlertDialog d = am.create();
                d.show();
                return true;
            }
        });
    }

}
