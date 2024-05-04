package com.example.n20dccn143_levietthanh.apis;


import com.example.n20dccn143_levietthanh.models.Coupon;
import com.example.n20dccn143_levietthanh.models.CouponDetail;
import com.example.n20dccn143_levietthanh.models.Product;
import com.example.n20dccn143_levietthanh.response.ApiResponse;
import com.example.n20dccn143_levietthanh.response.EntityStatusResponse;
import com.example.n20dccn143_levietthanh.response.ListEntityStatusResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.checkerframework.checker.units.qual.A;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("dd-MM-yyyy")
            .create();

    //http://localhost:9999/api/admin/product/all
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.0.106:9999/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);


    // Product API
    @GET("api/admin/product/all")
    Call<ListEntityStatusResponse<Product>> getProductAll(@Header("Authorization") String token);

    @Multipart
    @POST("api/admin/product/add")
    Call<ApiResponse> addProduct(@Header("Authorization") String token,
                                 @Part MultipartBody.Part image,
                                 @Part(Const.KEY_DATA) RequestBody data);

    @DELETE("api/admin/product/{product_id}/delete/")
    Call<ApiResponse> deleteProduct(@Header("Authorization") String token,
                            @Path("product_id") String product_id);

    @GET("api/admin/product/find/{product_id}")
    Call<Product> getProductDetail(@Header("Authorization") String token,
                                        @Path("product_id") String product_id);
    @Multipart
    @PUT("api/admin/product/{product_id}/update/")
    Call<ApiResponse> updateProduct(@Header("Authorization") String token,
                                @Path("product_id") String product_id,
                                @Part MultipartBody.Part image,
                                @Part(Const.KEY_DATA) RequestBody data);






    // Coupon API User
    @GET("api/coupon/all")
    Call<ListEntityStatusResponse<Coupon>> getAllCoupon(@Header("Authorization") String token);

    @GET("api/coupon/get")
    Call<ApiResponse> customerGetCoupon(@Header("Authorization") String token,
                                        @Query("coupon_id") String coupon_id);

    @GET("api/coupon/mycoupon")
    Call<ListEntityStatusResponse<CouponDetail>> getMyCoupon(@Header("Authorization") String token);

    // Coupon API Admin
    @GET("api/admin/coupon/all")
    Call<ListEntityStatusResponse<Coupon>> adminGetAllCoupon(@Header("Authorization") String token);

    @Multipart
    @POST("api/admin/coupon/add")
    Call<ApiResponse> addCoupon(@Header("Authorization") String token,
                      @Part MultipartBody.Part image,
                      @Part(Const.KEY_DATA) RequestBody data);


}

