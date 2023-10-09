package com.example.fruits;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fruits.end.FruitAdapter;
import com.example.fruits.end.FruitData;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static int LANGUAGE_ENGLISH = 0;
    public static int LANGUAGE_CHINESE = 1;
    private final String[] languageChoice = {"English","简体中文"};
    private static int languageMode = 0;
    private List<FruitData> fruitData_en;
    private List<FruitData> fruitData_zh;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ActionBar bar = getSupportActionBar();
        //hide the default tool bar
        if(bar != null){
            bar.hide();
        }
        //font
        Typeface font = Typeface.createFromAsset(getAssets(),"fonts/SmallCheese.ttf");
        //set language spinner
        Spinner language = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.item_select,languageChoice);
        language.setAdapter(adapter);
        language.setSelection(0);
        language.setOnItemSelectedListener(new LanguageSelectListener());
        //init font
        TextView title = findViewById(R.id.title);
        title.setTypeface(font);

        //init list_view
        fruitData_en = new ArrayList<>();
        fruitData_zh = new ArrayList<>();

        fruitData_en.add(new FruitData(R.drawable.banana , R.string.banana , R.string.banana_description));
        fruitData_en.add(new FruitData(R.drawable.green_apple , R.string.apple , R.string.apple_description));
        fruitData_en.add(new FruitData(R.drawable.orange , R.string.orange , R.string.orange_description));

        fruitData_zh.add(new FruitData(R.drawable.banana , R.string.banana_zh , R.string.banana_description_zh));
        fruitData_zh.add(new FruitData(R.drawable.green_apple , R.string.apple_zh , R.string.apple_description_zh));
        fruitData_zh.add(new FruitData(R.drawable.orange , R.string.orange_zh , R.string.orange_description_zh));

        changeLanguage();
        /*TextView banana = findViewById(R.id.banana_text);
        TextView apple = findViewById(R.id.apple_text);
        TextView orange = findViewById(R.id.orange_text);

        banana.setTypeface(font);
        apple.setTypeface(font);
        orange.setTypeface(font);

        //init listener
        ImageView banana_image = findViewById(R.id.banana_image);
        ImageView apple_image = findViewById(R.id.apple_image);
        ImageView orange_image = findViewById(R.id.orange_image);

        banana_image.setOnClickListener(this);
        banana.setOnClickListener(this);
        apple_image.setOnClickListener(this);
        apple.setOnClickListener(this);
        orange_image.setOnClickListener(this);
        orange.setOnClickListener(this);*/

    }

    /*@Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("language" , languageMode);
        if(view.getId() == R.id.banana_image || view.getId() == R.id.banana_text){
            //Toast.makeText(MainActivity.this , "banana" , Toast.LENGTH_SHORT).show();
            Intent toBanana = new Intent(this , Description.class);
            bundle.putInt("image",R.drawable.banana);
            toBanana.putExtras(bundle);
            startActivity(toBanana);
        }
        else if(view.getId() == R.id.apple_image || view.getId() == R.id.apple_text){
            //Toast.makeText(MainActivity.this , "apple" , Toast.LENGTH_SHORT).show();
            Intent toApple = new Intent(this , Description.class);
            bundle.putInt("image",R.drawable.green_apple);
            toApple.putExtras(bundle);
            startActivity(toApple);
        }
        else if(view.getId() == R.id.orange_image || view.getId() == R.id.orange_text){
            //Toast.makeText(MainActivity.this , "orange" , Toast.LENGTH_SHORT).show();
            Intent toOrange = new Intent(this , Description.class);
            bundle.putInt("image",R.drawable.orange);
            toOrange.putExtras(bundle);
            startActivity(toOrange);
        }
    }*/

    /**
     * The language change is too hard
     * there isn't too much text in the application...
     * so I will take the easy way out
     * It is funny...
     * But it JUST WORK!
     */
    public void changeLanguage(){
        ListView fruitList = findViewById(R.id.fruit_list);
        TextView title = findViewById(R.id.title);
        if(languageMode == LANGUAGE_ENGLISH){
            //directly change the text
            FruitAdapter adapter = new FruitAdapter(this,R.layout.item_list,fruitData_en);
            fruitList.setAdapter(adapter);
            title.setText(R.string.app_name);
        }else{
            //directly change the text
            FruitAdapter adapter = new FruitAdapter(this , R.layout.item_list , fruitData_zh);
            fruitList.setAdapter(adapter);
            title.setText(R.string.app_name_zh);
        }

    }

    class LanguageSelectListener implements AdapterView.OnItemSelectedListener{
        //listener
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if(languageChoice[i] == languageChoice[0]){
                //Toast.makeText(MainActivity.this , "English Mode" , Toast.LENGTH_SHORT).show();
                languageMode = LANGUAGE_ENGLISH;
                changeLanguage();
            }else if(languageChoice[i] == languageChoice[1]){
                //Toast.makeText(MainActivity.this , "中文模式" , Toast.LENGTH_SHORT).show();
                languageMode = LANGUAGE_CHINESE;
                changeLanguage();
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {}
    }

}