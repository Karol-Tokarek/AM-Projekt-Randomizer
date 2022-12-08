package com.example.layout_project01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class DateTimeActivity extends AppCompatActivity {



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private static void validateDates(String datefrom, String dateto) throws CheckException{
        if(datefrom == null || dateto == null)
        {
            throw new CheckException("Oh no! Popraw daty!", new Throwable());
        }


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate dateTime = LocalDate.parse(datefrom, formatter);
            System.out.println("The string is a date and time: " + dateTime);

        }catch (Exception e)
        {
            throw new CheckException("Oh no! Popraw daty!", new Throwable());

        }

        try {
            LocalDate dateTime2 = LocalDate.parse(dateto, formatter);
            System.out.println("The string is a date and time: " + dateTime2);
        }catch(Exception e)
        {
            throw new CheckException("Oh no! Popraw daty!", new Throwable());

        }




    }
    public List<LocalDate> toDateList(String startDate, String endDate) {
        try {
            final LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            final LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            final long days = start.until(end, ChronoUnit.DAYS);
            return LongStream.rangeClosed(0, days)
                    .mapToObj(start::plusDays)
                    .collect(Collectors.toList());
        } catch(DateTimeException d)
        {
            Toast.makeText(getApplicationContext(), "ERROR: "+d.getMessage(), Toast.LENGTH_LONG).show();
            return null;

        } catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), "ERROR: "+e.getMessage(), Toast.LENGTH_LONG).show();
            return null;

        }

    }

    private static void validateFieldHowMuch(String howMuch) throws CheckException{
        if(howMuch == null || howMuch == " " || howMuch.isEmpty())
        {
            throw new CheckException("Oh no! Bad number howmuch!", new Throwable());
        }
        if(Integer.parseInt(String.valueOf(howMuch.toString())) == 0)
        {
            throw new CheckException("Oh no! Bad number howmuch!", new Throwable());

        }

    }


//    public static class DatePickerFragment extends DialogFragment
//            implements DatePickerDialog.OnDateSetListener {
//
//        public static String data = "";
//        @Override
//        public Dialog onCreateDialog(Bundle savedInstanceState) {
//            // Use the current date as the default date in the picker
//            final Calendar c = Calendar.getInstance();
//            int year = c.get(Calendar.YEAR);
//            int month = c.get(Calendar.MONTH);
//            int day = c.get(Calendar.DAY_OF_MONTH);
//            // Create a new instance of DatePickerDialog and return it
//            return new DatePickerDialog(requireContext(), this, year, month, day);
//            data = year + "-" + month + "-" + day;
//            gowno(data);
//        }
//
//        public void onDateSet(DatePicker view, int year, int month, int day) {
//            // Do something with the date chosen by the user
//            // EditText DateFrom=(EditText) findViewById(R.id.editDateFrom);
//             //Toast.makeText(requireContext(), year + "-", Toast.LENGTH_SHORT).show();
//             data = year + "-" + month + "-" + day;
//             gowno(data);
//
//            //Toast.makeText().setText(day + "-" + month + "-" + year);
//        }
//    }
//    public static void gowno(String x)
//    {
//        //EditText DateFrom=(EditText) findViewById(R.id.editTextDate);
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);
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
        DateTimeActivity.this.setTitle("DATA - Randomizer ++");
        EditText howMuchTimesInput =(EditText)findViewById(R.id.howMuchTimesInput);

        EditText DateFrom=(EditText)findViewById(R.id.editTextTime1);
        EditText DateTo=(EditText)findViewById(R.id.editTextTime2);
        Button buttonGenerateDate=(Button)findViewById(R.id.buttonGenerateTime);

      //  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
       // LocalDate parsed1 = LocalDate.parse(DateFrom.getText());
      //  LocalDate parsed2 = LocalDate.parse(DateFrom.getText());


        buttonGenerateDate.setOnClickListener(new View.OnClickListener() {


            public boolean contains(final int[] arr, final int key) {
                return Arrays.stream(arr).anyMatch(i -> i == key);
            }
            @Override
            public void onClick(View view) {
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
//                LocalDate date1 = LocalDate.parse(DateFrom.getText().toString(), formatter);
//                LocalDate date2 = LocalDate.parse(DateTo.getText().toString(), formatter);

                CheckBox checkedbox=(CheckBox)findViewById(R.id.checkBoxRepeat);

                if(checkedbox.isChecked()) {
//                    try {

                        String content = "";
//                        if(toDateList(DateFrom.getText().toString(), DateTo.getText().toString()) == null)
//                        {
//                            return;
//                        }
                   List<LocalDate> l;
                    try {
                        validateDates(DateFrom.getText().toString(), DateTo.getText().toString());
                        Toast.makeText(getApplicationContext(), toDateList(DateFrom.getText().toString(), DateTo.getText().toString()).toString(), Toast.LENGTH_SHORT).show();
                        l = toDateList(DateFrom.getText().toString(), DateTo.getText().toString());

                    } catch (CheckException e) {
                        Toast.makeText(getApplicationContext(), "POPRAW DATY!", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                        return;

                    }
                    try {
                        validateFieldHowMuch(String.valueOf(howMuchTimesInput.getText()));

                    } catch (CheckException e) {
                        Toast.makeText(getApplicationContext(), "POPRAW ILOSC DAT!", Toast.LENGTH_SHORT).show();

                        e.printStackTrace();
                        return;
                    }
                        for (int i = 0; i < Integer.parseInt(String.valueOf(howMuchTimesInput.getText())); i++) {
                            // Toast.makeText(getApplicationContext(), toDateList(DateFrom.getText().toString(), DateTo.getText().toString()).toString(), Toast.LENGTH_SHORT).show();
                            Random r = new Random();
                            int ranNum = r.nextInt(l.size());
                            content += l.get(ranNum) + ", ";
                        }
                        TextView txt = (TextView) findViewById(R.id.textViewDisplayDate);
                        txt.setText(content);
                        content = "";

//                    } catch (NumberFormatException e) {
//                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
//                        return;
//
//                    } catch (Exception e) {
//                        Toast.makeText(getApplicationContext(), "ZŁY ZAKRES", Toast.LENGTH_SHORT).show();
//
//                    } catch (Throwable e) {
//                        Toast.makeText(getApplicationContext(), "ZŁY ZAKRES", Toast.LENGTH_SHORT).show();
//
//                    }


                }
                else {
                    String content = "";
                    int[] tab;
                    List<LocalDate> l;
                    try {
                        validateDates(DateFrom.getText().toString(), DateTo.getText().toString());
                        l = toDateList(DateFrom.getText().toString(), DateTo.getText().toString());
                    } catch (CheckException e) {
                        Toast.makeText(getApplicationContext(), "POPRAW DATY!", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                        return;

                    }
                    try {
                        validateFieldHowMuch(String.valueOf(howMuchTimesInput.getText()));
                        tab = new int[Integer.parseInt(String.valueOf(howMuchTimesInput.getText()))];

                    } catch (CheckException e) {
                        Toast.makeText(getApplicationContext(), "POPRAW ILOSC DAT!", Toast.LENGTH_SHORT).show();

                        e.printStackTrace();
                        return;
                    }
                    try {
//                        if(toDateList(DateFrom.getText().toString(), DateTo.getText().toString()) == null)
//                        {
//                            return;
//                        }

                        for (int i = 0; i < Integer.parseInt(String.valueOf(howMuchTimesInput.getText())); i++) {
                            // Toast.makeText(getApplicationContext(), toDateList(DateFrom.getText().toString(), DateTo.getText().toString()).toString(), Toast.LENGTH_SHORT).show();
                            if(l.size() > Integer.parseInt(String.valueOf(howMuchTimesInput.getText()))) {
                                Random r = new Random();
                            int ranNum = r.nextInt(l.size());
                                while (contains(tab, ranNum)) {
                                    ranNum = r.nextInt(l.size());
                                }

                            tab[i] = ranNum;

                            String ranDate = l.get(ranNum).toString();
//                            while(content.contains(ranDate))
//                            {
//                                Random r = new Random();
//
//                                ranNum = r.nextInt(l.size());
//                            }
                            content += ranDate + ", ";
                            }else{
                                Toast.makeText(getApplicationContext(), "ZŁY ZAKRES, BĄDŹ LICZBA DAT!", Toast.LENGTH_SHORT).show();

                            }
                        }
                        TextView txt = (TextView) findViewById(R.id.textViewDisplayDate);

                        txt.setText(content);
                        content="";

                    } catch (NumberFormatException e) {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();

                    } catch (StringIndexOutOfBoundsException e) {
                        Toast.makeText(getApplicationContext(), "ZŁY ZAKRES", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();

                    } catch (InputMismatchException e) {
                        Toast.makeText(getApplicationContext(), "ZŁY ZAKRES, SPRAWDŹ POPRAWNOŚĆ DANYCH !", Toast.LENGTH_SHORT).show();
                        System.out.println("Invalid value!");
                        e.printStackTrace();


                    }
                }
                }





            });




        DateFrom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Date value = new Date();
                final Calendar cal = Calendar.getInstance();
                cal.setTime(value);
                new DatePickerDialog(DateTimeActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override public void onDateSet(DatePicker view,
                                                            int y, int m, int d) {
                                cal.set(Calendar.YEAR, y);
                                cal.set(Calendar.MONTH, m);
                                cal.set(Calendar.DAY_OF_MONTH, d);

                                int year = cal.get(Calendar.YEAR);
                                int month = cal.get(Calendar.MONTH);
                                int day = cal.get(Calendar.DAY_OF_MONTH);
                                EditText DateFrom=(EditText) findViewById(R.id.editTextTime1);
                                //Toast.makeText(requireContext(), year + "-", Toast.LENGTH_SHORT).show();
                                String formatted = String.format("%02d", month+1);
                                String formatted2 = String.format("%02d", day);

                                DateFrom.setText( year + "-" + formatted + "-" + formatted2);
                                // now show the time picker
//                                new TimePickerDialog(DateTime.this,
//                                        new TimePickerDialog.OnTimeSetListener() {
//                                            @Override public void onTimeSet(TimePicker view,
//                                                                            int h, int min) {
//                                                cal.set(Calendar.HOUR_OF_DAY, h);
//                                                cal.set(Calendar.MINUTE, min);
//                                            }
//                                        }, cal.get(Calendar.HOUR_OF_DAY),
//                                        cal.get(Calendar.MINUTE), true).show();
                            }
                        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show();





            }





        });
        DateTo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Date value = new Date();
                final Calendar cal = Calendar.getInstance();
                cal.setTime(value);
                new DatePickerDialog(DateTimeActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override public void onDateSet(DatePicker view,
                                                            int y, int m, int d) {
                                cal.set(Calendar.YEAR, y);
                                cal.set(Calendar.MONTH, m);
                                cal.set(Calendar.DAY_OF_MONTH, d);
                                int year = cal.get(Calendar.YEAR);
                                int month = cal.get(Calendar.MONTH);
                                int day = cal.get(Calendar.DAY_OF_MONTH);
                                String formatted = String.format("%02d", month+1);
                                String formatted2 = String.format("%02d", day);
                                EditText DateFrom=(EditText) findViewById(R.id.editTextTime2);
                                //Toast.makeText(requireContext(), year + "-", Toast.LENGTH_SHORT).show();
                                DateFrom.setText( year + "-" + formatted + "-" + formatted2);

                            }
                        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show();





            }





        });
        TextView txt = (TextView) findViewById(R.id.textViewDisplayDate);
        txt.setOnClickListener(new View.OnClickListener() {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

            @Override
            public void onClick(View view) {
                ClipData clipData = ClipData.newPlainText("textView", txt.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getApplicationContext(), "skopiowano do schowka", Toast.LENGTH_SHORT).show();
            }
        });

    }
}