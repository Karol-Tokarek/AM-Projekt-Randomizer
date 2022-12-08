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
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class StanyUSAActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_stany_usa);
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
        StanyUSAActivity.this.setTitle("STANY USA - Randomizer ++");
        TextView textViewDisplayCountry = (TextView) findViewById(R.id.textViewDisplayCountry);

        String[] states = new String[]{"AK - Alaska", "AL - Alabama",
                "AR - Arkansas",
                "AS - American Samoa",
                "AZ - Arizona",
                "CA - California",
                "CO - Colorado",
                "CT - Connecticut",
                "DC - District of Columbia",
                "DE - Delaware",
                "FL - Florida",
                "GA - Georgia",
                "GU - Guam",
                "HI - Hawaii",
                "IA - Iowa",
                "ID - Idaho",
                "IL - Illinois",
                "IN - Indiana",
                "KS - Kansas",
                "KY - Kentucky",
                "LA - Louisiana",
                "MA - Massachusetts",
                "MD - Maryland",
                "ME - Maine",
                "MI - Michigan",
                "MN - Minnesota",
                "MO - Missouri",
                "MS - Mississippi",
                "MT - Montana",
                "NC - North Carolina",
                "ND - North Dakota",
                "NE - Nebraska",
                "NH - New Hampshire",
                "NJ - New Jersey",
                "NM - New Mexico",
                "NV - Nevada",
                "NY - New York",
                "OH - Ohio",
                "OK - Oklahoma",
                "OR - Oregon",
                "PA - Pennsylvania",
                "PR - Puerto Rico",
                "RI - Rhode Island",
                "SC - South Carolina",
                "SD - South Dakota",
                "TN - Tennessee",
                "TX - Texas",
                "UT - Utah",
                "VA - Virginia",
                "VI - Virgin Islands",
                "VT - Vermont",
                "WA - Washington",
                "WI - Wisconsin",
                "WV - West Virginia",
                "WY - Wyoming"};
        Button buttonGenerateCountry = (Button) findViewById(R.id.buttonGenerateCountry);
        buttonGenerateCountry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                TextView textViewDisplayCountry = (TextView) findViewById(R.id.textViewDisplayCountry);
                try {
                    Random r = new Random();
                    int ranNum = r.nextInt(states.length - 0);
                    textViewDisplayCountry.setText(states[ranNum]);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    return;
                }

            }



        });
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        textViewDisplayCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipData clipData = ClipData.newPlainText("textView", textViewDisplayCountry.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getApplicationContext(), "skopiowano do schowka", Toast.LENGTH_SHORT).show();


            }
        });
    }
}