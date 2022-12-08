package com.example.layout_project01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Random;

class TimeIntervalHelper {  ///klasa odpowiadajaca za GENEROWANIE Z PRZEDZIAŁU ODPOWIEDNICH CZASÓW
    public static void generateTimeInterval(final LinkedList<String> intervals, final int startHour, final boolean isStartHalf, int endHour, final boolean isEndHalf) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR, startHour);
            calendar.set(Calendar.MINUTE, isStartHalf ? 1 : 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            //endHour = endHour == 12 ? 0 : endHour;
            while (calendar.get(Calendar.HOUR) != endHour)
                intervals.add(getInterval(calendar));
            intervals.add(getInterval(calendar));

            if (isEndHalf)
                intervals.add(getInterval(calendar));
        }
        catch (Exception e)
        {
            return;
        }
    }

    public static void generateTimeInterval(final LinkedList<String> intervals, final int startHour, int endHour) {
        generateTimeInterval(intervals, startHour, false, endHour+1, false);
    }

    private static String getInterval(final Calendar calendar) {
        @SuppressLint("DefaultLocale") final String interval = String.format(
                "%d.%02d",
                calendar.get(Calendar.HOUR) != 0 ? calendar.get(Calendar.HOUR) : 12,
                calendar.get(Calendar.MINUTE)
        );
        calendar.add(Calendar.MINUTE, 1);
        return interval;
    }
}
public class TimeStamp extends AppCompatActivity {
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public static int getRandomNumber(int min, int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_stamp);
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
        TimeStamp.this.setTitle("CZASY - Randomizer ++");

        EditText HOWMUCH =(EditText)findViewById(R.id.howMuchTimesInput);

        EditText DateFrom=(EditText)findViewById(R.id.editTextTime1);
        EditText DateTo=(EditText)findViewById(R.id.editTextTime2);
        Button buttonGenerateDate=(Button)findViewById(R.id.buttonGenerateTime);

        buttonGenerateDate.setOnClickListener(new View.OnClickListener() {


            public boolean contains(final int[] arr, final int key) {
                return Arrays.stream(arr).anyMatch(i -> i == key);
            }



            @Override
            public void onClick(View view) {

                try {
                    String time1 = DateFrom.getText().toString();
                    String time2 = DateTo.getText().toString();
                    CheckBox checkedbox = (CheckBox) findViewById(R.id.checkBoxRepeat);

                    if (checkedbox.isChecked()) {
                        //z potworzeniami czyli zostrawiamy
                        TextView txt = (TextView) findViewById(R.id.textViewDisplayDate);
                        DateTimeFormatter f = DateTimeFormatter.ofPattern("hh:mm", Locale.UK);
                        String[] time1split = time1.split(":");
                        String[] time2split = time2.split(":");
                        int hour1 = Integer.parseInt(time1split[0]);
                        int hour2 = Integer.parseInt(time2split[0]);

                        final LinkedList<String> intervals = new LinkedList<>();
                        TimeIntervalHelper.generateTimeInterval(intervals, hour1, hour2);

                        String content = "";
                        HashSet hs = new HashSet();

                        for (int i = intervals.indexOf(time1.replace(":", ".")); i < intervals.indexOf(time2.replace(":", ".")) + 1; i++) {
                            //content += intervals.get(i) + ", ";
                            hs.add(intervals.get(i));
                        }
                        String[] hs2 = (String[]) hs.toArray(new String[hs.size()]);
                        for (int i = 0; i < Integer.parseInt(String.valueOf(HOWMUCH.getText())); i++) {
                            Random r = new Random();
                            int ranNum = r.nextInt(hs.size());

                            content += hs2[ranNum] + ", ";
                        }
                        txt.setText(content);


                    } else {

                        ///bez powtorzen -> ZABEZPIECZYC
                        TextView txt = (TextView) findViewById(R.id.textViewDisplayDate);
                        DateTimeFormatter f = DateTimeFormatter.ofPattern("hh:mm", Locale.UK);
                        String[] time1split = time1.split(":");
                        String[] time2split = time2.split(":");
                        int hour1 = Integer.parseInt(time1split[0]);
                        int hour2 = Integer.parseInt(time2split[0]);

                        final LinkedList<String> intervals = new LinkedList<>();
                        TimeIntervalHelper.generateTimeInterval(intervals, hour1, hour2);

                        String content = "";
                        HashSet hs = new HashSet();
                        int[] tab = new int[1000];

                        for (int i = intervals.indexOf(time1.replace(":", ".")); i < intervals.indexOf(time2.replace(":", ".")) + 1; i++) {
                            //content += intervals.get(i) + ", ";
                            hs.add(intervals.get(i));
                        }
                        String[] hs2 = (String[]) hs.toArray(new String[hs.size()]);
                        for (int i = 0; i < Integer.parseInt(String.valueOf(HOWMUCH.getText())); i++) {
                            Random r = new Random();
                            int ranNum = r.nextInt(hs.size());
                            while (contains(tab, ranNum)) {
                                ranNum = r.nextInt(hs.size());
                            }
                            tab[i] = ranNum;
                            content += hs2[ranNum] + ", ";
                        }
                        txt.setText(content);

                    }
                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "COS POSZLO NIE TAK: "+e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }

        });



        DateFrom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Date value = new Date();
                final Calendar cal = Calendar.getInstance();
                cal.setTime(value);

                new TimePickerDialog(TimeStamp.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override public void onTimeSet(TimePicker view,
                                                            int h, int min) {
                                cal.set(Calendar.HOUR_OF_DAY, h);
                                cal.set(Calendar.MINUTE, min);
                                int hour = cal.get(Calendar.HOUR_OF_DAY);
                                int min2 = cal.get(Calendar.MINUTE);
                                String formatted2 = String.format("%02d", min2);

                                DateFrom.setText( hour + ":" + formatted2);

                            }
                        }, cal.get(Calendar.HOUR_OF_DAY),
                        cal.get(Calendar.MINUTE), true).show();
            }

        });

        DateTo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Date value = new Date();
                final Calendar cal = Calendar.getInstance();
                cal.setTime(value);
//
                                new TimePickerDialog(TimeStamp.this,
                                        new TimePickerDialog.OnTimeSetListener() {
                                            @Override public void onTimeSet(TimePicker view,
                                                                            int h, int min) {
                                                cal.set(Calendar.HOUR_OF_DAY, h);
                                                cal.set(Calendar.MINUTE, min);
                                                int hour = cal.get(Calendar.HOUR_OF_DAY);
                                                int min2 = cal.get(Calendar.MINUTE);
                                                String formatted2 = String.format("%02d", min2);

                                                DateTo.setText( hour + ":" + formatted2);
                                            }
                                        }, cal.get(Calendar.HOUR_OF_DAY),
                                        cal.get(Calendar.MINUTE), true).show();


            }
        });
        TextView textViewDisplayDate = (TextView) findViewById(R.id.textViewDisplayDate);
        textViewDisplayDate.setOnClickListener(new View.OnClickListener() {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

            @Override
            public void onClick(View view) {
                ClipData clipData = ClipData.newPlainText("textView", textViewDisplayDate.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getApplicationContext(), "skopiowano do schowka", Toast.LENGTH_SHORT).show();
            }
        });




    }
}