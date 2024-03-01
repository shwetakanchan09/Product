package com.example.product;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class AllProductModel {
    @SerializedName("products")
    List<Products> products;

    @SerializedName("total")
    int total;

    @SerializedName("skip")
    int skip;

    @SerializedName("limit")
    int limit;


    public void setProducts(List<Products> products) {
        this.products = products;
    }
    public List<Products> getProducts() {
        return products;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    public int getTotal() {
        return total;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }
    public int getSkip() {
        return skip;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
    public int getLimit() {
        return limit;
    }
}
