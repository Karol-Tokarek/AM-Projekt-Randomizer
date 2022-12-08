package com.example.layout_project01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;

public class LiteryActivity extends AppCompatActivity {
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_litery);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.arrow);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0F9D58"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        LiteryActivity.this.setTitle("litery  - Randomizer ++");
        TextView textViewDisplayLetter = (TextView)findViewById(R.id.textViewDisplayLetter);


        String[] lettersPl = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "v", "w", "x", "y", "z", "ó", "ą", "ć", "ę", "ł", "ń", "ś", "ź", "ż"};
        String[] lettersDe = new String[]{"A", "Ä", "B", "C", "D", "E", "F", "G", "H", "I","J" ,"K","L","M" ,"N", "O", "Ö", "P", "Q", "R", "S", "ß", "T", "U", "Ü", "V", "W", "X", "Y", "Z"};
        Character[] lettersEn = new Character[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

        Spinner spinnerSetAlphabet = findViewById(R.id.spinnerSetAlphabet);
//create a list of items for the spinner.
        String[] items = new String[]{"niemiecki", "polski", "angielski"};

        ////get api
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        spinnerSetAlphabet.setAdapter(adapter);



        Button buttonGenerateLetter = (Button) findViewById(R.id.buttonGenerateLetter);
        buttonGenerateLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner dropdown = findViewById(R.id.spinnerSetAlphabet);

                if(dropdown.getSelectedItemId() == 0) {
                    Random r = new Random();
                    int ranNum = r.nextInt(lettersDe.length - 0);
                    textViewDisplayLetter.setText(lettersDe[ranNum]);


                }
                if(dropdown.getSelectedItemId() == 1) {
                    Random r = new Random();
                    int ranNum = r.nextInt(lettersPl.length - 0);
                    textViewDisplayLetter.setText(lettersPl[ranNum]);


                }
                if(dropdown.getSelectedItemId() == 2) {
                    Random r = new Random();
                    int ranNum = r.nextInt(lettersEn.length - 0);
                    textViewDisplayLetter.setText(lettersEn[ranNum].toString());


                }
            }

        });










    }
}