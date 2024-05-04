package com.example.n20dccn143_levietthanh.activities;





import static com.example.n20dccn143_levietthanh.activities.AMainActivity.tokenAdmin;
import static com.example.n20dccn143_levietthanh.activities.AMainActivity.tokenStaff;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.util.Pair;

import com.example.n20dccn143_levietthanh.R;
import com.example.n20dccn143_levietthanh.adapters.CouponAdapter;
import com.example.n20dccn143_levietthanh.apis.ApiService;
import com.example.n20dccn143_levietthanh.models.Coupon;
import com.example.n20dccn143_levietthanh.models.Product;
import com.example.n20dccn143_levietthanh.response.ListEntityStatusResponse;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CouponListAdminActivity extends AppCompatActivity {
    private Button btnAddCoupon;
    public static ListView lvCouponList;
    public static CouponAdapter couponManagerAdapter;
    private ImageView ivBack;
    public static List<Coupon> couponList= new ArrayList<>();
    private androidx.appcompat.widget.SearchView svCoupon;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_coupon_admin);
        setControl();
        setEvent();
    }

    private void setControl() {
        btnAddCoupon = findViewById(R.id.btnAddCoupon);
        lvCouponList = findViewById(R.id.lv_couponList);
        ivBack = findViewById(R.id.ivBack);
        svCoupon = findViewById(R.id.svCoupon);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, AMainActivity.class);
        startActivity(intent);
    }

    private void setEvent() {

        btnAddCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CouponListAdminActivity.this, com.example.n20dccn143_levietthanh.activities.CouponAddingAdminActivity.class));
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CouponListAdminActivity.this, AMainActivity.class);
                startActivity(intent);
            }
        });
        getAllCoupon();
//        svCoupon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openDatePicker(tokenStaff);
//            }
//        });



    }









    public static void getAllCoupon() {
        ApiService.apiService.adminGetAllCoupon(tokenStaff).enqueue(new Callback<ListEntityStatusResponse<Coupon>>() {
            @Override
            public void onResponse(Call<ListEntityStatusResponse<Coupon>> call, Response<ListEntityStatusResponse<Coupon>> response) {
                if(response.isSuccessful()){
                    ListEntityStatusResponse<Coupon> resultResponse = response.body();
                    Log.i("QQQQQQ", ""+response);
                    if(resultResponse != null){
                        couponList = resultResponse.getData();
                        couponManagerAdapter = new CouponAdapter(lvCouponList.getContext(), R.layout.item_coupon_admin, couponList);
                        lvCouponList.setAdapter(couponManagerAdapter);
                    } else{
                        Toast.makeText(lvCouponList.getContext(), "result null",Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(lvCouponList.getContext(), "response false",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ListEntityStatusResponse<Coupon>> call, Throwable t) {
                Toast.makeText(lvCouponList.getContext(), "ko call dc api",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
