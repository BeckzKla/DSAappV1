package com.example.dsaappv1.CreateNewProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dsaappv1.Dashboard;
import com.example.dsaappv1.R;
import com.example.dsaappv1.UsersActivity.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Locale;

public class CreateProfile2 extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase database ;
    private DatabaseReference myRef;

    private String TAG ="createPActivity2";

    private Calendar c;
    private DatePickerDialog dpd;


    private EditText fullName,tellefNumber;

    private TextView datebrtd, typeU;

    Spinner spinerTyper ;


    private Button create;
    private String typseUS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String languageToLoad  = "it"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        this.setContentView(R.layout.activity_create_profile2);


        datebrtd= (TextView) findViewById(R.id.birthdayDate);
        typeU= (TextView) findViewById(R.id.typeU);
        fullName= (EditText) findViewById(R.id.Fname);
        tellefNumber= (EditText) findViewById(R.id.tellefNumber);
        create= (Button) findViewById(R.id.create);
        spinerTyper= (Spinner) findViewById(R.id.spinnerType);

        populateSpinnerType();

        mAuth= FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");




        datebrtd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c= Calendar.getInstance();
                int day= c.get(Calendar.DAY_OF_MONTH);
                int month= c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd= new DatePickerDialog(CreateProfile2.this, new  DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker dataPicker, int Year , int Month , int Day) {
                        datebrtd.setText(Day +"/"+Month +"/"+Year);
                    }

                },day,month,year);


                dpd.show();


            }
        }
        );


        typeU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typseUS= spinerTyper.getSelectedItem().toString();
                Toast.makeText(CreateProfile2.this, "tipe: "+typseUS, Toast.LENGTH_SHORT).show();
            }
        }
        );




        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database= FirebaseDatabase.getInstance();
                myRef= database.getReference("users");

                String fullNameS= fullName.getEditableText().toString();
                Intent intent = getIntent();
                String emailS= intent.getStringExtra(createProfile.EXTRA_EMAIL);
                String tellefNumberS= tellefNumber.getEditableText().toString();
                String datebrtS = datebrtd.getText().toString();
                typseUS= spinerTyper.getSelectedItem().toString();


                String idUser = mAuth.getUid();

                User uD = new User(CreateProfile2.this,emailS, fullNameS, datebrtS, typseUS, tellefNumberS, idUser);

                uD.createUser();



                //myRef.child(idUser).setValue(uD);

                //DatabaseReference myRefTemp = FirebaseDatabase.getInstance().getReference("Tutors");
                //myRefTemp.child(mAuth.getUid()).setValue(this);

                Intent intent2 = new Intent(CreateProfile2.this, Dashboard.class);
                finish();
                startActivity(intent2);


            }
        }
        );
    }

    private void populateSpinnerType() {
        String[] tipeUsers= new String[3];

        ArrayAdapter<String> TyperUsers = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.type_User));
        spinerTyper.setAdapter(TyperUsers);
    }





}