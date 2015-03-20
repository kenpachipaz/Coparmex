package com.coparmex.coparmexbeta;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NavigationAdapter extends BaseAdapter {
    private Activity activity;
    ArrayList<ItemObj>arrayItems;

    NavigationAdapter(Activity activity,ArrayList<ItemObj> listaArray){
        super();
        this.activity = activity;
        this.arrayItems = listaArray;
    }

    @Override
    public int getCount() {
        return arrayItems.size();
    }
   //Retorna el objeto ItemObj del array list
    @Override
    public Object getItem(int position) {
        return arrayItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public  static  class Fila{
        TextView titulo_item;
        ImageView icono;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fila view;
        LayoutInflater inflater = activity.getLayoutInflater();
        if(convertView == null) {
            view = new Fila();
            //Creo objeto  item y lo obtengo de array
            ItemObj itm = arrayItems.get(position);
            convertView = inflater.inflate(R.layout.itm, null);
            //Titulo
            view.titulo_item = (TextView) convertView.findViewById(R.id.titulo_item);
            //Seteo  en el campo titulo el nombre correspondiente obtenido del object
            view.titulo_item.setText(itm.getTitulo());
            //Icono
            view.icono = (ImageView) convertView.findViewById(R.id.icon);
            //Seteo del Icono
            view.icono.setImageResource(itm.getIcono());
            convertView.setTag(view);
        }else{
             view = (Fila)convertView.getTag();

        }
        return convertView;
    }
}
