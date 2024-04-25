package com.example.n20dccn143_levietthanh.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


import com.example.n20dccn143_levietthanh.R;
import com.example.n20dccn143_levietthanh.models.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class AMainActivity extends AppCompatActivity {
    Button btnProductManager, btnCouponManager, btnUsingCoupon;
    public static List<ProductCategory> categoryList = new ArrayList<>();
    public static String tokenAdmin = "Bearer "+"eyJhbGciOiJIUzM4NCJ9.eyJpYXQiOjE3MTM1MDg2OTEsImV4cCI6MTcxNDExMzQ5MSwidXNlcm5hbWUiOiJBRE1JTjIiLCJhdXRob3JpdGllcyI6IkFETUlOIn0.NIJyitsR4qU3avResJhBVxY_uLozMS1zAtM6s9qe1zsS3yJPez_MiW0PjlL03RJR";
    public static String tokenStaff="Bearer " + "eyJhbGciOiJIUzM4NCJ9.eyJpYXQiOjE3MTM1MjIzMzcsImV4cCI6MTcxNDEyNzEzNywidXNlcm5hbWUiOiJTVEFGRjEiLCJhdXRob3JpdGllcyI6IlNUQUZGIn0.Rwv3_I3Ga-ddId4dMw3uRaQzBow4JQTZ_aHJP5Qz6VDLfTVsqrsoNXMhO3E3Fx74";
    public static String tokenUser = "Bearer "+"eyJhbGciOiJIUzM4NCJ9.eyJpYXQiOjE3MTM5NzMwMTgsImV4cCI6MTcxNDU3NzgxOCwidXNlcm5hbWUiOiJDVVNUT01FUjEiLCJhdXRob3JpdGllcyI6IkNVU1RPTUVSIn0.YxpT0YfapUAMI_MDMaDd1SJzHuOGqXcjHeDdYMl824ucIM4JL9arIhDe_HZjVRBw";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setControl();
        khoiTao();
        setEvent();

    }

    private void khoiTao() {
        categoryList.add(new ProductCategory("Cà phê", "ca-phe"));
        categoryList.add(new ProductCategory("Trà sữa", "tra-sua"));
        categoryList.add(new ProductCategory("Trà", "tra"));
        categoryList.add(new ProductCategory("Freeze", "freeze"));
        categoryList.add(new ProductCategory("Nước ngọt", "nuoc-ngot"));
        Log.i("XXXXXXXXXX", ""+categoryList.size());
    }

    private void setEvent() {
        btnProductManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AMainActivity.this, com.example.n20dccn143_levietthanh.activities.ProductListActivity.class));
            }
        });
        btnCouponManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AMainActivity.this, com.example.n20dccn143_levietthanh.activities.CouponListAdminActivity.class);
                startActivity(intent);
            }
        });
        btnUsingCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AMainActivity.this, UserOrderBuyNowActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        btnProductManager = (Button) findViewById(R.id.btnProductManager);
        btnCouponManager = (Button)findViewById(R.id.btnCounponManager);
        btnUsingCoupon = (Button) findViewById(R.id.btnUsingCoupon);
    }
}
