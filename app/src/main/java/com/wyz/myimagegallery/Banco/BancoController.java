package com.wyz.myimagegallery.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wyz.myimagegallery.classes.Gallery;


public class BancoController {
    private SQLiteDatabase db;
    private GalleryDAO banco;

    public BancoController(Context context){
        banco = new GalleryDAO(context);
    }

    public String insereDado(Gallery gallery){
        ContentValues valores;
        long resultado;


        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(GalleryDAO.TITULO, gallery.getTitulo());
        valores.put(GalleryDAO.DETALHES, gallery.getDetalhes());
        valores.put(GalleryDAO.IMAGEM, String.valueOf(gallery.getImagem()));


        resultado = db.insert(GalleryDAO.TABELA, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {banco.ID, banco.TITULO,banco.IMAGEM};
        db = banco.getReadableDatabase();

        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);


        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.TITULO,banco.DETALHES,banco.IMAGEM};
        String where = GalleryDAO.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(GalleryDAO.TABELA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alteraRegistro(Gallery gallery){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = GalleryDAO.ID + "=" + gallery.getID();

        valores = new ContentValues();
        valores.put(GalleryDAO.TITULO, gallery.getTitulo());
        valores.put(GalleryDAO.DETALHES, gallery.getDetalhes());
        valores.put(GalleryDAO.IMAGEM, String.valueOf(gallery.getImagem()));


        db.update(GalleryDAO.TABELA,valores,where,null);
        db.close();
    }

    public void deletaRegistro(int id){
        String where = GalleryDAO.ID + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(GalleryDAO.TABELA,where,null);
        db.close();
    }
}
