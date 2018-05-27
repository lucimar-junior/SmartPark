package br.com.smartpark.smartpark;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;

import static br.com.smartpark.smartpark.R.id;
import static br.com.smartpark.smartpark.R.layout;

public class NovoAgedamento extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Bundle savedInstanceState;
    boolean dataEntrada = false;
    DatabaseHelper myDb;
    FloatingActionButton btnCancelar, btnSalvar;
    EditText txtDtEntrada, txtDtSaida, txtHoraEntrada, txtHoraSaida;
    RadioButton rbDiarista, rbMensalista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(layout.activity_novo_agedamento);


        myDb = new DatabaseHelper(this);

        txtDtEntrada = findViewById(id.txtDtEntrada);
        txtDtSaida = findViewById(id.txtDtSaida);
        btnCancelar = findViewById(R.id.btnCancelar);
        txtHoraEntrada = findViewById(id.txtHoraEntrada);
        txtHoraSaida = findViewById(id.txtHoraSaida);
        btnSalvar = findViewById(id.btnSalvar);
        rbDiarista = findViewById(id.rbtDiarista);
        rbMensalista = findViewById(id.rbtAvulso);

        txtDtEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment datePicker = new DatePicherFragment();
                datePicker.show(getSupportFragmentManager(), "date Picker");

                dataEntrada = true;

            }
        });

        txtDtSaida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment datePicker = new DatePicherFragment();
                datePicker.show(getSupportFragmentManager(), "date Picker");

                dataEntrada = false;
            }
        });

        cancelar();
        insertMascara();
        insertReserva();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void  onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

        Calendar myCalendar = Calendar.getInstance();

        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, (month + 1));
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


        if (dataEntrada) {
            String dataEntrada = String.format("%02d-%02d-%d", dayOfMonth,(month + 1),year);
            EditText editText = findViewById(id.txtDtEntrada);
            editText.setText(dataEntrada);
        } else {
            String dataSaida = String.format("%02d-%02d-%d", dayOfMonth,(month + 1),year);
            EditText editText = findViewById(id.txtDtSaida);
            editText.setText(dataSaida);
        }

    }

    private void cancelar() {
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void insertMascara(){


        EditText inputDate = findViewById(id.txtDtEntrada);
        inputDate.addTextChangedListener(Mask.insert(Mask.DATE_MASK, inputDate));

        EditText exitDate = findViewById(id.txtDtSaida);
        exitDate.addTextChangedListener(Mask.insert(Mask.DATE_MASK, exitDate));

        EditText horaEntrada = findViewById(id.txtHoraEntrada);
        horaEntrada.addTextChangedListener(Mask.insert(Mask.DATE_HOUR, horaEntrada));

        EditText horaSaida = findViewById(id.txtHoraSaida);
        horaSaida.addTextChangedListener(Mask.insert(Mask.DATE_HOUR, horaSaida));

    }

    private void insertReserva() {
        btnSalvar.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                try {
                    boolean isInserted = myDb.insertReserva(txtDtEntrada.getText().toString(), txtDtSaida.getText().toString(), txtHoraEntrada.getText().toString(), txtHoraSaida.getText().toString());
                    if (isInserted) {
                        Toast.makeText(NovoAgedamento.this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(NovoAgedamento.this, PrincipalActivity.class));

                    }   else {

                        Toast.makeText(NovoAgedamento.this, "Erro ao cadastrar! \n Verifique se todos os dados foram preenchidos corretamente.", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

}