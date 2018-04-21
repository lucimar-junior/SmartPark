package br.com.smartpark.smartpark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MeusVeiculos extends AppCompatActivity {
    ImageButton btnNovoVeiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_veiculos);

        btnNovoVeiculo = findViewById(R.id.btnMais);
        btnNovoVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeusVeiculos.this, CadastroVeiculos.class));
            }
        });
    }

}
