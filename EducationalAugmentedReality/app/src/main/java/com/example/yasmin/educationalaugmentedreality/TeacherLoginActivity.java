package com.example.yasmin.educationalaugmentedreality;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class TeacherLoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        username = (EditText) findViewById(R.id.userText);
        password = (EditText) findViewById(R.id.passwordText);

    }
    public void onClick(View view){

        if (view.getId() == R.id.playButton) {

            if (username.getText().toString().equals("admin") &&
                password.getText().toString().equals("password")){

                Intent intent = new Intent(this, TeacherMapActivity.class);
                startActivity(intent);
            }

        }
    }
}
