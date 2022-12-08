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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

public class TeamsActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_teams);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        SharedPreferences sharedPreferences = getSharedPreferences("CHOSEN_ELEMENT", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("", "");
        sharedPreferences.edit().remove("").commit();

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
        TeamsActivity.this.setTitle("DRUŻYNY - Randomizer ++");

        Spinner dropdown = findViewById(R.id.spinnerHowMuchTeams);


//create a list of items for the spinner.
        String[] items = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

        ////get api
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);


        Button buttonCreateNewList = (Button) findViewById(R.id.buttonCreateNewList);
        buttonCreateNewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewListCreate.class);
                startActivity(intent);




            }


        });
        TextView displayTeamsTxt = (TextView) findViewById(R.id.textCreatedTeams);

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        displayTeamsTxt.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View view) {

                    ClipData clipData = ClipData.newPlainText("textView", displayTeamsTxt.getText().toString());
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(getApplicationContext(), "skopiowano do schowka", Toast.LENGTH_SHORT).show();


            }
        });
        Button button12 = (Button) findViewById(R.id.buttonCreateTeams);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /////UTWORZ DRUZYNY !!!!

                TextView displayTeamsTxt = (TextView) findViewById(R.id.textCreatedTeams);

                Spinner spinnerHowMuchTeams = findViewById(R.id.spinnerHowMuchTeams);
                String selected = (String) spinnerHowMuchTeams.getSelectedItem().toString();
                int selected2 = (Integer.parseInt(selected));
                String content = "";
                String content2 = "";
                try {
                    SharedPreferences sharedPreferences = getSharedPreferences("CHOSEN_ELEMENT", MODE_PRIVATE);
                    String textDisplay = sharedPreferences.getString("", "");
                    String[] txtafterSplit = textDisplay.split(":");
                    String[] personsToRandom = txtafterSplit[1].split(",");
                    HashSet set = new HashSet();
                    int licznik = 0;
                    Map<String, String> map = new HashMap<String, String>();


                    for (int j = 0; j < personsToRandom.length - 1; j++) {

                        if (!set.contains(j)) {
                            for (int i = 1; i < selected2 + 1; i++) {
                                if (licznik < personsToRandom.length - 1) {
                                    Random r = new Random();
                                    int nm = r.nextInt(personsToRandom.length - 1);
                                    while (set.contains(nm)) {
                                        nm = r.nextInt(personsToRandom.length - 1);
                                    }
                                    for (Map.Entry<String, String> pair : map.entrySet()) {
                                        //System.out.format("key: %s, value: %d%n", pair.getKey(), pair.getValue());
                                        content2 += pair.getValue();
                                    }
                                    while (content2.contains(personsToRandom[nm])) {

                                        nm = r.nextInt(personsToRandom.length - 1);

                                    }
                                    map.put("Team" + i + ": ", map.getOrDefault("Team" + i + ": ", "") + personsToRandom[nm] + ", ");
                                    set.add(nm);
                                    licznik++;
                                }

                            }
                        }
                        set.clear();

                    }
                    ArrayList<String> list = new ArrayList<>();
                    for (Map.Entry<String, String> pair : map.entrySet()) {
                        //System.out.format("key: %s, value: %d%n", pair.getKey(), pair.getValue());
                        //  content += pair.getKey() + pair.getValue() + " \n\n";
                        list.add(pair.getKey() + pair.getValue() + " \n\n");

                    }
                    Collections.sort(list, new Comparator<String>() {
                        public int compare(String str, String str1) {
                            return (str).compareTo(str1);
                        }
                    });

//                    for (int i = 1; i < selected2 + 1; i++) {
//                        content += "DRUŻYNA " + i + ": \n ";
//
//                        Random r = new Random();
//                        int nm = r.nextInt(personsToRandom.length - 1);
//                        while (set.contains(nm)) {
//                            nm = r.nextInt(personsToRandom.length - 1);
//                        }
//                        set.add(nm);
//                        content += personsToRandom[nm];
//
//                        content += "\n";
////                    content += txtafterSplit[1];
//
//                    }
                    for (int i = 0; i < list.size(); i++) {
                        content += list.get(i);
                    }
                    displayTeamsTxt.setText(content);


                }catch (Exception e)
                {
                    Toast.makeText(TeamsActivity.this, "ERROR, WYBIERZ LISTĘ  !", Toast.LENGTH_SHORT).show();

                }
            }

        });
        String[] val;
        Button buttonLoadPlayers = (Button) findViewById(R.id.buttonLoadPlayers);
        buttonLoadPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///ZALADUJ SHAREDPREFFERENCES !
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


                    AlertDialog.Builder builder = new AlertDialog.Builder(TeamsActivity.this);
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
                            Toast.makeText(TeamsActivity.this, "TWÓJ WYBÓR: " + selected + " ,TRWA ŁADOWANIE LISTY.....", Toast.LENGTH_SHORT).show();
                            TextView listsview = (TextView) findViewById(R.id.ListsView);
                            listsview.setText(selected);
                            SharedPreferences sharedPreferences = getSharedPreferences("CHOSEN_ELEMENT", MODE_PRIVATE);
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
                    Toast.makeText(TeamsActivity.this, "ERROR, STWÓRZ LISTY !", Toast.LENGTH_SHORT).show();

                }
            }




        });
    }
}