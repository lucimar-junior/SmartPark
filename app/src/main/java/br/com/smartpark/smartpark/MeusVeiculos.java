package br.com.smartpark.smartpark;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MeusVeiculos extends AppCompatActivity {
    FloatingActionButton btnNovoVeiculo;
    ListView listView;
    DatabaseHelper myDb;
    ArrayList<String> modelo = new ArrayList<>();
    ArrayList<String> placa = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_veiculos);

        listView = (ListView) findViewById(R.id.listVeiculos);
        myDb = new DatabaseHelper(this);

        Cursor res = myDb.getVeiculo();

        if (res.getCount() == 0) {
            Toast.makeText(MeusVeiculos.this, "Não foi possível localizar os veículos!", Toast.LENGTH_LONG).show();
            return;
        }

        while (res.moveToNext()) {
            placa.add(res.getString(3));
            modelo.add(res.getString(2));
        }

        ListAdapter myAdapter = new ListAdapter(MeusVeiculos.this, placa, modelo);

        listView.setAdapter(myAdapter);

        btnNovoVeiculo = findViewById(R.id.btnMais);
        btnNovoVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeusVeiculos.this, CadastroVeiculos.class));
            }
        });

        listView.setOnItemLongClickListener (new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView parent, View view, int position, long id) {
                showMessage();
                return false;
            }
        });
    }

    public void showMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setItems(R.array.opcoes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    startActivity(new Intent(MeusVeiculos.this, CadastroVeiculos.class));
                }
                else if (which == 1){
                    boolean isInserted = myDb.deleteVeiculo("8");
                    if(isInserted){
                        Toast.makeText(MeusVeiculos.this, "Excluído com sucesso!", Toast.LENGTH_LONG).show();
                        finish();
                    }

                    else{
                        Toast.makeText(MeusVeiculos.this, "Erro ao excluir!", Toast.LENGTH_LONG).show();;
                    }
                }
            }
        });

        builder.show();
    }
}
