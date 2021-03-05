package com.example.dsaappv1.UsersActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dsaappv1.R;
import com.example.dsaappv1.dsaActivies.listHourDisp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;



public class Lessons {

    // CLASSE CHE GESTISCE LE ELAZIONI
    // I parametri che descrivono questa classe sono:
    //1) course: ci fornisce informazioni sul tipo di materia
    //2) date: la data in ci sarà la lezione
    //3) timwS e timeE: ci indica l'ora di inizio e fine della lezione
    //4) nameTutor: nome del tutor che svolgerà la lezione
    //5) idLesson: identificativo della lezione
    //6) idTutor: identificativo del tutor
    //7) isFreeHours: questa lista ci dice per ogni ora se essa è libeara è prenotabile o meno
    //8) isReservable: ci dice se questa lezione è ancora libera oppure è è completamente riservata
    //9) isUniversityStudent: ci dice se lo studente è univesitario o meno


    private String course;
    private String date;
    private String timeS;
    private String timeE;
    private String nameTutor;
    private String idLesson;
    private String idTutor;
    private List<Boolean> isFreeHours;
    private boolean isReservable;
    private Boolean isUniversStudent;


    public String getIdTutor() {
        return idTutor;
    }

    public void setReservable(boolean reservable) {
        isReservable = reservable;
    }

    public String getTimeE() {
        return timeE;
    }

    public void setTimeE(String timeE) {
        this.timeE = timeE;
    }

    public void setIdTutor(String idTutor) {
        this.idTutor = idTutor;
    }

    public String getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(String idLesson) {
        this.idLesson = idLesson;
    }

    public List<Boolean> getIsFreeHours() {
        return isFreeHours;
    }

    public void setIsFreeHours(List<Boolean> isFreeHours) {
        this.isFreeHours = isFreeHours;
    }

    public void setIsFreeHours(int pos, boolean value)
    {
        this.isFreeHours.set(pos,value);
    }

    public boolean getIsFreeHours(int pos)
    {
        return this.isFreeHours.get(pos);
    }

    public List<Boolean> getListHourDisp(){return this.isFreeHours;}

    public Boolean getUniversStudent() {
        return isUniversStudent;
    }

    public void setUniversStudent(Boolean universStudent) {
        isUniversStudent = universStudent;
    }

    public Lessons(String course, String date, String timeS, String nameTutor, String timeend, String idLesson, String idTutor, boolean isUniversStudent) {
        this.course = course;
        this.date = date;
        this.timeS = timeS;
        this.nameTutor = nameTutor;
        this.timeE= timeend;
        this.idLesson= idLesson;
        this.idTutor= idTutor;
        this.isUniversStudent=isUniversStudent;

        ArrayList<String> allHours= createListHour();
        isFreeHours=new ArrayList<Boolean>(Arrays.asList(new Boolean[allHours.size()]));
        Collections.fill(this.isFreeHours, Boolean.TRUE);

        isReservable= true;

    }

    public Lessons() {

    }

    public String getNameTutor() {
        return nameTutor;
    }

    public void setNameTutor(String nameTutor) {
        this.nameTutor = nameTutor;
    }


    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeS() {
        return timeS;
    }

    public void setTimeS(String timeS) {
        this.timeS = timeS;
    }

    public String toString()
    {
        return course+nameTutor+date+timeS;
    }


    public void updateLesson()
    {
        DatabaseReference myRefTemp = FirebaseDatabase.getInstance().getReference("Lessons");

        myRefTemp.child(idLesson).setValue(this);
    }

    public boolean checkDate()
    {
        SimpleDateFormat formater=  new SimpleDateFormat("dd/MM/yyyy");
        Date currentTime = Calendar.getInstance().getTime();
        String curranteDate = DateFormat.getDateInstance().format(currentTime);

        Date lessonDate = new SimpleDateFormat("dd/MM/yyyy").parse(this.getDate(), new ParsePosition(1));

        if(lessonDate.after(currentTime))
        {
            return true;
        }
        else
        {
            removeLesson();
            return false;
        }

    }

    public void removeLesson()
    {
        DatabaseReference myRefTemp = FirebaseDatabase.getInstance().getReference("Lessons");

        myRefTemp.child(idLesson).removeValue();

        DatabaseReference myRefTemp1 = FirebaseDatabase.getInstance().getReference("users");
        myRefTemp.child(idTutor).child("myLessons").child(idLesson).removeValue();
    }


    public ArrayList<String> createListHour()
    {
        ArrayList<String> allHours = new ArrayList<String>();
        listHourDisp arrayAdapter;

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date date1 = format.parse(timeS, new ParsePosition(0));
        Date date2 = format.parse(timeE, new ParsePosition(0));

        int hourS= date1.getHours();
        int hourE= date2.getHours();

        long difference = hourE - hourS;
        int minute = date1.getMinutes();

        for(int i=1; i<=difference+1;i++)
        {
            allHours.add(Integer.toString(hourS+i-1)+":"+Integer.toString(minute)+"->"+Integer.toString(hourS+i)+":"+Integer.toString(minute));
        }

        return allHours;
    }

    public void setReservable() {

        for (boolean b : isFreeHours)
        {
            if(b==true)
            {
               isReservable= true;
               break;
            }
        }

        isReservable= false;
    }

    public boolean isReservable() {
        return isReservable;
    }


    //utilizzo la funzione succesiva per avere la stringa corrispondente a una certa ora
    public String returnHourStringFormeStart(int offset)
    {
        String start;

        //per prima cosa vado a convertira la stinga in una data
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        Date date1 = format.parse(timeS, new ParsePosition(0));
        //adesso estraggo l'ora che mi interessa
        int hourS= date1.getHours();

        //ed estraggo anche il minuto dell'inizio della prenotazione
        int minute = date1.getMinutes();

        start= Integer.toString(hourS+offset)+":"+Integer.toString(minute);

        return start;
    }
}

