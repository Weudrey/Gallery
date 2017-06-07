package com.wyz.myimagegallery.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wyz.myimagegallery.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = (Button)findViewById(R.id.Login);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });
        Button consultar = (Button)findViewById(R.id.consultar);
        consultar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ConsultaActivity.class);
                startActivity(intent);

            }
        });
        Button inserirTeste = (Button)findViewById(R.id.inserirTeste);
        inserirTeste.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,InsereActivity.class);
                startActivity(intent);

            }
        });
    }

}
