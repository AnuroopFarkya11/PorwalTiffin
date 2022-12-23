package com.example.porwaltiffin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerView_adapter_thali extends RecyclerView.Adapter<RecyclerView_adapter_thali.ViewHolder> {

    List<Order_model> order_modelList = new ArrayList<>();


    public RecyclerView_adapter_thali(List<Order_model> order_modelList) {
        this.order_modelList = order_modelList;
    }

    @NonNull
    @Override
    public RecyclerView_adapter_thali.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View v = inflater.inflate(R.layout.single_order_view,parent,false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView_adapter_thali.ViewHolder holder, int position) {

        holder.thali_name.setText(order_modelList.get(position).getOrd_name());
        holder.thali_price.setText(order_modelList.get(position).getOrd_price());
        holder.thaliImage.setImageResource(order_modelList.get(position).getOrd_image());

        holder.add_btn_click.setOnClickListener(view -> hide_btn(holder));

        holder.add_btn.setOnClickListener(view -> add_order_count(holder,position));

        holder.minus.setOnClickListener(view -> minus_order_count(holder,position));

    }

    private void minus_order_count(ViewHolder holder, int position) {


        int count = order_modelList.get(position).getOrd_cnt();
        count = count -1;
        order_modelList.get(position).setOrd_cnt(count);

        holder.count.setText(""+count);

//        int cnt = order_modelList.get(position).getOrd_cnt();
//        if(cnt == 0)
//        {
//            holder.count.setText(""+0);
//            order_modelList.get(position).setOrd_cnt(0);
//            holder.add_btn_click.setVisibility(View.VISIBLE);
//            holder.advance_btn.setVisibility(View.GONE);
//        }
//        else
//        {
//            cnt = cnt - 1;
//            order_modelList.get(position).setOrd_cnt(cnt);
//            holder.count.setText(cnt);
//        }
    }

    private void add_order_count(ViewHolder holder, int position) {

        int cnt = order_modelList.get(position).getOrd_cnt();
        cnt = cnt + 1;
        holder.count.setText(""+cnt);
        order_modelList.get(position).setOrd_cnt(cnt);

    }


    @Override
    public int getItemCount() {
        return order_modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView thaliImage;
        TextView thali_name;
        TextView thali_price;

        Button add_btn_click;

        View advance_btn;
        Button add_btn;
        TextView count;
        Button minus;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            thaliImage = itemView.findViewById(R.id.thaliImage);
            thali_name = itemView.findViewById(R.id.thali_name);
            thali_price = itemView.findViewById(R.id.thali_price);

            advance_btn = itemView.findViewById(R.id.advance_btn);
            add_btn = itemView.findViewById(R.id.add_btn);
            count = itemView.findViewById(R.id.show_cnt);
            minus = itemView.findViewById(R.id.minus_btn);

            add_btn_click = itemView.findViewById(R.id.click_add);





        }
    }


    private void hide_btn(RecyclerView_adapter_thali.ViewHolder holder) {

        holder.add_btn_click.setVisibility(View.GONE);
        holder.advance_btn.setVisibility(View.VISIBLE);

    }
}
