package br.com.smartpark.smartpark;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<String> {
    ArrayList<String> placa = new ArrayList<>();
    ArrayList<String> modelo = new ArrayList<>();
    Context mContext;

    public ListAdapter(Context context, ArrayList<String> placa, ArrayList<String> modelo){
        super(context, R.layout.activity_meus_veiculos);
        this.placa = placa;
        this.modelo = modelo;
        this.mContext = context;
    }

    @Override
    public int getCount(){
        return placa.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder mViewHolder = new ViewHolder();

        if(convertView == null){
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_veiculos, parent, false);
            mViewHolder.mModelo = (TextView) convertView.findViewById(R.id.txtListModelo);
            mViewHolder.mPlaca = (TextView) convertView.findViewById(R.id.txtListPlaca);
            convertView.setTag(mViewHolder);
        } else{
            mViewHolder = (ViewHolder)convertView.getTag();
        }

        //necessário converter de ArrayList para Array para usar o método setText(array[position])
        String[] placaArray = new String[getCount()];
        String[] modeloArray = new String[getCount()];
        int i = 0;

        for(String placa : placa){
            placaArray[i] = placa;
            i++;
        }

        i = 0;

        for(String modelo : modelo){
            modeloArray[i] = modelo;
            i++;
        }

        mViewHolder.mModelo.setText(modeloArray[position]);
        mViewHolder.mPlaca.setText(placaArray[position]);

        return convertView;
    }

    static class ViewHolder{
        TextView mModelo;
        TextView mPlaca;
    }
}
