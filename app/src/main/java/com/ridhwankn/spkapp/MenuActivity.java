package com.ridhwankn.spkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;

import com.ridhwankn.spkapp.databinding.ActivityMenuBinding;

import java.util.Date;

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
        binding.tvDate.setText(getDate().toString());
    }

    private void initView(){
        binding.lnList.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, VendorWeddingActivity.class);
            startActivity(intent);
        });

        binding.ivLogout.setOnClickListener(v -> {
            finish();
        });

        binding.ivSpk.setOnClickListener(v->{
            Intent intent = new Intent(this, SpkVendorWeddingActivity.class);
            startActivity(intent);
        });
        binding.ivAppInfo.setOnClickListener(v->{
            Intent intent = new Intent(this, AppInfoActivity.class);
            startActivity(intent);
        });
    }

    private CharSequence getDate(){
        Date d = new Date();
        CharSequence s  = DateFormat.format("MMMM d, yyyy ", d.getTime());
        return s;
    }
}