package com.netspacekenya.leftie.countries;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import test.leftie.com.countries.R;

/**
 * Created by Edwin on 20-Mar-15.
 */
public class CountryDetailsFragment extends android.support.v4.app.Fragment {

    TextView country_name_tv;
    TextView formal_name_tv;
    TextView capital_tv;
    TextView currency_tv;
    TextView type_tv;
    TextView tel_code_tv;
    TextView letter_code_tv;
    TextView internet_code_tv;
    TextView wiki_tv;
    ImageView im;
    CardView flag_cv;
    Country c;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.country_details_layout, container, false);
        c = MainActivity.cList[getArguments().getInt("position")];

        country_name_tv = (TextView) rootView.findViewById(R.id.country_tv);
        country_name_tv.setText(c.getCommonName());

        formal_name_tv = (TextView) rootView.findViewById(R.id.formal_name_tv);
        formal_name_tv.setText(c.getFormalName());

        capital_tv = (TextView) rootView.findViewById(R.id.capital_tv);
        capital_tv.setText(c.getCapital());

        currency_tv = (TextView) rootView.findViewById(R.id.currency_tv);

        currency_tv.setText(c.getCurrencyName()+"("+c.getCurrencyCode()+")");

        type_tv = (TextView) rootView.findViewById(R.id.type_tv);
        type_tv.setText(c.getType());


        tel_code_tv = (TextView) rootView.findViewById(R.id.tel_code_tv);
        if(c.getTelephoneCode().charAt(0)=='-'){
            tel_code_tv.setText("+1"+c.getTelephoneCode());

        }
        else tel_code_tv.setText(c.getTelephoneCode());

        letter_code_tv = (TextView) rootView.findViewById(R.id.letter_code_tv);
        letter_code_tv.setText(c.getLetterCode2());

        internet_code_tv = (TextView) rootView.findViewById(R.id.internet_code_tv);
        internet_code_tv.setText(c.getTldCode());

        wiki_tv = (TextView) rootView.findViewById(R.id.wiki_tv);
        wiki_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String countryName = c.getCommonName();
                String link = "http://www.m.wikipedia.org/wiki/" + countryName;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(intent);

            }
        });
        try {
            String x = c.getTldCode().substring(1);
            im = (ImageView) rootView.findViewById(R.id.country_iv);
            im.setImageResource(getResources().getIdentifier("test.leftie.com.countries:drawable/" + x, null, null));
        } catch (Exception ex) {
            ///ignore

        }
        return rootView;
    }
}
