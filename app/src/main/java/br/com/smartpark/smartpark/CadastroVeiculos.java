package br.com.smartpark.smartpark;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroVeiculos extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText txtMarca, txtModelo, txtPlaca, txtCor;
    Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_veiculos);

        myDb = new DatabaseHelper(this);

        txtMarca = (EditText)findViewById(R.id.txtMarca);
        txtModelo = (EditText)findViewById(R.id.txtModelo);
        txtPlaca = (EditText)findViewById(R.id.txtPlaca);
        txtCor = (EditText)findViewById(R.id.txtCor);

        btnSalvar = (Button)findViewById(R.id.btnSalvar);

        insertVeiculo();
    }

    private void insertVeiculo() {
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDb.insertVeiculo(txtMarca.getText().toString(), txtModelo.getText().toString(), txtPlaca.getText().toString(), txtCor.getText().toString());
                if(isInserted){
                    Toast.makeText(CadastroVeiculos.this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
                }

                else{
                    Toast.makeText(CadastroVeiculos.this, "Erro ao cadastrar!", Toast.LENGTH_LONG).show();;
                }
            }
        });
    }
}
