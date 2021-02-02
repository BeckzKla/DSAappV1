package com.example.dsaappv1.UsersActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dsaappv1.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class listUsersAdapter extends ArrayAdapter<User>
{

    private Context contex;
    private List<User> users;
    private int resorse;


    public listUsersAdapter(Context contex,int resorse,  List<User> users) {
        super(contex,resorse,users);
        this.contex = contex;
        this.resorse= resorse;
        this.users= users;

    }



    @Override
    public View getView(int position, View view, ViewGroup parent) {
        User user = users.get(position);

        LayoutInflater layoutInflater = LayoutInflater.from(contex);

        view = layoutInflater.inflate(R.layout.activity_userslayout,null);

        TextView tutor = (TextView)view.findViewById(R.id.tutor);
        TextView email = (TextView)view.findViewById(R.id.email);

        tutor.setText(user.getFullName());
        email.setText(user.getEmail());
        return view;

    }
}


