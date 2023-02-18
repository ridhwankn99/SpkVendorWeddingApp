package com.ridhwankn.spkapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ridhwankn.spkapp.R;
import com.ridhwankn.spkapp.model.bean.SpkVendorWeddingBean;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class SpkVendorWeddingAdapter extends RecyclerView.Adapter<SpkVendorWeddingAdapter.MyViewHolder>{
    private Context mContext;
    private ArrayList<SpkVendorWeddingBean> list;
    private ItemClickListener mItemClickListener;

    public SpkVendorWeddingAdapter(Context context, ArrayList<SpkVendorWeddingBean> list){
        this.mContext = context;
        this.list = list;
    }

    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    @NonNull
    @Override
    public SpkVendorWeddingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_spk_vendor_wedding, parent, false);
        return new SpkVendorWeddingAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpkVendorWeddingAdapter.MyViewHolder holder, int position) {
        SpkVendorWeddingBean bean = list.get(position);
        holder.tvTitle.setText(bean.getVendorName());
        holder.tvNameGedung.setText(bean.getVenue());
        holder.tvPrice.setText(formatCurrency(Double.parseDouble(bean.getPrice())));
        holder.tvRating.setText(bean.getRating());
        holder.tvLuasGedung.setText(bean.getMaximumGuests());
        holder.tvLuasParkir.setText(bean.getInvitation());
        holder.tvRasaMakanan.setText(bean.getTasteFood());

        holder.btnDelete.setOnClickListener(v->{
            mItemClickListener.onItemClick(bean.getUid());
        });
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

    public interface ItemClickListener {
        void onItemClick(String uid);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvTitle, tvNameGedung, tvPrice, tvRating, tvLuasGedung, tvLuasParkir,tvRasaMakanan, btnDelete;
        public MyViewHolder(View iteView){
            super(iteView);
            tvTitle = iteView.findViewById(R.id.tvTitle);
            tvNameGedung = iteView.findViewById(R.id.tvNameGedung);
            tvPrice = iteView.findViewById(R.id.tvPrice);
            tvRating = iteView.findViewById(R.id.tvRating);
            tvLuasGedung = iteView.findViewById(R.id.tvLuasGedung);
            tvLuasParkir = iteView.findViewById(R.id.tvLuasParkir);
            tvRasaMakanan = iteView.findViewById(R.id.tvRasaMakanan);
            btnDelete = iteView.findViewById(R.id.btnDelete);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
