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

public class listReservationAdapter extends ArrayAdapter<Reservation>
{

    private Context contex;
    private List<Reservation> reservations;
    private int resorse;

    private int hourReserv;

    public listReservationAdapter(Context contex,int resorse,  List<Reservation> reservations) {
        super(contex,resorse,reservations);
        this.contex = contex;
        this.resorse= resorse;
        this.reservations= reservations;

    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Reservation reserv = reservations.get(position);

        LayoutInflater layoutInflater = LayoutInflater.from(contex);

        view = layoutInflater.inflate(R.layout.activity_reservationslayout,null);

        TextView course = (TextView)view.findViewById(R.id.courseC);
        TextView date = (TextView)view.findViewById(R.id.dateC);
        TextView timeS = (TextView)view.findViewById(R.id.TimeS);
        TextView tutor = (TextView)view.findViewById(R.id.tutor);


        course.setText(reserv.getCourse());
        date.setText(reserv.getDate());
        timeS.setText(reserv.getTimeS());
        tutor.setText(reserv.getNameTutor());


        return view;

    }
}


