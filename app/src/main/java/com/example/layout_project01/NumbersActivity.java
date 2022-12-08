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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;


public class NumbersActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static void validateMinMax(String min, String max) throws CheckException{
        if(Integer.parseInt(String.valueOf(max.toString())) < Integer.parseInt(String.valueOf(min.toString())))
        {
            throw new CheckException("Oh no! Bad number max and min!", new Throwable());
        }
        if(min == null || min == " " || min.isEmpty() || max == null || max == " " || max.isEmpty() )
        {
            throw new CheckException("Oh no! Bad number max and min!", new Throwable());


        }


    }

    private static void validateFieldHowMuch2(String howMuch) throws CheckException{
        if(howMuch == null || howMuch == " " || howMuch.isEmpty())
        {
            throw new CheckException("Oh no! Bad number howmuch!", new Throwable());
        }
        if(Integer.parseInt(String.valueOf(howMuch.toString())) == 0)
        {
            throw new CheckException("Oh no! Bad number howmuch!", new Throwable());

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        TextView textViewDisplayDate=(TextView)findViewById(R.id.textViewDisplayDate);
        actionBar.setHomeAsUpIndicator(R.drawable.arrow);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setHomeAsUpIndicator(R.drawable.arrow);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0F9D58"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        NumbersActivity.this.setTitle("LICZBY - RANDOMIZER ++");

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        textViewDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipData clipData = ClipData.newPlainText("textView", textViewDisplayDate.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getApplicationContext(), "skopiowano do schowka", Toast.LENGTH_SHORT).show();


            }
        });


        Button button = (Button) findViewById(R.id.buttonGenerateTime);



            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view){
                    // Toast.makeText(getApplicationContext(), "GENEROWANIE LICZB.........", Toast.LENGTH_SHORT).show();

                    TextView textViewDisplayDate = (TextView) findViewById(R.id.textViewDisplayDate);
                    CheckBox checkBoxPowtorzenia = (CheckBox) findViewById(R.id.checkBoxRepeat);

                    EditText howMuchEditText = (EditText) findViewById(R.id.howMuchTimesInput);
                    EditText from = (EditText) findViewById(R.id.editDateFrom);
                    EditText to = (EditText) findViewById(R.id.editTextTo);

                    String content = "";
                    int min = 0;
                    int max = 0;
                    try {
                        validateFieldHowMuch2(String.valueOf(howMuchEditText.getText()));
                    } catch (CheckException e) {
                        Toast.makeText(getApplicationContext(), "POPRAW LICZBĘ LICZB DO LOSOWANIA !", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                        return;
                    };
                    try {
                        min = Integer.parseInt(String.valueOf(from.getText()));
                        max = Integer.parseInt(String.valueOf(to.getText()));

                        validateMinMax(String.valueOf(min), String.valueOf(max));
                    } catch (CheckException e) {
                        Toast.makeText(getApplicationContext(), "POPRAW ZAKRES !", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                        return;

                    }
                    catch(NumberFormatException e)
                    {
                        Toast.makeText(getApplicationContext(), "POPRAW ZAKRES !", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                        return;
                    }
//                    if(Integer.parseInt(String.valueOf(howMuchEditText.getText())) == 0 || Integer.parseInt(String.valueOf(howMuchEditText.getText())) == 0) {                       Toast.makeText(getApplicationContext(), "POPRAW LICZBY", Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getApplicationContext(), "POPRAW LICZBY", Toast.LENGTH_SHORT).show();
//
//
//                        return;
//
//                    }
//                    if(max < min){
//                        Toast.makeText(getApplicationContext(), "POPRAW LICZBY", Toast.LENGTH_SHORT).show();
//                        return;
//
//                    }
                    Random r = new Random();
                    ArrayList<Integer> numbers = new ArrayList<Integer>();
                        if (!checkBoxPowtorzenia.isChecked()) {
                            if((max - min)+1 < Integer.parseInt(String.valueOf(howMuchEditText.getText()))) {
                                Toast.makeText(getApplicationContext(), "POPRAW LICZBY", Toast.LENGTH_SHORT).show();

                                return;

                            }

                            //declare a hash set
                            HashSet set = new HashSet();
                            int ranNum;

                            for (int i = 0; i < Integer.parseInt(String.valueOf(howMuchEditText.getText())); i++) {
                                try {
                                    ranNum = r.nextInt(max - min + 1) + min;
                                    do {
                                        ranNum = r.nextInt(max - min + 1) + min;
                                    } while (set.contains(ranNum));
                                    set.add(ranNum);
                                    if (set.size() < Integer.parseInt(String.valueOf(howMuchEditText.getText()))) {
                                        content += ranNum + ", ";

                                    } else {
                                        content += ranNum;

                                    }
                                }catch(Exception e)
                                {
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "ERROR !!!", Toast.LENGTH_SHORT).show();
                                }

                                }


                            }

                         else {
                            while (numbers.size() < Integer.parseInt(String.valueOf(howMuchEditText.getText()))) {

                                int randomNum = r.nextInt(max - min + 1) + min;
                                numbers.add(randomNum);
                                if (numbers.size() < Integer.parseInt(String.valueOf(howMuchEditText.getText()))) {
                                    content += randomNum + ", ";

                                } else {
                                    content += randomNum;

                                }
                            }

                        }



                    textViewDisplayDate.setText(content);


                }


            });


    }



}