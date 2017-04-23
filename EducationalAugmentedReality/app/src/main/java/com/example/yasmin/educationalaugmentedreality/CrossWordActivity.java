package com.example.yasmin.educationalaugmentedreality;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

                //Clear all previous selected boxes
                for (int i=0; i<100; i++){
                    TextView textView = (TextView) grid.getChildAt(i);
                    textView.setBackgroundResource(R.drawable.rounded_box);
                    //Set unused boxes as transparent again
                    if (String.valueOf(CrossWord.getItem(i)).equals(" ")){
                        textView.setBackgroundResource(R.drawable.rounded_box_transparent);
                    }
                }


                Log.d("WORDLENGTH", "START: "+start);

                if (CrossWord.wordOrientation.equals("ACROSS")) {
                    for (int i = start; i < start + CrossWord.wordLength; i++) {
                        TextView textView = (TextView) grid.getChildAt(i);
                        textView.setBackgroundResource(R.drawable.selected_box);
                    }
                }
                /*
                //For DOWN, need to add 10 with each letter
                else {
                    for (int i = start; i< (start + CrossWord.wordLength)*10; i=i+10) {
                        TextView textView = (TextView) grid.getChildAt(i);
                        textView.setBackgroundResource(R.drawable.selected_box);
                    }
                }*/
            }
        });
    }

    public void onClick(View view) {

        if (view.getId() == R.id.mapButton) {

            if (CrossWord.selectedWord.equals("")){
                Toast.makeText(CrossWordActivity.this, "Select an empty box before continuing",
                        Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(this, MapsActivity.class);
                startActivityForResult(intent, 1);
            }
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            //PRINT WORD ONTO CROSSWORD
        }
    }
}
