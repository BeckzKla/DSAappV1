package com.example.dsaappv1.dsaActivies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dsaappv1.Dashboard;
import com.example.dsaappv1.R;

public class Calender_Activity extends AppCompatActivity {

    private Button goBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);


        goBackButton=(Button) findViewById(R.id.goBackButton);

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Calender_Activity.this, Dashboard.class);
                finish();
                startActivity(intent);
            }
        });
    }
}