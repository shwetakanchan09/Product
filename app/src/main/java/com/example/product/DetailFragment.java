package com.example.product;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.product.databinding.ItemSliderBinding;
import com.example.product.helpers.ApiClient;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailFragment extends DialogFragment {
    Context context;
    ImageView back;
    TextView title, description, percentage, rating, price;
    Button btnAdd;
    ViewPager2 viewPager;
    TabLayout pagerIndicator;
    Products products;

    @Override
    public int getTheme() {
        return R.style.FullScreenDialog;
    }

    public DetailFragment(Products product) {
            this.products = (Products) product;

    }

    RecyclerView.Adapter<PagerHolder> adapter = new RecyclerView.Adapter<PagerHolder>() {
        // List<ProductDetailModel> productDetailModelList;

        @NonNull
        @Override
        public PagerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PagerHolder(ItemSliderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PagerHolder holder, int position) {
            //   Glide.with(context).load(images.get(position)).into(holder.itemBinding.imageView);
            Glide.with(context)
                    .load(products.getImages().get(position)).apply(new RequestOptions().centerCrop())
                    .into(holder.itemBinding.imageView);

        }


        @Override
        public int getItemCount() {
            return products.getImages().size();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        context = requireActivity();
        title = v.findViewById(R.id.txtTitle);
        description = v.findViewById(R.id.txtdes);
        percentage = v.findViewById(R.id.per);
        rating = v.findViewById(R.id.txtRate);
        price = v.findViewById(R.id.txtRs);
        viewPager = v.findViewById(R.id.viewPager);
        pagerIndicator = v.findViewById(R.id.pagerIndicator);
        back = v.findViewById(R.id.back);
        btnAdd = v.findViewById(R.id.btnAdd);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Your Product has been Added", Toast.LENGTH_SHORT).show();
            }
        });
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(pagerIndicator, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
            }
        }).attach();

        if (products != null){
            getProductDetail();
            setData();
        }

        return v;
    }

    private void setData() {
        title.setText(products.title);
        description.setText(products.description);
        price.setText(String.valueOf(products.price));
        percentage.setText(String.valueOf(products.discountPercentage));
        rating.setText(String.valueOf(products.rating));
    }

    public void getProductDetail() {
        Call<ProductDetailModel> call = ApiClient.productInterface().getDetailProduct(products.getId());
        call.enqueue(new Callback<ProductDetailModel>() {
            @Override
            public void onResponse(Call<ProductDetailModel> call, Response<ProductDetailModel> response) {
                if (response.code() == 200 && response.body() != null) {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();
                     //images.add((ProductDetailModel) productDetailModel.images);
                   setData();
                }

            }

            @Override
            public void onFailure(Call<ProductDetailModel> call, Throwable t) {
                Toast.makeText(requireContext(), "onFailure", Toast.LENGTH_SHORT).show();

            }
        });
    }

    class PagerHolder extends RecyclerView.ViewHolder {
        ItemSliderBinding itemBinding;

        public PagerHolder(@NonNull ItemSliderBinding itemView) {
            super(itemView.getRoot());
            this.itemBinding = itemView;
        }


    }

}