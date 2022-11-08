package com.ridhwankn.spkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ridhwankn.spkapp.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {
    ActivityMenuBinding binding;
    private String name="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        initView();
    }

    private void initData(){
        name = getIntent().getStringExtra("name");

        binding.tvName.setText("Hi "+name);
    }

    private void initView(){
        binding.lnList.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, VendorWeddingActivity.class);
            startActivity(intent);
        });

        binding.ivLogout.setOnClickListener(v -> {
            finish();
        });
    }
}