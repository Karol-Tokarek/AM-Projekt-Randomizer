package com.example.layout_project01;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.widget.Button;
import android.widget.Toast;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class UnitTests{

    public String validateName(String name){
        if(name.length() < 2)
        {
            return null;
        }
        return name;

    }

    public StringBuilder createNewPass(int length, boolean upperCase, boolean lowerCase, boolean numbers, boolean specialCharacters){

        String upperCaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseChars = "abcdefghijklmnopqrstuvwxyz";
        String numberChars = "0123456789";
        String specialChars = "!@#$%^&*()_-+=<>?/{}~|";
        //actionBar.setHomeAsUpIndicator(R.drawable.arrow);

        Random rn = new Random();
        StringBuilder sb = new StringBuilder(length);

        Random r = new Random();

        // int wybor = r.nextInt(4)+1;
        try {
            while (sb.length() < length && (upperCase == true || lowerCase == true || numbers == true || specialCharacters == true)) {
                if (upperCase) {
                    if (sb.length() < length) {
                        sb.append(upperCaseChars.charAt(rn.nextInt(upperCaseChars.length() - 1)));
                    }
                }


                if (lowerCase) {
                    if (sb.length() < length) {
                        sb.append(lowerCaseChars.charAt(rn.nextInt(lowerCaseChars.length() - 1)));
                    }
                }
                if (numbers) {
                    if (sb.length() < length) {

                        sb.append(numberChars.charAt(rn.nextInt(numberChars.length() - 1)));
                    }
                }
                if (specialCharacters) {
                    if (sb.length() < length) {

                        sb.append(specialChars.charAt(rn.nextInt(specialChars.length() - 1)));
                    }
                }


            }
            if (sb.length() > 1) {
                return sb;
            }
        }
        catch (Exception e)
        {
//            Toast.makeText(getApplicationContext(), "ERROR: "+e.getMessage(), Toast.LENGTH_LONG).show();
            return null;

        }
        return null;

    }

    public List<LocalDate> toDateList(String startDate, String endDate) {  ///FUNKCJA GENERUJĄCA DATY
        try {
            final LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            final LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            final long days = start.until(end, ChronoUnit.DAYS);
            return LongStream.rangeClosed(0, days)
                    .mapToObj(start::plusDays)
                    .collect(Collectors.toList());
        } catch(DateTimeException d)
        {
            return null;

        } catch(Exception e)
        {
            return null;

        }

    }

        private int[] validateMinMax(String min, String max){
        try {
            if (Integer.parseInt(String.valueOf(max.toString())) < Integer.parseInt(String.valueOf(min.toString()))) {
//            throw new CheckException("Oh no! Bad number max and min!", new Throwable());
                return null;
            }
            if (min == null || min == " " || min.isEmpty() || max == null || max == " " || max.isEmpty()) {
//            throw new CheckException("Oh no! Bad number max and min!", new Throwable());
                return null;
            }
        } catch (NumberFormatException e)
        {
            return null;
        } catch (Exception e)
        {
            return null;
        }
        return new int[]{ Integer.parseInt(String.valueOf(min.toString())),  Integer.parseInt(String.valueOf(max.toString()))};

    }


    @Test
    public void datetime_isCorrect() {
        List<LocalDate> tabexpected = Arrays.asList( LocalDate.parse("2022-11-09"), LocalDate.parse("2022-11-10"), LocalDate.parse("2022-11-11"));
        List<LocalDate> tabexpected2 = Arrays.asList( LocalDate.parse("2022-11-10"), LocalDate.parse("2022-11-11"));  //niepełna lista

        ///SPRAWDZAM CZY FUNKCJA DOBRZE GENERUJE DATY ---------> TEST PRZECHODZI POMYŚLNIE (generuje odpowiednie daty z zakresu od 9 do 11)
        assertArrayEquals(new List[]{tabexpected}, new List[]{toDateList("2022-11-09", "2022-11-11")} );

    /////GDY BRAKUJE JEDNEJ - ERROR ----------> ERROR
       //  assertArrayEquals(new List[]{tabexpected2}, new List[]{toDateList("2022-11-09", "2022-11-11")} );

    }
    @Test
    public void min_and_max_isCorrect() {

        ///SPRAWDZAM wartosci min i max przyjmowane przy liczbach
        ///jesli poprawne - zwraca nam dobre (zwalidowane) liczby - test przechodzi pomyslnie
        assertArrayEquals(new int[]{2, 5}, validateMinMax("2", "5") );

        ///jesli NIEPOPRAWNE - zwraca null - test nieprzechodzi  (musza byc liczby) (MIN MUSI BYC MNIEJSZE OD MAX ! ITD....

        //        assertArrayEquals(new int[]{2, 5}, validateMinMax("5", "2") );

    }

    @Test
    public void name_isCorrect() {

        ///SPRAWDZAM wartosc name (string) przyjmowane przy pobieraniu nazwy listy
        ///jesli poprawne - zwraca nam takiego samego stringa - test przechodzi pomyslnie
        assertEquals("KAROLEX", validateName("KAROLEX") );

        ///jesli NIEPOPRAWNE - zwraca null - test nieprzechodzi  za krotkie !

        //         assertEquals("A", validateName("A") );

    }

    @Test
    public void password_isCorrect() {


        assertEquals (13, createNewPass(13, true, true, true, true).length() );
        /////w tym przypadku jedynie moge sprawdzic czy funkcja generuje poprawna dlugosc hasla, reszty nie sprawdze - generuje się to losowo - tutaj test przejdzie
        //pozytywnie i zostanie stworzone 13-sto znakowe haslo ..


    }






}