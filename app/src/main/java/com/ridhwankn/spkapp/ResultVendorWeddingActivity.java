package com.ridhwankn.spkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ridhwankn.spkapp.databinding.ActivityResultVendorWeddingBinding;
import com.ridhwankn.spkapp.model.SpkVendorWeddingModel;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class ResultVendorWeddingActivity extends AppCompatActivity {
    private ActivityResultVendorWeddingBinding binding;
    private ArrayList<SpkVendorWeddingModel> list= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultVendorWeddingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        initView();
        initToolBar();
    }

    private void initToolBar(){
        binding.top.tvTitle.setText("Hasil Pendukung Keputusan");
    }

    private void initData(){
        Intent mData = getIntent();
        list = mData.getParcelableArrayListExtra("data");
    }

    private void initView(){
        String text = "Sistem pendukung keputusan dengan metode weight product memutuskan vendor terbaik yaitu " + list.get(0).getNameVendor()
                +". Menggunakan gedung "+list.get(0).getNameGedung()+" dengan harga Rp. "
                + formatCurrency(list.get(0).getPrice());
        binding.tvText.setText(text);
    }

    public static String formatCurrency(double amount){
        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(amount);
        return formattedNumber;
    }
}