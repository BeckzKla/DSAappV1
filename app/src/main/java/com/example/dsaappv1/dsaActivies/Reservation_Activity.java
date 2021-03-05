package com.example.dsaappv1.dsaActivies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dsaappv1.Dashboard;
import com.example.dsaappv1.R;
import com.example.dsaappv1.UsersActivity.Constants;
import com.example.dsaappv1.UsersActivity.listLessonAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Reservation_Activity extends AppCompatActivity {

    private TextView course, tutor, date;
    private ImageView goBackBtn;
    private ListView allHourDisp;
    private Button reservBtn;
    private listHourDisp arrayAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_);

        course = (TextView) findViewById(R.id.courseC);
        tutor = (TextView) findViewById(R.id.tutor);
        date = (TextView) findViewById(R.id.dateC);
        goBackBtn = (ImageView) findViewById(R.id.goBackButton);
        allHourDisp=(ListView) findViewById(R.id.allhour);
        reservBtn=(Button) findViewById(R.id.reservBtn);

        course.setText(Constants.lessonsTrasp.getCourse());
        tutor.setText(Constants.lessonsTrasp.getNameTutor());
        date.setText(Constants.lessonsTrasp.getDate());

        insertListView();

        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Reservation_Activity.this, courses_Activity.class);
                finish();
                startActivity(intent);
            }
        });

        reservBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //vado ad aggiorna re

                arrayAdapter.updateLesson();
                Constants.lessonsTrasp.updateLesson();

                Constants.myUser.addReservation(Constants.lessonsTrasp.getIdLesson());

                Intent intent=new Intent(Reservation_Activity.this, courses_Activity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    public void insertListView()
    {
        arrayAdapter = new listHourDisp(this, R.layout.support_simple_spinner_dropdown_item, Constants.lessonsTrasp.createListHour());

        allHourDisp.setAdapter(arrayAdapter);
    }


}