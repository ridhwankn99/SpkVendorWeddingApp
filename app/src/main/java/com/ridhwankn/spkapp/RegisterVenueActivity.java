package com.ridhwankn.spkapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ridhwankn.spkapp.databinding.ActivityRegisterVenueBinding;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class RegisterVenueActivity extends AppCompatActivity {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://spkwedding-b87ac-default-rtdb.firebaseio.com");
    private ActivityRegisterVenueBinding binding;
    private String username="";
    private String vendorName="";
    private static ProgressDialog progressDialog;
    private int RESULT_LOAD_IMG = 100;
    private String logoFromDb = "";
    private String imgEncode = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterVenueBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        initData();
    }

    private void initView(){
        binding.topBar.tvTitle.setText("Register Venue");
        binding.topBar.ivBack.setOnClickListener(v->{
            finish();
        });

        binding.btnSave.setOnClickListener(v-> {
            gotoSaveDataToDatabase();
        });

        binding.btnEdit.setOnClickListener(v-> {
            prgrsDialog().show();
            gotoEditData();
        });

        binding.btnUploadImg.setOnClickListener(v->{
            gotoPutImageFromGalery();
        });
    }

    private void initData(){
        username = getIntent().getStringExtra("name");
        getData();

    }

    private void getData(){

        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(username)){
                    vendorName = snapshot.child(username).child("vendorName").getValue(String.class);
                    setDataFromDatabase();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void gotoPutImageFromGalery(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }

    private void gotoSaveDataToDatabase(){
        String vendorName = binding.etNameVendor.getText().toString();
        String venue = binding.etNameGedung.getText().toString();
        String price =  binding.etPrice.getText().toString();
        String rating = binding.etRating.getText().toString();
        String invitation = binding.etUndangan.getText().toString();
        String maximumGuests = binding.etMaxTamu.getText().toString();
        String tasteFood = binding.etRasaMakanan.getText().toString();
        String description = binding.etDesc.getText().toString();
        String service = binding.etLayanan.getText().toString();
        String address = binding.etAddress.getText().toString();
        String location = binding.etLocation.getText().toString();
        String noWa = binding.etNoWa.getText().toString();
        String email = binding.etEmail.getText().toString();

        if (vendorName.isEmpty()
                || venue.isEmpty() || price.isEmpty()
                || rating.isEmpty() || invitation.isEmpty()
                || maximumGuests.isEmpty() || tasteFood.isEmpty()
                || description.isEmpty() || service.isEmpty()
                || address.isEmpty() || location.isEmpty()
                || noWa.isEmpty() || email.isEmpty()
                || imgEncode.isEmpty()) {
            Toast.makeText(RegisterVenueActivity.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
        } else {
            databaseReference.child("vendor").child(vendorName).child("vendorName").setValue(vendorName);
            databaseReference.child("vendor").child(vendorName).child("venue").setValue(venue);
            databaseReference.child("vendor").child(vendorName).child("price").setValue(price);
            databaseReference.child("vendor").child(vendorName).child("rating").setValue(rating);
            databaseReference.child("vendor").child(vendorName).child("invitation").setValue(invitation);
            databaseReference.child("vendor").child(vendorName).child("maximumGuests").setValue(maximumGuests);
            databaseReference.child("vendor").child(vendorName).child("tasteFood").setValue(tasteFood);
            databaseReference.child("vendor").child(vendorName).child("description").setValue(description);
            databaseReference.child("vendor").child(vendorName).child("service").setValue(service);
            databaseReference.child("vendor").child(vendorName).child("address").setValue(address);
            databaseReference.child("vendor").child(vendorName).child("location").setValue(location);
            databaseReference.child("vendor").child(vendorName).child("noWa").setValue(noWa);
            databaseReference.child("vendor").child(vendorName).child("email").setValue(email);
            databaseReference.child("vendor").child(vendorName).child("imageLogo").setValue(imgEncode);

            Toast.makeText(RegisterVenueActivity.this, "Venue registered successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void gotoEditData(){
        String venue = binding.etNameGedung.getText().toString();
        String price =  binding.etPrice.getText().toString();
        String rating = binding.etRating.getText().toString();
        String invitation = binding.etUndangan.getText().toString();
        String maximumGuests = binding.etMaxTamu.getText().toString();
        String tasteFood = binding.etRasaMakanan.getText().toString();
        String description = binding.etDesc.getText().toString();
        String service = binding.etLayanan.getText().toString();
        String address = binding.etAddress.getText().toString();
        String location = binding.etLocation.getText().toString();
        String noWa = binding.etNoWa.getText().toString();
        String email = binding.etEmail.getText().toString();
        HashMap vendor = new HashMap();
        vendor.put("price", price);
        vendor.put("venue", venue);
        vendor.put("rating", rating);
        vendor.put("invitation", invitation);
        vendor.put("maximumGuests", maximumGuests);
        vendor.put("tasteFood", tasteFood);
        vendor.put("description", description);
        vendor.put("service", service);
        vendor.put("address", address);
        vendor.put("location", location);
        vendor.put("noWa", noWa);
        vendor.put("email", email);
        vendor.put("imageLogo", imgEncode);
        databaseReference = FirebaseDatabase.getInstance().getReference("vendor");
        databaseReference.child(vendorName).updateChildren(vendor).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                Toast.makeText(RegisterVenueActivity.this, "Edit data successfully", Toast.LENGTH_SHORT).show();
                prgrsDialog().dismiss();
                finish();
            }
        });
    }

    private void setDataFromDatabase(){
        prgrsDialog().show();
        databaseReference.child("vendor").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot2) {
                if (snapshot2.hasChild(vendorName)){
                    binding.etNameVendor.setVisibility(View.GONE);
                    binding.tvNameVendor.setVisibility(View.VISIBLE);
                    binding.tvNameVendor.setText(snapshot2.child(vendorName).child("vendorName").getValue(String.class));
                    binding.etNameGedung.setText(snapshot2.child(vendorName).child("venue").getValue(String.class));
                    binding.etPrice.setText(snapshot2.child(vendorName).child("price").getValue(String.class));
                    binding.etRating.setText(snapshot2.child(vendorName).child("rating").getValue(String.class));
                    binding.etUndangan.setText(snapshot2.child(vendorName).child("invitation").getValue(String.class));
                    binding.etMaxTamu.setText(snapshot2.child(vendorName).child("maximumGuests").getValue(String.class));
                    binding.etRasaMakanan.setText(snapshot2.child(vendorName).child("tasteFood").getValue(String.class));
                    binding.etDesc.setText(snapshot2.child(vendorName).child("description").getValue(String.class));
                    binding.etLayanan.setText(snapshot2.child(vendorName).child("service").getValue(String.class));
                    binding.etAddress.setText(snapshot2.child(vendorName).child("address").getValue(String.class));
                    binding.etLocation.setText(snapshot2.child(vendorName).child("location").getValue(String.class));
                    binding.etNoWa.setText(snapshot2.child(vendorName).child("noWa").getValue(String.class));
                    binding.etEmail.setText(snapshot2.child(vendorName).child("email").getValue(String.class));
                    logoFromDb = snapshot2.child(vendorName).child("imageLogo").getValue(String.class);
                    byte [] bytes = Base64.decode(logoFromDb, Base64.DEFAULT);
                    Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    binding.ivLogo.setImageBitmap(bm);
                    binding.btnSave.setVisibility(View.GONE);
                    binding.btnEdit.setVisibility(View.VISIBLE);

                    dismisDialog();
                } else {
                    dismisDialog();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dismisDialog();
            }
        });
    }

    private ProgressDialog prgrsDialog(){
        progressDialog = new ProgressDialog(RegisterVenueActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

        return progressDialog;
    }

    private void dismisDialog(){
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imgEncode = encodeImage(selectedImage);
                binding.ivLogo.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(RegisterVenueActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(RegisterVenueActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

    private String encodeImage(Bitmap bm){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bytes = stream.toByteArray();
        String encodedString = Base64.encodeToString(bytes, Base64.DEFAULT);
        return encodedString;
    }

}