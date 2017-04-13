package com.example.yasmin.educationalaugmentedreality;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class CrossWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cross_word);


        CrossWord.populateList();
        CrossWord.rearrange();
        CrossWord.populateBoard();


        GridView grid = (GridView) findViewById(R.id.grid);
        grid.setAdapter(new TextAdapter(this));



        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(CrossWordActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
                Log.d("POSITION", "" + position);
            }
        });



    }
}
