package com.example.porwaltiffin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

//import com.example.porwaltiffin.databinding.ActivityPasswordBinding;
import com.example.porwaltiffin.databinding.ActivityGetPasswordBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class getPassword extends AppCompatActivity {

    // REAL TIME DATABASE INSTANCE
    FirebaseDatabase firebaseDatabase;

    ActivityGetPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        // GETTING ALL DETAILS FROM ALL THE ACTIVITIES
        SharedPreferences sharedPreferences = getSharedPreferences("USERDATA", Context.MODE_PRIVATE);



        binding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pass = binding.getPass.getText().toString();
                String cpass = binding.getCPass.getText().toString();

                boolean verify = validate(pass,cpass);

                if(verify)
                {
                    String number = sharedPreferences.getString("number", null);
                    String name = sharedPreferences.getString("name", null);

                    //creating a tree of our credentials
                    User newuser = new User(name, number, pass);
                    firebaseDatabase.getReference().child("myuser").child(number).setValue(newuser);

                    Toast.makeText(getApplicationContext(), "Password entered successfully!", Toast.LENGTH_SHORT).show();


                }



            }

        });

    }

    boolean validate(String pass, String cpass)
    {
        if(!pass.equals(cpass))
        {
            binding.getCPass.setError("Password doesn't match");
            return false;
        }
        if(pass.length()<6&&cpass.length()<6)
        {
            binding.getPass.setError("Password length should be greater than 6");
            return false;
        }
        return true;
    }

}