package com.wyz.myimagegallery.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.wyz.myimagegallery.Banco.BancoController;
import com.wyz.myimagegallery.Banco.GalleryDAO;
import com.wyz.myimagegallery.R;

public class ConsultaActivity extends Activity {

    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        BancoController crud = new BancoController(getBaseContext());
        final Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String[] {GalleryDAO.TITULO, GalleryDAO.IMAGEM};
        int[] idViews = new int[] {R.id.nomeGallery, R.id.imagemGallery};


        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.gallery_layout,cursor,nomeCampos,idViews, 0);
        lista = (ListView)findViewById(R.id.listView);
        lista.setAdapter(adaptador);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(GalleryDAO.ID));
                Intent intent = new Intent(ConsultaActivity.this, AlterarActivity.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);

            }
        });

        Button voltar = (Button)findViewById(R.id.voltar);
        voltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultaActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
