package com.example.product;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.product.helpers.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductFragment extends Fragment {
    RecyclerView productRecycler;
    ProductAdapter productAdapter;
    AllProductModel allProductModel;
    Products products;
    Activity context;
    int id;

/*
    public ProductFragment(Products products, int id) {
        this.products = products;
        this.id = id;
    }
*/
    public ProductFragment(Object object, int id) {
        if (object instanceof Products)
            this.products = (Products) object;
        if (object instanceof AllProductModel)
            this.allProductModel = (AllProductModel) object;
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_product, container, false);
        context = requireActivity();

        productRecycler = v.findViewById(R.id.productRecycler);

        getProductList();

        return v;
    }

    public void getProductList(){
        Call<AllProductModel> call = ApiClient.productInterface().getProducts();
        call.enqueue(new Callback<AllProductModel>() {
            @Override
            public void onResponse(Call<AllProductModel> call, Response<AllProductModel> response) {
               // progressDoalog.dismiss();
                if (response.code()==200 && response.body()!=null) {
                    generateProduct(response.body());
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllProductModel> call, Throwable t) {
                Toast.makeText(requireContext(), "onFailure", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void generateProduct(AllProductModel allProductModel) {
        productAdapter = new ProductAdapter(requireContext(),allProductModel);
        productRecycler.setLayoutManager(new LinearLayoutManager(context));
        productRecycler.setAdapter(productAdapter);
    }

}