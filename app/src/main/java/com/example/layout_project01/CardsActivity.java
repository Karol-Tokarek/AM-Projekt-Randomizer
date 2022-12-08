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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class CardsActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_cards);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.arrow);

        actionBar.setDisplayHomeAsUpEnabled(true);

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0F9D58"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        CardsActivity.this.setTitle("KARTY - Randomizer ++");

        Button buttonDrawCard = (Button) findViewById(R.id.buttonDrawCard);
        TextView numberTextViewCard = (TextView) findViewById(R.id.numberTextViewCard);
        ImageView CardImage = (ImageView) findViewById(R.id.CardImage);

        buttonDrawCard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String[] nr = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
                Random r = new Random();

                try {
                    int ranNum = r.nextInt(nr.length - 0) + 0;
                    numberTextViewCard.setText(nr[ranNum]);
                    int ranNum2 = r.nextInt(5) + 0;
                    switch (ranNum2) {
                        case 1:
                            CardImage.setBackground(getDrawable(R.drawable.serce));
                            numberTextViewCard.setTextColor(Color.RED);
                            break;
                        case 2:
                            CardImage.setBackground(getDrawable(R.drawable.zoladz));
                            numberTextViewCard.setTextColor(Color.BLACK);
                            break;
                        case 3:
                            CardImage.setBackground(getDrawable(R.drawable.romb));
                            numberTextViewCard.setTextColor(Color.RED);
                            break;
                        case 4:
                            CardImage.setBackground(getDrawable(R.drawable.karo));
                            numberTextViewCard.setTextColor(Color.BLACK);
                            break;
                    }
                } catch (NumberFormatException e)
                {
                    Toast.makeText(getApplicationContext(), "ERROR: "+e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                } catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "ERROR: "+e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                ////losuj symbol i numerek !!!
            }
        });

    }
}