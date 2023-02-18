package com.ridhwankn.spkapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ridhwankn.spkapp.databinding.ActivityMenuBinding;
import com.ridhwankn.spkapp.model.bean.UserBean;

import java.util.Date;

public class MenuActivity extends AppCompatActivity {
    ActivityMenuBinding binding;
    private String name="";
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://spkwedding-b87ac-default-rtdb.firebaseio.com");
    private String role ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        initView();
    }

    private void initData(){
        name = getIntent().getStringExtra("name");
        if (name==null){
            SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREFS_USERNAME,0);
            name = sharedPreferences.getString("username", "");
        }

        binding.tvName.setText("Hi "+name);
        binding.tvDate.setText(getDate().toString());

        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(name)){
                    role = snapshot.child(name).child("role").getValue(String.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initView(){
        binding.lnList.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, VendorWeddingActivity.class);
            startActivity(intent);
        });

        binding.ivLogout.setOnClickListener(v -> {
            SharedPreferences sharedPreferences =getSharedPreferences(LoginActivity.PREFS_NAME,0);
            SharedPreferences prefUsername =getSharedPreferences(LoginActivity.PREFS_USERNAME,0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            SharedPreferences.Editor editorUsername = prefUsername.edit();
            editor.putBoolean("hasLoggedIn", false);
            editorUsername.putString("username", "");
            editor.apply();
            editorUsername.apply();

            Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        binding.ivSpk.setOnClickListener(v->{
            if (role.contains("customer")){
                UserBean.getInstance().setUsername(name);
                Intent intent = new Intent(this, SpkVendorWeddingActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(MenuActivity.this, "you don't have access to this menu", Toast.LENGTH_SHORT).show();
            }

        });
        binding.ivAppInfo.setOnClickListener(v->{
            Intent intent = new Intent(this, AppInfoActivity.class);
            startActivity(intent);
        });
        binding.ivRegVenue.setOnClickListener(v->{
            if (role.equalsIgnoreCase("vendor")){
                Intent intent = new Intent(this, RegisterVenueActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            } else {
                Toast.makeText(MenuActivity.this, "you don't have access to this menu", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public void onBackPressed() {
        // empty
    }

    private CharSequence getDate(){
        Date d = new Date();
        CharSequence s  = DateFormat.format("MMMM d, yyyy ", d.getTime());
        return s;
    }
}