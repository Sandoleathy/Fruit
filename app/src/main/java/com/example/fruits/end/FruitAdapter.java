package com.example.fruits.end;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fruits.R;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<FruitData> {
    public FruitAdapter(@NonNull Context context, int resource , @NonNull List<FruitData> objects) {
        super(context, resource , objects);
    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        FruitData fruit = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_list,parent,false);
        ImageView imageView = view.findViewById(R.id.fruit_image);
        TextView textView = view.findViewById(R.id.fruit_text);
        imageView.setImageResource(fruit.getImageID());
        textView.setText(fruit.getName());
        return view;
    }
}
