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
import android.widget.TextView;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.widget.ProfilePictureView;

import com.facebook.AccessToken;
import com.facebook.login.widget.ProfilePictureView;
import com.wyz.myimagegallery.Banco.BancoController;
import com.wyz.myimagegallery.Banco.GalleryDAO;
import com.wyz.myimagegallery.R;
import com.wyz.myimagegallery.Util.Util;

import org.json.JSONException;
import org.json.JSONObject;


public class ConsultaActivity extends Activity {

    private ListView lista;
    private String facebookID;
    private TextView textNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        facebookID = getIntent().getStringExtra("FB_ID");
        loadUsername();
        textNome =(TextView) findViewById(R.id.textNome);

        ProfilePictureView FotoUsuario;
        FotoUsuario = (ProfilePictureView) findViewById(R.id.profile);
        AccessToken token = AccessToken.getCurrentAccessToken();
        FotoUsuario.setProfileId(token.getUserId());
        //FotoUsuario.setProfileId(facebookID);

        BancoController crud = new BancoController(getBaseContext());
        final Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String[] {GalleryDAO.ID, GalleryDAO.TITULO, GalleryDAO.DETALHES};
        int[] idViews = new int[] {R.id.idInutil, R.id.nomeGallery, R.id.detalheGallery};

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

        Button ADD = (Button)findViewById(R.id.ADD);
        ADD.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultaActivity.this, InsereActivity.class);

                startActivity(intent);

            }
        });
    }
    public void loadUsername(){
        new GraphRequest(AccessToken.getCurrentAccessToken(),"me", null, HttpMethod.GET, new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse response) {
                JSONObject obj = response.getJSONObject();
                try{
                    String nome = obj.getString("name");
                    textNome.setText("Olá" + nome);

                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }).executeAsync();
    }


}
