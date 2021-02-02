package com.example.dsaappv1.dsaActivies;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dsaappv1.Dashboard;
import com.example.dsaappv1.R;
import com.example.dsaappv1.UsersActivity.Constants;
import com.example.dsaappv1.UsersActivity.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class myProfile_Activity extends AppCompatActivity {

    private FirebaseDatabase database ;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;

    private TextView email,fullName,tellefNumber,datebrt, typeU;

    private ImageView goBackButton,bM4,bM3, bM2, bM1;

    private TextView nameUserUi, typeUserUi, SBtn, TBtn, EBtn;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_);


        email= (EditText) findViewById(R.id.emailU);
        fullName= (EditText) findViewById(R.id.nameU);
        tellefNumber= (EditText) findViewById(R.id.telefonN);
        datebrt= (EditText) findViewById(R.id.dateBrtd);
        goBackButton=(ImageView) findViewById(R.id.goBackButton);
        bM1=(ImageView) findViewById(R.id.bM1);
        bM2=(ImageView) findViewById(R.id.bM2);
        bM3=(ImageView) findViewById(R.id.bM3);
        bM4=(ImageView) findViewById(R.id.bM4);

        nameUserUi=(TextView) findViewById(R.id.nameUserUI);
        typeUserUi=(TextView) findViewById(R.id.typeUserUI);
        SBtn=(TextView) findViewById(R.id.SBtn);
        TBtn=(TextView) findViewById(R.id.TBtn);
        EBtn=(TextView) findViewById(R.id.EBtn);




        nameUserUi.setText(Constants.myUser.getFullName());
        if(Constants.myUser.getTypeU()=="Studente")
        {
            typeUserUi.setText(Constants.myUser.getTypeU());
            SBtn.setBackground(getDrawable(R.drawable.custom_input2));
            SBtn.setTextColor(0xFFFFFFFF);
        }
        else if(Constants.myUser.getTypeU()=="Tutor")
        {
            typeUserUi.setText(Constants.myUser.getTypeU());
            TBtn.setBackground(getDrawable(R.drawable.custom_input2));
            TBtn.setTextColor(0xFFFFFFFF);
        }
        else
        {
            typeUserUi.setText("Studente & Tutor");
            EBtn.setBackground(getDrawable(R.drawable.custom_input2));
            EBtn.setTextColor(0xFFFFFFFF);
        }


        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.myUser.updateUser();
                Intent intent=new Intent(myProfile_Activity.this, Dashboard.class);
                finish();
                startActivity(intent);
            }
        });


        SBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.myUser.setTypeU("Studente");
                SBtn.setBackground(getDrawable(R.drawable.custom_input2));
                TBtn.setBackground(getDrawable(R.drawable.custom_input));
                EBtn.setBackground(getDrawable(R.drawable.custom_input));
                typeUserUi.setText(Constants.myUser.getTypeU());
            }
        });


        TBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.myUser.setTypeU("Tutor");
                SBtn.setBackground(getDrawable(R.drawable.custom_input));
                TBtn.setBackground(getDrawable(R.drawable.custom_input2));
                EBtn.setBackground(getDrawable(R.drawable.custom_input));
                typeUserUi.setText(Constants.myUser.getTypeU());
            }
        });


        EBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.myUser.setTypeU("Entrambi");
                SBtn.setBackground(getDrawable(R.drawable.custom_input));
                TBtn.setBackground(getDrawable(R.drawable.custom_input));
                EBtn.setBackground(getDrawable(R.drawable.custom_input2));
                typeUserUi.setText("Studente & Tutor");
            }
        });

        bM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
        mAuth= FirebaseAuth.getInstance();



        fullName.setText(Constants.myUser.getFullName());
        email.setText(Constants.myUser.getEmail());
        tellefNumber.setText(Constants.myUser.getTellefNumber());
        datebrt.setText(Constants.myUser.getDatebrtd());


    }
}