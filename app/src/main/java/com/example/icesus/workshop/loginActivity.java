package com.example.icesus.workshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class loginActivity extends AppCompatActivity {

    private EditText et_userLogin;
    private EditText et_passLogin;
    private Button btn_Login;
    private Button btn_regisLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_userLogin = (EditText)findViewById(R.id.et_userLogin);
        et_passLogin = (EditText)findViewById(R.id.et_passLogin);
        btn_Login = (Button)findViewById(R.id.btn_login);
        btn_regisLogin = (Button)findViewById(R.id.btn_regisLogin);

        btn_regisLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(loginActivity.this, registerActivity.class);
                startActivity(i);
            }
        });
    }
}
