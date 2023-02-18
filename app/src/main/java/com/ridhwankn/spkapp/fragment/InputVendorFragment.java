package com.ridhwankn.spkapp.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ridhwankn.spkapp.databinding.FragmentInputVendorBinding;
import com.ridhwankn.spkapp.model.bean.UserBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class InputVendorFragment extends Fragment {

    private FragmentInputVendorBinding binding;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://spkwedding-b87ac-default-rtdb.firebaseio.com");
    private static ProgressDialog progressDialog;
    private String username = "";
    private String userId = "";
    private String key = "";
    final List<String> list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initView();
        getSearchVendor();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInputVendorBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    private void initData(){
        username = UserBean.getInstance().getUsername();
    }

    private void initView(){
        binding.btnInput.setOnClickListener(v->{
            loading().show();
            gotoSaveDb();
        });
    }

    private void gotoSaveDb(){
        if (binding.etNameVendor.getText().toString().equals("")||binding.etNameGedung.getText().toString().equals("")||binding.etRating.getText().toString().equals("")||binding.etRasaMakanan.getText().toString().equals("")
                ||binding.etPrice.getText().toString().equals("")||binding.etMaxGuest.getText().toString().equals("")||binding.etTtlInvitation.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Form tidak boleh kosong", Toast.LENGTH_SHORT).show();
            dismisDialog();
            return;
        }

        databaseReference.child("vendorUser").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(binding.etNameVendor.getText().toString())){
                    Toast.makeText(getActivity(), "Vendor is already registered", Toast.LENGTH_SHORT).show();
                } else {
                    userId = UUID.randomUUID().toString();

                    HashMap<String,String> map = new HashMap<>();
                    map.put("vendorName", binding.etNameVendor.getText().toString());
                    map.put("venue", binding.etNameGedung.getText().toString());
                    map.put("price", binding.etPrice.getText().toString());
                    map.put("rating", binding.etRating.getText().toString());
                    map.put("maximumGuests", binding.etMaxGuest.getText().toString());
                    map.put("invitation", binding.etTtlInvitation.getText().toString());
                    map.put("tasteFood", binding.etRasaMakanan.getText().toString());
                    map.put("uid", userId);

                    databaseReference.child("vendorUser").child(username).child(userId).setValue(map);

                }
                getClear();
                dismisDialog();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dismisDialog();
            }
        });

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
                if (snapshot.hasChild(query.trim())){
                    binding.etNameVendor.setText(snapshot.child(query).child("vendorName").getValue(String.class));
                    binding.etNameGedung.setText(snapshot.child(query).child("venue").getValue(String.class));
                    binding.etPrice.setText(snapshot.child(query).child("price").getValue(String.class));
                    binding.etRating.setText(snapshot.child(query).child("rating").getValue(String.class));
                    binding.etTtlInvitation.setText(snapshot.child(query).child("invitation").getValue(String.class));
                    binding.etMaxGuest.setText(snapshot.child(query).child("maximumGuests").getValue(String.class));
                    binding.etRasaMakanan.setText(snapshot.child(query).child("tasteFood").getValue(String.class));
                } else {
                    Toast.makeText(getActivity(), "Data vendor not found", Toast.LENGTH_SHORT).show();
                }
                dismisDialog();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getClear(){
//        list.clear();
        binding.etNameVendor.getText().clear();
        binding.etNameGedung.getText().clear();
        binding.etPrice.getText().clear();
        binding.etRating.getText().clear();
        binding.etMaxGuest.getText().clear();
        binding.etTtlInvitation.getText().clear();
        binding.etRasaMakanan.getText().clear();
    }

    private ProgressDialog loading(){
        progressDialog = new ProgressDialog(getActivity());
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