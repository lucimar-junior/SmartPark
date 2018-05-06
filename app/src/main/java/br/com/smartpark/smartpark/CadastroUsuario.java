package br.com.smartpark.smartpark;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroUsuario extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText txtNome, txtCpf, txtTelefone, txtNomeCartao, txtNumeroCartao, txtValidade, txtCodSeguranca, txtBandeira;
    FloatingActionButton btnSalvar, btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        myDb = new DatabaseHelper(this);

        txtNome = (EditText)findViewById(R.id.txtNome);
        txtCpf = (EditText)findViewById(R.id.txtCpf);
        txtTelefone = (EditText)findViewById(R.id.txtTelefone);
        txtNomeCartao = (EditText)findViewById(R.id.txtNomeCartao);
        txtNumeroCartao = (EditText)findViewById(R.id.txtNumero);
        txtValidade = (EditText)findViewById(R.id.txtValidade);
        txtCodSeguranca = (EditText)findViewById(R.id.txtCodSeguranca);
        txtBandeira = (EditText)findViewById(R.id.txtBandeira);

        btnSalvar = (FloatingActionButton)findViewById(R.id.btnSalvar);
        btnVoltar = (FloatingActionButton)findViewById(R.id.btnVoltar);

        insertUsuario();
        cancelar();
    }

    private void insertUsuario() {
        btnSalvar.setOnClickListener(new View.OnClickListener() {
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
        });
    }

    private void cancelar(){
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CadastroUsuario.this, PrincipalActivity.class));
            }
        });
    }
}
