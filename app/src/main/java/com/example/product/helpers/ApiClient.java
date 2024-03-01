package com.example.product.helpers;

import com.example.product.AllProductModel;
import com.example.product.ProductDetailModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiClient {
    public static ProductInterface productInterface;

    public static ProductInterface productInterface() {
        if (productInterface == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(80, TimeUnit.SECONDS)
                    .connectTimeout(80, TimeUnit.SECONDS)
                    .build();
            Gson gson = new GsonBuilder()
                    .setLenient().create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://dummyjson.com")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
            productInterface = retrofit.create(ProductInterface.class);

        }
        return productInterface;
    }

    public interface ProductInterface {
        @GET("/products")
        Call<AllProductModel> getProducts();
        @GET("/products/")
        Call<ProductDetailModel> getDetailProduct(@Query("product_id") int product_id);

    }
}
