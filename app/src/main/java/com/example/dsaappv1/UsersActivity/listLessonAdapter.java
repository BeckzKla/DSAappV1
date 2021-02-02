package com.example.dsaappv1.UsersActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dsaappv1.Dashboard;
import com.example.dsaappv1.R;
import com.example.dsaappv1.dsaActivies.All_Tutor_Activity;
import com.example.dsaappv1.dsaActivies.Reservation_Activity;
import com.example.dsaappv1.dsaActivies.courses_Activity;
import com.example.dsaappv1.dsaActivies.newLesson_Activity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.widget.NumberPicker;
import android.widget.Toast;

public class listLessonAdapter extends ArrayAdapter<Lessons>
{

    private Context contex;
    private List<Lessons> lessons;
    private int resorse;

    private FirebaseDatabase database ;
    private DatabaseReference myRef;

    private int hourReserv;

    int res=5;

    public listLessonAdapter(Context contex,int resorse,  List<Lessons> lessons) {
        super(contex,resorse,lessons);
        this.contex = contex;
        this.resorse= resorse;
        this.lessons= lessons;

    }


    public listLessonAdapter(Context contex,int resorse,  List<Lessons> lessons, boolean isUniversityS)
    {
        super(contex,resorse,lessons);
        this.contex = contex;
        this.resorse= resorse;

        this.lessons= new ArrayList<>();


        for(Lessons l : lessons)
        {
            if(l.getUniversStudent()==isUniversityS)
            {
                this.lessons.add(l);
            }
        }
    }

    public listLessonAdapter(Context contex,int resorse) {
        super(contex,resorse);
        this.contex = contex;
        this.resorse= resorse;

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Lessons");

    }

    /* SE RIMANE COMENTATO ELIMINARE
    public void loadLesson()
    {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Lessons temp = postSnapshot.getValue(Lessons.class);

                    lessons.add(temp);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


     */

    public List<Lessons> getLessons() {
        return lessons;
    }

    public void add(Lessons l)
    {
        lessons.add(l);
    }

    //prendo le lezioni per ragazzi delle seuperiori
    public List<Lessons> getLessonsHschool()
    {
        List<Lessons> lessonsHschool = new ArrayList<Lessons>();

        for(Lessons l : lessons)
        {

            if(!l.getUniversStudent())
            {
                Toast.makeText(this.contex, "classe:"+l.getUniversStudent(),Toast.LENGTH_SHORT).show();
                lessonsHschool.add(l);
            }
        }
        return lessonsHschool;
    }


    //prendo le lezioni per l'università
    public List<Lessons> getLessonsUniversity()
    {
        List<Lessons> lessonsHschool = new ArrayList<Lessons>();

        for(Lessons l : lessons)
        {

            if(l.getUniversStudent())
            {
                Toast.makeText(this.contex, "classe:"+l.getUniversStudent(),Toast.LENGTH_SHORT).show();
                lessonsHschool.add(l);
            }
        }
        return lessonsHschool;
    }


    //per disegnare la lista a schermo
    @Override
    public View getView(int position,  View view, ViewGroup parent) {
        Lessons lesson = lessons.get(position);

        LayoutInflater layoutInflater = LayoutInflater.from(contex);

        view = layoutInflater.inflate(R.layout.activity_lessonlayout,null);

        TextView course = (TextView)view.findViewById(R.id.courseC);
        TextView date = (TextView)view.findViewById(R.id.dateC);
        TextView timeS = (TextView)view.findViewById(R.id.TimeS);
        TextView timeE = (TextView)view.findViewById(R.id.timeE);
        TextView tutor = (TextView)view.findViewById(R.id.tutor);
        Button reservBtn  = (Button) view.findViewById(R.id.reservBtn);



        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date date1 = format.parse(lesson.getTimeS(), new ParsePosition(0));
        Date date2 = format.parse(lesson.getTimeE(), new ParsePosition(0));
        long difference = date2.getHours() - date1.getHours();



        //resrvHours.setMaxValue((int)(difference));


        course.setText(lesson.getCourse());
        date.setText(lesson.getDate());
        timeS.setText(lesson.getTimeS());
        tutor.setText(lesson.getNameTutor());
        timeE.setText(lesson.getTimeE());


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Reservetions");


        hourReserv=1;


        reservBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /*
                String idLesson = lesson.getIdLesson();
                String tutor = lesson.getNameTutor();
                String timeS= lesson.getTimeS();
                String date = lesson.getDate();
                String course = lesson.getCourse();

                String mReservId = myRef.push().getKey();

                Reservation reservation = new Reservation(idLesson, tutor,hourReserv ,timeS, mReservId, date, course);

                reservation.updateReserv();

                Constants.myUser.addReservation(reservation.getIdReservation());

                Constants.myUser.updateUser();

                 */

                Constants.lessonsTrasp=lesson;
                Intent intent=new Intent(contex, Reservation_Activity.class);
                contex.startActivity(intent);

            }
        });

        return view;

    }


    public void update()
    {
        for(Lessons l : lessons)
        {
            l.updateLesson();
        }
    }
}

