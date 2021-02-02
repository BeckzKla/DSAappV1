package com.example.dsaappv1.dsaActivies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dsaappv1.Dashboard;
import com.example.dsaappv1.R;
import com.example.dsaappv1.UsersActivity.Constants;
import com.example.dsaappv1.UsersActivity.Lessons;
import com.example.dsaappv1.UsersActivity.Reservation;
import com.example.dsaappv1.UsersActivity.User;
import com.example.dsaappv1.UsersActivity.listLessonAdapter;
import com.example.dsaappv1.UsersActivity.listReservationAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class myReservation extends AppCompatActivity {

    private ListView listView ;
    private Button goBackButton;
    private FirebaseDatabase database ;
    private DatabaseReference myRef;
    private listReservationAdapter arrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservation);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Reservations");
        listView = (ListView) findViewById(R.id.listView);
        goBackButton = (Button) findViewById(R.id.goBackButton);


        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myReservation.this, Dashboard.class);
                finish();
                startActivity(intent);
            }
        });

        ArrayList<Reservation> myReservation = new ArrayList<Reservation>();

        arrayAdapter = new listReservationAdapter(this, R.layout.support_simple_spinner_dropdown_item, myReservation);



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Reservation temp = postSnapshot.getValue(Reservation.class);
                    if(Constants.myUser.getMyReservetion().contains(temp.getIdReservation()))
                    {
                        myReservation.add(temp);
                    }
                }

                listView.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}