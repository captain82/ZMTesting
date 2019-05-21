package com.captain.ak.zmtesting.interfaces;

import com.captain.ak.zmtesting.responses.CategoryResponse;
import com.captain.ak.zmtesting.responses.SearchResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface Service {


    @GET("/api/v2.1/categories")
    Call<CategoryResponse>  getAllCategories();


    @GET("/api/v2.1/search")
    Call<SearchResponse> search(@QueryMap Map<String,String> queryParameters);


}
