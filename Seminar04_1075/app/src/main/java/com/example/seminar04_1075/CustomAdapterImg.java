package com.example.seminar04_1075;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapterImg extends BaseAdapter {

    private List<ImagineObiect> imagini;
    private Context ctx;
    private int resursaLayout;

    public CustomAdapterImg(List<ImagineObiect> imagini, Context ctx, int resursaLayout) {
        this.imagini = imagini;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return imagini.size();
    }

    @Override
    public Object getItem(int position) {
        return imagini.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout, parent, false);

        ImageView imgView = v.findViewById(R.id.imagineTricou);
        TextView tvDescriere = v.findViewById(R.id.imagineTricouText);

        ImagineObiect item = (ImagineObiect) getItem(position);

        imgView.setImageBitmap(item.getImagine());
        tvDescriere.setText(item.getTextAfisat());

        return v;
    }
}
