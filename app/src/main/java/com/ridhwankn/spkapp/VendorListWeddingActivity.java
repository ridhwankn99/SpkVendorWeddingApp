package com.ridhwankn.spkapp;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.ridhwankn.spkapp.databinding.ActivityVendorListWeddingBinding;

public class VendorListWeddingActivity extends AppCompatActivity {
    private ActivityVendorListWeddingBinding binding;
    private String urlWedding="https://www.bridestory.com/id/indonesia";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityVendorListWeddingBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        initData();
        initView();
    }

    private void initData(){

    }

    private void initView(){
        binding.webViewWedding.getSettings().setLoadsImagesAutomatically(true);
        binding.webViewWedding.getSettings().setJavaScriptEnabled(true);
        binding.webViewWedding.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        binding.webViewWedding.getSettings().setUseWideViewPort(true);
        binding.webViewWedding.getSettings().setLoadWithOverviewMode(true);
        binding.webViewWedding.setWebViewClient(new WebViewClient());
        binding.webViewWedding.loadUrl(urlWedding);
    }
}