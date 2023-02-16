package com.ridhwankn.spkapp;

import android.content.Intent;
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
            finish();
        });

        binding.ivSpk.setOnClickListener(v->{
            Intent intent = new Intent(this, SpkVendorWeddingActivity.class);
            startActivity(intent);
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

    private CharSequence getDate(){
        Date d = new Date();
        CharSequence s  = DateFormat.format("MMMM d, yyyy ", d.getTime());
        return s;
    }
}