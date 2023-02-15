package com.ridhwankn.spkapp;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ridhwankn.spkapp.databinding.ActivityCreatedAccountBinding;

import java.util.UUID;

public class CreatedAccountActivity extends AppCompatActivity {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://spkwedding-b87ac-default-rtdb.firebaseio.com");

    private ActivityCreatedAccountBinding binding;
    private String [] items = {"Vendor", "Customer"};
    private ArrayAdapter<String> adapterItems;
    private String role = "";
    private boolean mIsPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreatedAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView(){
        binding.topBar.tvTitle.setText("Created Account");
        binding.topBar.ivBack.setOnClickListener(v->{
            finish();
        });
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

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, items);
        binding.tvAutoComplete.setAdapter(adapterItems);
        binding.tvAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                role = item;
                Toast.makeText(getApplicationContext(), "Item " +item, Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnCreated.setOnClickListener(view -> {
            final String username = binding.etName.getText().toString();
            final String password = binding.etPassword.getText().toString();
            databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(username)){
                        Toast.makeText(CreatedAccountActivity.this, "Username is already registered", Toast.LENGTH_SHORT).show();
                    } else {
//                        String uniqueID = UUID.randomUUID().toString();
                        databaseReference.child("users").child(username).child("username").setValue(username);
                        databaseReference.child("users").child(username).child("password").setValue(password);

                        Toast.makeText(CreatedAccountActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        });
    }
}