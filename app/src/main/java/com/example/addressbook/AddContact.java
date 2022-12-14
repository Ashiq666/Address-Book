package com.example.addressbook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContact extends AppCompatActivity {

    Button save, cancel;
    EditText name, phone, email, address;
    String key = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        name = findViewById(R.id.ETName);
        email = findViewById(R.id.ETEmail);
        phone = findViewById(R.id.ETPhone);
        address = findViewById(R.id.ETAddress);
        save = (Button) findViewById(R.id.BSave);
        cancel = (Button) findViewById(R.id.BCancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddContact.this, MainActivity.class);
                startActivity(i);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String Phone = phone.getText().toString().trim();
                String Address = address.getText().toString().trim();;


                String errMsg = "";
                if ( Name.length() < 2){
                    errMsg += "Name is not valid";
                }
                if (Email.length() < 11){
                    errMsg += "Email is not valid";
                }
                if (Phone.length() < 11){
                    errMsg += "Phone is not valid";
                }
                if (Address.length() < 3){
                    errMsg += "Description is not valid";
                }

                if (errMsg.length() == 0){
                    String key, value;
                    key = Name+"--"+System.currentTimeMillis();
                    showDialog("Do you want to save save the event info?", "save info", "ok","back", key, Name, Email, Phone, Address);
                }
                else{
                    showDialog(errMsg,"Error", "ok", "back","" ,"" ,"","","");
                }
            }
        });

        Intent i = getIntent();
        key = i.getStringExtra("key");
        if (key != null){
            HandleDB keyvalue7 = new HandleDB(getApplicationContext());
            Cursor c = keyvalue7.getValueByKey(key);

            name.setText(c.getString(1));
            email.setText(c.getString(2));
            phone.setText(c.getString(3));
            address.setText(c.getString(4));

        }
    }
    private void showDialog(String message, String title, String btn1, String btn2, String key, String Name, String Email, String Phone, String Address){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(title);
        //setting message manually and performing action on btn click
        builder.setCancelable(false).setPositiveButton(btn1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Util.getInstance().deleteByKey(MainActivity.this, key);
                        dialog.cancel();
                        // loadData();
                        //  adapter.notifyDataSetChanged();
                        if (btn1 == "ok"){
                            HandleDB hdb = new HandleDB(getApplicationContext());
                            hdb.updateValueByKey(key,Name,Email,Phone,Address);

                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                        }

                    }
                })
                .setNegativeButton(btn2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}