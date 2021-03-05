package com.example.dsaappv1.UsersActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.dsaappv1.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Reservation {


    private String idLesson;
    private String nameTutor;
    private String date;
    private String timeS;
    private String timeE;
    private String idReservation;
    private String course;

    public String getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(String idLesson) {
        this.idLesson = idLesson;
    }

    public String getDate() {
        return date;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Reservation(String idLesson, String nameTutor, String  TimeE, String TimeS, String date, String course) {
        this.idLesson = idLesson;
        this.nameTutor = nameTutor;
        this.timeE = TimeE;
        this.timeS= TimeS;
        this.date=date;
        this.course=course;



        //updateReserv();

    }

    public Reservation(Lessons lesson, String nameTutor, String TimeS, String TimeE, String idReservation) {
        this.idLesson = lesson.getIdLesson();
        this.nameTutor = nameTutor;
        this.timeE = TimeE;
        this.timeS= TimeS;
        this.date=lesson.getDate();
        this.course=lesson.getCourse();
        this.idReservation= idReservation;




        //myRefTemp.child(this.idReservation).setValue("ciaociccio");

    }

    public String getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(String idReservation) {
        this.idReservation = idReservation;
    }

    public String getTimeS() {
        return timeS;
    }

    public void setTimeS(String timeS) {
        this.timeS = timeS;
    }

    public Reservation() {
    }

    public String getNumberLesson() {
        return idLesson;
    }

    public void setNumberLesson(String numberLesson) {
        this.idLesson = numberLesson;
    }

    public String getNameTutor() {
        return nameTutor;
    }

    public void setNameTutor(String nameTutor) {
        this.nameTutor = nameTutor;
    }

    public String getTimeE() {
        return timeE;
    }

    public void setTimeE(String timeE) {
        this.timeE = timeE;
    }

    public void updateReserv()
    {
        DatabaseReference myRefTemp = FirebaseDatabase.getInstance().getReference("Reservation");

        myRefTemp.child(idReservation).setValue(this);
    }

    public String ToString()
    {
        return idLesson+nameTutor+date+timeS;
    }


}
