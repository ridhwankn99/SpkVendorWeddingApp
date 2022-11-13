package com.ridhwankn.spkapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ridhwankn.spkapp.R;
import com.ridhwankn.spkapp.model.bean.DetailVendorBean;

import java.util.ArrayList;

public class VendorWeddingAdapter extends RecyclerView.Adapter<VendorWeddingAdapter.MyViewHolder> {

    private ArrayList<DetailVendorBean> detailVendorsListBean;
    private Context mContext;
    private MyItemClickListener mItemClickListener;


    public VendorWeddingAdapter(Context context, ArrayList<DetailVendorBean> detailVendorsListBean){
        this.mContext = context;
        this.detailVendorsListBean = detailVendorsListBean;
    }

    public void setListDataItems(ArrayList<DetailVendorBean> detailList){
        this.detailVendorsListBean = detailList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_vendor_wedding, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DetailVendorBean detailVendorBean = detailVendorsListBean.get(position);
        holder.tvNameVendor.setText(detailVendorBean.nameVendor);
        holder.location.setText(detailVendorBean.location);
        holder.rating.setRating((float) detailVendorBean.numStar);
        Glide.with(mContext)
                .load(detailVendorBean.image)
                .into(holder.logo);
        holder.itemView.setOnClickListener(v->{
            if (mItemClickListener != null){
                mItemClickListener.onItemClick(v, position, detailVendorBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return detailVendorsListBean.size();
    }

    public void setOnItemClickListener(MyItemClickListener listener){
        this.mItemClickListener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvNameVendor, location;
        private RatingBar rating;
        private ImageView logo;
        public MyViewHolder(View iteView){
            super(iteView);
            tvNameVendor = iteView.findViewById(R.id.tvNameVendor);
            location = iteView.findViewById(R.id.location);
            rating = iteView.findViewById(R.id.rating);
            logo = iteView.findViewById(R.id.ivLogo);
        }

        @Override
        public void onClick(View v) {

        }
    }
    public interface MyItemClickListener {
        void onItemClick(View view, int position, DetailVendorBean detailVendorBean);
    }
}
