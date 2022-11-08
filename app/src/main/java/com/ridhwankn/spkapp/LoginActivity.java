package com.ridhwankn.spkapp;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.ridhwankn.spkapp.databinding.ActivityLoginBinding;
import com.ridhwankn.spkapp.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.btnLogin.setOnClickListener(v -> {
            if (binding.etName.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Please Input Your Name", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                intent.putExtra("name", binding.etName.getText().toString());
                startActivity(intent);
            }
        });
    }
}