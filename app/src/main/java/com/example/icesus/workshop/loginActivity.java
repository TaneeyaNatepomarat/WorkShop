package com.example.icesus.workshop;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class loginActivity extends AppCompatActivity {

    // Test Login username = TOT , pass = 00000

    private EditText et_userLogin;
    private EditText et_passLogin;
    private Button btn_Login;
    private Button btn_regisLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_userLogin = (EditText) findViewById(R.id.et_userLogin);
        et_passLogin = (EditText) findViewById(R.id.et_passLogin);
        btn_Login = (Button) findViewById(R.id.btn_login);
        btn_regisLogin = (Button) findViewById(R.id.btn_regisLogin);

        setListener();

    }

    private void setListener() {
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (setValidate()){

                    new login(et_userLogin.getText().toString(),
                            et_passLogin.getText().toString()).execute();
                }else {
                    Toast.makeText(loginActivity.this, "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_regisLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(loginActivity.this, registerActivity.class);
                startActivity(i);
            }
        });
    }

    private boolean setValidate() {
        String getUsername = et_userLogin.getText().toString();
        String getPassword = et_passLogin.getText().toString();

        if (getUsername.isEmpty()) return false;

        if (getPassword.isEmpty()) return false;

        return true;
    }

    private class login extends AsyncTask<Void,Void, String>{
        private String userName;
        private String passWord;

        public login (String userName, String passWord){
            this.userName = userName;
            this.passWord = passWord;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            Request request;
            Response response = null;

            RequestBody requestBody = new FormBody.Builder()
                    .add("username", userName)
                    .add("password", passWord)
                    .build();

            request = new Request.Builder()
                    .url("http://kimhun55.com/pollservices/login.php")
                    .post(requestBody)
                    .build();

            try {
                response = client.newCall(request).execute();

                if (response.isSuccessful()){
                    return response.body().string();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Toast.makeText(loginActivity.this, s, Toast.LENGTH_LONG).show();

            try {

                JSONObject rootObj = new JSONObject(s);

                if (rootObj.has("result")){
                    JSONObject resultObj = rootObj.getJSONObject("result");
                    if (resultObj.getInt("result") == 1){
                        Intent i = new Intent(loginActivity.this, news_ListActivity.class);
                        startActivity(i);
                    }else {
                        Toast.makeText(loginActivity.this, resultObj.getString("result_desc"),Toast.LENGTH_LONG).show();
                    }
                }

            }catch (JSONException e){
                e.printStackTrace();
            }


        }
    }
}
