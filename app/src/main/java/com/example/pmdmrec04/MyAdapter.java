package com.example.pmdmrec04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.pmdmrec04.R;

import java.util.ArrayList;

import sqlite.Provincia;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Provincia> names;
    Provincia currentName;
    TextView textViewProvincia, textViewFase;

    public MyAdapter(Context context, int layout, ArrayList<Provincia> names) {
        this.context = context;
        this.layout = layout;
        this.names = names;
    }

    @Override
    public int getCount() {
        return this.names.size();
    }

    @Override
    public Object getItem(int position) {
        return this.names.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override

    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        View v = convertView;

        LayoutInflater layoutInflater = LayoutInflater.from(this.context);

        v = layoutInflater.inflate(R.layout.lista_provincias, null);

        currentName = names.get(position);

        textViewProvincia = (TextView) v.findViewById(R.id.textListaProvincias);
        textViewProvincia.setText("Provincia:" + currentName.getNombre());

        textViewFase = (TextView) v.findViewById(R.id.textListaFase);
        textViewFase.setText("Fase:" + currentName.getFase());


        Button botonVerMas = (Button) v.findViewById(R.id.imageButtonListaProvincias);
        botonVerMas.setFocusable(false);
        botonVerMas.setClickable(false);

        return v;
    }
}