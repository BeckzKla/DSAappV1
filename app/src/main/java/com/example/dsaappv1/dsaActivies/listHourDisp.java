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
import com.example.dsaappv1.UsersActivity.Reservation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// classe ListhourDisp mi serve per gestire al check list dove posso prenotare le ore
// questa classe si occupa della gestione dell'interfaccia e del controllo delle ore

public class listHourDisp extends ArrayAdapter<String> {
    //queste due parti sono i riferimenti al database che mi servono nel io crei
    private FirebaseDatabase database ;
    private DatabaseReference myRef;


    private Context contex;
    private List<String> allPoss;
    private int resorse;
    private List<CheckBox> listCheckBox;

    boolean[] listBeforeTochange;


    public listHourDisp(Context contex,int resorse,  List<String> hours) {
        super(contex,resorse,hours);
        this.contex = contex;
        this.resorse= resorse;
        this.allPoss= hours;
        listCheckBox= new ArrayList<CheckBox>();

        //prendo la liste delle ore prima di cambiarl
        listBeforeTochange= toPrimitiveArray(Constants.lessonsTrasp.getListHourDisp());

    }

    private boolean[] toPrimitiveArray(final List<Boolean> booleanList) {
        final boolean[] primitives = new boolean[booleanList.size()];
        int index = 0;
        for (Boolean object : booleanList) {
            primitives[index++] = object;
        }
        return primitives;
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
            hourDisp.setEnabled(true);
        }
        else
        {
            hourDisp.setChecked(true);
        }

        listCheckBox.add(hourDisp);


        return view;

    }

    // tengo traccia delle che ore che questo utente sta prenotando , cosi da poter creare una
    private boolean[] hourReserv ;


    // aggiorno la lista dei boolenai che contiene le ore disponibili e prenotabili
    public void updateLesson()
    {
        hourReserv = new boolean[listCheckBox.size()];



        for(int i =0; i<listCheckBox.size();i++)
        {
            try{
                if( Constants.lessonsTrasp.getIsFreeHours(i))
                {
                    Constants.lessonsTrasp.setIsFreeHours(i, !listCheckBox.get(i).isChecked());
                    hourReserv[i]= true;

                }
                else
                {
                    hourReserv[i]= false;
                }

            }
            catch (Exception exeptione)
            {
                Toast.makeText(this.contex, "PROBLEMA CON LA PRENOTAZIONE",Toast.LENGTH_SHORT).show();
            }

        }


        //prendo la liste delle ore dopo di cambiarla
        boolean[] listAfterTochange= toPrimitiveArray(Constants.lessonsTrasp.getListHourDisp());


        boolean[] changesList =listAfterTochange;

        for(int i =0; i<listAfterTochange.length; i++)
        {
            changesList[i]= listAfterTochange[i] ^ listBeforeTochange[i];
        }



        checkForReservation(changesList);


        Constants.lessonsTrasp.setReservable();

    }

    public void checkForReservation(boolean[] changeList)
    {
        boolean holdValue= false;
        boolean currentValue= false;

        String startH= "";
        String finishH;



        for(int i =0; i<changeList.length-1;i++)
        {
            holdValue= currentValue;
            currentValue= changeList[i];


            //creao una prenotzione ogni volta che ho una continuità nelle ore ( esempio: se l'utente prenota dalle 8-10 e poi dalle 11-12 la stessa lezione
            //queste sono due prenotazioni distinte
            if(changeList[i] && !holdValue)
            {
                startH=  Constants.lessonsTrasp.returnHourStringFormeStart(i);
            }
            else if(!changeList[i] && holdValue)
            {
                finishH= Constants.lessonsTrasp.returnHourStringFormeStart(i);

                DatabaseReference myRefTemp = FirebaseDatabase.getInstance().getReference("Reservations");
                String idReservation = myRefTemp.push().getKey();

                Reservation newResv = new Reservation(Constants.lessonsTrasp, Constants.myUser.getFullName(), startH, finishH, idReservation);

                newResv.updateReserv();

                Constants.myUser.addReservation(newResv.getIdReservation());
                Constants.myUser.updateUser();

            }



        }
    }




}
