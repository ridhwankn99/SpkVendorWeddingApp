package com.ridhwankn.spkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.ridhwankn.spkapp.databinding.ActivityDetailVendorWeddingBinding;
import com.ridhwankn.spkapp.model.bean.DetailVendorBean;

public class DetailVendorWeddingActivity extends AppCompatActivity {
    ActivityDetailVendorWeddingBinding binding;
    private DetailVendorBean detailVendorBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailVendorWeddingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initData();
    }

    private void initData(){
        Intent mData = getIntent();
        detailVendorBean = (DetailVendorBean) mData.getSerializableExtra("data");

        if (detailVendorBean !=null){
            Glide.with(getApplicationContext())
                    .load(detailVendorBean.image)
                    .into(binding.ivLogoDetail);
            binding.title.setText(detailVendorBean.nameVendor);
            binding.tvAboutMe.setText(detailVendorBean.aboutMe);
            binding.tvServiceSet.setText(detailVendorBean.service);
            binding.tvAddrs.setText(detailVendorBean.address);
            binding.tvLocation.setText(detailVendorBean.location);
        }
    }
}