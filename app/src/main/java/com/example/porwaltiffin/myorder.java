package com.example.porwaltiffin;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class myorder extends Fragment {

    List<Order_model> order_modelList = new ArrayList<Order_model>();
    RecyclerView order_view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_myorder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initiateList();

        Context context = view.getContext();

        order_view= view.findViewById(R.id.current_list);

        Recycler_view_myorder_adapter recycler_view_myorder_adapter = new Recycler_view_myorder_adapter(order_modelList);


        order_view.setAdapter(recycler_view_myorder_adapter);

        order_view.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));

    }

    private void initiateList() {
        order_modelList.add(new Order_model("normal_thali", "50", 0, R.drawable.specialthalih));
        order_modelList.add(new Order_model("normal_thali", "50", 0, R.drawable.specialthalih));
        order_modelList.add(new Order_model("normal_thali", "50", 0, R.drawable.specialthalih));
        order_modelList.add(new Order_model("normal_thali", "50", 0, R.drawable.specialthalih));
        order_modelList.add(new Order_model("normal_thali", "50", 0, R.drawable.specialthalih));
        order_modelList.add(new Order_model("normal_thali", "50", 0, R.drawable.specialthalih));
        order_modelList.add(new Order_model("normal_thali", "50", 0, R.drawable.specialthalih));

    }
}