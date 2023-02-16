package com.ridhwankn.spkapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ridhwankn.spkapp.databinding.ActivityLoginBinding;
import com.ridhwankn.spkapp.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;
    private boolean mIsPasswordVisible = false;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://spkwedding-b87ac-default-rtdb.firebaseio.com");
    private static ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        initView();


    }

    private void initView(){
        binding.imgPasswordVisible.setOnClickListener(v->{
            if (mIsPasswordVisible){
                binding.etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                binding.imgPasswordVisible.setImageResource(R.drawable.login_user_invisible);
                mIsPasswordVisible = false;
            } else {
                binding.etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                binding.imgPasswordVisible.setImageResource(R.drawable.login_user_visible);
                mIsPasswordVisible = true;
            }
        });

        binding.createAcc.setOnClickListener(v->{
            Intent intent = new Intent(LoginActivity.this, CreatedAccountActivity.class);
            startActivity(intent);
        });

        binding.btnLogin.setOnClickListener(v -> {
            loading().show();
            gotoLogin();
        });
    }

    private void gotoLogin(){
        if (binding.etName.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Please Input Your Name", Toast.LENGTH_SHORT).show();
            dismisDialog();
        } else if (binding.etPassword.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Please Input Your Password", Toast.LENGTH_SHORT).show();
            dismisDialog();
        } else {
            databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(binding.etName.getText().toString())){
                        final String getPassword = snapshot.child(binding.etName.getText().toString()).child("password").getValue(String.class);
                        if (binding.etPassword.getText().toString().equals(getPassword)){
                            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                            intent.putExtra("name", binding.etName.getText().toString());
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    dismisDialog();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }

    private ProgressDialog loading(){
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

        return progressDialog;
    }

    public void dismisDialog(){
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}