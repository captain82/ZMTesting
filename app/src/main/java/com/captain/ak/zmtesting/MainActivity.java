package com.captain.ak.zmtesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.captain.ak.zmtesting.interfaces.Service;
import com.captain.ak.zmtesting.responses.CategoryResponse;
import com.captain.ak.zmtesting.responses.SearchResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Service mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SessionConfig sessionConfig = new SessionConfig.Builder()
                .setApiKey("327e75c31ca03dbb55cbabe4257acfa9")
                .build(getApplicationContext());
        mService = ZomatoApi.with(sessionConfig).build(getApplicationContext()).createService();

        getAllCategories();
    }

    public void getAllCategories() {

        HashMap<String,String> queryParamsMap = new HashMap<>();

        queryParamsMap.put("q","dum");
        mService.search(queryParamsMap).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                Toast.makeText(MainActivity.this,"success: "+response.body().
                        getRestaurants().get(0).getRestaurant()
                        .getName(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();


            }
        });

        mService.getAllCategories().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                Log.i("Check1" , response.body().getCategories().get(1).getCategories().getName());
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
