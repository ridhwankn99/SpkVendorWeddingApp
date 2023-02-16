package com.ridhwankn.spkapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;

import androidx.appcompat.app.AppCompatActivity;

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
        initView();
    }

    private void initView(){
        binding.topBar.tvTitle.setText("Detail Vendor");
        binding.topBar.ivBack.setOnClickListener(v->{
            finish();
        });
    }

    private void initData() {
        Intent mData = getIntent();
        detailVendorBean = (DetailVendorBean) mData.getSerializableExtra("data");

//        if (detailVendorBean !=null){
//            Glide.with(getApplicationContext())
//                    .load(detailVendorBean.imageLogo)
//                    .into(binding.ivLogoDetail);
        byte[] bytes = Base64.decode(detailVendorBean.imageLogo, Base64.DEFAULT);
        Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        binding.ivLogoDetail.setImageBitmap(bm);
        binding.title.setText(detailVendorBean.vendorName);
        binding.tvAboutMe.setText(detailVendorBean.description);
        binding.tvServiceSet.setText(detailVendorBean.service);
        binding.tvAddrs.setText(detailVendorBean.address);
        binding.tvLocation.setText(detailVendorBean.location);
    }
}