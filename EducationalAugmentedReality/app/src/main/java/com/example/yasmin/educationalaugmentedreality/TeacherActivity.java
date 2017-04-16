package com.example.yasmin.educationalaugmentedreality;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class TeacherActivity extends AppCompatActivity {

    EditText wordField;
    EditText descField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        wordField = (EditText) findViewById(R.id.wordField);
        descField = (EditText) findViewById(R.id.descField);
    }

    public void onClick(View view) {

        if (view.getId() == R.id.addButton) {
            if (!wordField.toString().matches("") || !descField.toString().matches("")){
                //Can complete action
            }

        }
    }
}
