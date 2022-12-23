package com.example.porwaltiffin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.porwaltiffin.databinding.ActivityPasswordForgotBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class password_forgot extends AppCompatActivity {

    //     VIEW BINDING
    ActivityPasswordForgotBinding binding;


    String pass, cpass;


    //  REAL TIME DATABASE VARIABLE

    FirebaseDatabase firebaseDatabase;


    // REFERENCE TO REALTIME DATABASE

    DatabaseReference databaseReference;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPasswordForgotBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();

        setContentView(view);

        // GETTING INSTANCE OF REALTIME DATABASE
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


        // GET THE NAME FOR THE RESPECTIVE USER
        SharedPreferences sharedPreferences = getSharedPreferences("USERNAME", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name",null);
        String number = sharedPreferences.getString("number",null);

        binding.helloName.setText("Hello " + name + "!!");



        //  CONFIRM PASSWORD ONTOUCH


        binding.getCPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                pass = binding.getPass.getText().toString();
                if(pass.length()<6||pass.isEmpty())
                {
                    binding.getPass.setError("Password length is less than 6 characters");
                }
                return false;
            }
        });



        //    CONTINUE BUTTON

        binding.Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pass = binding.getPass.getText().toString();
                cpass = binding.getCPass.getText().toString();

                // VERIFYING BOTH THE PASSWORD
                boolean checkEqual = validatepass(pass,cpass);

                if(checkEqual)
                {
                    //  UPDATING NEW PASSWORD IN THE DATABASE

                    databaseReference.child("myuser").child(number).child("pass").setValue(cpass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful())
                            {
                                Toast.makeText(password_forgot.this, "Password updated!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(password_forgot.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }


                    });


                }

            }
        });

    }


    // THIS FUNCTION VERIFIES THE PASSWORD

    boolean validatepass(String pass, String cpass)
    {
        if(!pass.equals(cpass))
        {
            binding.getCPass.setError("Password mismatched!");
            return false;
        }

        return true;

    }
}