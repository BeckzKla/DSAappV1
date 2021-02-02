package com.example.dsaappv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


import com.example.dsaappv1.UsersActivity.Constants;
import com.example.dsaappv1.UsersActivity.User;
import com.example.dsaappv1.dsaActivies.All_Tutor_Activity;
import com.example.dsaappv1.dsaActivies.Calender_Activity;
import com.example.dsaappv1.dsaActivies.Info_Activity;
import com.example.dsaappv1.dsaActivies.courses_Activity;
import com.example.dsaappv1.dsaActivies.myProfile_Activity;
import com.example.dsaappv1.dsaActivies.myReservation;
import com.example.dsaappv1.dsaActivies.newLesson_Activity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity {


    LinearLayout inforamtion, calender, newlesson, prof , courses, myReserv, myProfile, signout;
    private FirebaseAuth mAuth;

    private FirebaseDatabase database ;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        inforamtion=(LinearLayout) findViewById(R.id.information);
        calender=(LinearLayout) findViewById(R.id.calender);
        newlesson=(LinearLayout) findViewById(R.id.newlesson);
        prof=(LinearLayout) findViewById(R.id.prof);
        courses=(LinearLayout) findViewById(R.id.courses);
        myReserv=(LinearLayout) findViewById(R.id.myreserv);
        myProfile=(LinearLayout) findViewById(R.id.myProfile);
        signout=(LinearLayout) findViewById(R.id.exit);

        mAuth= FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");

        Constants.IDusers= mAuth.getUid();

        inforamtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this, Info_Activity.class);
                finish();
                startActivity(intent);
            }
        });
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this, Calender_Activity.class);
                finish();
                startActivity(intent);
            }
        });
        newlesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this, newLesson_Activity.class);
                finish();
                startActivity(intent);
            }
        });
        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this, All_Tutor_Activity.class);
                finish();
                startActivity(intent);
            }
        });
        courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this, courses_Activity.class);
                finish();
                startActivity(intent);
            }
        });
        myReserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this, myReservation.class);
                finish();
                startActivity(intent);
            }
        });
        myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this, myProfile_Activity.class);
                finish();
                startActivity(intent);
            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this,Intro_Activity.class);
                finish();
                startActivity(intent);
                mAuth.signOut();
            }
        });


        myRef.child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Constants.myUser = snapshot.getValue(User.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}