package com.example.layout_project01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

public class OwnListsActivity extends AppCompatActivity {
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
        SharedPreferences sharedPreferences = getSharedPreferences("CHOSEN_ELEMENT2", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("", null);
        sharedPreferences.edit().remove("").commit();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_lists);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.arrow);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        // Define ColorDrawable object and parse color

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0F9D58"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        OwnListsActivity.this.setTitle("WŁASNE LISTY - Randomizer ++");

        Button buttonNewListCreate = (Button) findViewById(R.id.buttonNewListCreate);
        buttonNewListCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewListCreate.class);
                startActivity(intent);




            }


        });

        Button buttonLoadList = (Button) findViewById(R.id.buttonLoadList);
        buttonLoadList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOptionsDialog();





            }
            String selected = "";
            String[] options;
            String[] values;
            private void showOptionsDialog() {
                try {
                    SharedPreferences sharedPreferences = getSharedPreferences("PREFERENCES", MODE_PRIVATE);
                    Map<String, ?> keys = sharedPreferences.getAll();

                    int i = 0;
                    for (Map.Entry<String, ?> entry : keys.entrySet()) {
                        //options[i] = entry.getKey() + ": " + entry.getValue();
                        i++;
                    }  ///trzeba policzyc ile tego jest gowna

                    options = new String[i];  //zeby stworzyc odpowiednia dluosc talbicy ...
                    values = new String[i];  //zeby stworzyc odpowiednia dluosc talbicy ...

                    int j = 0;
                    for (Map.Entry<String, ?> entry : keys.entrySet()) { ///i wrzucic do opcji
                        //options[j] = entry.getKey() + ": " + entry.getValue();\
                        options[j] = entry.getKey();
                        values[j] = "" + entry.getValue();
                        j++;
                    }
                    selected = values[0];


                    AlertDialog.Builder builder = new AlertDialog.Builder(OwnListsActivity.this);
                    builder.setTitle("Wybierz listę:");

                    builder.setSingleChoiceItems(options, 0, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            selected = values[which];
                            //Toast.makeText(Teams.this, "Your just clicked: "+selected, Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setPositiveButton("WYBIERZ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //dialog.dismiss();
                            Toast.makeText(OwnListsActivity.this, "TWÓJ WYBÓR: " + selected + " ,TRWA ŁADOWANIE LISTY.....", Toast.LENGTH_SHORT).show();
                            TextView listsview = (TextView) findViewById(R.id.ListsView);
                            listsview.setText(selected);
                            SharedPreferences sharedPreferences = getSharedPreferences("CHOSEN_ELEMENT2", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("", selected);
                            editor.apply();

                            dialog.dismiss();


                        }
                    });
                    builder.setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });
                    builder.show();

                }catch (Exception e)
                {
                    Toast.makeText(OwnListsActivity.this, "ERROR, STWÓRZ LISTY !", Toast.LENGTH_SHORT).show();

                }
            }




        });

        Button buttonShuffleList = (Button) findViewById(R.id.buttonShuffleList);
        buttonShuffleList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    TextView resultListTxt = (TextView) findViewById(R.id.resultListTxt);
                    String content = "";
                    String content2 = "";
                    SharedPreferences sharedPreferences = getSharedPreferences("CHOSEN_ELEMENT2", MODE_PRIVATE);
                    String textDisplay = sharedPreferences.getString("", "");
                    String[] txtafterSplit = textDisplay.split(":");
                    String[] elementsList = txtafterSplit[1].split(",");
                    Random rand = new Random();
                    ArrayList<String> list = new ArrayList<String>();
                    for (int i = 0; i < elementsList.length - 1; i++) {
                        list.add(elementsList[i]);
                    }
                    Collections.shuffle(list);
                    for (int i = 0; i < list.size(); i++) {

                        content += list.get(i) + "\n";

                    }


                    resultListTxt.setText(content);


                }catch (Exception e)
                {
                    Toast.makeText(OwnListsActivity.this, "ERROR, WYBIERZ LISTĘ  !", Toast.LENGTH_SHORT).show();

                }
            }
        });

        Button buttonFindOne = (Button) findViewById(R.id.buttonFindOne);
        buttonFindOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    TextView resultList = (TextView) findViewById(R.id.resultListTxt);
                    String content = "";
                    String content2 = "";
                    SharedPreferences sharedPreferences = getSharedPreferences("CHOSEN_ELEMENT2", MODE_PRIVATE);
                    String textDisplay = sharedPreferences.getString("", "");
                    String[] txtafterSplit = textDisplay.split(":");
                    String[] elementsList = txtafterSplit[1].split(",");
                    Random rand = new Random();


                    resultList.setText(elementsList[rand.nextInt(elementsList.length - 1)]);
                }
                catch (Exception e)
                {
                    Toast.makeText(OwnListsActivity.this, "ERROR, WYBIERZ LISTĘ  !", Toast.LENGTH_SHORT).show();

                }



            }
        });


        TextView resultList = (TextView) findViewById(R.id.resultListTxt);

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        resultList.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View view) {

                ClipData clipData = ClipData.newPlainText("textView", resultList.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getApplicationContext(), "skopiowano do schowka", Toast.LENGTH_SHORT).show();


            }
        });

            }

}