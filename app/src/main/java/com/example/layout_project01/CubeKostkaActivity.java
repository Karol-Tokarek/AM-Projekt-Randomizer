package com.example.layout_project01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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
import android.widget.Toast;

import java.util.HashSet;
import java.util.Random;

public class CubeKostkaActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_cube_kostka);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.arrow);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0F9D58"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        CubeKostkaActivity.this.setTitle("Rzuty kostką - Randomizer ++");


        Spinner spinnerHowMuchCubes = findViewById(R.id.spinnerHowMuchCubes);

        String[] items = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        spinnerHowMuchCubes.setAdapter(adapter);

        Spinner spinnerTypeCube = findViewById(R.id.spinnerTypeCube);
//create a list of items for the spinner.
        String[] items2 = new String[]{"D3", "D4", "D5", "D6", "D7", "D8", "D10", "D12"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
//set the spinners adapter to the previously created one.
        spinnerTypeCube.setAdapter(adapter2);

        Button throwCubeButton = (Button) findViewById(R.id.throwCubeButton);
        TextView displayMacro = (TextView)findViewById(R.id.displayMacro);

        throwCubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int howMuchCubes = Integer.parseInt(spinnerHowMuchCubes.getSelectedItem().toString());
               String typeCube = spinnerTypeCube.getSelectedItem().toString();
               typeCube = typeCube.replace("D", "");


               Random r = new Random();
               String content = "";
               try {
                   int sum = 0;
                   HashSet set = new HashSet();
                   for (int i = 0; i < howMuchCubes; i++) {

                       int randomNum = r.nextInt(Integer.parseInt(typeCube)) + 1;
                       set.add(randomNum);
                       if (i < howMuchCubes - 1) {
                           content += randomNum + " + ";
                           sum += randomNum;
                       } else {
                           content += randomNum;
                           sum += randomNum;
                       }


                   }
                   if (howMuchCubes != 1) {
                       content += " = " + sum;
                       displayMacro.setText(content);
                   } else {
                       displayMacro.setText(content);

                   }
               }catch (Exception e)
               {
                   Toast.makeText(getApplicationContext(), "ERROR: "+e.getMessage(), Toast.LENGTH_LONG).show();

               }



            }
        });



        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        displayMacro.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {

                ClipData clipData = ClipData.newPlainText("textView", displayMacro.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getApplicationContext(), "skopiowano do schowka", Toast.LENGTH_SHORT).show();
            }
        });
    }
}