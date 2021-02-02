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
    private int hour;
    private String date;
    private String timeS;
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

    public Reservation(String idLesson, String nameTutor, int hour, String TimeS, String idReservation, String date, String course) {
        this.idLesson = idLesson;
        this.nameTutor = nameTutor;
        this.hour = hour;
        this.timeS= TimeS;
        this.idReservation= idReservation;
        this.date=date;
        this.course=course;
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

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void updateReserv()
    {
        DatabaseReference myRefTemp = FirebaseDatabase.getInstance().getReference("Reservations");

        myRefTemp.child(idReservation).setValue(this);
    }

    public String ToString()
    {
        return idLesson+nameTutor+date+timeS;
    }


}
