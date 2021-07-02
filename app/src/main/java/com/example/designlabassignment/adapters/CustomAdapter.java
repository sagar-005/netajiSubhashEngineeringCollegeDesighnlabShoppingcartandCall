package com.example.designlabassignment.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.designlabassignment.DetailActivity;
import com.example.designlabassignment.OrdersActivity;
import com.example.designlabassignment.R;
import com.example.designlabassignment.modals.MainModal;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.cutsomViewHolder> {


    ArrayList<MainModal> li;
    Context context;

    public CustomAdapter(ArrayList<MainModal> li, Context context) {
        this.li = li;
        this.context = context;
    }

    @NonNull
    @Override
    public cutsomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_sample,parent,false);
        return new cutsomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cutsomViewHolder holder, int position) {

        final MainModal modal = li.get(position);
        holder.imageView.setImageResource(modal.getImage());
        holder.name.setText(modal.getName());
        holder.price.setText(modal.getPrice());
        holder.desc.setText(modal.getDesc());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("name",modal.getName());
                intent.putExtra("price",modal.getPrice());
                intent.putExtra("desc",modal.getDesc());
                intent.putExtra("image",modal.getImage());
                intent.putExtra("type",1);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return li.size();
    }

    public class cutsomViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,price,desc;
        public cutsomViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView2);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            desc = itemView.findViewById(R.id.description);
        }
    }
}
