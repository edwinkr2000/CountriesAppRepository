package com.netspacekenya.leftie.countries;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Edwin on 20-Mar-15.
 */
public class CountrySelectedStateAdapter extends FragmentStatePagerAdapter {
    CountryDetailsFragment frag;
    Bundle b;
    ActionBarActivity activity;

    public CountrySelectedStateAdapter(ActionBarActivity activity) {
        super(activity.getSupportFragmentManager());

        this.activity = activity;
    }

    @Override
    public Fragment getItem(int position) {
         b = new Bundle();
        b.putInt("position", position);
        frag = new CountryDetailsFragment();
        frag.setArguments(b);
        return frag;
    }

    @Override
    public int getCount() {
        return 225;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return MainActivity.list[position];
    }



}
