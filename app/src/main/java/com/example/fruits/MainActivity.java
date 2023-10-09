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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static int LANGUAGE_ENGLISH = 0;
    public static int LANGUAGE_CHINESE = 1;
    private String[] languageChoice = {"English","简体中文"};
    private static int languageMode = 0;
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
        TextView banana = findViewById(R.id.banana_text);
        TextView apple = findViewById(R.id.apple_text);
        TextView orange = findViewById(R.id.orange_text);

        banana.setTypeface(font);
        apple.setTypeface(font);
        orange.setTypeface(font);
        TextView title = findViewById(R.id.title);
        /*TextView spinner_text =findViewById(R.id.language_spinner);
        spinner_text.setTypeface(font);*/
        title.setTypeface(font);
        //init listener
        ImageView banana_image = findViewById(R.id.banana_image);
        ImageView apple_image = findViewById(R.id.apple_image);
        ImageView orange_image = findViewById(R.id.orange_image);

        banana_image.setOnClickListener(this);
        banana.setOnClickListener(this);
        apple_image.setOnClickListener(this);
        apple.setOnClickListener(this);
        orange_image.setOnClickListener(this);
        orange.setOnClickListener(this);

    }

    @Override
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
    }

    /**
     * The language change is too hard
     * there isn't too much text in the application...
     * so I will take the easy way out
     * It is funny...
     * But it JUST WORK!
     */
    public void changeLanguage(){
        TextView banana = findViewById(R.id.banana_text);
        TextView apple = findViewById(R.id.apple_text);
        TextView orange = findViewById(R.id.orange_text);
        TextView title = findViewById(R.id.title);
        if(languageMode == LANGUAGE_ENGLISH){
            //directly change the text
            banana.setText(R.string.banana);
            apple.setText(R.string.apple);
            orange.setText(R.string.orange);
            title.setText(R.string.app_name);
        }else{
            //directly change the text
            banana.setText("香蕉");
            apple.setText("绿苹果");
            orange.setText("橘子");
            title.setText("水果！");
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