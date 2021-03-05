package com.example.dsaappv1.dsaActivies;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dsaappv1.Dashboard;
import com.example.dsaappv1.R;
import com.example.dsaappv1.UsersActivity.Constants;
import com.example.dsaappv1.UsersActivity.Lessons;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)
public class newLesson_Activity extends AppCompatActivity{




    EditText coruse;
    TextView date, timeS, timeE, universityBool, hSchoolBool;
    Button back ,create;
    String TAG ="resev_Activity";

    private boolean unBool;

    private FirebaseDatabase database ;
    private DatabaseReference myRef;



    //usato per poter ricevere la data del calendario
    private Calendar c;
    private DatePickerDialog dpd;

    private String typeSS;
    int hoursVal;
    private Button goBackButton;

    private DatePickerDialog.OnDateSetListener datelistener;

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



        setContentView(R.layout.activity_newlesson);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");


        database= FirebaseDatabase.getInstance();
        myRef= database.getReference("Lessons");

        coruse= (EditText) findViewById(R.id.course);
        date= (TextView) findViewById(R.id.dateReserv);
        timeS= (TextView) findViewById(R.id.timeStart);
        timeE= (TextView) findViewById(R.id.timeEnd);
        universityBool= (TextView) findViewById(R.id.typeSU);
        hSchoolBool= (TextView) findViewById(R.id.typeSS);
        create= (Button) findViewById(R.id.createBTN);

        goBackButton=(Button) findViewById(R.id.goBackButton);


        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(newLesson_Activity.this, Dashboard.class);
                finish();
                startActivity(intent);
            }
        });




        //populateSpinnerType();

        universityBool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unBool=true;
                universityBool.setBackground(getDrawable(R.drawable.custom_input2));
                hSchoolBool.setBackground(getDrawable(R.drawable.custom_input));
            }
        });

        hSchoolBool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unBool=false;
                universityBool.setBackground(getDrawable(R.drawable.custom_input));
                hSchoolBool.setBackground(getDrawable(R.drawable.custom_input2));
            }
        });





        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Calendar c= Calendar.getInstance();

                int day= c.get(Calendar.DAY_OF_MONTH);
                int month= c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                DatePickerDialog datePicker = new DatePickerDialog(newLesson_Activity.this, datelistener,year,month,day);

                //datePicker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                datePicker.show();



            }
        }
        );


        datelistener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month = month+1;
                date.setText(dayOfMonth +"/"+month +"/"+year);

            }
        };


        timeS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c= Calendar.getInstance();


                int hour= c.get(Calendar.HOUR_OF_DAY);
                int minute= c.get(Calendar.MINUTE);



                TimePickerDialog timePickerDialog = new TimePickerDialog(newLesson_Activity.this, android.R.style.Theme_Holo_Dialog_MinWidth , new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int HOUR, int MINUTE) {


                        timeS.setText(HOUR+":"+MINUTE );
                    }
                }, hour, minute, true);

                timePickerDialog.show();
            }

        }
        );

        timeE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c= Calendar.getInstance();


                int hour= c.get(Calendar.HOUR_OF_DAY);
                int minute= c.get(Calendar.MINUTE);



                TimePickerDialog timePickerDialog = new TimePickerDialog(newLesson_Activity.this, android.R.style.Theme_Holo_Dialog_MinWidth,  new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int HOUR, int MINUTE) {


                        timeE.setText(HOUR+":"+MINUTE );
                    }
                }, hour, minute, true);



                timePickerDialog.show();


            }

        }
        );




        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cousrseS= coruse.getText().toString().trim();

                String dateS= date.getText().toString().trim();

                String timeSS= timeS.getText().toString().trim();
                String timeES= timeE.getText().toString().trim();

                //String typeStudS= spinnerTStudent.getSelectedItem().toString();

                String mReservId = myRef.push().getKey();

                Lessons newLesson = new Lessons(cousrseS, dateS, timeSS, Constants.myUser.getFullName(), timeES, mReservId, Constants.myUser.getIdUser(),unBool);

                newLesson.updateLesson();

                Constants.myUser.addLesson(mReservId);

                Constants.myUser.updateUser();
            }
        });


    }

    /*
    private void populateSpinnerType() {

        String[] tipeUsers= new String[3];

        ArrayAdapter<String> TyperUsers = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.type_Student));
        spinnerTStudent.setAdapter(TyperUsers);
    }
    */



}