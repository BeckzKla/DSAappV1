package com.example.dsaappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dsaappv1.CreateNewProfile.createProfile;
import com.google.firebase.auth.FirebaseAuth;

public class Intro_Activity extends AppCompatActivity {

    private Button goToCreateProfile;
    private String TAG ="introActivity";
    private FirebaseAuth mAuth;
    private TextView login;
    private ImageView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_);

        mAuth= FirebaseAuth.getInstance();

        login= (TextView) findViewById(R.id.login);
        register= (ImageView) findViewById(R.id.register);

        if(mAuth.getCurrentUser()!=null)
        {
            //user not logged in
            goIn();

        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intro_Activity.this,accessActivity.class);
                finish();
                startActivity(intent);
            }
        }
        );

        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intro_Activity.this,createProfile.class);
                finish();
                startActivity(intent);
             }
        }


        );

    }

    private void goIn()
    {
        Intent intent=new Intent(Intro_Activity.this,Dashboard.class);
        finish();
        startActivity(intent);
    }
}