package com.monitora.android.nufscar.main.json;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.monitora.android.nufscar.R;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.monitora.android.nufscar.model.News;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NoticiasJSONActivity extends AppCompatActivity implements LoadJSONNoticias.Listener, AdapterView.OnItemClickListener {

    private ListView mListView;

    public static final String URL = "https://ufscar-monitora.firebaseio.com/.json";

    private List<HashMap<String, String>> mAndroidMapList = new ArrayList<>();

    private static final String KEY_AUTOR = "autor";
    private static final String KEY_DATA = "data";
    private static final String KEY_FIGCAPTION = "figcapion";
    private static final String KEY_IDNOTICIA = "idNoticia";
    private static final String KEY_IMG = "image_src";
    private static final String KEY_TEXTO = "texto";
    private static final String KEY_TITULO = "titulo";
    private static final String KEY_URL = "url";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_view);

        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);
        new LoadJSONNoticias(this).execute(URL);
    }


    public void bEventos(View view){
        Intent it = new Intent(NoticiasJSONActivity.this, EventosJSONActivity.class);
        startActivity(it);
    }



    @Override
    public void onLoaded(List<News> androidList) {

        for (News android : androidList) {

            HashMap<String, String> map = new HashMap<>();

            map.put(KEY_AUTOR, android.getAutor());
            map.put(KEY_DATA, android.getData());
            map.put(KEY_FIGCAPTION, android.getFigcaption());
            map.put(KEY_IDNOTICIA, android.getIdNoticia());
            map.put(KEY_IMG, android.getImg_src());
            map.put(KEY_TEXTO, android.getTexo());
            map.put(KEY_TITULO, android.getTitulo());
            map.put(KEY_URL, android.getUrl());

            mAndroidMapList.add(map);
        }

        loadListView();
    }

    @Override
    public void onError() {

        Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(this, mAndroidMapList.get(i).get(KEY_TITULO),Toast.LENGTH_LONG).show();


    }

    private void loadListView() {

        ListAdapter adapter = new SimpleAdapter(NoticiasJSONActivity.this, mAndroidMapList, R.layout.list_item,
                new String[] {  KEY_TITULO, KEY_IMG, KEY_IDNOTICIA },
                new int[] {R.id.version,R.id.name, R.id.api });

        mListView.setAdapter(adapter);

    }
}