package br.com.smartpark.smartpark;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class NovoAgedamento extends AppCompatActivity {
    FloatingActionButton btnSalvar, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_agedamento);

        btnSalvar = (FloatingActionButton)findViewById(R.id.btnSalvar);
        btnCancelar = (FloatingActionButton)findViewById(R.id.btnCancelar);

        cancelar();
    }

    private void insertUsuario() {
        /*btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDb.insertUsuario(txtNome.getText().toString(), txtCpf.getText().toString(), txtTelefone.getText().toString(), txtNomeCartao.getText().toString(), txtNumeroCartao.getText().toString(), txtBandeira.getText().toString(), txtValidade.getText().toString(), txtCodSeguranca.getText().toString());
                if(isInserted){
                    Toast.makeText(CadastroUsuario.this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
                }

                else{
                    Toast.makeText(CadastroUsuario.this, "Erro ao cadastrar!", Toast.LENGTH_LONG).show();;
                }
            }
        });*/
    }

    private void cancelar(){
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NovoAgedamento.this, PrincipalActivity.class));
            }
        });
    }
}
