package com.netspacekenya.leftie.countries;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import test.leftie.com.countries.R;

/**
 * Created by Edwin on 31-Mar-15.
 */
public class CountriesAdapter extends ArrayAdapter {
    Context context;
    Country c;
    TextView tv;
    ImageView iv;


    public CountriesAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    @Override
    public int getCount() {
        return MainActivity.list.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.custom_view, null);
         iv = (ImageView) rootView.findViewById(R.id.iv1);
        c = MainActivity.cList[position];
        try {
            iv.setImageResource(context.getResources().getIdentifier("test.leftie.com.countries:drawable/" + c.getTldCode().substring(1), null, null));
        }catch (Exception e){
            //ignore
        }
         tv = (TextView)rootView.findViewById(R.id.tv1);
        tv.setText(c.getCommonName());



        return rootView;
    }
}
