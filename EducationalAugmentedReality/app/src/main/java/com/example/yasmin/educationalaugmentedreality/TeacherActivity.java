package com.example.yasmin.educationalaugmentedreality;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class TeacherActivity extends AppCompatActivity {

    EditText wordField;
    EditText descField;
    EditText latField;
    EditText lngField;
    TextView objText;
    LatLng objLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        wordField = (EditText) findViewById(R.id.wordField);
        descField = (EditText) findViewById(R.id.descField);
        latField = (EditText) findViewById(R.id.latText);
        lngField = (EditText) findViewById(R.id.lngText);
        objText = (TextView) findViewById(R.id.object);
    }

    public void onClick(View view) {

        if (view.getId() == R.id.addButton) {
            if (!wordField.toString().matches("") && !descField.toString().matches("")
                    && !latField.toString().matches("") && !lngField.toString().matches("") ){

                Items i = new Items(wordField.getText().toString(), descField.getText().toString(),
                        objLocation);

                Items.itemsList.add(i);

                //Clear text fields ready for new object info input
                wordField.setText("");
                descField.setText("");
                latField.setText("");
                lngField.setText("");

                //Get new heading to flash
                Animation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(300);
                objText.startAnimation(anim);
                objText.setText("Object #" + (Items.getItemsNumber() + 1));
                Log.d("DONE", ""+Items.getItemsNumber());

            }

        }
        else if (view.getId() == R.id.searchLoc){
            Intent intent = new Intent(this, TeacherMapActivity.class);
            startActivityForResult(intent, 1);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            objLocation = data.getExtras().getParcelable("location");
            Toast.makeText(TeacherActivity.this, "" + objLocation,
                    Toast.LENGTH_SHORT).show();

            latField.setText(String.valueOf(objLocation.latitude));
            lngField.setText(String.valueOf(objLocation.longitude));


        }
    }
}
