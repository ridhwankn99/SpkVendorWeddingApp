package com.ridhwankn.spkapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ridhwankn.spkapp.adapter.SpkVendorWeddingAdapter;
import com.ridhwankn.spkapp.databinding.ActivitySpkVendorWeddingBinding;
import com.ridhwankn.spkapp.model.SpkVendorWeddingModel;

import java.util.ArrayList;
import java.util.Collections;

public class SpkVendorWeddingActivity extends AppCompatActivity {
    private ActivitySpkVendorWeddingBinding binding;
    private SpkVendorWeddingAdapter adapter;
    private ArrayList<SpkVendorWeddingModel> list= new ArrayList<>();
    private ArrayList<SpkVendorWeddingModel> list2= new ArrayList<>();
    private ArrayList<SpkVendorWeddingModel> listFix= new ArrayList<>();
    private ArrayList<Double> normalisasi = new ArrayList<>();
    private ArrayList<Double> valueV = new ArrayList<>();
    private double sumNormalisasi = 0.0;
    private static ProgressDialog progressDialog;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://spkwedding-b87ac-default-rtdb.firebaseio.com");

    private double W1= -0.238;
    private double W2= 0.238;
    private double W3= 0.19;
    private double W4= 0.142;
    private double W5= 0.19;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpkVendorWeddingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        initView();
        getSearchVendor();
    }

    private void initData(){
        ArrayList<SpkVendorWeddingModel> list= new ArrayList<>();

    }
    private void initView(){
        binding.topBar.tvTitle.setText("SPK Vendor Wedding");
        binding.topBar.ivBack.setOnClickListener(v->{
            finish();
        });
        binding.btnInput.setOnClickListener(v->{
            if (binding.etNameVendor.getText().toString().equals("")||binding.etNameGedung.getText().toString().equals("")||binding.etRating.getText().toString().equals("")||binding.etRasaMakanan.getText().toString().equals("")
                    ||binding.etPrice.getText().toString().equals("")||binding.etMaxGuest.getText().toString().equals("")||binding.etTtlInvitation.getText().toString().equals("")){
                Toast.makeText(this, "Form tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }
            list.add(new SpkVendorWeddingModel(
                    binding.etNameVendor.getText().toString(),
                    binding.etNameGedung.getText().toString(),
                    Double.parseDouble(binding.etPrice.getText().toString()),
                    Double.parseDouble(binding.etRating.getText().toString()),
                    Integer.parseInt(binding.etMaxGuest.getText().toString()),
                    Integer.parseInt(binding.etTtlInvitation.getText().toString()),
                    Integer.parseInt(binding.etRasaMakanan.getText().toString())
            ));
            list2.addAll(list);
            getClear();
            adapter = new SpkVendorWeddingAdapter(getApplicationContext(), list2);
            binding.rvSpkVendorWedding.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            binding.rvSpkVendorWedding.setAdapter(adapter);
        });

        binding.btnConfirm.setOnClickListener(v->{
            if (list2.size()<2){
                Toast.makeText(this, "Data harus lebih dari satu", Toast.LENGTH_SHORT).show();
                return;
            }
            for (int i =0; i<list2.size(); i++){
                normalisasi.add(Math.pow(list2.get(i).getPrice(),W1)
                        *Math.pow(list2.get(i).getRating(),W2)
                        *Math.pow(list2.get(i).getMaxGuest(),W3)
                        *Math.pow(list2.get(i).getTotalInvitation(),W4)
                        *Math.pow(list2.get(i).getRasaMakanan(),W5)
                );
            }
            for (int i = 0; i<normalisasi.size(); i++){
                sumNormalisasi += normalisasi.get(i);
            }
            for (Double d : normalisasi){
                valueV.add(d/sumNormalisasi);
            }
            System.out.println(valueV);
            Object obj = Collections.max(valueV);
            int index = valueV.indexOf(obj);

            System.out.println("Max Element of Java ArrayList is : " + obj+ " and index position is " + index);
            for (int i=0; i<list2.size(); i++){
                if (index==i){
                    list.add(new SpkVendorWeddingModel(
                       list2.get(i).getNameVendor(),
                       list2.get(i).getNameGedung(),
                       list2.get(i).getPrice(),
                       list2.get(i).getRating(),
                       list2.get(i).getMaxGuest(),
                       list2.get(i).getTotalInvitation(),
                       list2.get(i).getRasaMakanan()

                    ));
                    listFix.addAll(list);
                    list.clear();
                }
            }
            Intent intent = new Intent(SpkVendorWeddingActivity.this, ResultVendorWeddingActivity.class);
//            intent.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) listFix);
            intent.putExtra("price", listFix.get(0).getPrice());
            intent.putExtra("vendorName", listFix.get(0).getNameVendor());
            intent.putExtra("nameGedung", listFix.get(0).getNameGedung());
            intent.putExtra("ttlInvitation", listFix.get(0).getTotalInvitation());
            startActivity(intent);
            System.out.println("list" + listFix.get(0));
        });
    }

    private void getClear(){
        list.clear();
        binding.etNameVendor.getText().clear();
        binding.etNameGedung.getText().clear();
        binding.etPrice.getText().clear();
        binding.etRating.getText().clear();
        binding.etMaxGuest.getText().clear();
        binding.etTtlInvitation.getText().clear();
        binding.etRasaMakanan.getText().clear();
    }

    private void getSearchVendor(){
        binding.searchV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                getDataByQueryDb(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private void getDataByQueryDb(String query){
        loading().show();
        databaseReference.child("vendor").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(query)){
                    binding.etNameVendor.setText(snapshot.child(query).child("vendorName").getValue(String.class));
                    binding.etNameGedung.setText(snapshot.child(query).child("venue").getValue(String.class));
                    binding.etPrice.setText(snapshot.child(query).child("price").getValue(String.class));
                    binding.etRating.setText(snapshot.child(query).child("rating").getValue(String.class));
                    binding.etTtlInvitation.setText(snapshot.child(query).child("invitation").getValue(String.class));
                    binding.etMaxGuest.setText(snapshot.child(query).child("maximumGuests").getValue(String.class));
                    binding.etRasaMakanan.setText(snapshot.child(query).child("tasteFood").getValue(String.class));
                }
                dismisDialog();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setDataByQuery(String query){
//        databaseReference.child("vendor").addListenerForSingleValueEvent()
    }

    private ProgressDialog loading(){
        progressDialog = new ProgressDialog(SpkVendorWeddingActivity.this);
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