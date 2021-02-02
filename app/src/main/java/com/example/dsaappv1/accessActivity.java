package com.example.dsaappv1;

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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dsaappv1.CreateNewProfile.CreateProfile2;
import com.example.dsaappv1.CreateNewProfile.createProfile;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


public class accessActivity extends AppCompatActivity {

    private Button goToCreateProfile;
    private String TAG ="accesActivity";
    private FirebaseAuth mAuth;
    private EditText email, password;
    private Button access, register;
    private ImageView regBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mAuth= FirebaseAuth.getInstance();

        email= (EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);

        access= (Button) findViewById(R.id.access);
        regBtn= (ImageView) findViewById(R.id.register);

        //Check if user is already loggedIn





        access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getemail= email.getText().toString().trim();
                String getpassword= password.getText().toString().trim();
                callsignin(getemail,getpassword);
             }
            }
        );


        regBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent=new Intent(accessActivity.this, createProfile.class);
                finish();
                startActivity(intent);
            }
        }


        );

    }


    private void goIn()
    {
        Intent intent=new Intent(accessActivity.this,Dashboard.class);
        finish();
        startActivity(intent);
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


    private void callsignin(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            userProfile();
                            goIn();
                            Toast.makeText(accessActivity.this, "Accesso eseguito",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(accessActivity.this, "Acesso non eseguito",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }


}