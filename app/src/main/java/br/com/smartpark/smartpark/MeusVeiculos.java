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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MeusVeiculos extends AppCompatActivity {
    FloatingActionButton btnNovoVeiculo;
    ListView listView;
    DatabaseHelper myDb;
    ArrayList<String> modelo = new ArrayList<>();
    ArrayList<String> placa = new ArrayList<>();
    ArrayList<Integer> id = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_veiculos);

        listView = (ListView) findViewById(R.id.listVeiculos);

        buscaVeiculos();

        btnNovoVeiculo = findViewById(R.id.btnMais);
        btnNovoVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeusVeiculos.this, CadastroVeiculos.class));
            }
        });

        listView.setOnItemLongClickListener (new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView parent, View view, int position, long id) {
                String idSelecionado = ((TextView)findViewById(R.id.txtListId)).getText().toString();
                showMessage(idSelecionado);
                return false;
            }
        });
    }

    public void showMessage(final String idSelecionado){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setItems(R.array.opcoes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    Bundle bundle = new Bundle();
                    bundle.putString("ID", idSelecionado);
                    Intent intent = new Intent(MeusVeiculos.this, CadastroVeiculos.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 1);
                }
                else if (which == 1){
                    boolean isDeleted = myDb.deleteVeiculo(idSelecionado);
                    if(isDeleted){
                        Toast.makeText(MeusVeiculos.this, "Excluído com sucesso!", Toast.LENGTH_LONG).show();
                        buscaVeiculos();
                    }

                    else{
                        Toast.makeText(MeusVeiculos.this, "Erro ao excluir!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        builder.show();
    }

    private void buscaVeiculos(){
        placa.clear();
        modelo.clear();
        id.clear();

        myDb = new DatabaseHelper(this);

        Cursor res = myDb.getVeiculo();

        if (res.getCount() == 0) {
            Toast.makeText(MeusVeiculos.this, "Não foi possível localizar os veículos!", Toast.LENGTH_LONG).show();
            return;
        }

        while (res.moveToNext()) {
            id.add(res.getInt(0));
            placa.add(res.getString(3));
            modelo.add(res.getString(2));
        }

        ListAdapter myAdapter = new ListAdapter(MeusVeiculos.this, placa, modelo, id);

        listView.setAdapter(myAdapter);
    }
}