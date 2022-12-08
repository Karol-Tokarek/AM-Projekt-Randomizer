package com.example.layout_project01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NewListCreate extends AppCompatActivity {
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static void validateName(String name) throws CheckException{
        if(name.length() < 1)
        {
            throw new CheckException("Oh no! Bad string", new Throwable());
        }



    }
    public String TEXT = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list_create);
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
        NewListCreate.this.setTitle("NOWA LISTA - Randomizer ++");


        Button buttonAddProperty = (Button) findViewById(R.id.buttonAddProperty);
        buttonAddProperty.setOnClickListener(new View.OnClickListener() {
            int i = 0;

            @Override
            public void onClick(View view) {

                EditText editTextAddOption = (EditText) findViewById(R.id.editTextAddOption);


                String text = editTextAddOption.getText().toString();
//                ScrollView scr = (ScrollView) findViewById(R.id.scrollView2);
                LinearLayout gr = (LinearLayout) findViewById(R.id.ln);

                try {
                    validateName(text);
                    TextView newTxt = new TextView(getApplicationContext());
                    i = i + 1;
                    newTxt.setText(i + ". " + text);
                    TEXT += text + ", ";
                    newTxt.setTextColor(Color.WHITE);
                    newTxt.setTextSize(20);
                    editTextAddOption.setText("");
                    gr.addView(newTxt);

                } catch (CheckException e) {
                    Toast.makeText(getApplicationContext(), "POPRAW NAZWĘ POZYCJI!", Toast.LENGTH_SHORT).show();

                    e.printStackTrace();
                }


            }


        });

        Button button15 = (Button) findViewById(R.id.button15);
        button15.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                /////SAVE BUTTON
                ///ZAPISZ DO PAMIĘCI, ABY MOZNA POZNIEJ WYBRAC TĄ LISTĘ..........................
                EditText editTextNameList = (EditText) findViewById(R.id.editTextNameList);
                String NAMETXT = editTextNameList.getText().toString();
                //check name exception
                try {
                    validateName(NAMETXT);
                } catch (CheckException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "POPRAW NAZWĘ LISTY!", Toast.LENGTH_SHORT).show();
                    return;

                }
                saveData();
             //   loadData();
                finish();
                NewListCreate.super.onBackPressed();
                ////zap[isanei do pamieci listy aby byla widoczna w innych activity




            }
        });

    }
    public void saveData()
    {
        EditText editTextNameList = (EditText) findViewById(R.id.editTextNameList);
        String NAMETXT = editTextNameList.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("PREFERENCES", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NAMETXT, NAMETXT+": "+TEXT);
        editor.apply();
        //editor.putBoolean()

    }
//    public void loadData(){
//        EditText NAME = (EditText) findViewById(R.id.editTextTextPersonName);
//        String NAMETXT = NAME.getText().toString();
//        SharedPreferences sharedPreferences = getSharedPreferences("PREFERENCES", MODE_PRIVATE );
//        String textDisplay = sharedPreferences.getString(NAMETXT, "");
//        Toast.makeText(getApplicationContext(), textDisplay, Toast.LENGTH_SHORT).show();
//
//    }
}