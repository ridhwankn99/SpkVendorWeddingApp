package com.ridhwankn.spkapp;

import android.app.ProgressDialog;
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

public class CreatedAccountActivity extends AppCompatActivity {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://spkwedding-b87ac-default-rtdb.firebaseio.com");

    private ActivityCreatedAccountBinding binding;
    private String [] items = {"Vendor", "Customer"};
    private ArrayAdapter<String> adapterItems;
    private String role = "";
    private boolean mIsPasswordVisible = false;
    private boolean vendorNameField = false;
    private static ProgressDialog progressDialog;

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
                if (item.equalsIgnoreCase("vendor")){
                    binding.etNameVendor.setVisibility(View.VISIBLE);
                    vendorNameField = true;
                } else {
                    vendorNameField = false;
                    binding.etNameVendor.setVisibility(View.GONE);
                }
//                Toast.makeText(getApplicationContext(), "Item " +item, Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnCreated.setOnClickListener(view -> {
            loading().show();
            final String username = binding.etName.getText().toString();
            final String password = binding.etPassword.getText().toString();
            final String vendorName = binding.etNameVendor.getText().toString();
            if (username.isEmpty() || password.isEmpty() || role.isEmpty()){
                Toast.makeText(getApplicationContext(), "field cannot be empty", Toast.LENGTH_SHORT).show();
                dismisDialog();
            } else if (vendorNameField && vendorName.isEmpty()){
                Toast.makeText(getApplicationContext(), "Field cannot be empty", Toast.LENGTH_SHORT).show();
                dismisDialog();
            } else {
                databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(username)){
                            Toast.makeText(CreatedAccountActivity.this, "Username is already registered", Toast.LENGTH_SHORT).show();
                        } else {
//                        String uniqueID = UUID.randomUUID().toString();
                            databaseReference.child("users").child(username).child("username").setValue(username);
                            databaseReference.child("users").child(username).child("password").setValue(password);
                            databaseReference.child("users").child(username).child("role").setValue(role);
                            databaseReference.child("users").child(username).child("vendorName").setValue(vendorName);

                            Toast.makeText(CreatedAccountActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        dismisDialog();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    private ProgressDialog loading(){
        progressDialog = new ProgressDialog(CreatedAccountActivity.this);
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