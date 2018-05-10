package br.com.smartpark.smartpark;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "SmartPark.db";
    public static final String TABLE_USUARIO = "usuario";
    public static final String COL_1 = "Nome";
    public static final String COL_2 = "Cpf";
    public static final String COL_3 = "Telefone";
    public static final String COL_4 = "NomeCartao";
    public static final String COL_5 = "NumeroCartao";
    public static final String COL_6 = "ValidadeCartao";
    public static final String COL_7 = "CodSeguranca";

    public static final String TABLE_VEICULO = "Veiculo";
    public static final String COL_8 = "Marca";
    public static final String COL_9 = "Modelo";
    public static final String COL_10 = "Placa";
    public static final String COL_11 = "Cor";

    public static final String TABLE_CARTAO = "Cartao";
    public static final String COL_12 = "NomeImpresso";
    public static final String COL_13 = "NumeroCartao";
    public static final String COL_14 = "Bandeira";
    public static final String COL_15 = "Validade";
    public static final String COL_16 = "CodSeguranca";

    public static final String TABLE_RESERVA = "Reserva";
    public static final String COL_17 = "DataEntrada";
    public static final String COL_18 = "DataSaida";

    public static final String TABLE_VAGA = "Vaga";
    public static final String COL_19 = "NumeroVaga";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_USUARIO + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Nome TEXT, Cpf INTEGER, Telefone INTEGER, NomeCartao INTEGER, NumeroCartao INTEGER, ValidadeCartao INTEGER, CodSeguranca INTEGER)");
        db.execSQL("CREATE TABLE " + TABLE_VEICULO + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Marca TEXT, Modelo TEXT, Placa TEXT, Cor TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_CARTAO + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NomeImpresso TEXT, NumeroCartao INTEGER, Bandeira TEXT, Validade DATETIME, CodSeguranca INTEGER)");
        db.execSQL("CREATE TABLE " + TABLE_RESERVA + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, DataEntrada DATETIME, DataSaida DATETIME)");
        db.execSQL("CREATE TABLE " + TABLE_VAGA + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NumeroVaga INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VEICULO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARTAO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VAGA);
        onCreate(db);
    }

    public boolean insertUsuario(String nome, String cpf, String telefone, String nomeImpresso, String numeroCartao, String bandeira, String validade, String codSeguranca){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, nome);
        contentValues.put(COL_2, cpf);
        contentValues.put(COL_3, telefone);

        long result = db.insert(TABLE_USUARIO, null, contentValues);

        if (result == 1) {
            return false;
        }

        else{
            boolean resultCartao;
            resultCartao = insertCartao(nomeImpresso, numeroCartao, bandeira, validade, codSeguranca);

            if (!resultCartao) {
                return false;
            }

            else{
                return true;
            }
        }
    }

    public boolean insertVeiculo(String marca, String modelo, String placa, String cor){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_8, marca);
        contentValues.put(COL_9, modelo);
        contentValues.put(COL_10, placa);
        contentValues.put(COL_11, cor);

        long result = db.insert(TABLE_VEICULO, null, contentValues);

        if (result == 1) {
            return false;
        }

        else{
            return true;
        }
    }

    public boolean insertCartao(String nomeImpresso, String numeroCartao, String bandeira, String validade, String codSeguranca){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_12, nomeImpresso);
        contentValues.put(COL_13, numeroCartao);
        contentValues.put(COL_14, bandeira);
        contentValues.put(COL_15, validade);
        contentValues.put(COL_16, codSeguranca);

        long result = db.insert(TABLE_CARTAO, null, contentValues);

        if (result == 1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean insertReserva(String dataEntrada, String dataSaida){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_17, dataEntrada);
        contentValues.put(COL_18, dataSaida);

        long result = db.insert(TABLE_RESERVA, null, contentValues);

        if (result == 1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean insertVaga(String numeroVaga){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_19, numeroVaga);

        long result = db.insert(TABLE_VAGA, null, contentValues);

        if (result == 1){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getVeiculo(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_VEICULO, null);
        return res;
    }

    public Cursor getReserva(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_RESERVA, null);
        return res;
    }

    public Cursor getCartao(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_CARTAO, null);
        return res;
    }

    public Cursor getUsuario(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_USUARIO, null);
        return res;
    }

    public Cursor getVaga(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_VAGA, null);
        return res;
    }

    public boolean updateVeiculo(String marca, String modelo, String placa, String cor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_8, marca);
        contentValues.put(COL_9, modelo);
        contentValues.put(COL_10, placa);
        contentValues.put(COL_11, cor);
        long result = db.update(TABLE_VEICULO, contentValues, "Placa = ? ", new String[]{marca});

        if (result == 1) {
            return false;
        }

        else{
            return true;
        }
    }

    public boolean updateCartao(String nomeImpresso, String numeroCartao, String bandeira, String validade, String codSeguranca){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_12, nomeImpresso);
        contentValues.put(COL_13, numeroCartao);
        contentValues.put(COL_14, bandeira);
        contentValues.put(COL_15, validade);
        contentValues.put(COL_16, codSeguranca);

        long result = db.update(TABLE_CARTAO, contentValues, "NumeroCartao", new String[]{numeroCartao});

        if (result == 1) {
            return false;
        }

        else{
            return true;
        }
    }

    public boolean updateReserva (String dataentrada, String datasaida){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_17, dataentrada);
        contentValues.put(COL_18, datasaida);

        long result = db.update(TABLE_RESERVA, contentValues, "DataEntrada", new String[]{dataentrada});

        if (result == 1) {
            return false;
        }

        else{
            return true;
        }
    }

    public boolean updateVaga(String numerovaga){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_19, numerovaga);

        long result = db.update(TABLE_VAGA, contentValues, "NumeroVaga", new String[]{numerovaga});

        if (result == 1) {
            return false;
        }

        else{
            return true;
        }
    }
    public boolean updateUsuario(String nome, String cpf, String telefone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, nome);
        contentValues.put(COL_2, cpf);
        contentValues.put(COL_3, telefone);
        long result = db.update(TABLE_USUARIO, contentValues, "cpf", new String[]{cpf});

        if (result == 1) {
            return false;
        }

        else{
            return true;
        }
    }

    public boolean deleteVeiculo(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_VEICULO, "ID = :", new String[] { id });

        if (result == 1) {
            return false;
        }

        else{
            return true;
        }
    }

}
