package br.com.smartpark.smartpark;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class PrincipalActivity extends AppCompatActivity {
    ImageButton btnMeusVeiculos;
    ImageButton btnNovoVeiculo;
    ImageButton btnConsultarAgendamento;
    ImageButton btnAlterarCadastro;
    ImageButton btnNovoAgendamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnMeusVeiculos = findViewById(R.id.btnMeusVeiculos);
        btnMeusVeiculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PrincipalActivity.this, MeusVeiculos.class));
            }
        });

        btnNovoVeiculo = findViewById(R.id.btnNovoVeiculo);
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
        });

        btnAlterarCadastro = findViewById(R.id.btnAlterarCadastro);
        btnAlterarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PrincipalActivity.this, PrincipalActivity.class));
            }
        });*/

        btnNovoAgendamento = findViewById(R.id.btnNovoAgendamento);
        btnNovoAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PrincipalActivity.this, NovoAgedamento.class));
            }
        });
    }
}
