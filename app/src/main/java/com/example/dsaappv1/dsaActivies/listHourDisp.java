package com.example.dsaappv1.dsaActivies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dsaappv1.R;
import com.example.dsaappv1.UsersActivity.Constants;
import com.example.dsaappv1.UsersActivity.Lessons;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class listHourDisp extends ArrayAdapter<String> {
    private Context contex;
    private List<String> allPoss;
    private int resorse;
    private List<CheckBox> listCheckBox;

    public listHourDisp(Context contex,int resorse,  List<String> hours) {
        super(contex,resorse,hours);
        this.contex = contex;
        this.resorse= resorse;
        this.allPoss= hours;
        listCheckBox= new ArrayList<CheckBox>();
    }

    //per disegnare la lista a schermo
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        String possible = allPoss.get(position);

        LayoutInflater layoutInflater = LayoutInflater.from(contex);

        view = layoutInflater.inflate(R.layout.hour_reserv,null);

        CheckBox hourDisp = (CheckBox)view.findViewById(R.id.hour_disp);

        hourDisp.setText(possible);

        if( Constants.lessonsTrasp.getIsFreeHours(position))
        {
            hourDisp.setChecked(true);
        }
        else
        {
            hourDisp.setEnabled(true);
        }


        listCheckBox.add(hourDisp);

        return view;

    }


    public void updateLesson()
    {
        for(int i =0; i<listCheckBox.size();i++)
        {
            try{
                Constants.lessonsTrasp.setIsFreeHours(i, listCheckBox.get(i).isChecked());
            }
            catch (Exception exeptione)
            {
                Toast.makeText(this.contex, "PROBLEMA CON LA PRENOTAZIONE",Toast.LENGTH_SHORT).show();
            }

        }

        Constants.lessonsTrasp.setReservable();

    }

}
