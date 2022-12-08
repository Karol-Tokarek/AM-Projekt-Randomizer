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

public class YesOrNotActivity extends AppCompatActivity {
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static void validateSth(int age) throws CheckException{
        if(age <0)
        {
            throw new CheckException("Oh no!", new Throwable());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yes_or_not);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.arrow);

        actionBar.setDisplayHomeAsUpEnabled(true);
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1000);
        rotate.setInterpolator(new LinearInterpolator());
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0F9D58"));

        actionBar.setBackgroundDrawable(colorDrawable);
        YesOrNotActivity.this.setTitle("TAK CZY NIE - Randomizer ++");
        ImageView imageViewDisplayYesorNot = (ImageView)findViewById(R.id.imageViewDisplayYesorNot);

        imageViewDisplayYesorNot.setBackground(null);

        Button buttonGenerateYesorNot = (Button) findViewById(R.id.buttonGenerateYesorNot);
        buttonGenerateYesorNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                ImageView imageViewDisplayYesorNot = (ImageView)findViewById(R.id.imageViewDisplayYesorNot);
                int ranNum = r.nextInt(2 - 1 + 1) ;

                if(ranNum == 1) {
                    imageViewDisplayYesorNot.setBackground(getDrawable(R.drawable.nie));
                  //  Toast.makeText(getApplicationContext(), "TO JEST NIE !", Toast.LENGTH_LONG).show();
                    imageViewDisplayYesorNot.startAnimation(rotate);
                    try {
                        validateSth(-1);
                    } catch (CheckException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    imageViewDisplayYesorNot.setBackground(getDrawable(R.drawable.tak));
                    //Toast.makeText(getApplicationContext(), "TO JEST TAK ! ", Toast.LENGTH_LONG).show();
                    imageViewDisplayYesorNot.startAnimation(rotate);

                }

            }
        });
    }
}