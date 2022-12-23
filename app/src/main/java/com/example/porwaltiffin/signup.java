package com.example.porwaltiffin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.porwaltiffin.databinding.ActivityGetPasswordBinding;
import com.example.porwaltiffin.databinding.ActivitySignupBinding;

public class signup extends AppCompatActivity {

    //   VIEW BINDING

     ActivitySignupBinding binding;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignupBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

       // CONTINUE BUTTON ONCLICK

        binding.Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //taking input from user
                String number = binding.getNumber.getText().toString();
                String name = binding.getName.getText().toString();


                //checking if our given number is correct or not

                boolean ans = validate(number);


                if (ans) {

                    //first we're storing our credential in our database
//                    FirebaseDatabase database = FirebaseDatabase.getInstance();
//                    DatabaseReference reference = database.getReference(name);
//                    database.getReference(number);
//                    reference.setValue(number,name);
                    Toast.makeText(signup.this, "Success!", Toast.LENGTH_SHORT).show();


                    //now we're sending our number to get verified
////                    SharedPreferences sharedPreferences = getSharedPreferences("Contact", Context.MODE_PRIVATE);
                    SharedPreferences sharedPreferences = getSharedPreferences("USERDATA", Context.MODE_PRIVATE);
                    SharedPreferences.Editor shared = sharedPreferences.edit();
                    shared.putString("number", "+91"+number);
                    shared.putString("name",name);
                    shared.apply();

                    startActivity(new Intent(signup.this,otpverify.class));
                    finish();

                }
                else{
                    Toast.makeText(signup.this, "Incorrect Number", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // IF USER ALREADY HAVE A ACCOUNT

         binding.already.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Intent signing_intent = new Intent(signup.this,signing.class);
                 startActivity(signing_intent);
             }
         });
    }


    // THIS METHOD CHECKS THE SYNTAX OF A NUMBER

    boolean validate(String number){

        if (number.length()==10) {

            if (Patterns.PHONE.matcher(number).matches()) {
                return true;
            }
        }
        binding.getNumber.setError("Invalid Number!");
        return false;
    }
}