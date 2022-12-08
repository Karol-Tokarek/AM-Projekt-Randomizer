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
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PassActivity extends AppCompatActivity {
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public StringBuilder createNewPass(int length, boolean upperCase, boolean lowerCase, boolean numbers, boolean specialCharacters){

        String upperCaseZnaki = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseZnaki = "abcdefghijklmnopqrstuvwxyz";
        String numberZnaki = "0123456789";
        String specialZnaki = "!@#$%^&*()_-+=<>?/{}~|";
        //actionBar.setHomeAsUpIndicator(R.drawable.arrow);

        Random rn = new Random();
        StringBuilder sb = new StringBuilder(length);

        Random r = new Random();

        // int wybor = r.nextInt(4)+1;
        try {
            while (sb.length() < length && (upperCase == true || lowerCase == true || numbers == true || specialCharacters == true)) {
                if (upperCase) {
                    if (sb.length() < length) {
                        sb.append(upperCaseZnaki.charAt(rn.nextInt(upperCaseZnaki.length() - 1)));
                    }
                }


                if (lowerCase) {
                    if (sb.length() < length) {
                        sb.append(lowerCaseZnaki.charAt(rn.nextInt(lowerCaseZnaki.length() - 1)));
                    }
                }
                if (numbers) {
                    if (sb.length() < length) {

                        sb.append(numberZnaki.charAt(rn.nextInt(numberZnaki.length() - 1)));
                    }
                }
                if (specialCharacters) {
                    if (sb.length() < length) {

                        sb.append(specialZnaki.charAt(rn.nextInt(specialZnaki.length() - 1)));
                    }
                }


            }
            if (sb.length() > 1) {
                return sb;
            }
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "ERROR: "+e.getMessage(), Toast.LENGTH_LONG).show();
            return null;

        }
        return null;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass);
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
        PassActivity.this.setTitle("HASŁA - Randomizer ++");
        Spinner spinnerHowMuchSize = findViewById(R.id.spinnerHowMuchSize);
//create a list of items for the spinner.
        String[] items = new String[]{"4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        spinnerHowMuchSize.setAdapter(adapter);
        TextView textViewDisplayPass = (TextView) findViewById(R.id.textViewDisplayPass);

        Button button = (Button) findViewById(R.id.buttonGeneratePassword);
        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                CheckBox checkBoxLITERY = (CheckBox) findViewById(R.id.checkBoxLITERY);
                CheckBox checkboxlitery = (CheckBox) findViewById(R.id.checkBoxlitery);
                CheckBox checkboxCyfry = (CheckBox) findViewById(R.id.checkBoxCyfry);
                CheckBox checkboxZnaki = (CheckBox) findViewById(R.id.checkBoxZnaki);
                TextView textViewDisplayPass = (TextView) findViewById(R.id.textViewDisplayPass);

                boolean upperCase = checkBoxLITERY.isChecked();
                boolean lowerCase = checkboxlitery.isChecked();
                boolean numbers = checkboxCyfry.isChecked();
                boolean specialCharacters = checkboxZnaki.isChecked();
                int max_length = Integer.parseInt(spinnerHowMuchSize.getSelectedItem().toString());
                textViewDisplayPass.setText(createNewPass(max_length, checkBoxLITERY.isChecked(), checkboxlitery.isChecked(), checkboxCyfry.isChecked(), checkboxZnaki.isChecked()));

            }
        });



        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        textViewDisplayPass.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {

                ClipData clipData = ClipData.newPlainText("textView", textViewDisplayPass.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getApplicationContext(), "skopiowano do schowka", Toast.LENGTH_SHORT).show();
            }
            });



    }
}