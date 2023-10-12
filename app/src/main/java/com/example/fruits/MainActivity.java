package com.example.fruits;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.fruits.end.FruitAdapter;
import com.example.fruits.end.FruitData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static int LANGUAGE_ENGLISH = 0;
    public static int LANGUAGE_CHINESE = 1;
    private final String[] languageChoice = {"English","简体中文"};
    private static int languageMode = 0;
    private List<FruitData> fruitData_en;
    private List<FruitData> fruitData_zh;
    public static float screenWidth = 0;
    public static float screenHeight = 0;
    public static float dpi = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        screenHeight = point.y;
        screenWidth = point.x;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        dpi = (int) (dm.density*160);

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

    }


    /**
     * The language change is too hard
     * there isn't too much text in the application...
     * so I will take the easy way out
     * It is funny...
     * But it JUST WORK!
     */
    public void changeLanguage(){
        Typeface font = Typeface.createFromAsset(getAssets(),"fonts/SmallCheese.ttf");
        ListView fruitList = findViewById(R.id.fruit_list);
        TextView title = findViewById(R.id.title);
        if(languageMode == LANGUAGE_ENGLISH){
            //directly change the text
            FruitAdapter adapter = new FruitAdapter(this,R.layout.item_list,fruitData_en);
            adapter.setFont(font);
            fruitList.setAdapter(adapter);
            title.setText(R.string.app_name);
        }else{
            //directly change the text
            FruitAdapter adapter = new FruitAdapter(this , R.layout.item_list , fruitData_zh);
            adapter.setFont(font);
            fruitList.setAdapter(adapter);
            title.setText(R.string.app_name_zh);
        }
        fruitList.setOnItemClickListener(this);

        if(screenWidth <= 500){
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) title.getLayoutParams();
            params.addRule(RelativeLayout.CENTER_HORIZONTAL, 0);
            title.setLayoutParams(params);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle bundle = new Bundle();
        bundle.putInt("language" , languageMode);
        if(i == 0){
            //Toast.makeText(MainActivity.this , "banana" , Toast.LENGTH_SHORT).show();
            Intent toBanana = new Intent(this , Description.class);
            bundle.putInt("image",R.drawable.banana);
            toBanana.putExtras(bundle);
            startActivity(toBanana);
        }
        else if(i == 1){
            //Toast.makeText(MainActivity.this , "apple" , Toast.LENGTH_SHORT).show();
            Intent toApple = new Intent(this , Description.class);
            bundle.putInt("image",R.drawable.green_apple);
            toApple.putExtras(bundle);
            startActivity(toApple);
        }
        else if(i == 2){
            //Toast.makeText(MainActivity.this , "orange" , Toast.LENGTH_SHORT).show();
            Intent toOrange = new Intent(this , Description.class);
            bundle.putInt("image",R.drawable.orange);
            toOrange.putExtras(bundle);
            startActivity(toOrange);
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