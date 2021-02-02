package com.example.dsaappv1.dsaActivies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dsaappv1.Dashboard;
import com.example.dsaappv1.R;

public class Info_Activity extends AppCompatActivity {

    private ImageView fbBtn, instBtn, lkdBtn;
    private ImageView goBackButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_);

        goBackButton=(ImageView) findViewById(R.id.goBackButton);
        fbBtn=(ImageView) findViewById(R.id.fb);
        instBtn=(ImageView) findViewById(R.id.instagram);
        lkdBtn=(ImageView) findViewById(R.id.linkedin);

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Info_Activity.this, Dashboard.class);
                finish();
                startActivity(intent);
            }
        });

        fbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"));
                startActivity(intent);
            }
        });

        instBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/"));
                startActivity(intent);
            }
        });

        lkdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/feed/"));
                startActivity(intent);
            }
        });


    }
}