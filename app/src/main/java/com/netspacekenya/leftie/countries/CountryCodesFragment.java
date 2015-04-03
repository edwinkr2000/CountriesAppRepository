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
public class CountryCodesFragment extends ListFragment {
    List<String> codesList;
    CodeSelectedListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_layout, container, false);
        loadCodesList();
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, codesList));
        return rootView;
    }
    private void loadCodesList(){
        codesList = new ArrayList<String>();

        for(Country c : MainActivity.cList){
            String code = c.getTelephoneCode();
            if(code!=null && !code.equals("")){
                if(code.charAt(0)=='-'){
                    codesList.add("+1"+ code+" ("+c.getCommonName() + ")");
                }
                else {
                    codesList.add(code + " (" + c.getCommonName() + ")");
                }
            }

        }
        Collections.sort(codesList);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String x = codesList.get(position);
        listener.onTelCodeSelected(x.substring(x.indexOf("(")+1, x.lastIndexOf(")")));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.listener = (CodeSelectedListener) activity;
    }
}
