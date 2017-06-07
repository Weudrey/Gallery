package com.wyz.myimagegallery.Activitys;

import android.app.Activity;
import android.content.Intent;
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
import com.wyz.myimagegallery.classes.Gallery;
import com.wyz.myimagegallery.R;
import com.wyz.myimagegallery.Util.Util;

import java.io.IOException;

public class InsereActivity extends Activity {
    final int ACTIVITY_SELECT_IMAGE = 1234;
    ImageView imageView;
    String base64Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere);
        base64Image = null;
        imageView = (ImageView)findViewById(R.id.ImagemInsere);
        Button botao = (Button)findViewById(R.id.envia);
        Button pega_imagem_botao = (Button)findViewById(R.id.pega_imagem);

        pega_imagem_botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Selecione a Imagem"),ACTIVITY_SELECT_IMAGE);


            }

        });

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BancoController crud = new BancoController(getBaseContext());
                EditText titulo = (EditText)findViewById(R.id.InsereTitulo);
                EditText detalhes = (EditText)findViewById((R.id.InsereDetalhes));


                String tituloString = titulo.getText().toString();
                String detalhesString = detalhes.getText().toString();
                String imagemString =  Util.ImagetoBase64 (((BitmapDrawable)imageView.getDrawable()).getBitmap());
                String resultado;

                Gallery gallery = new Gallery(tituloString, detalhesString);
                gallery.setImagem(imagemString);

                resultado = crud.insereDado(gallery);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(InsereActivity.this,ConsultaActivity.class);
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
                        imageView.setImageBitmap(bitmap);
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
