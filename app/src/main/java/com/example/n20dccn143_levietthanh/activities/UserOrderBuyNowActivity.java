package com.example.n20dccn143_levietthanh.activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.n20dccn143_levietthanh.R;

public class UserOrderBuyNowActivity extends AppCompatActivity {
    private Button btnShowListCoupon;
    private ImageView ivBack;
    TextView tv_totalPrice, tv_useValue;
    public static Long totalPrice;
    String useValue;
    LinearLayout ll_coupon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_user_buy_now);
        setControl();
        setEvent();
        totalPrice = Long.valueOf((String) tv_totalPrice.getText());

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
        if(useValue!=null){
            ll_coupon.setVisibility(View.VISIBLE);
            String[] parts = useValue.split(" ");
            String useValue1 = parts[1];
            tv_useValue.setText(useValue1);
            //tv_totalPrice.setText(String.valueOf(Long.valueOf((String)tv_totalPrice.getText())-Long.valueOf(useValue1)));
        }
    }



    protected void setControl() {
        btnShowListCoupon = findViewById(R.id.btnShowListCoupon);
        ivBack = findViewById(R.id.ivBack);
        tv_totalPrice = findViewById(R.id.tv_totalPrice);
        ll_coupon = findViewById(R.id.ll_coupon);
        tv_useValue = findViewById(R.id.tv_useValue);
    }
}
