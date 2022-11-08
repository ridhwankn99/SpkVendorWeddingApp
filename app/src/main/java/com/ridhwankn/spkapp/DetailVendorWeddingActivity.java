package com.ridhwankn.spkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.ridhwankn.spkapp.databinding.ActivityDetailVendorWeddingBinding;
import com.ridhwankn.spkapp.model.DetailVendor;

public class DetailVendorWeddingActivity extends AppCompatActivity {
    ActivityDetailVendorWeddingBinding binding;
    private DetailVendor detailVendor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailVendorWeddingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initData();
    }

    private void initData(){
        Intent mData = getIntent();
        detailVendor = (DetailVendor) mData.getSerializableExtra("data");

        if (detailVendor!=null){
            Glide.with(getApplicationContext())
                    .load(detailVendor.image)
                    .into(binding.ivLogoDetail);
            binding.title.setText(detailVendor.nameVendor);
            binding.tvAboutMe.setText(detailVendor.aboutMe);
            binding.tvServiceSet.setText(detailVendor.service);
            binding.tvAddrs.setText(detailVendor.address);
            binding.tvLocation.setText(detailVendor.location);
        }
    }
}