package br.com.smartpark.smartpark;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ConsultaAgendamento extends AppCompatActivity {
    FloatingActionButton btnMais;
    ListView listView;
    DatabaseHelper myDb;
    ArrayList<String> agendamento = new ArrayList<>();
    ArrayList<String> modelo = new ArrayList<>();
    ArrayList<String> placa = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_agendamento);

        listView = findViewById(R.id.listAgendamentos);
        myDb = new DatabaseHelper(this);

        Cursor res = myDb.getReserva();

        if (res.getCount() <= 0) {
            Toast.makeText(ConsultaAgendamento.this, "Não foi possível localizar os agendamentos!", Toast.LENGTH_LONG).show();
            return;
        }

        while (res.moveToNext()) {
            agendamento.add(res.getString(1));
        }

        ListAgendamento myAdapter = new ListAgendamento(ConsultaAgendamento.this, agendamento);

        listView.setAdapter(myAdapter);


        btnMais = findViewById(R.id.btnMais);
        btnMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConsultaAgendamento.this, NovoAgedamento.class));
            }
        });
    }
}