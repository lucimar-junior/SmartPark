package br.com.smartpark.smartpark;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAgendamento extends ArrayAdapter<String> {
    ArrayList<String> agendamento = new ArrayList<>();
    Context mContext;

    public ListAgendamento(Context context, ArrayList<String> agendamento){
        super(context, R.layout.activity_consulta_agendamento);
        this.agendamento = agendamento;
        this.mContext = context;
    }

    @Override
    public int getCount(){
        return agendamento.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ListAdapter.ViewHolder mViewHolder = new ListAdapter.ViewHolder();

        if(convertView == null){
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_veiculos, parent, false);
            mViewHolder.mModelo = (TextView) convertView.findViewById(R.id.txtListModelo);
            mViewHolder.mPlaca = (TextView) convertView.findViewById(R.id.txtListPlaca);
            convertView.setTag(mViewHolder);
        } else{
            mViewHolder = (ListAdapter.ViewHolder)convertView.getTag();
        }

        //necessário converter de ArrayList para Array para usar o método setText(array[position])
        String[] agendamentoArray = new String[getCount()];
        int i = 0;

        for(String agendamento : agendamento){
            agendamentoArray[i] = agendamento;
            i++;
        }

        mViewHolder.mModelo.setText(agendamentoArray[position]);

        return convertView;
    }

    static class ViewHolder{
        TextView mModelo;
        TextView mPlaca;
    }
}
