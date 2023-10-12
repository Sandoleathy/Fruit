package com.example.fruits.end;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fruits.MainActivity;
import com.example.fruits.R;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<FruitData>{
    private Typeface font = null;
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
        if(font != null){
            textView.setTypeface(font);
        }
        if(MainActivity.screenWidth > 1100){
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            params.height = (int)(350 * (MainActivity.dpi / 160));
            imageView.setLayoutParams(params);
        }
        return view;
    }
    public void setFont(Typeface t){
        this.font = t;
    }

}
