package com.wyz.myimagegallery.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wyz.myimagegallery.R;

public class UserActivity extends Activity {

    private TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        textViewName = (TextView) findViewById(R.id.text1);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText("Welcome " + nameFromIntent);

        Button consultarLogin = (Button)findViewById(R.id.consultarLogin);
        consultarLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this,ConsultaActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Button inserirLogin = (Button)findViewById(R.id.inserirLogin);
        inserirLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this,InsereActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Button jason = (Button)findViewById(R.id.jason);
        jason.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Button naosei = (Button)findViewById(R.id.naosei);

        Button logout = (Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
