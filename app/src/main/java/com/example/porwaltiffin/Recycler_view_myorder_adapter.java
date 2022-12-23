package com.example.porwaltiffin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Recycler_view_myorder_adapter extends RecyclerView.Adapter<Recycler_view_myorder_adapter.ViewHolder>{

    List<Order_model> current_list = new ArrayList<>();

    public Recycler_view_myorder_adapter(List<Order_model> current_list) {
        this.current_list = current_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.myorder_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.ord_name.setText(current_list.get(position).getOrd_name());
//        holder.ord_price.setText(current_list.get(position).getOrd_price());
//        holder.plus_btn.setOnClickListener(view -> add_Order(holder,position));
//        holder.minus_btn.setOnClickListener(view -> minus_Order(holder,position) );
    }

    private void minus_Order(ViewHolder holder, int position) {
        int current_cnt = current_list.get(position).getOrd_cnt();
        current_cnt= current_cnt-1;
        holder.count.setText(""+current_cnt);
        current_list.get(position).setOrd_cnt(current_cnt);
    }

    private void add_Order(ViewHolder holder, int position) {
        int current_cnt = current_list.get(position).getOrd_cnt();
        current_cnt = current_cnt+1;
        holder.count.setText(""+current_cnt);
        current_list.get(position).setOrd_cnt(current_cnt);
    }



    @Override
    public int getItemCount() {

        return current_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView ord_name, ord_price;
        View advance_btn;
        Button minus_btn, plus_btn;
        TextView count;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ord_name = itemView.findViewById(R.id.ord_name_textview);
            ord_price = itemView.findViewById(R.id.ord_rate_textview);
            advance_btn = itemView.findViewById(R.id.advance_btn);
            minus_btn = itemView.findViewById(R.id.minus_btn);
            plus_btn = itemView.findViewById(R.id.plus_btn);
            count = itemView.findViewById(R.id.show_cnt);
        }
    }


}

