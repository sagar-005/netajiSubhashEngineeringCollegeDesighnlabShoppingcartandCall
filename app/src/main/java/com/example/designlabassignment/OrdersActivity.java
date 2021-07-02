package com.example.designlabassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.designlabassignment.adapters.OrderAdapter;
import com.example.designlabassignment.databinding.ActivityMainBinding;
import com.example.designlabassignment.databinding.ActivityOrdersBinding;
import com.example.designlabassignment.databinding.OrderlayoutBinding;
import com.example.designlabassignment.modals.OrderModal;

import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {

    ActivityOrdersBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<OrderModal> li ;
        DbHelper db = new DbHelper(OrdersActivity.this);
        li = db.getOrders();

        binding.orderrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        OrderAdapter adapter = new OrderAdapter(li,this);
        binding.orderrecyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
}