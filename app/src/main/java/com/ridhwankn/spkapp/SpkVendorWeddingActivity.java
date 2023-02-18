package com.ridhwankn.spkapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ridhwankn.spkapp.adapter.ViewPagerAdapter;
import com.ridhwankn.spkapp.databinding.ActivitySpkVendorWeddingBinding;
import com.ridhwankn.spkapp.fragment.InputVendorFragment;
import com.ridhwankn.spkapp.fragment.ListVendorUserFragment;
import com.ridhwankn.spkapp.model.bean.SpkVendorWeddingBean;

import java.util.ArrayList;

public class SpkVendorWeddingActivity extends AppCompatActivity {
    private ActivitySpkVendorWeddingBinding binding;
    private String username = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpkVendorWeddingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        initView();

    }

    private void initData(){
        ArrayList<SpkVendorWeddingBean> list= new ArrayList<>();
        username = getIntent().getStringExtra("name");
    }
    private void initView(){
        binding.topBar.tvTitle.setText("SPK Vendor Wedding");
        binding.topBar.ivBack.setOnClickListener(v->{
            finish();
        });

        binding.tabLayout.setupWithViewPager(binding.viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.addFragment(new InputVendorFragment(), "Input Vendor");
        viewPagerAdapter.addFragment(new ListVendorUserFragment(), "Your Vendor");
        binding.viewPager.setAdapter(viewPagerAdapter);
    }


    private void setDataByQuery(String query){
//        databaseReference.child("vendor").addListenerForSingleValueEvent()
    }


}