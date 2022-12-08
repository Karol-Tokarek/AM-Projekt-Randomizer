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
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class CountriesActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_countries);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.arrow);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0F9D58"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        CountriesActivity.this.setTitle("KRAJE ŚWIATA - Randomizer ++");

        TextView textViewDisplayCountry = (TextView) findViewById(R.id.textViewDisplayCountry);

        String[] country_list = new String[]{"Afganistan","Albania","Algieria","Andora","Angola","Anguilla","Antigua i Barbuda","Argentyna","Armenia"," Aruba","Australia","Austria","Azerbejdżan","Bahamy","Bahrajn","Bangladesz","Barbados","Białoruś","Belgia","Belize","Benin","Bermudy", "Bhutan","Boliwia","Bośnia i Hercegowina","Botswana","Brazylia","Brytyjskie Wyspy Dziewicze","Brunei","Bułgaria","Burkina Faso","Burundi","Kambodża", "Kamerun", "Wyspy Zielonego Przylądka", "Kajmany", "Czad", "Chile", "Chiny", "Kolumbia", "Kongo", "Wyspy Cooka", "Kostaryka", "Wybrzeże Kości Słoniowej","Chorwacja","Statek wycieczkowy","Kuba","Cypr","Czechy","Dania","Dżibuti","Dominika","Dominikana","Ekwador","Egipt","El Salwador","Gwinea Równikowa","Estonia","Etiopia","Falklandy","Wyspy Owcze","Fidżi","Finlandia","Francja","Polinezja Francuska","Francuskie Indie Zachodnie"," Gabon","Gambia","Gruzja","Niemcy","Ghana","Gibraltar","Grecja","Grenlandia","Grenada","Guam","Gwatemala","Guernsey","Gwinea" ,"Gwinea Bissau","Gujana","Haiti"," Honduras","Hongkong","Węgry","Islandia","Indie","Indonezja","Iran","Irak","Irlandia","Wyspa Man","Izrael","Włochy", "Jamajka","Japonia","Jersey","Jordania","Kazachstan","Kenia","Kuwejt","Republika Kirgiska","Laos","Łotwa","Liban","Lesotho"," Liberia","Libia","Liechtenstein","Litwa","Luksemburg","Makau","Macedonia","Madagaskar","Malawi","Malezja","Malediwy","Mali","Malta" ,"Mauretania","Mauritius","Meksyk","Mołdawia","Monako","Mongolia","Czarnogóra","Montserrat","Maroko","Mozambik","Namibia","Nepal"," Holandia","Antyle Holenderskie","Nowa Kaledonia","Nowa Zelandia","Nikaragua","Niger","Nigeria","Norwegia","Oman","Pakistan","Palestyna","Panama","Papua Nowa Gwinea", "Paragwaj", "Peru", "Filipiny", "Polska", "Portugalia", "Portoryko", "Katar", "Reunion", "Rumunia", "Rosja", "Rwanda","Święty Pierre &amp;", " Miquelon","Samoa","San Marino","Satelita","Arabia Saudyjska","Senegal","Serbia","Seszele","Sierra Leone","Singapur","Słowacja","Słowenia", "Republika Południowej Afryki", "Korea Południowa", "Hiszpania", "Sri Lanka", "St Kitts &amp; Nevis", "St Lucia", "St Vincent", "St. Lucia","Sudan","Surinam","Suazi","Szwecja","Szwajcaria","Syria","Tajwan","Tadżykistan","Tanzania","Tajlandia","Timor L'Este", "Togo", "Tonga", "Trynidad &amp; Tobago","Tunezja","Turcja","Turkmenistan","Turks &amp; Caicos","Uganda","Ukraina","Zjednoczone Emiraty Arabskie","Wielka Brytania","Urugwaj","Uzbekistan","Wenezuela","Wietnam","Wyspy Dziewicze (USA)","Jemen", "Zambia","Zimbabwe"};

        Button buttonGenerateCountry = (Button) findViewById(R.id.buttonGenerateCountry);
        buttonGenerateCountry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                TextView textView = (TextView) findViewById(R.id.textViewDisplayCountry);

                Random r = new Random();
                try {
                    int ranNum = r.nextInt(country_list.length - 0);
                    textView.setText(country_list[ranNum]);
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "ERROR (brak textView albo zły zakres losowania): "+e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        });
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        textViewDisplayCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipData clipData = ClipData.newPlainText("textView", textViewDisplayCountry.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getApplicationContext(), "skopiowano do schowka", Toast.LENGTH_SHORT).show();


            }
        });

    }
}