package com.example.icesus.workshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class news_ListActivity extends AppCompatActivity {

    private ListView lv_manuNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news__list);

        lv_manuNews = (ListView)findViewById(R.id.lv_manuNews);
        lv_manuNews.setAdapter(new CustomAdapterActivity(getApplicationContext()));
    }
}
