package com.example.dsaappv1.CreateNewProfile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dsaappv1.R;
import com.example.dsaappv1.accessActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class createProfile extends AppCompatActivity {
    public static final String EXTRA_EMAIL= "com.example.dsaappv1.email";
    private FirebaseAuth mAuth;
    private FirebaseDatabase database ;
    private DatabaseReference myRef;

    EditText email, password;
    private Button next, rtoAccess;
    private String TAG ="createPActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);


        // autetication and database structure
        mAuth= FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");

        email= (EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);
        next= (Button) findViewById(R.id.next);
        rtoAccess= (Button) findViewById(R.id.rToAccess);


        if(mAuth.getCurrentUser()!=null)
        {

            //user not logged in
            finish();
            Toast.makeText(createProfile.this, "Profile already exists.",
                    Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), accessActivity.class));

        }



        rtoAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(createProfile.this,accessActivity.class);
                finish();
                startActivity(intent);

            }
        }
        );

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getemail= email.getText().toString().trim();
                String getpassword= password.getText().toString().trim();

                createAccount(getemail,getpassword);
                mAuth = FirebaseAuth.getInstance();



            }
        }
        );

    }

    private void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            userProfile();

                            Intent intent = new Intent(createProfile.this, CreateProfile2.class);
                            intent.putExtra(EXTRA_EMAIL,email);
                            startActivity(intent);


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(createProfile.this, "Questo profilo è già esistente",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


    private void userProfile()
    {
        FirebaseUser user = mAuth.getCurrentUser();
        if(user!=null)
        {
            UserProfileChangeRequest profileUpdate= new UserProfileChangeRequest.Builder()
                    //.setPhotoUri(Uri.parse("http"))   Qui si può anche aggiungere un link ad un'immaigne
                    .build();

            user.updateProfile(profileUpdate)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                                Log.d("TESTING","User profile update");
                        }
                    });
        }

    }



}