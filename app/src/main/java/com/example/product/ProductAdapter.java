package com.example.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.product.helpers.Common;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    Context context;
    AllProductModel allProductModel;
    RequestOptions option;
    public ProductAdapter(Context context, AllProductModel allProductModel) {
        this.context = context;
        this.allProductModel = allProductModel;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.progress_animation).error(R.drawable.progress_animation);
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        holder.title.setText(""+ allProductModel.getProducts().get(position).getTitle());
        holder.desc.setText(""+ allProductModel.getProducts().get(position).getDescription());
        holder.rate.setText(""+ allProductModel.getProducts().get(position).getRating());
        holder.rupee.setText(""+ allProductModel.getProducts().get(position).getPrice());
        Glide.with(context)
                .load(""+allProductModel.getProducts().get(position).getThumbnail())
                .apply(option)
                .into(holder.imgProduct);

    }

    @Override
    public int getItemCount() {
        return allProductModel.products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView title,desc,rate,rupee;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgUpNext);
            title = itemView.findViewById(R.id.txtTitle);
            desc = itemView.findViewById(R.id.txtdes);
            rate = itemView.findViewById(R.id.txtRate);
            rupee = itemView.findViewById(R.id.txtRs);
            linearLayout = itemView.findViewById(R.id.llitem);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Products model = allProductModel.getProducts().get(getAdapterPosition());
                    Common.openFragment(new DetailFragment(model));
                    //Common.openFragment(new DetailFragment(allProductModel.getProducts().get(getAdapterPosition())));
                }
            });

        }
    }
}
