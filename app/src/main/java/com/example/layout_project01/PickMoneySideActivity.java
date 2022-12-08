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
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class PickMoneySideActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_pick_money_side);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.arrow);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1000);
        rotate.setInterpolator(new LinearInterpolator());
        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0F9D58"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        PickMoneySideActivity.this.setTitle("ORZEŁ CZY RESZKA - RANDOMIZER ++");
        ImageView imageViewDisplayCoin = (ImageView)findViewById(R.id.imageViewDisplayCoin);
        imageViewDisplayCoin.setBackground(null);

        Button buttonGenerateMoneySide = (Button) findViewById(R.id.buttonGenerateMoneySide);
        buttonGenerateMoneySide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                ImageView imageViewDisplayCoin= (ImageView)findViewById(R.id.imageViewDisplayCoin);
                int ranNum = r.nextInt(2 - 1 + 1) ;

                if(ranNum == 1) {
                    imageViewDisplayCoin.setBackground(getDrawable(R.drawable.orzel));
                    //  Toast.makeText(getApplicationContext(), "TO JEST NIE !", Toast.LENGTH_LONG).show();
                    imageViewDisplayCoin.startAnimation(rotate);
                }
                else {
                    imageViewDisplayCoin.setBackground(getDrawable(R.drawable.reszka));
                    //  Toast.makeText(getApplicationContext(), "TO JEST TAK ! ", Toast.LENGTH_LONG).show();
                    imageViewDisplayCoin.startAnimation(rotate);

                }

            }
        });
    }
}