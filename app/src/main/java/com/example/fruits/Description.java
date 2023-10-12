package com.example.fruits;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class Description extends AppCompatActivity {
    public static int LANGUAGE_ENGLISH = 0;
    public static int LANGUAGE_CHINESE = 1;
    private String[] languageChoice = {"English","简体中文"};
    private int languageMode;
    private int imageID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        ActionBar bar = getSupportActionBar();
        //hide the default tool bar
        languageMode = LANGUAGE_ENGLISH;
        imageID = 0;
        if(bar != null){
            bar.hide();
        }
        ImageView image = findViewById(R.id.desc_img);

        Intent fromMain = getIntent();
        Bundle data = fromMain.getExtras();
        if(data != null){
            languageMode = data.getInt("language");
            image.setImageResource(data.getInt("image"));
            imageID = data.getInt("image");
        }
        //init spinner for language switcher
        Spinner language = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.item_select,languageChoice);
        language.setAdapter(adapter);
        language.setSelection(languageMode);
        language.setOnItemSelectedListener(new LanguageSelectListener());

        changeLanguage();
        //If the screen is too large, stretch the height of the image
        if(MainActivity.screenWidth > 1100){
            ViewGroup.LayoutParams params = image.getLayoutParams();
            params.height = (int)(350 * (MainActivity.dpi / 160));
            image.setLayoutParams(params);
        }
    }
    public void changeLanguage(){
        Typeface font = Typeface.createFromAsset(getAssets(),"fonts/SmallCheese.ttf");
        TextView title = findViewById(R.id.desc_name);
        TextView body = findViewById(R.id.desc_body);
        TextView head = findViewById(R.id.title);
        title.setTypeface(font);
        body.setTypeface(font);
        head.setTypeface(font);
        if(languageMode == LANGUAGE_ENGLISH){
            head.setText(R.string.app_name);
            if(imageID == R.drawable.banana){
                title.setText(R.string.banana);
                body.setText(R.string.banana_description);
            } else if (imageID == R.drawable.green_apple) {
                title.setText(R.string.apple);
                body.setText(R.string.apple_description);
            } else if(imageID == R.drawable.orange){
                title.setText(R.string.orange);
                body.setText(R.string.orange_description);
            }
        }else{
            head.setText(R.string.app_name_zh);
            if(imageID == R.drawable.banana){
                title.setText(R.string.banana_zh);
                body.setText(R.string.banana_description_zh);
            } else if (imageID == R.drawable.green_apple) {
                title.setText(R.string.apple_zh);
                body.setText(R.string.apple_description_zh);
            } else if(imageID == R.drawable.orange){
                title.setText(R.string.orange_zh);
                body.setText(R.string.orange_description_zh);
            }
        }
        //If the screen is too small, the title should be left to prevent overlap
        if(MainActivity.screenWidth <= 500){
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) head.getLayoutParams();
            params.addRule(RelativeLayout.CENTER_HORIZONTAL, 0);
            head.setLayoutParams(params);
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