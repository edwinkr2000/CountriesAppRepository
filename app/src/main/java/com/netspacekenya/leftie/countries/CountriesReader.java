package com.netspacekenya.leftie.countries;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import test.leftie.com.countries.R;

/**
 * Created by Edwin on 16-Mar-15.
 */
public class CountriesReader {
    private BufferedReader br;
    String separator;
    Context context;
    int counter;
    private ArrayList<Country> countryList;
    private String[] headers;
    private String[] holder;

    public CountriesReader(Context context){
        this.context=context;
        separator=",";
        countryList = new ArrayList<Country>(300);
        counter=0;
        try {
            br = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.countries)));
            ///Read headers
            headers = br.readLine().split(separator);

            //Read countries
            do {
                holder = br.readLine().split(separator);
                Country c = new Country(Integer.parseInt(holder[0]), holder[1], holder[2], holder[3], holder[4], holder[5], holder[6], holder[7], holder[8], holder[9],
                        holder[10], holder[11], Integer.parseInt(holder[12]), holder[13]);
                countryList.add(c);
                counter++;
            }
            while (counter<271);
            br.close();
        }catch (Exception ex){
            //ignore
        }

    }
    public ArrayList<Country> getCountryList(){
        return countryList;
    }

}
