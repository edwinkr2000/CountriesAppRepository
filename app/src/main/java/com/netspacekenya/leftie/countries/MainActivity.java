package com.netspacekenya.leftie.countries;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import test.leftie.com.countries.R;

/**
 * Created by Edwin Muigai on 16-Mar-15.
 *
 */
public class MainActivity extends ActionBarActivity implements CountrySelectedListener, CapitalSelectedListener, CodeSelectedListener {
    ArrayList<Country> countryList;
    DrawerLayout drawer;
    ListView drawer_lv;
    String [] drawerList={"Countries", "Capital Cities", "Calling Codes", "About"};
    static String[] list;
    static Country[] cList;
    CountriesReader cr;
    ArrayList<String> voiceList;
    ActionBarDrawerToggle drawerToggle;

    int counter = 0;
    final static int VOICE_REQUEST_CODE = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(R.color.azure));
        cr = new CountriesReader(this);
        countryList = cr.getCountryList();
        cList = countryList.toArray(new Country[countryList.size()]);
        list = new String[countryList.size()];

          for (Country c : countryList) {
        list[counter] = c.getCommonName();
        counter++;
      }
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer_lv = (ListView) findViewById(R.id.left_drawer);
        drawer_lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        drawer_lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, drawerList));
        drawer_lv.setOnItemClickListener(new DrawerClickListener(getSupportFragmentManager()));

        drawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.open_drawer, R.string.close_drawer);
        drawer.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_drawer));
        getSupportActionBar().setHomeButtonEnabled(true);


        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().add(R.id.main_container, new CountriesFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.action_voice_search){
            Intent voiceIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            voiceIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            voiceIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "say a country name");
           voiceIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            try {
                startActivityForResult(voiceIntent, VOICE_REQUEST_CODE);
            }catch (ActivityNotFoundException ex){
                Toast.makeText(this, "Voice Search not supported!", Toast.LENGTH_SHORT).show();
            }
        }
        else if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        else if(id==R.id.action_search){

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    public void onCountrySelected(int position) {
        Intent intent = new Intent(this, CountryDetailsActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==VOICE_REQUEST_CODE && resultCode==RESULT_OK){
             voiceList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
           String word = voiceList.get(0);
            int position = lookForVoiceWord(word);
            if(position!=-1) onCountrySelected(position);
            else Toast.makeText(this, "You said \""+word +"\", try again", Toast.LENGTH_SHORT).show();


        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private int lookForVoiceWord(String voiceWord ){
        int position;
        int counter = 0;
        for(String x : MainActivity.list){
            if(voiceWord.equalsIgnoreCase(x)){
                return position=counter;
            }
            else {
                counter++;
            }
        }
        return -1;
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onCapitalSelected(String capitalName) {
        int counter = 0;
        for(Country c : MainActivity.cList){
            if(c.getCapital().equals(capitalName)){
                Intent intent = new Intent(this, CountryDetailsActivity.class);
                intent.putExtra("position", counter);
                startActivity(intent);

            }
            counter++;
        }

    }

    @Override
    public void onTelCodeSelected(String code) {
        int counter = 0;
        for(Country c : MainActivity.cList){
            if(c.getCommonName().equals(code)){
                Intent intent = new Intent(this, CountryDetailsActivity.class);
                intent.putExtra("position", counter);
                startActivity(intent);
            }
            counter++;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_MENU){
            if(drawer.isDrawerOpen(GravityCompat.START)){
                drawer.closeDrawers();
            }
            else {
                drawer.openDrawer(GravityCompat.START);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Created by Edwin on 29-Mar-15.
     */
    class DrawerClickListener implements ListView.OnItemClickListener{
        FragmentManager fragMag;

        public DrawerClickListener(FragmentManager fragMag) {

            this.fragMag = fragMag;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            drawer_lv.setItemChecked(position, true);
            switch (position){
                case 0 : fragMag.beginTransaction().replace(R.id.main_container, new CountriesFragment()).commit();
                    drawer.closeDrawers();
                    getSupportActionBar().setTitle("Countries");
                    break;
                case 1 : fragMag.beginTransaction().replace(R.id.main_container, new CapitalCitiesFragment()).commit();
                    drawer.closeDrawers();
                    getSupportActionBar().setTitle("Captal Cities");
                    break;
                case 2 : fragMag.beginTransaction().replace(R.id.main_container, new CountryCodesFragment()).commit();
                    drawer.closeDrawers();
                    getSupportActionBar().setTitle("Tel Codes");
                    break;
                case 3 :
                    break;

            }
        }
    }

}
