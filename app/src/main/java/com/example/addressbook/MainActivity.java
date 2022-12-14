package com.example.addressbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button create;
    ListView listView;
    // Reference objects for handling event lists
    private ArrayList<Name> names = new ArrayList<Name>();
    private CustomNameAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        create = (Button) findViewById(R.id.createNewBtn);
        listView = findViewById(R.id.listViewNames);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddContact.class);
                startActivity(intent);
            }
        });
        LoadData();
    }

    private void LoadData() {
        names.clear();
        HandleDB db = new HandleDB(this);
        Cursor c = db.getAllKeyValues();

        while (c.moveToNext()){
            String key = c.getString(0);

            String name = c.getString(1);
            String email = c.getString(2);
            String phone = c.getString(3);
            String address = c.getString(4);

            Name e = new Name(key,name, email, phone, address);
            names.add(e);
        }


        db.close();
        adapter = new CustomNameAdapter(this, names);
        listView.setAdapter(adapter);
    }
}