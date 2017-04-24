package com.example.yasmin.educationalaugmentedreality;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.regex.Pattern;

public class TeacherActivity extends AppCompatActivity {

    EditText wordField;
    EditText descField;
    EditText latField;
    EditText lngField;
    TextView objText;
    TextView wordDis;
    TextView desDis;
    TextView imageDis;
    TextView locDis;
    LatLng objLocation;
    public static boolean cont = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        wordField = (EditText) findViewById(R.id.wordField);
        descField = (EditText) findViewById(R.id.descField);
        latField = (EditText) findViewById(R.id.latText);
        lngField = (EditText) findViewById(R.id.lngText);
        objText = (TextView) findViewById(R.id.object);
        wordDis = (TextView) findViewById(R.id.word);
        desDis = (TextView)findViewById(R.id.desc);
        imageDis = (TextView) findViewById(R.id.image);
        locDis = (TextView) findViewById(R.id.location);

        objText.setTextSize(40);
        wordDis.setTextSize(20);
        desDis.setTextSize(20);
        imageDis.setTextSize(20);
        locDis.setTextSize(20);
    }

    public void onClick(View view) {

        if (view.getId() == R.id.addButton) {
            if (wordField.getText().toString().matches("") || descField.getText().toString().matches("")
                    || latField.getText().toString().matches("")){

                Toast toast = Toast.makeText(this, "You cannot leave any fields empty",
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0, 0);
                toast.show();
            }
            else if (!Pattern.matches(".*[a-zA-Z]+.*", wordField.getText().toString())){
                Toast toast = Toast.makeText(this, "Your word must contain letters only",
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0, 0);
                toast.show();
            }
            else if (wordField.getText().toString().length() > 8){
                Toast toast = Toast.makeText(this, "Your word cannot be more 8 " +
                                "characters long",
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0, 0);
                toast.show();
            }
            else if (descField.getText().toString().length() < 30){
                Toast toast = Toast.makeText(this, "Your word description must be at least 30 " +
                                "characters long",
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0, 0);
                toast.show();
            }
            else{
                Items i = new Items(wordField.getText().toString().toUpperCase(), descField.getText().toString(),
                        objLocation);

                Items.itemsList.add(i);

                //Clear text fields ready for new object info input
                wordField.setText("");
                descField.setText("           ");
                latField.setText("                 ");
                lngField.setText("                 ");

                //Get new heading to flash
                Animation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(300);
                objText.startAnimation(anim);
                objText.setText("Object #" + (Items.getItemsNumber() + 1));

                cont = true;
            }

        }
        else if (view.getId() == R.id.searchLoc){
            Intent intent = new Intent(this, TeacherMapActivity.class);
            startActivityForResult(intent, 1);
        }
        else if (view.getId() == R.id.doneButton){
            cont = true;
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            objLocation = data.getExtras().getParcelable("location");

            latField.setText(String.valueOf(objLocation.latitude));
            lngField.setText(String.valueOf(objLocation.longitude));
        }
    }
}
