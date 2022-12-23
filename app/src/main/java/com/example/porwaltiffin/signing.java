package com.example.porwaltiffin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.porwaltiffin.databinding.ActivitySigningBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signing extends AppCompatActivity {


    //Binding for signing
    ActivitySigningBinding binding;

    //REAL TIME DATABASE INSTANCE
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    // MOBILE NUMBER + PASSWORD VARIABLES
    String mobileNumber;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySigningBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();

        setContentView(view);

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference();



        //   LOGIN BUTTON ON CLICK LISTENER

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mobileNumber = binding.mobileNumberEditText.getText().toString();
                password = binding.passwordEditText.getText().toString();

                boolean Mverify = validateMobile(mobileNumber);
                boolean Pverify = validatePass(password);

                String newMobileNumber = "+91" + mobileNumber;

                if(Mverify&&Pverify)
                {



                    Intent intent = new Intent(getApplicationContext(),mainhomelinear.class);
                    startActivity(intent);
                    finish();
//                    // THIS RETRIEVE THE DATA FROM DATABASE
//
//                    databaseReference.child("myuser").addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                            if(snapshot.hasChild(newMobileNumber))
//                            {
//                                String dataPass = snapshot.child(newMobileNumber).child("pass").getValue(String.class);
//
//                                if(password.equals(dataPass))
//                                {
//                                    Toast.makeText(signing.this, "Login Successful", Toast.LENGTH_SHORT).show();
//                                }
//                                else
//                                {
//                                    Toast.makeText(signing.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
//                                }
//
//                            }
//                            else
//                            {
//                                Toast.makeText(signing.this, "No user found!", Toast.LENGTH_SHORT).show();
//                            }
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });


                }

            }

        });

        //   CODE FOR FORGOT PASSWORD

        binding.forgetPass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // check syntax of a mobile number

                String mobile = binding.mobileNumberEditText.getText().toString();

                boolean Mverify = validateMobile(mobile);


                // VALIDATE THE GIVEN NUMBER IN THE DATABASE

                if(Mverify){

                    Intent forgot_password_intent = new Intent(signing.this,password_forgot.class);

                    startActivity(forgot_password_intent);

                    databaseReference.child("myuser").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            // CHECKING THE NUMBER FROM THE DATABASE

                            if(snapshot.hasChild("+91"+mobile)){

//                                Toast.makeText(signing.this, "Okay Forgetting is working", Toast.LENGTH_SHORT).show();

                                //Getting the name of the respective user
                                String name = snapshot.child("+91"+mobile).child("name").getValue(String.class);

                                // Shared preference will store the name of the user
                                // the name will reflect to forget password screen

                                SharedPreferences sharedPreferences = getSharedPreferences("USERNAME", Context.MODE_PRIVATE);

                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString("name",name);
                                editor.putString("number","+91"+mobile);

                                editor.apply();


                                        Intent forgot_password_intent = new Intent(signing.this,password_forgot.class);

                                        startActivity(forgot_password_intent);

                                        finish();

                            }
                            else
                            {
                                binding.mobileNumberEditText.setError("User Not Found!");
                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
            }});


        //   IF THE USER DOESN'T HAVE A ACCOUNT

        binding.noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup_intent = new Intent(signing.this,signup.class);
                startActivity(signup_intent);
                finish();
            }
        });
    }


    // THis function verifies the proper format of a number

    boolean validateMobile(String mobileNumber)
    {

        //  IF/ELSE IS RESPONSIBLE FOR CHECKING SPECIAL CHARACTER OR ALPHABET IN THE INPUT

        if(!Patterns.PHONE.matcher(mobileNumber).matches()||mobileNumber.length()!=10)
        {
            binding.mobileNumberEditText.setError("Enter a valid mobile number");

            return false;
        }


        return true;
    }


    boolean validatePass(String pass)
    {
        if(pass.length()<6)
        {
            binding.passwordEditText.setError("Low Strength");
            return false;
        }
        return true;
    }

}