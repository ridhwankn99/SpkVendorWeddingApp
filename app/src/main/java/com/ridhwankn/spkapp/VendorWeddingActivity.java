package com.ridhwankn.spkapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ridhwankn.spkapp.adapter.VendorWeddingAdapter;
import com.ridhwankn.spkapp.databinding.ActivityVendorWeddingBinding;
import com.ridhwankn.spkapp.model.bean.DetailVendorBean;
import com.ridhwankn.spkapp.viewmodel.VendorWeddingViewModel;

import java.util.ArrayList;

public class VendorWeddingActivity extends AppCompatActivity {
    private ActivityVendorWeddingBinding binding;
    private VendorWeddingViewModel viewModel;
    private VendorWeddingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVendorWeddingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(VendorWeddingViewModel.class);
        initData();

    }

    private void initData(){
        ArrayList<DetailVendorBean> detail = viewModel.getDetail();
        adapter = new VendorWeddingAdapter(this, detail);
        binding.rvListWedding.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.rvListWedding.setAdapter(adapter);

        adapter.setOnItemClickListener(new VendorWeddingAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position, DetailVendorBean detailVendorBean) {
                Intent intent = new Intent(VendorWeddingActivity.this, DetailVendorWeddingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", detailVendorBean);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}