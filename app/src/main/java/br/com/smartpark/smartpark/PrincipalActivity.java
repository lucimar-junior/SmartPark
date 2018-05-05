package br.com.smartpark.smartpark;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity {
    ImageButton btnMeusVeiculos;
    ImageButton btnNovoVeiculo;
    ImageButton btnConsultarAgendamento;
    ImageButton btnAlterarCadastro;
    ImageButton btnNovoAgendamento;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        myDb = new DatabaseHelper(this);

        btnMeusVeiculos = (ImageButton)findViewById(R.id.btnMeusVeiculos);
        verVeiculos();

        btnNovoVeiculo = (ImageButton)findViewById(R.id.btnNovoVeiculo);
        btnNovoVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PrincipalActivity.this, CadastroVeiculos.class));
            }
        });

        /*btnConsultarAgendamento = findViewById(R.id.btnConsultarAgendamento);
        btnConsultarAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PrincipalActivity.this, PrincipalActivity.class));
            }
        });*/

        btnAlterarCadastro = (ImageButton)findViewById(R.id.btnAlterarCadastro);
        btnAlterarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PrincipalActivity.this, CadastroUsuario.class));
            }
        });

        btnNovoAgendamento = findViewById(R.id.btnNovoAgendamento);
        btnNovoAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PrincipalActivity.this, NovoAgedamento.class));
            }
        });
    }

    public void verVeiculos() {
        btnMeusVeiculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PrincipalActivity.this, MeusVeiculos.class));
                /*Cursor res = myDb.getVeiculo();
                if (res.getCount() == 0) {
                    Toast.makeText(PrincipalActivity.this, "Não foi possível localizar os veículos!", Toast.LENGTH_LONG).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();

                while (res.moveToNext()) {
                    buffer.append("ID: " + res.getString(0) + "\n");
                    buffer.append("Marca: " + res.getString(1) + "\n");
                    buffer.append("Modelo: " + res.getString(2) + "\n");
                    buffer.append("Placa: " + res.getString(3) + "\n");
                    buffer.append("Cor: " + res.getString(4) + "\n");
                }

                showMessage("Dados", buffer.toString());*/
            }
        });
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
