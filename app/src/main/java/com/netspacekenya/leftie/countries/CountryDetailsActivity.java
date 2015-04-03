package com.netspacekenya.leftie.countries;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import test.leftie.com.countries.R;

/**
 * Created by Edwin on 18-Mar-15.
 */
public class CountryDetailsActivity extends ActionBarActivity {

    ViewPager pager;
    PagerTitleStrip titleStrip;
    SharedPreferences sp;
    static int colorPointer = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.country_view_pager);

        pager =(ViewPager) findViewById(R.id.pager);
        titleStrip = (PagerTitleStrip) findViewById(R.id.pager_title_strip);
        sp = PreferenceManager.getDefaultSharedPreferences(this);

            pager.setAdapter(new CountrySelectedStateAdapter(this));

        if(savedInstanceState==null) {
            pager.setCurrentItem(getIntent().getIntExtra("position", 0));


        }
        else {
            pager.setCurrentItem(sp.getInt("saved item", 0));
        }

            pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    colorChanger();

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    private void colorChanger(){
        switch (colorPointer){
            case 0 : titleStrip.setBackgroundColor(getResources().getColor(R.color.atomic_tangerine));
                colorPointer++;
                break;
            case 1 : titleStrip.setBackgroundColor(getResources().getColor(R.color.azure));
                colorPointer++;
                break;
            case 2 : titleStrip.setBackgroundColor(getResources().getColor(R.color.bright_green));
                colorPointer++;
                break;
            case 3 : titleStrip.setBackgroundColor(getResources().getColor(R.color.baker_miller));
                colorPointer=0;
                break;


        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("saved item", pager.getCurrentItem());
        editor.commit();
    }
}
