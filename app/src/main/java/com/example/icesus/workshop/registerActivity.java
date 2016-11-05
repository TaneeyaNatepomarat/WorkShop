package com.example.icesus.workshop;

import android.app.DownloadManager;
import android.os.AsyncTask;
import android.speech.tts.Voice;
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

public class registerActivity extends AppCompatActivity {

    private EditText et_DisplayRegis;
    private EditText et_userRegis;
    private EditText et_passRegis;
    private EditText et_passconfirmRegis;
    private Button btn_regisRegis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_DisplayRegis = (EditText) findViewById(R.id.et_DisplayRegis);
        et_userRegis = (EditText) findViewById(R.id.et_userRegis);
        et_passRegis = (EditText) findViewById(R.id.et_passRegis);
        et_passconfirmRegis = (EditText) findViewById(R.id.et_passconfirmRegis);
        btn_regisRegis = (Button) findViewById(R.id.btn_regisRegis);

        setListener();
    }

    private void setListener() {
        btn_regisRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (setValidate()) {

                    new register(et_userRegis.getText().toString(),
                            et_passRegis.getText().toString(),
                            et_passconfirmRegis.getText().toString(),
                            et_DisplayRegis.getText().toString()).execute();
                } else {
                    Toast.makeText(registerActivity.this, "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private boolean setValidate() {

        //ToDo validate form
        String displayName = et_DisplayRegis.getText().toString();
        String userName = et_userRegis.getText().toString();
        String password = et_passRegis.getText().toString();
        String passConfirm = et_passconfirmRegis.getText().toString();

        if (displayName.isEmpty()) return false;

        if (userName.isEmpty()) return false;

        if (password.isEmpty()) return false;

        if (passConfirm.isEmpty()) return false;

        if (!password.equals(passConfirm)) return false;

        return true;
    }

    private class register extends AsyncTask<Void, Void, String> {  //void = ค่าที่ไม่ต้องการส่งค่าใด ๆ

        //โครงสร้าง onPre > doIn > onPost

        private String userName;
        private String password;
        private String passConfirm;
        private String displayName;

        public register(String userName, String password, String passConfirm, String displayName) {
            this.userName = userName;
            this.password = password;
            this.passConfirm = passConfirm;
            this.displayName = displayName;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {  //เขียนการเชื่อมต่อกับ Server ในนี้
            OkHttpClient client = new OkHttpClient();
            Request request;
            Response response = null;

            //ข้อมูลที่ต้องการส่ง
            RequestBody requestBody = new FormBody.Builder()
                    .add("username", userName)
                    .add("password", password)
                    .add("password_con", passConfirm)
                    .add("display_name", displayName)
                    .build();

            request = new Request.Builder()
                    .url("http://kimhun55.com/pollservices/signup.php")  //ส่งไปที่ไหน
                    .post(requestBody)  //ส่งข้อมูลอะไร
                    .build();

            try {

                response = client.newCall(request).execute();

                if (response.isSuccessful()) {
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
            Toast.makeText(registerActivity.this, s, Toast.LENGTH_LONG).show();

            try {
                JSONObject rootObj = new JSONObject(s);
                if (rootObj.has("result")) {
                    JSONObject resultObj = rootObj.getJSONObject("result");
                    if(resultObj.getInt("result") == 1){
                        Toast.makeText(registerActivity.this, resultObj.getString("result_desc"), Toast.LENGTH_LONG).show();
                        finish();
                    }else {
                        Toast.makeText(registerActivity.this, resultObj.getString("result_desc"), Toast.LENGTH_LONG).show();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
