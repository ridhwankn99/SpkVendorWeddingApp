package com.ridhwankn.spkapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ridhwankn.spkapp.R;
import com.ridhwankn.spkapp.model.SpkVendorWeddingModel;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class SpkVendorWeddingAdapter extends RecyclerView.Adapter<SpkVendorWeddingAdapter.MyViewHolder>{
    private Context mContext;
    private ArrayList<SpkVendorWeddingModel> list;

    public SpkVendorWeddingAdapter(Context context, ArrayList<SpkVendorWeddingModel> list){
        this.mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SpkVendorWeddingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_spk_vendor_wedding, parent, false);
        return new SpkVendorWeddingAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpkVendorWeddingAdapter.MyViewHolder holder, int position) {
        SpkVendorWeddingModel bean = list.get(position);
        holder.tvTitle.setText(bean.getNameVendor());
        holder.tvNameGedung.setText(bean.getNameGedung());
        holder.tvPrice.setText(formatCurrency(bean.getPrice()));
        holder.tvRating.setText(Double.toString(bean.getRating()));
        holder.tvLuasGedung.setText(Double.toString(bean.getLuasGedung()));
        holder.tvLuasParkir.setText(Double.toString(bean.getLuasParkir()));
        holder.tvRasaMakanan.setText(Integer.toString(bean.getRasaMakanan()));
    }

    public static String formatCurrency(double amount){
        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(amount);
        return formattedNumber;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvTitle, tvNameGedung, tvPrice, tvRating, tvLuasGedung, tvLuasParkir,tvRasaMakanan;
        public MyViewHolder(View iteView){
            super(iteView);
            tvTitle = iteView.findViewById(R.id.tvTitle);
            tvNameGedung = iteView.findViewById(R.id.tvNameGedung);
            tvPrice = iteView.findViewById(R.id.tvPrice);
            tvRating = iteView.findViewById(R.id.tvRating);
            tvLuasGedung = iteView.findViewById(R.id.tvLuasGedung);
            tvLuasParkir = iteView.findViewById(R.id.tvLuasParkir);
            tvRasaMakanan = iteView.findViewById(R.id.tvRasaMakanan);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
