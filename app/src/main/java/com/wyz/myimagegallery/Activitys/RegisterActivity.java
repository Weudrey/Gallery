package com.wyz.myimagegallery.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wyz.myimagegallery.Banco.UserDAO;
import com.wyz.myimagegallery.R;
import com.wyz.myimagegallery.classes.User;

public class RegisterActivity extends Activity {
    private UserDAO UserDAO;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button cadastroFazer = (Button)findViewById(R.id.cadastroFazer);
        TextView voltarCadastro = (TextView)findViewById(R.id.voltarCadastro);

        cadastroFazer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                postDataToSQLite();
            }
        });

        voltarCadastro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void postDataToSQLite(){
        EditText nomeCadastro = (EditText)findViewById(R.id.nomeCadastro);
        EditText emailCadastro = (EditText)findViewById(R.id.emailCadastro);
        EditText senhaCadastro = (EditText)findViewById(R.id.senhaCadastro);

        if (!UserDAO.checkUser(emailCadastro.getText().toString().trim())) {

            user.setName(nomeCadastro.getText().toString().trim());
            user.setEmail(emailCadastro.getText().toString().trim());
            user.setPassword(senhaCadastro.getText().toString().trim());

            UserDAO.addUser(user);
            Intent deu = new Intent(this, LoginActivity.class);
            startActivity(deu);

        } else {
            Intent vorta = new Intent(this, MainActivity.class);
            startActivity(vorta);

        }


    }
}
