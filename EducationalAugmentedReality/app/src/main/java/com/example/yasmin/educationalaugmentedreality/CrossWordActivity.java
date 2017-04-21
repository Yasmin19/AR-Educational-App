package com.example.yasmin.educationalaugmentedreality;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class CrossWordActivity extends AppCompatActivity {

    static Typeface font;
    private Context mContext;
    GridView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cross_word);


        CrossWord.populateList();
        CrossWord.rearrange();
        CrossWord.populateBoard();


        grid = (GridView) findViewById(R.id.grid);
        grid.setAdapter(new TextAdapter(this));

        //Setup .ttf font file for use in crossword
        AssetManager am = this.getApplicationContext().getAssets();
        font = Typeface.createFromAsset(am,
                String.format("fonts/%s", "kids.ttf"));

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(CrossWordActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
                Log.d("POSITION", "" + position);

                int start = CrossWord.checkPosition(position);

                for (int i=start; i<start+CrossWord.wordLength; i++) {
                    TextView textView = (TextView) grid.getChildAt(i);
                    textView.setBackgroundResource(R.drawable.selected_box);
                }

            }
        });
    }
}
