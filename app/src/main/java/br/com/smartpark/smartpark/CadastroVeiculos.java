package br.com.smartpark.smartpark;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class CadastroVeiculos extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText txtMarca, txtModelo, txtPlaca, txtCor;
    FloatingActionButton btnSalvar, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_veiculos);

        myDb = new DatabaseHelper(this);

        txtMarca = (EditText)findViewById(R.id.txtMarca);
        txtModelo = (EditText)findViewById(R.id.txtModelo);
        txtPlaca = (EditText)findViewById(R.id.txtPlaca);
        txtCor = (EditText)findViewById(R.id.txtCor);

        btnSalvar = (FloatingActionButton)findViewById(R.id.btnSalvar);
        btnCancelar = (FloatingActionButton)findViewById(R.id.btnCancelar);

        SimpleMaskFormatter simpleMaskPlaca = new SimpleMaskFormatter("UUU-NNNN");
        MaskTextWatcher maskPlaca = new MaskTextWatcher(txtPlaca, simpleMaskPlaca);
        txtPlaca.addTextChangedListener(maskPlaca);

        insertVeiculo();
        cancelar();
    }

    private void insertVeiculo() {
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDb.insertVeiculo(txtMarca.getText().toString(), txtModelo.getText().toString(), txtPlaca.getText().toString(), txtCor.getText().toString());
                if(isInserted){
                    Toast.makeText(CadastroVeiculos.this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(CadastroVeiculos.this, PrincipalActivity.class));
                }

                else{
                    Toast.makeText(CadastroVeiculos.this, "Erro ao cadastrar!", Toast.LENGTH_LONG).show();;
                }
            }
        });
    }

    private void cancelar(){
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CadastroVeiculos.this, PrincipalActivity.class));
            }
        });
    }
}
