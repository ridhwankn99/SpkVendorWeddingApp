package com.ridhwankn.spkapp.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.common.util.CollectionUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ridhwankn.spkapp.ResultVendorWeddingActivity;
import com.ridhwankn.spkapp.adapter.SpkVendorWeddingAdapter;
import com.ridhwankn.spkapp.databinding.FragmentListVendorUserBinding;
import com.ridhwankn.spkapp.model.bean.SpkVendorWeddingBean;
import com.ridhwankn.spkapp.model.bean.UserBean;
import com.ridhwankn.spkapp.model.bean.VendorUserBean;

import java.util.ArrayList;
import java.util.Collections;

public class ListVendorUserFragment extends Fragment implements SpkVendorWeddingAdapter.ItemClickListener {

    private FragmentListVendorUserBinding binding;
    private SpkVendorWeddingAdapter adapter;
    private ArrayList<SpkVendorWeddingBean> list= new ArrayList<>();
    private ArrayList<SpkVendorWeddingBean> listBean= new ArrayList<>();
    private ArrayList<SpkVendorWeddingBean> listBean2= new ArrayList<>();
    private ArrayList<SpkVendorWeddingBean> listBean3= new ArrayList<>();
    private SpkVendorWeddingBean bean = new SpkVendorWeddingBean();
    private ArrayList<Double> normalisasi = new ArrayList<>();
    private ArrayList<Double> valueV = new ArrayList<>();
    private double sumNormalisasi = 0.0;
    private static ProgressDialog progressDialog;

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://spkwedding-b87ac-default-rtdb.firebaseio.com");

    private String username = "";

    private double W1= -0.238;
    private double W2= 0.238;
    private double W3= 0.19;
    private double W4= 0.142;
    private double W5= 0.19;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListVendorUserBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void initData(){
        username = UserBean.getInstance().getUsername();
        adapter = new SpkVendorWeddingAdapter(getActivity(), list);
        adapter.addItemClickListener(this);
//        adapter.addItemClickListener();
        binding.rvSpkVendorWedding.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        binding.rvSpkVendorWedding.setAdapter(adapter);
        getListFromDb();

    }

    private void getListFromDb(){
        loading().show();
        list.clear();
        databaseReference.child("vendorUser").child(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot item : snapshot.getChildren()){
                    SpkVendorWeddingBean bean = item.getValue(SpkVendorWeddingBean.class);
                    list.add(bean);
                }
                adapter.notifyDataSetChanged();
                dismisDialog();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initView(){
        binding.btnConfirm.setOnClickListener(v->{
            if (list.size()<2){
                Toast.makeText(getActivity(), "Data harus lebih dari satu", Toast.LENGTH_SHORT).show();
                return;
            }
            for (int i =0; i<list.size(); i++){
                normalisasi.add(Math.pow(Double.parseDouble(list.get(i).getPrice()),W1)
                        *Math.pow(Double.parseDouble(list.get(i).getRating()),W2)
                        *Math.pow(Double.parseDouble(list.get(i).getMaximumGuests()),W3)
                        *Math.pow(Double.parseDouble(list.get(i).getInvitation()),W4)
                        *Math.pow(Double.parseDouble(list.get(i).getTasteFood()),W5)
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

            for (int i=0; i<list.size(); i++){
                if (index==i){
                    bean.setVendorName(list.get(i).getVendorName());
                    bean.setInvitation(list.get(i).getInvitation());
                    bean.setPrice(list.get(i).getPrice());
                    bean.setVenue(list.get(i).getVenue());
                    bean.setRating(list.get(i).getRating());
                    bean.setMaximumGuests(list.get(i).getMaximumGuests());
                    bean.setTasteFood(list.get(i).getTasteFood());
                }
            }
            Intent intent = new Intent(getActivity(), ResultVendorWeddingActivity.class);
            intent.putExtra("price", Double.parseDouble(bean.getPrice()));
            intent.putExtra("vendorName", bean.getVendorName());
            intent.putExtra("nameGedung", bean.getVenue());
            intent.putExtra("ttlInvitation", Integer.parseInt(bean.getInvitation()));
            startActivity(intent);
        });
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

    @Override
    public void onItemClick(String uid) {
        databaseReference.child("vendorUser").child(username).child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getKey().contains(uid)){
                    databaseReference.child("vendorUser").child(username).child(uid).removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}