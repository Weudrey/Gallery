package com.wyz.myimagegallery.Banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;




public class GalleryDAO extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "banco.db";
    public static final String TABELA = "GALERIA";
    public static final String ID = "_id";
    public static final String TITULO = "TITULO";
    public static final String DETALHES = "DETALHES";
    public static final String IMAGEM = "IMAGEM";
    public static final int VERSAO = 7;

    public GalleryDAO(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + TITULO + " text,"
                + DETALHES + " text,"
                + IMAGEM + " text"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
