package com.example.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    Context context;
    List<ProductDetailModel> productDetailModelList;
    RequestOptions option;

    public DetailAdapter(Context context, List<ProductDetailModel> productDetailModelList) {
        this.context = context;
        this.productDetailModelList = productDetailModelList;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.progress_animation).error(R.drawable.progress_animation);

    }

    @NonNull
    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .load(productDetailModelList.get(position).getImages()).apply(option)
                .into(holder.imgPic);

    }

    @Override
    public int getItemCount() {
        return productDetailModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPic = itemView.findViewById(R.id.imageView);

        }
    }
}
