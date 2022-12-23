package com.example.porwaltiffin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.porwaltiffin.databinding.ActivityMainhomelinearBinding;

public class mainhomelinear extends AppCompatActivity {

    ActivityMainhomelinearBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainhomelinearBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

//        replaceFragment(new Homescreen_fragment());

        replaceFragment(new newhome_recyclerview());

        binding.appbottomnav.setOnItemSelectedListener(item -> {

            switch(item.getItemId())
            {
                case R.id.home:{
                    replaceFragment(new newhome_recyclerview());
                    break;
                }
                case R.id.order:{
                    replaceFragment(new myorder());
                    break;
                }
            }
            return true;
        });

    }
    void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.useme,fragment);
        fragmentTransaction.commit();
    }
}