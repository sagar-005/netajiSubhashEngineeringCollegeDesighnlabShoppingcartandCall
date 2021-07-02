package com.example.designlabassignment.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.designlabassignment.DbHelper;
import com.example.designlabassignment.DetailActivity;
import com.example.designlabassignment.R;
import com.example.designlabassignment.modals.OrderModal;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {


    ArrayList<OrderModal> li ;
    Context context;

    public OrderAdapter(ArrayList<OrderModal> li, Context context) {
        this.li = li;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.orderlayout,parent,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {

        final OrderModal modal = li.get(position);

        holder.number.setText(modal.getOrdernumber());
        holder.price.setText(modal.getOrderprice());
        holder.name.setText(modal.getOrdername());
        holder.img.setImageResource(modal.getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id",Integer.parseInt(modal.getOrdernumber()));
                intent.putExtra("type",2);
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                new AlertDialog.Builder(context)
                        .setTitle("Delete Item")
                        .setMessage("Are you sure to delete this item")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DbHelper helper = new DbHelper(context);
                                if(helper.deleteOrder(modal.getOrdernumber())>0) {
                                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                }
                            else {
                                    Toast.makeText(context, "Err", Toast.LENGTH_SHORT).show();
                                }
                                notifyDataSetChanged();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

                notifyDataSetChanged();
               return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return li.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name,price,number;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.orderimage);
            name = itemView.findViewById(R.id.ordername);
            price = itemView.findViewById(R.id.orderprice);
            number = itemView.findViewById(R.id.ordernumber);
        }
    }
}


