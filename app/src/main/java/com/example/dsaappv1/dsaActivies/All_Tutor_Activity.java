package com.example.dsaappv1.dsaActivies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.dsaappv1.Dashboard;
import com.example.dsaappv1.R;
import com.example.dsaappv1.UsersActivity.Constants;
import com.example.dsaappv1.UsersActivity.Reservation;
import com.example.dsaappv1.UsersActivity.User;
import com.example.dsaappv1.UsersActivity.listReservationAdapter;
import com.example.dsaappv1.UsersActivity.listUsersAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class All_Tutor_Activity extends AppCompatActivity {


    private ListView listView ;
    private Button goBackButton;
    private FirebaseDatabase database ;
    private DatabaseReference myRef;
    private listUsersAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__tutor_);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
        listView = (ListView) findViewById(R.id.listView);
        goBackButton = (Button) findViewById(R.id.goBackButton);


        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(All_Tutor_Activity.this, Dashboard.class);
                finish();
                startActivity(intent);
            }
        });

        ArrayList<User> allTutor = new ArrayList<User>();

        arrayAdapter = new listUsersAdapter(this, R.layout.support_simple_spinner_dropdown_item, allTutor);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    User temp = postSnapshot.getValue(User.class);

                    if(temp.getTypeU()!="Studente")
                    {
                        allTutor.add(temp);
                        Log.w("all_tutor",allTutor.get(0).getTypeU());
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