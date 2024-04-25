package com.example.n20dccn143_levietthanh.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.n20dccn143_levietthanh.models.ProductCategory;

import java.util.List;

public class CategoryFilterAdapter extends BaseAdapter {

    Context context;
    int resource;
    List<ProductCategory> data;
    TextView txtStatus;

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        return convertView;
    }


}
