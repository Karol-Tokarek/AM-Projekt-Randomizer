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
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class ColorsActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_colors);
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
        ColorsActivity.this.setTitle("KOLORY - RANDOMIZER ++");

        Button buttonGenerateColor = (Button) findViewById(R.id.buttonGenerateColor);
        Button buttonDisplayColor = (Button) findViewById(R.id.buttonDisplayColor);

        buttonGenerateColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                try {
                    int randNum = random.nextInt(16777215);
                    String szesnastkowe = "";
                    while (randNum != 0) {
                        if (randNum % 16 < 10)
                            szesnastkowe = Integer.toString(randNum % 16) + szesnastkowe;
                        else
                            szesnastkowe = (char) ((randNum % 16) + 55) + szesnastkowe;
                        randNum = randNum / 16;
                    }

                    buttonDisplayColor.setBackgroundColor(Color.parseColor("#" + ((szesnastkowe.length() < 6) ? String.format("%0" + (6 - szesnastkowe.length()) + "d", 0) : "") + szesnastkowe));
                    buttonDisplayColor.setText("#" + ((szesnastkowe.length() < 6) ? String.format("%0" + (6 - szesnastkowe.length()) + "d", 0) : "") + szesnastkowe);

                }catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(), "ERROR: "+e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }



        });
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        buttonDisplayColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipData clipData = ClipData.newPlainText("textView", buttonDisplayColor.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getApplicationContext(), "skopiowano do schowka", Toast.LENGTH_SHORT).show();


            }
        });
    }
}