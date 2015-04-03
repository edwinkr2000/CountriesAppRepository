package com.netspacekenya.leftie.countries;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import test.leftie.com.countries.R;

/**
 * Created by Edwin on 29-Mar-15.
 */
public class CapitalCitiesFragment extends ListFragment {
    List<String> capitalList;
    CapitalSelectedListener listener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        populatecCapitalsList();
        View rootView = inflater.inflate(R.layout.list_layout, container, false);
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, capitalList));

        return rootView;
    }
    private void populatecCapitalsList(){
        capitalList  = new ArrayList<String>();
        for(Country c : MainActivity.cList) {
            String capital = c.getCapital();

            if (capital != null && !capital.equals("")) capitalList.add(capital);
        }
        Collections.sort(capitalList);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        listener.onCapitalSelected(capitalList.get(position));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.listener = (CapitalSelectedListener) activity;
    }
}
