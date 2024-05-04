package com.example.n20dccn143_levietthanh.activities;

import static com.example.n20dccn143_levietthanh.activities.AMainActivity.tokenAdmin;
import static com.example.n20dccn143_levietthanh.activities.AMainActivity.tokenUser;
import static com.example.n20dccn143_levietthanh.apis.ApiService.apiService;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.n20dccn143_levietthanh.R;
import com.example.n20dccn143_levietthanh.adapters.CouponAdapter;
import com.example.n20dccn143_levietthanh.functions.OnSaveClickListener;
import com.example.n20dccn143_levietthanh.models.Coupon;
import com.example.n20dccn143_levietthanh.models.CouponDetail;
import com.example.n20dccn143_levietthanh.models.Product;
import com.example.n20dccn143_levietthanh.response.ApiResponse;
import com.example.n20dccn143_levietthanh.response.EntityStatusResponse;
import com.example.n20dccn143_levietthanh.response.ListEntityStatusResponse;
import com.squareup.picasso.Picasso;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CouponUserActivity extends AppCompatActivity {
    private TextView tvShowList, tvShowListReceived, tvSaveCoupon;
    private ListView lv_listCoupon;
    private List<Coupon> listDataCoupon = new ArrayList<>();
    private CouponAdapter couponAdapter;
    private ImageView ivBack;
    boolean isCallApi=false, isChangeList=false;
    Coupon coupon1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_coupon_user);

        setControl();
        setEvent();
    }

    protected void setEvent() {
        getCouponList();

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        tvShowListReceived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvShowList.setTextColor(getColor(R.color.black));
                tvShowList.setBackground(null);
                tvShowListReceived.setBackground(getDrawable(R.drawable.style_border_bottom_3));
                tvShowListReceived.setTextColor(getColor(R.color.mainColor));
                getMyCoupon();

//                if((isCallApi == false) || isChangeList){
//                    getMyCoupon();
//                    isCallApi=true;
//                    isChangeList=false;
//                } else{

//                }
            }
        });

        tvShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvShowListReceived.setTextColor(getColor(R.color.black));
                tvShowListReceived.setBackground(null);
                tvShowList.setBackground(getDrawable(R.drawable.style_border_bottom_3));
                tvShowList.setTextColor(getColor(R.color.mainColor));
                couponAdapter = new CouponAdapter(CouponUserActivity.this, R.layout.item_coupon, listDataCoupon);
                lv_listCoupon.setAdapter(couponAdapter);
            }
        });


    }

    protected void setControl() {
        lv_listCoupon = findViewById(R.id.lv_listCoupon);
        tvShowList = findViewById(R.id.tv_showListCoupon);
        tvShowListReceived = findViewById(R.id.tv_showListReceivedCoupon);
        ivBack = findViewById(R.id.ivBack);
        tvSaveCoupon = findViewById(R.id.tvSaveCoupon);

    }

    public void getCouponList() {
        apiService.getAllCoupon(tokenUser).enqueue(new Callback<ListEntityStatusResponse<Coupon>>() {
            @Override
            public void onResponse(Call<ListEntityStatusResponse<Coupon>> call, Response<ListEntityStatusResponse<Coupon>> response) {
                Log.i("XXXXXX", "" + response);
                if (response.isSuccessful()) {

                    ListEntityStatusResponse<Coupon> result = response.body();
                    if (result != null) {
                        List<Coupon> listAllCoupon = result.getData();
                        for(Coupon coupon:listAllCoupon){
                            if(coupon.getStatus().equals("active")&&coupon.getRemaining_amount()>0){
                                listDataCoupon.add(coupon);
                            }
                        }
                        couponAdapter = new CouponAdapter(CouponUserActivity.this, R.layout.item_coupon, listDataCoupon);
                        couponAdapter.setOnSaveClickListener(new OnSaveClickListener() {
                            @Override
                            public void onSaveClick(String coupon_id) {
                                Long couponId = Long.valueOf(coupon_id);
                                customerGetCoupon(tokenUser, couponId);
                            }
                        });
                        lv_listCoupon.setAdapter(couponAdapter);
                    }
                } else {
                    Toast.makeText(CouponUserActivity.this, "response false", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListEntityStatusResponse<Coupon>> call, Throwable t) {
                Toast.makeText(CouponUserActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getMyCoupon() {
        List<Coupon> MyCouponList = new ArrayList<>();
        apiService.getMyCoupon(tokenUser).enqueue(new Callback<ListEntityStatusResponse<CouponDetail>>() {
            @Override
            public void onResponse(Call<ListEntityStatusResponse<CouponDetail>> call, Response<ListEntityStatusResponse<CouponDetail>> response) {
                if (response.isSuccessful()) {

                    ListEntityStatusResponse<CouponDetail> result = response.body();
                    if (result != null) {

                        List<CouponDetail> couponList = result.getData();
                        for (CouponDetail couponDetail: couponList){
                            Coupon coupon =  couponDetail.getCoupon();
                            MyCouponList.add(coupon);
                        }
                        couponAdapter = new CouponAdapter(CouponUserActivity.this, R.layout.item_coupon_user, MyCouponList);
                        lv_listCoupon.setAdapter(couponAdapter);
                        //MyCouponList.clear();
                    }
                } else {
                    Toast.makeText(CouponUserActivity.this, "getMyCoupon false", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListEntityStatusResponse<CouponDetail>> call, Throwable t) {
                Toast.makeText(CouponUserActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }




    public void customerGetCoupon(String tokenUser, Long coupon_id) {
        apiService.customerGetCoupon(tokenUser, coupon_id.toString()).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.i("XXXX",""+response);
                if (response.isSuccessful()) {
                    Toast.makeText(CouponUserActivity.this, "response true", Toast.LENGTH_SHORT).show();
                } else {
                    assert response.body() != null;
                    Toast.makeText(CouponUserActivity.this, "response false", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(CouponUserActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

