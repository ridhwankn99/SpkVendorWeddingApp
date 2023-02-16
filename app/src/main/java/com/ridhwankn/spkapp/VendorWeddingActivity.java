package com.ridhwankn.spkapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ridhwankn.spkapp.adapter.VendorWeddingAdapter;
import com.ridhwankn.spkapp.databinding.ActivityVendorWeddingBinding;
import com.ridhwankn.spkapp.model.bean.DetailVendorBean;
import com.ridhwankn.spkapp.viewmodel.VendorWeddingViewModel;

import java.util.ArrayList;

public class VendorWeddingActivity extends AppCompatActivity {
    private ActivityVendorWeddingBinding binding;
    private VendorWeddingViewModel viewModel;
    private VendorWeddingAdapter adapter;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://spkwedding-b87ac-default-rtdb.firebaseio.com");
    private static ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVendorWeddingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(VendorWeddingViewModel.class);
        initData();
        initView();
    }

    private void initView(){
        binding.topBar.ivBack.setOnClickListener(v->{
            finish();
        });
    }

    private void initData(){
        loading().show();
//        ArrayList<DetailVendorBean> detail = viewModel.getDetail();
        ArrayList<DetailVendorBean> detail = new ArrayList<>();
        adapter = new VendorWeddingAdapter(this, detail);
        databaseReference = FirebaseDatabase.getInstance().getReference("vendor");
        binding.rvListWedding.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.rvListWedding.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    DetailVendorBean bean = dataSnapshot.getValue(DetailVendorBean.class);
                    detail.add(bean);
                }
                adapter.notifyDataSetChanged();
                dismisDialog();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter.setOnItemClickListener(new VendorWeddingAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position, DetailVendorBean detailVendorBean) {
                Intent intent = new Intent(VendorWeddingActivity.this, DetailVendorWeddingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", detailVendorBean);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private ProgressDialog loading(){
        progressDialog = new ProgressDialog(VendorWeddingActivity.this);
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