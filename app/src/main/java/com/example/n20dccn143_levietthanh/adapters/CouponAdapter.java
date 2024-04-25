package com.example.n20dccn143_levietthanh.adapters;



import static com.example.n20dccn143_levietthanh.activities.UserOrderBuyNowActivity.totalPrice;
import static com.example.n20dccn143_levietthanh.functions.Function.formatDateTimeToDate;
import static com.example.n20dccn143_levietthanh.functions.Function.formatToVND;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.n20dccn143_levietthanh.R;
import com.example.n20dccn143_levietthanh.activities.CouponUserActivity;
import com.example.n20dccn143_levietthanh.activities.UserOrderBuyNowActivity;
import com.example.n20dccn143_levietthanh.functions.OnSaveClickListener;
import com.example.n20dccn143_levietthanh.models.Coupon;
import com.example.n20dccn143_levietthanh.models.Product;


import java.util.ArrayList;
import java.util.List;

public class CouponAdapter extends ArrayAdapter<Coupon> {
    private Context context;
    private Integer resource;
    private List<Coupon> data;
    private OnSaveClickListener onSaveClickListener;
    private List<Coupon> originalList;
    private List<Coupon> filteredList;
    private Filter filter;
    public void setOnSaveClickListener(OnSaveClickListener listener) {
        this.onSaveClickListener = listener;
    }
    public CouponAdapter(@NonNull Context context, int resource, List<Coupon> data){
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    public CouponAdapter(@NonNull Context context, int resource, List<Coupon> data, List<Coupon> filteredList){
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.filteredList=filteredList;
    }


    static class ViewHolder{
        TextView tvValue, tvQuantity, tvMinium, tvTime, tvCouponId, tvStatus, tvSaveCoupon, tvUseCoupon;
        ImageView ivCoupon;

    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            holder = new ViewHolder();
            holder.tvValue = convertView.findViewById(R.id.tv_value);
            holder.tvMinium = convertView.findViewById(R.id.tv_minium);
            holder.tvTime = convertView.findViewById(R.id.tv_time);
            holder.tvQuantity = convertView.findViewById(R.id.tvQuantity);
            holder.ivCoupon = convertView.findViewById(R.id.iv_coupon);
            holder.tvCouponId = convertView.findViewById(R.id.tv_couponId);
            holder.tvStatus = convertView.findViewById(R.id.tv_couponStatus);
            holder.tvSaveCoupon = convertView.findViewById(R.id.tvSaveCoupon);
            holder.tvUseCoupon = convertView.findViewById(R.id.tv_useCoupon);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        Coupon coupon = data.get(position);
        holder.tvValue.setText("Giảm " + formatToVND(coupon.getUse_value()));

        if(holder.tvQuantity!=null) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.tvQuantity.getLayoutParams();
            params.weight = Float.valueOf((coupon.getQuantity()-coupon.getRemaining_amount())*100/coupon.getQuantity());
            //mButton.setLayoutParams(params);
            holder.tvQuantity.setLayoutParams(params);
        }
        holder.tvMinium.setText("Đơn tối thiểu " + formatToVND(coupon.getMinimum_value()));
        holder.tvTime.setText(formatDateTimeToDate(coupon.getStart_date()) +" - " + formatDateTimeToDate(coupon.getEnd_date()));
        Glide.with(convertView)
                .load(coupon.getImage())
                .into(holder.ivCoupon);

        if(holder.tvCouponId != null){
            holder.tvCouponId.setText("Mã Coupon: " + coupon.getCoupon_id().toString());
            holder.tvStatus.setText(coupon.getStatus());

            if(coupon.getStatus().equals("active")){
                holder.tvStatus.setText("Active");
                holder.tvStatus.setTextColor(ContextCompat.getColorStateList(CouponAdapter.this.getContext(), R.color.green));

            } else if(coupon.getStatus().equals("unactive")) {
                holder.tvStatus.setText("Inactive");
                holder.tvStatus.setTextColor(ContextCompat.getColorStateList(CouponAdapter.this.getContext(), R.color.mainColor));
            }
        }

        if(holder.tvSaveCoupon!=null) {
            holder.tvSaveCoupon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onSaveClickListener != null) {
                        onSaveClickListener.onSaveClick(String.valueOf(coupon.getCoupon_id()));
                    }
                    Log.i("YYY", "đã bấm"+coupon.getCoupon_id());
                }
            });
        }

        if(holder.tvUseCoupon!=null){
            if(coupon.getMinimum_value()>totalPrice){
                holder.tvUseCoupon.setTextColor(R.color.darkGrey);
            } else {
                holder.tvUseCoupon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), UserOrderBuyNowActivity.class);
                        intent.putExtra("USE_VALUE", holder.tvValue.getText());
                        v.getContext().startActivity(intent);
                    }
                });
            }
        }
        return convertView;
    }
}
