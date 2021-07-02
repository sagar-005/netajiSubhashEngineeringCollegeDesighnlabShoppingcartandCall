package com.example.designlabassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.designlabassignment.adapters.CustomAdapter;
import com.example.designlabassignment.databinding.ActivityMainBinding;
import com.example.designlabassignment.modals.MainModal;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ArrayList<MainModal> li = new ArrayList<>();
        li.add(new MainModal(R.drawable.burger1,"Burger","2","cheesy burger"));
        li.add(new MainModal(R.drawable.momos,"momos","5","cheesy burger"));
        li.add(new MainModal(R.drawable.pasta,"pasta","8","cheesy burger"));
        li.add(new MainModal(R.drawable.pizza,"pizza","10","cheesy burger"));
        li.add(new MainModal(R.drawable.noodles,"noodels","7","cheesy burger"));
        li.add(new MainModal(R.drawable.burger1,"Burger","2","cheesy burger"));
        li.add(new MainModal(R.drawable.momos,"momos","5","cheesy burger"));
        li.add(new MainModal(R.drawable.noodles,"noodles","8","cheesy burger"));
        li.add(new MainModal(R.drawable.pizza,"pizza","10","cheesy burger"));
        li.add(new MainModal(R.drawable.pasta,"pasta","7","cheesy burger"));

        CustomAdapter adapter = new CustomAdapter(li,MainActivity.this);
        binding.recyclerview.setAdapter(adapter);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.myorders:
                startActivity(new Intent(MainActivity.this,OrdersActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}