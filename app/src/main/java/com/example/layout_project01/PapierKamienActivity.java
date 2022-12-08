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
import android.widget.TextView;

import java.util.Random;

public class PapierKamienActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_papier_kamien);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.arrow);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1000);
        rotate.setInterpolator(new LinearInterpolator());
        // Define ColorDrawable object and parse color

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0F9D58"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);

        PapierKamienActivity.this.setTitle("PAPIER, kamień... - Randomizer ++");


        ImageView imageViewDisplayRandomEl = (ImageView)findViewById(R.id.imageViewDisplayRandomEl);
        TextView textViewDisplayText = (TextView)findViewById(R.id.textViewDisplayText);


        Button button5 = (Button) findViewById(R.id.buttonGeneratePaperETC);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                int ranNum = r.nextInt(3) ;

                if(ranNum == 0) {
                    imageViewDisplayRandomEl.setBackground(getDrawable(R.drawable.papier1));
                     // Toast.makeText(getApplicationContext(), "TO JEST NIE1 !", Toast.LENGTH_LONG).show();
                    imageViewDisplayRandomEl.startAnimation(rotate);
                    textViewDisplayText.setText("PAPIER");

                }
                else if(ranNum == 1) {
                    imageViewDisplayRandomEl.setBackground(getDrawable(R.drawable.nozyce2));
                   //   Toast.makeText(getApplicationContext(), "TO JEST TAK 2! ", Toast.LENGTH_LONG).show();
                    imageViewDisplayRandomEl.startAnimation(rotate);
                    textViewDisplayText.setText("NOŻYCE");

                }
                else if(ranNum == 2) {
                    imageViewDisplayRandomEl.setBackground(getDrawable(R.drawable.kamien));
                     // Toast.makeText(getApplicationContext(), "TO JEST TAK3 ! ", Toast.LENGTH_LONG).show();
                    imageViewDisplayRandomEl.startAnimation(rotate);
                    textViewDisplayText.setText("KAMIEŃ");

                }

            }
        });




    }
}