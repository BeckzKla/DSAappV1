package com.example.dsaappv1.UsersActivity;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dsaappv1.CreateNewProfile.createProfile;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static java.lang.Integer.parseInt;

public class User{
    private String email,fullName, datebrtd, typeU;
    private String tellefNumber, IdUser;
    private List<String> myLessons = new ArrayList<String>();

    private List<String> myReservetion = new ArrayList<String>();

    public List<String> getMyLessons() {
        return myLessons;
    }



    public List<String> getMyReservetion() {
        return myReservetion;
    }

    public void setMyReservetion(List<String> myReservetion) {
        this.myReservetion = myReservetion;
    }

    public String getIdUser() {
        return IdUser;
    }

    public void setIdUser(String idUser) {
        IdUser = idUser;
    }

    public User(String email, String fullName, String datebrtd, String typeU, String tellefNumber, List<String> myLessons,
                List<String> myReservation , String idUser) {
        this.email = email;
        this.fullName = fullName;
        this.datebrtd = datebrtd;
        this.typeU = typeU;
        this.tellefNumber = tellefNumber;
        this.myLessons = myLessons;
        this.myReservetion = myReservation;
        this.IdUser = idUser;
    }

    public void setMyLessons(List<String> myLessons) {
        this.myLessons = myLessons;
    }

    public User() {

    }


    public User(Context context, String email, String fullName, String datebrtd, String typeU, String tellefNumber, String idUser) {
        this.email = email;
        this.fullName = fullName;
        this.datebrtd = datebrtd;
        this.typeU = typeU;
        this.tellefNumber = (tellefNumber);
        this.IdUser = idUser;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDatebrtd() {
        return datebrtd;
    }

    public void setDatebrtd(String datebrtd) {
        this.datebrtd = datebrtd;
    }

    public String getTypeU() {
        return typeU;
    }

    public String getTellefNumber() {
        return tellefNumber;
    }

    public void setTellefNumber(String tellefNumber) {
        this.tellefNumber = tellefNumber;
    }

    public void setTypeU(String typeU) {
        this.typeU = typeU;
    }


    public void addLesson(String idLessons)
    {
        this.myLessons.add(idLessons);
    }

    public void addReservation(String idReservation){this.myReservetion.add(idReservation);}

    public void updateUser()
    {
        DatabaseReference myRefTemp = FirebaseDatabase.getInstance().getReference("users");

        myRefTemp.child(IdUser).setValue(this);
    }
    public void createUser()
    {
        DatabaseReference myRefTemp = FirebaseDatabase.getInstance().getReference("users");

        myRefTemp.child(IdUser).setValue(this);

        if(this.typeU!="Studente")
        {
            myRefTemp = FirebaseDatabase.getInstance().getReference("Tutors");
            myRefTemp.child(this.getIdUser()).setValue(this.getIdUser());
        }

    }

}
