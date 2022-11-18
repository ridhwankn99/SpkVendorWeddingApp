package com.ridhwankn.spkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ridhwankn.spkapp.databinding.ActivityAppInfoBinding;

public class AppInfoActivity extends AppCompatActivity {
    private ActivityAppInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView(){
        binding.topBar.tvTitle.setText("App Info");
        binding.topBar.ivBack.setOnClickListener(v -> {
            finish();
        });
    }
}