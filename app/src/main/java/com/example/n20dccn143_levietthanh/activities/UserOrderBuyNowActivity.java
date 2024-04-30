package com.example.n20dccn143_levietthanh.activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.n20dccn143_levietthanh.R;

public class UserOrderBuyNowActivity extends AppCompatActivity {
    private Button btnShowListCoupon;
    private ImageView ivBack;
    TextView tv_totalPrice, tv_coupon;
    public static Long totalPrice;
    String useValue;
    LinearLayout ll_coupon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_user_buy_now);
        setControl();
        setEvent();
        String totalPriceHandle = (String)tv_totalPrice.getText();
        String[] parts = totalPriceHandle.split(" ");
        String part = parts[0];
        totalPrice = Long.valueOf(part);

    }

    protected void setEvent() {
        btnShowListCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserOrderBuyNowActivity.this, CouponUserActivity.class);
                startActivity(intent);
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserOrderBuyNowActivity.this, AMainActivity.class);
                startActivity(intent);
            }
        });

        useValue = getIntent().getStringExtra("USE_VALUE");
        Log.i("SSS", ""+useValue);
        if(useValue!=null){
            ll_coupon.setVisibility(View.VISIBLE);
            String[] parts = useValue.split(" ");
            String useValue1 = parts[1];
            Log.i("DDD",""+useValue1);
            String str = useValue1;
            str = str.replaceAll("[^0-9]", ""); // Loại bỏ tất cả các ký tự không phải số
            tv_coupon.setText(str.toString());
            int number = Integer.parseInt(str); // Chuyển đổi chuỗi thành số nguyên
            tv_totalPrice.setText(String.valueOf(Long.valueOf((String)tv_totalPrice.getText())-Long.valueOf(number)));
        }
    }



    protected void setControl() {
        btnShowListCoupon = findViewById(R.id.btnShowListCoupon);
        ivBack = findViewById(R.id.ivBack);
        tv_totalPrice = findViewById(R.id.tv_totalPrice);
        ll_coupon = findViewById(R.id.ll_coupon);
        tv_coupon = findViewById(R.id.tv_coupon);
    }
}
