package com.netspacekenya.leftie.countries;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import test.leftie.com.countries.R;

/**
 * Created by Edwin on 05-Apr-15.
 */
public class CountriesFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    FrameLayout frame;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_layout, container, false);

        recyclerView = new RecyclerView(getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new RecyclerAdapter(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        frame = (FrameLayout) rootView.findViewById(R.id.main_container);
        frame.addView(recyclerView);
        return rootView;
    }

}
