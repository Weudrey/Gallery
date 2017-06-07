package com.wyz.myimagegallery.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wyz.myimagegallery.Banco.UserDAO;
import com.wyz.myimagegallery.R;
import com.wyz.myimagegallery.Util.InputValidation;

public class LoginActivity extends Activity {

    private UserDAO UserDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = (Button) findViewById(R.id.login);
        TextView registrar = (TextView) findViewById(R.id.cadastrese);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                verifyFromSQLite();
            }
        });
        registrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


    private void verifyFromSQLite(){
        EditText Email = (EditText)findViewById(R.id.campoEmailLogin);
        EditText Password = (EditText)findViewById(R.id.campoSenhaLogin);


        if (UserDAO.checkUser(Email.getText().toString().trim()
                , Password.getText().toString().trim())) {
            Intent accountsIntent = new Intent(LoginActivity.this, UserActivity.class);
            accountsIntent.putExtra("EMAIL", Email.getText().toString().trim());
            startActivity(accountsIntent);
        } else {
            Intent vorta = new Intent(this, MainActivity.class);
            startActivity(vorta);
        }
    }
}
