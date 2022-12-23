package com.example.porwaltiffin;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class newhome_recyclerview extends Fragment {

    List<Order_model> order_modelList = new ArrayList<>();
    RecyclerView thali_recycler_view;
    RecyclerView algse_recycler_view;
    Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initiate_ord_list();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_newhome_recyclerview,container,false);



        return inflater.inflate(R.layout.fragment_newhome_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initiate_ord_list();

        context = view.getContext();

        //     UPPER RECYCLER VIEW
        thali_recycler_view = view.findViewById(R.id.thali_recycler);

        RecyclerView_adapter_thali adapter_thali = new RecyclerView_adapter_thali(order_modelList);

        thali_recycler_view.setAdapter(adapter_thali);

        thali_recycler_view.setLayoutManager(new GridLayoutManager(context,1,RecyclerView.HORIZONTAL,false));


        //    LOWER RECYCLER VIEW

        algse_recycler_view = view.findViewById(R.id.algse_recycler);

        //Adapter for algse recycler view
        Recycler_view_algse recycler_view_algse_adapter = new Recycler_view_algse(order_modelList);

        algse_recycler_view.setAdapter(recycler_view_algse_adapter);

        algse_recycler_view.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));


    }

    public void initiate_ord_list()
    {
        order_modelList.add(new Order_model("Normal Thali","Rs. 50",0,R.drawable.normalthali));
        order_modelList.add(new Order_model("Special Thali","Rs. 50",0,R.drawable.specialthalih));

    }
}