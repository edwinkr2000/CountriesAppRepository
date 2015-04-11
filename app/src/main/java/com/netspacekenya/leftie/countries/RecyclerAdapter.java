package com.netspacekenya.leftie.countries;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import test.leftie.com.countries.R;

/**
 * Created by Edwin on 04-Apr-15.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> implements View.OnClickListener {
    Context context;
    Country c;
    MyHolder vh;



    public RecyclerAdapter(Context context){
        this.context = context;

    }
    @Override
    public RecyclerAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_row, parent, false);
        v.setOnClickListener(this);
        v.setClickable(true);
        // set the view's size, margins, paddings and layout parameters

        vh = new MyHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MyHolder holder, int position) {
         c = MainActivity.cList[position];
         holder.tv.setText(c.getCommonName().toString());
         holder.cv.setImageResource(context.getResources().getIdentifier("test.leftie.com.countries:drawable/" + c.getTldCode().substring(1), null, null));


    }

    @Override
    public int getItemCount() {
        return MainActivity.list.length;
    }

    @Override
    public void onClick(View v) {
        v.setSelected(true);
        Intent intent = new Intent(v.getContext(), CountryDetailsActivity.class);
        TextView tv = (TextView) v.findViewById(R.id.county_tv);
        intent.putExtra("country name", tv.getText().toString());
        v.getContext().startActivity(intent);


    }

    public static class MyHolder extends RecyclerView.ViewHolder{
        CircleImageView cv;
        TextView tv;
        public MyHolder(View itemView) {
            super(itemView);
           this.tv = (TextView) itemView.findViewById(R.id.county_tv);
            this.cv = (CircleImageView) itemView.findViewById(R.id.country_cv);
        }
    }
}
