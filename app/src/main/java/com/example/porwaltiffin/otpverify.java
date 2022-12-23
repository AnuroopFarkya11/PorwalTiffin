package com.example.porwaltiffin;

//import static com.example.porwaltiffin.databinding.ActivityOtpverifyBinding.inflate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;
import com.example.porwaltiffin.databinding.ActivityOtpverifyBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class otpverify extends AppCompatActivity {

    String num;
    String otpid;
    ActivityOtpverifyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityOtpverifyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sharedPreferences = getSharedPreferences("USERDATA", Context.MODE_PRIVATE);
        num = sharedPreferences.getString("number",null);

        binding.displaynumber.setText("OTP sent to "+num); //displaying the number on which otp has been sent
        initiateOtp(num);

        binding.submitOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.insertotp.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Blank field cannot be processed", Toast.LENGTH_SHORT).show();
                }
                else if (binding.insertotp.getText().toString().length() != 6) {
                    Toast.makeText(getApplicationContext(), "Invalid otp!", Toast.LENGTH_SHORT).show();
                }
                else{
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpid, binding.insertotp.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });


        startTimer();


        //    RESEND OTP

        binding.resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.timer.setVisibility(View.VISIBLE);
                binding.resend.setVisibility(View.GONE);


                initiateOtp(num);
                startTimer();


            }
        });


    }
    private void initiateOtp(String phone){

        FirebaseAuth mAuth  = FirebaseAuth.getInstance();

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phone)       // Phone number to verify
                        .setTimeout(50L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                otpid = s;
                            }

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(otpverify.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    private final String TAG = "otp";

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(otpverify.this, getPassword.class));
                            Toast.makeText(otpverify.this, "OTP verified successfully!", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(otpverify.this, "Signin Code Error", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }



    public void startTimer()
    {

        //for timer

        long duration = TimeUnit.MINUTES.toMillis(1);

        //initialize countdown timer

        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                //when tick
                //convert millisecond to minute and second

                String sDuration = String.format(Locale.ENGLISH, "%02d : %02d"
                        ,TimeUnit.MILLISECONDS.toMinutes(l)
                        ,TimeUnit.MILLISECONDS.toSeconds(l)-
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));

                //set converted String on textview
                binding.timer.setText(sDuration);
            }

            @Override
            public void onFinish() {
                //when finish
                binding.timer.setVisibility(View.GONE);
                binding.resend.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "OTP entering time is over", Toast.LENGTH_SHORT).show();

            }
        }.start();
    }
}