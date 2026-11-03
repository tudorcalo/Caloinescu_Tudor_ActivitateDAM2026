package com.example.seminar04_1075;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class TricouAdapter extends BaseAdapter {
    private List<Tricou> tricouri;
    private Context ctx;
    private int resursaLayout;

    public TricouAdapter(List<Tricou> tricouri, Context ctx, int resursaLayout) {
        this.tricouri = tricouri;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }


    @Override
    public int getCount() {
        return tricouri.size();
    }

    @Override
    public Object getItem(int position) {
        return tricouri.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout, parent, false);

        TextView marcaTV = v.findViewById(R.id.marcaTV);
        TextView marimeTV = v.findViewById(R.id.marimeTV);
        CheckBox esteNouCB = v.findViewById(R.id.esteNouCB);
        RatingBar ratingRB = v.findViewById(R.id.ratingRB);

        Tricou tricou = (Tricou) getItem(position);

        marcaTV.setText(tricou.getMarca());
        marimeTV.setText("Mărime: " + tricou.getMarime());
        esteNouCB.setChecked(tricou.isEsteNou());
        ratingRB.setRating(tricou.getRating());

        return v;
    }



}
