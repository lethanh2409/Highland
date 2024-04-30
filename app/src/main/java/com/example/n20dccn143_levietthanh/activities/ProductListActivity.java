// Hien thi danh sach san pham

package com.example.n20dccn143_levietthanh.activities;

import static com.example.n20dccn143_levietthanh.activities.AMainActivity.categoryList;
import static com.example.n20dccn143_levietthanh.activities.AMainActivity.tokenStaff;
import static com.example.n20dccn143_levietthanh.apis.ApiService.apiService;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.n20dccn143_levietthanh.R;
import com.example.n20dccn143_levietthanh.adapters.ProductCategoryAdapterVer2;
import com.example.n20dccn143_levietthanh.adapters.ProductManagerAdapter;
import com.example.n20dccn143_levietthanh.models.Product;
import com.example.n20dccn143_levietthanh.response.ListEntityStatusResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends AppCompatActivity {

    public static ProductManagerAdapter productManagerAdapter;
    public static RecyclerView rvProduct;
    public static List<Product> productList = new ArrayList<>();
    private ImageView ivBack;
    private AppCompatSpinner spnFilter;
    private ImageButton btnAdd;
    private androidx.appcompat.widget.SearchView svProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_product_list);
        setControl();
        setEvent();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, AMainActivity.class);
        startActivity(intent);
    }

    private void setEvent() {
        // click vào nút Back trên ActionBar
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductListActivity.this, AMainActivity.class);
                startActivity(intent);
            }
        });

        // click vào nút Thêm sẽ chuyển đn giao diện thêm sản phẩm
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductListActivity.this, ProductAddingActivity.class);
                startActivity(intent);
            }
        });

        // gạch ngang dưới chân từng item
        rvProduct.setLayoutManager(new LinearLayoutManager(this));

        // call api lấy danh sách product
        getProductList();

        svProduct.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(String.valueOf(newText));
                return true;
            }

        });
    }

    private void filterList(String newText) {
        List<Product> filterList = new ArrayList<>();

        for(Product itemProduct: productList){
            if(itemProduct.getProductName().toLowerCase().contains(newText.toLowerCase())){
                filterList.add(itemProduct);
            }
        }

        if(filterList.isEmpty()){
            Toast.makeText(ProductListActivity.this, "No data", Toast.LENGTH_SHORT).show();
        } else{
            productManagerAdapter.setFilterList(filterList);
        }
    }


    private void setControl() {
        rvProduct = findViewById(R.id.rvProduct);
        spnFilter = findViewById(R.id.spnFilter);
        btnAdd = findViewById(R.id.btnAdd);
        ivBack = findViewById(R.id.ivBack);
        svProduct = findViewById(R.id.svProduct);
    }



    public static void getProductList(){
        apiService.getProductAll(tokenStaff).enqueue(new Callback<ListEntityStatusResponse<Product>>() {
            @Override
            public void onResponse(@NonNull Call<ListEntityStatusResponse<Product>> call, @NonNull Response<ListEntityStatusResponse<Product>> response) {
                if(response.isSuccessful()){
                    ListEntityStatusResponse<Product> result = response.body();     // nguyên cái json
                    if(result != null){
                        productList = result.getData();     // lấy list sp từ json
                        productManagerAdapter = new ProductManagerAdapter(rvProduct.getContext(), productList);
                        rvProduct.setAdapter(productManagerAdapter);
                    } else {
                        Toast.makeText(rvProduct.getContext(), "result null", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(rvProduct.getContext(), "response false", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(@NonNull Call<ListEntityStatusResponse<Product>> call, Throwable t) {
                Toast.makeText(rvProduct.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}