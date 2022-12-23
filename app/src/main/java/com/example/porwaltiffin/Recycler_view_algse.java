package com.example.porwaltiffin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Recycler_view_algse extends RecyclerView.Adapter<Recycler_view_algse.ViewHolder> {

    List<Order_model> menu_list = new ArrayList<>();

    public Recycler_view_algse(List<Order_model> menu_list) {
        this.menu_list = menu_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View algse_view = inflater.inflate(R.layout.single_order_algse_view,parent,false);

        ViewHolder viewHolder = new ViewHolder(algse_view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.ord_name.setText(menu_list.get(position).getOrd_name());
        holder.ord_price.setText(menu_list.get(position).getOrd_price());
        holder.ord_image.setImageResource(menu_list.get(position).getOrd_image());
//
        holder.add_click.setOnClickListener(view -> hide_btn(holder,position));
        holder.plus_btn.setOnClickListener(view -> add_order(holder,position));
        holder.minus_btn.setOnClickListener(view -> minus_ord(holder,position));


    }

    private void minus_ord(ViewHolder holder, int position) {
        int current_cnt = menu_list.get(position).getOrd_cnt();
        current_cnt = current_cnt -1;
        menu_list.get(position).setOrd_cnt(current_cnt);
        holder.ord_cnt.setText(""+current_cnt);

    }

    private void add_order(ViewHolder holder, int position) {

        int current_cnt = menu_list.get(position).getOrd_cnt();
        current_cnt = current_cnt + 1;
        menu_list.get(position).setOrd_cnt(current_cnt);
        holder.ord_cnt.setText(""+current_cnt);

    }

    private void hide_btn(ViewHolder holder, int position) {

        holder.add_click.setVisibility(View.GONE);
        holder.advance_btn.setVisibility(View.VISIBLE);

        holder.ord_cnt.setText(""+1);
        menu_list.get(position).setOrd_cnt(1);
    }
    @Override
    public int getItemCount() {
        return menu_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView ord_name;
        TextView ord_price;
        Button add_click;
        ImageView ord_image;


        View advance_btn;
        Button minus_btn;
        TextView ord_cnt;
        Button plus_btn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ord_name = itemView.findViewById(R.id.ord_name_textview);
            ord_price = itemView.findViewById(R.id.ord_rate_textview);
            add_click = itemView.findViewById(R.id.add_button);
            ord_image = itemView.findViewById(R.id.ord_imageview);

            advance_btn = itemView.findViewById(R.id.advance_btn);
            minus_btn = itemView.findViewById(R.id.minus_btn);
            ord_cnt = itemView.findViewById(R.id.show_cnt);
            plus_btn = itemView.findViewById(R.id.plus_btn);




        }
    }
}
