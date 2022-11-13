package com.ridhwankn.spkapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ridhwankn.spkapp.adapter.SpkVendorWeddingAdapter;
import com.ridhwankn.spkapp.databinding.ActivitySpkVendorWeddingBinding;
import com.ridhwankn.spkapp.model.SpkVendorWeddingModel;

import java.util.ArrayList;

public class SpkVendorWeddingActivity extends AppCompatActivity {
    private ActivitySpkVendorWeddingBinding binding;
    private SpkVendorWeddingAdapter adapter;
    private ArrayList<SpkVendorWeddingModel> list= new ArrayList<>();
    private ArrayList<SpkVendorWeddingModel> list2= new ArrayList<>();

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