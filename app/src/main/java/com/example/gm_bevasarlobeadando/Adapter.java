package com.example.gm_bevasarlobeadando;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Adapter extends ArrayAdapter<Termekek> {

    final List<Termekek> itemList;


    public Adapter(Context context, List<Termekek> items){
        super(context, R.layout.list_item, items);
        this.itemList = items;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Termekek item = itemList.get(position);

        TextView itemName = convertView.findViewById(R.id.item_name);
        TextView itemPrice = convertView.findViewById(R.id.item_price);
        TextView itemCount = convertView.findViewById(R.id.item_count);
        TextView itemUnit = convertView.findViewById(R.id.item_unit);

        Button modify = convertView.findViewById(R.id.modify);
/*
        modify.setOnClickListener(e -> {
            Intent intent = new Intent(Adapter.this.getContext(), .class);
            startActivity
        });
*/
        itemName.setText(item.getName());
        itemPrice.setText(item.getPricePerCount());
        itemCount.setText(String.valueOf(item.getCount()));
        itemUnit.setText(String.valueOf(item.getUnit()));

        return convertView;
    }
}
