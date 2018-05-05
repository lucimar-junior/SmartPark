package br.com.smartpark.smartpark;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<String> {
    String[] placa;
    String[] modelo;
    Context mContext;

    public ListAdapter(Context context, String[] placa, String[] modelo){
        super(context, R.layout.activity_meus_veiculos);
        this.placa = placa;
        this.modelo = modelo;
        this.mContext = context;
    }

    @Override
    public int getCount(){
        return placa.length;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder mViewHolder = new ViewHolder();

        if(convertView == null){
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_veiculos, parent, false);
            //mViewHolder.mModelo = (TextView) convertView.findViewById(R.id.txtListModelo);
            mViewHolder.mPlaca = (TextView) convertView.findViewById(R.id.textView22);
            mViewHolder.mLogo = (ImageView) convertView.findViewById(R.id.imageView22);
            convertView.setTag(mViewHolder);
        } else{
            mViewHolder = (ViewHolder)convertView.getTag();
        }

        mViewHolder.mModelo.setText(modelo[position]);
        mViewHolder.mPlaca.setText(placa[position]);
        return convertView;
    }

    static class ViewHolder{
        TextView mModelo;
        TextView mPlaca;
        ImageView mLogo;
    }
}
