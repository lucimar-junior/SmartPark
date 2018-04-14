package br.com.smartpark.smartpark;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "SmartPark.db";
    public static final String TABLE_NAME = "usuario";
    public static final String COL_1 = "Nome";
    public static final String COL_2 = "Cpf";
    public static final String COL_3 = "Telefone";
    public static final String COL_4 = "NomeCartao";
    public static final String COL_5 = "NumeroCartao";
    public static final String COL_6 = "ValidadeCartao";
    public static final String COL_7 = "CodSeguranca";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Nome TEXT, Cpf INTEGER, Telefone INTEGER, NomeCartao INTEGER, NumeroCartao INTEGER, ValidadeCartao INTEGER, CodSeguranca INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertData(String nome, String cpf, String telefone, String nomeCartao, String numeroCartao, String validadeCartao, String codSeguranca){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, nome);
        contentValues.put(COL_2, cpf);
        contentValues.put(COL_3, telefone);
        contentValues.put(COL_4, nomeCartao);
        contentValues.put(COL_5, numeroCartao);
        contentValues.put(COL_6, validadeCartao);
        contentValues.put(COL_7, codSeguranca);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == 1) {
            return false;
        }

        else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

    /*public boolean updateData(String nome, String cpf, String telefone, String nomeCartao, String numeroCartao, String validadeCartao, String codSeguranca){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, nome);
        contentValues.put(COL_2, cpf);
        contentValues.put(COL_3, telefone);
        contentValues.put(COL_4, nomeCartao);
        contentValues.put(COL_5, numeroCartao);
        contentValues.put(COL_6, validadeCartao);
        contentValues.put(COL_7, codSeguranca);
        db.update(TABLE_NAME, contentValues, "")
    }*/

    /*public Integer deleteData (String ){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, contentValues, "")
    }*/
}
