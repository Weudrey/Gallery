package com.wyz.myimagegallery.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wyz.myimagegallery.Banco.BancoController;
import com.wyz.myimagegallery.Banco.GalleryDAO;
import com.wyz.myimagegallery.classes.Gallery;
import com.wyz.myimagegallery.R;
import com.wyz.myimagegallery.Util.Util;

import java.io.IOException;

public class AlterarActivity extends Activity {

    EditText titulo;
    EditText detalhes;

    Button alterar;
    Button deletar;
    Cursor cursor;
    BancoController crud;
    String codigo;
    ImageView alteraImagem;
    final int ACTIVITY_SELECT_IMAGE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        codigo = this.getIntent().getStringExtra("codigo");

        crud = new BancoController(getBaseContext());

        titulo = (EditText)findViewById(R.id.alteraTitulo);
        detalhes = (EditText)findViewById(R.id.alteraDetalhe);

        alteraImagem = (ImageView)findViewById(R.id.alteraImagem);
        alterar = (Button)findViewById(R.id.alterar);

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        titulo.setText(cursor.getString(cursor.getColumnIndexOrThrow(GalleryDAO.TITULO)));
        detalhes.setText(cursor.getString(cursor.getColumnIndexOrThrow(GalleryDAO.DETALHES)));
        alteraImagem.setImageBitmap(Util.Base64toImage(cursor.getString(cursor.getColumnIndexOrThrow(GalleryDAO.IMAGEM))));

        alteraImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Selecione a Imagem"),ACTIVITY_SELECT_IMAGE);
            }
        });

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gallery gallery = new Gallery();
                gallery.setID(Integer.parseInt(codigo));
                gallery.setTitulo(titulo.getText().toString());
                gallery.setDetalhes(detalhes.getText().toString());
                gallery.setImagem(Util.ImagetoBase64 (((BitmapDrawable)alteraImagem.getDrawable()).getBitmap()));
                crud.alteraRegistro(gallery);
                Intent intent = new Intent(AlterarActivity.this,ConsultaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        deletar = (Button)findViewById(R.id.deletar);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.deletaRegistro(Integer.parseInt(codigo));
                Intent intent = new Intent(AlterarActivity.this,ConsultaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == ACTIVITY_SELECT_IMAGE){
            if(resultCode == Activity.RESULT_OK){
                if(data != null){
                    try{
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(),data.getData());
                        alteraImagem.setImageBitmap(bitmap);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }else if(resultCode == Activity.RESULT_CANCELED){
                    Toast.makeText(getApplicationContext(), "Cancelado.", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}
