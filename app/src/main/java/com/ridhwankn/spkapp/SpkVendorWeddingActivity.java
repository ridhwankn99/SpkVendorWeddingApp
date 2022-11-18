package com.ridhwankn.spkapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ridhwankn.spkapp.adapter.SpkVendorWeddingAdapter;
import com.ridhwankn.spkapp.databinding.ActivitySpkVendorWeddingBinding;
import com.ridhwankn.spkapp.model.SpkVendorWeddingModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class SpkVendorWeddingActivity extends AppCompatActivity {
    private ActivitySpkVendorWeddingBinding binding;
    private SpkVendorWeddingAdapter adapter;
    private ArrayList<SpkVendorWeddingModel> list= new ArrayList<>();
    private ArrayList<SpkVendorWeddingModel> list2= new ArrayList<>();
    private ArrayList<SpkVendorWeddingModel> listFix= new ArrayList<>();
    private ArrayList<Double> normalisasi = new ArrayList<>();
    private ArrayList<Double> valueV = new ArrayList<>();
    private double sumNormalisasi = 0.0;

    private double W1= -0.238;
    private double W2= 0.238;
    private double W3= 0.19;
    private double W4= 0.142;
    private double W5= 0.19;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpkVendorWeddingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        initView();
    }

    private void initData(){
        ArrayList<SpkVendorWeddingModel> list= new ArrayList<>();

    }
    private void initView(){
        binding.topBar.tvTitle.setText("SPK Vendor Wedding");
        binding.topBar.ivBack.setOnClickListener(v->{
            finish();
        });
        binding.btnInput.setOnClickListener(v->{
            if (binding.etNameVendor.getText().toString().equals("")||binding.etNameGedung.getText().toString().equals("")||binding.etRating.getText().toString().equals("")||binding.etRasaMakanan.getText().toString().equals("")
                    ||binding.etPrice.getText().toString().equals("")||binding.etLuasGedung.getText().toString().equals("")||binding.etLuasParkir.getText().toString().equals("")){
                Toast.makeText(this, "Form tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }
            list.add(new SpkVendorWeddingModel(
                    binding.etNameVendor.getText().toString(),
                    binding.etNameGedung.getText().toString(),
                    Double.parseDouble(binding.etPrice.getText().toString()),
                    Double.parseDouble(binding.etRating.getText().toString()),
                    Double.parseDouble(binding.etLuasGedung.getText().toString()),
                    Double.parseDouble(binding.etLuasParkir.getText().toString()),
                    Integer.parseInt(binding.etRasaMakanan.getText().toString())
            ));
            list2.addAll(list);
            getClear();
            adapter = new SpkVendorWeddingAdapter(getApplicationContext(), list2);
            binding.rvSpkVendorWedding.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            binding.rvSpkVendorWedding.setAdapter(adapter);
        });

        binding.btnConfirm.setOnClickListener(v->{
            if (list2.size()<2){
                Toast.makeText(this, "Data harus lebih dari satu", Toast.LENGTH_SHORT).show();
                return;
            }
            for (int i =0; i<list2.size(); i++){
                normalisasi.add(Math.pow(list2.get(i).getPrice(),W1)
                        *Math.pow(list2.get(i).getRating(),W2)
                        *Math.pow(list2.get(i).getLuasGedung(),W3)
                        *Math.pow(list2.get(i).getLuasParkir(),W4)
                        *Math.pow(list2.get(i).getRasaMakanan(),W5)
                );
            }
            for (int i = 0; i<normalisasi.size(); i++){
                sumNormalisasi += normalisasi.get(i);
            }
            for (Double d : normalisasi){
                valueV.add(d/sumNormalisasi);
            }
            System.out.println(valueV);
            Object obj = Collections.max(valueV);
            int index = valueV.indexOf(obj);

            System.out.println("Max Element of Java ArrayList is : " + obj+ " and index position is " + index);
            for (int i=0; i<list2.size(); i++){
                if (index==i){
                    list.add(new SpkVendorWeddingModel(
                       list2.get(i).getNameVendor(),
                       list2.get(i).getNameGedung(),
                       list2.get(i).getPrice(),
                       list2.get(i).getRating(),
                       list2.get(i).getLuasGedung(),
                       list2.get(i).getLuasParkir(),
                       list2.get(i).getRasaMakanan()

                    ));
                    listFix.addAll(list);
                    list.clear();
                }
            }
            Intent intent = new Intent(SpkVendorWeddingActivity.this, ResultVendorWeddingActivity.class);
            intent.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) listFix);
            startActivity(intent);
            System.out.println("list" + listFix.get(0));
        });
    }

    private void getClear(){
        list.clear();
        binding.etNameVendor.getText().clear();
        binding.etNameGedung.getText().clear();
        binding.etPrice.getText().clear();
        binding.etRating.getText().clear();
        binding.etLuasGedung.getText().clear();
        binding.etLuasParkir.getText().clear();
        binding.etRasaMakanan.getText().clear();
    }
}