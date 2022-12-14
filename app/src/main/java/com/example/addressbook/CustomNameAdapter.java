package com.example.addressbook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomNameAdapter extends ArrayAdapter<Name> {

    private final Context context;
    private final ArrayList<Name> values;

    public CustomNameAdapter(@NonNull Context context, @NonNull ArrayList<Name> items) {
        super(context, -1, items);
        this.context = context;
        this.values = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.show_address_row, parent, false);

        TextView PName = rowView.findViewById(R.id.tvPersonName);
        TextView PEmail = rowView.findViewById(R.id.tvPersonEmail);
        TextView PPhone = rowView.findViewById(R.id.tvPhoneNumber);
        TextView PAddress = rowView.findViewById(R.id.tvPersonAddress);

        Name e = values.get(position);
        PName.setText(e.name);
        PEmail.setText(e.email);
        PPhone.setText(e.phone);
        PAddress.setText(e.address);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AddContact.class);
                i.putExtra("key", values.get(position).key);
                context.startActivity(i);
            }
        });

        return rowView;

    }
}
