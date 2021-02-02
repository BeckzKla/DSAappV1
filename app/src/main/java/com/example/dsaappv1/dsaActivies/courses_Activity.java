package com.example.dsaappv1.dsaActivies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dsaappv1.Dashboard;
import com.example.dsaappv1.R;
import com.example.dsaappv1.UsersActivity.Constants;
import com.example.dsaappv1.UsersActivity.Lessons;
import com.example.dsaappv1.UsersActivity.User;
import com.example.dsaappv1.UsersActivity.listLessonAdapter;
import com.example.dsaappv1.accessActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class courses_Activity extends AppCompatActivity {

    private ListView listView ;

    private FirebaseDatabase database ;
    private DatabaseReference myRef;
    private String TAG ="courses";
    private Button goBackButton;

    private listLessonAdapter arrayAdapter;

    private androidx.cardview.widget.CardView hSchoolBtn, universityBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_);

        listView=(ListView) findViewById(R.id.listView);
        goBackButton=(Button) findViewById(R.id.goBackButton);
        hSchoolBtn=(androidx.cardview.widget.CardView) findViewById(R.id.HSchoolBtn);
        universityBtn=(androidx.cardview.widget.CardView) findViewById(R.id.universityBtn);

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(courses_Activity.this, Dashboard.class);
                finish();
                startActivity(intent);
            }
        });

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Lessons");


        ArrayList<Lessons> allLesson = new ArrayList<Lessons>();

        Lessons temp = new Lessons() ;

        arrayAdapter = new listLessonAdapter(this, R.layout.support_simple_spinner_dropdown_item, allLesson);


        myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Lessons temp = postSnapshot.getValue(Lessons.class);
/*
                    if(temp.isReservable())
                    {
                        allLesson.add(temp);
                    }
                    else if(temp.getIdTutor()==Constants.myUser.getIdUser())
                    {
                        Constants.myLesson.add(temp);
                    }

 */

                    allLesson.add(temp);

                    Toast.makeText(courses_Activity.this, "classe:"+temp,Toast.LENGTH_SHORT).show();


                }

                    /*


                    for(Lessons l : allLesson)
                    {
                        if(temp.checkDate() && !allLesson.contains(temp))
                        {
                            allLesson.add(temp);
                        }
                        else
                        {
                            allLesson.remove(temp);
                        }
                    }

                     */


                    listView.setAdapter(arrayAdapter);

                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        hSchoolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hSchoolBtn.setBackground(getDrawable(R.color.icon_color));
                universityBtn.setBackground(getDrawable(R.color.white));

                //listLessonAdapter Hsclesson = new listLessonAdapter(courses_Activity.this, R.layout.support_simple_spinner_dropdown_item, arrayAdapter.getLessonsHschool());

                listLessonAdapter Hsclesson = new listLessonAdapter(courses_Activity.this, R.layout.support_simple_spinner_dropdown_item, arrayAdapter.getLessonsHschool());
                listView.setAdapter(Hsclesson);
            }
        });

        universityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hSchoolBtn.setBackground(getDrawable(R.color.white));
                universityBtn.setBackground(getDrawable(R.color.icon_color));

                listLessonAdapter universityLesson = new listLessonAdapter(courses_Activity.this, R.layout.support_simple_spinner_dropdown_item, arrayAdapter.getLessonsUniversity());

                listView.setAdapter(universityLesson);
            }
        });





    }
}