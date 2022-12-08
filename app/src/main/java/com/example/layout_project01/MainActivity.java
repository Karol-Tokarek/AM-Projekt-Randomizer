package com.example.layout_project01;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0F9D58"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        MainActivity.this.setTitle("Randomizer ++");

        Button buttonNumbers = (Button) findViewById(R.id.buttonNumbers);
        buttonNumbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NumbersActivity.class);
                startActivity(intent);
            }
        });

        Button buttonYesOrNot = (Button) findViewById(R.id.buttonYesOrNot);
        buttonYesOrNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), YesOrNotActivity.class);
                startActivity(intent);
            }
        });
        Button buttonCoinGenerate = (Button) findViewById(R.id.buttonCoinGenerate);
        buttonCoinGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PickMoneySideActivity.class);
                startActivity(intent);
            }
        });

        Button buttonColorsGenerate = (Button) findViewById(R.id.buttonColorsGenerate);
        buttonColorsGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ColorsActivity.class);
                startActivity(intent);
            }
        });

        Button buttonPaperRockSciz = (Button) findViewById(R.id.buttonPaperRockSciz);
        buttonPaperRockSciz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PapierKamienActivity.class);
                startActivity(intent);
            }
        });

        Button buttonCubeGenerate = (Button) findViewById(R.id.buttonCubeGenerate);
        buttonCubeGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CubeKostkaActivity.class);
                startActivity(intent);
            }
        });

        Button buttonLettersGenerate = (Button) findViewById(R.id.buttonLettersGenerate);
        buttonLettersGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LiteryActivity.class);
                startActivity(intent);
            }
        });

        Button buttonTeamsGenerate = (Button) findViewById(R.id.buttonTeamsGenerate);
        buttonTeamsGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TeamsActivity.class);
                startActivity(intent);
            }
        });

        Button buttonOwnLists = (Button) findViewById(R.id.buttonOwnLists);
        buttonOwnLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OwnListsActivity.class);
                startActivity(intent);
            }
        });

        Button buttonCardsGenerate = (Button) findViewById(R.id.buttonCardsGenerate);
        buttonCardsGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CardsActivity.class);
                startActivity(intent);
            }
        });

        Button buttonCountriesGenerate = (Button) findViewById(R.id.buttonCountriesGenerate);
        buttonCountriesGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CountriesActivity.class);
                startActivity(intent);
            }
        });

        Button buttonStatesUSA = (Button) findViewById(R.id.buttonStatesUSA);
        buttonStatesUSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StanyUSAActivity.class);
                startActivity(intent);
            }
        });

        Button buttonDateTimeGenerate = (Button) findViewById(R.id.buttonDateTimeGenerate);
        buttonDateTimeGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DateTimeActivity.class);
                startActivity(intent);
            }
        });

        Button buttonTimeGenerate = (Button) findViewById(R.id.buttonTimeGenerate);
        buttonTimeGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TimeStamp.class);
                startActivity(intent);
            }
        });

        Button buttonPassGenerate = (Button) findViewById(R.id.buttonPassGenerate);
        buttonPassGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PassActivity.class);
                startActivity(intent);
            }
        });
    }






//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        switch(item.getItemId())
//        {
//            case R.id.o_mnie:
//                Toast.makeText(this, "O MNIE !", Toast.LENGTH_LONG).show();
//                return true;
//        }
//
//
//
//        return super.onContextItemSelected(item);
//    }

   // menu item select listener
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.o_mnie:
            Context cnt = getApplicationContext();
                Toast tst = Toast.makeText(cnt, "AUTOR: KAROL TOKAREK 4PT5", Toast.LENGTH_SHORT);
                tst.setGravity(Gravity.BOTTOM|Gravity.BOTTOM, 0, 0);
                tst.show();
            return true;
            case R.id.wyjscie:
                Context cnt3 = getApplicationContext();
                Toast tst3 = Toast.makeText(cnt3, "SIEMKA! WRACAJ SZYBKO!", Toast.LENGTH_SHORT);
                tst3.show();
                finish();
                return true;
            case R.id.info:
                Context cnt2 = getApplicationContext();
                Toast tst2 = Toast.makeText(cnt2, "INFO: 07.12.2022 - poprawki, refaktoryzacja kodu, dodanie obsługi niektórych aktywnosci", Toast.LENGTH_SHORT);
                tst2.setGravity(Gravity.BOTTOM|Gravity.BOTTOM, 0, 0);
                tst2.show();

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // you can set menu header with title icon etc
        inflater.inflate(R.menu.menu, menu);
        return true;
    }



    }


