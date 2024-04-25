package com.example.n20dccn143_levietthanh.models;

public class Product {


    private String product_id, product_name, image, description, status;
    private ProductCategory category;
    private PriceUpdateDetail[] price_update_detail;
    public Product() {
    }

    public Product(String productID, String productName, String productImg,
                   PriceUpdateDetail[] price_update_detail, ProductCategory productCategories,
                   String description, String status) {
        this.product_id = productID;
        this.product_name = productName;
        this.image = productImg;
        this.price_update_detail = price_update_detail;
        this.category = productCategories;
        this.description = description;
        this.status = status;
    }

    public ProductCategory getProductCategories() {
        return category;
    }

    public void setProductCategories(ProductCategory productCategories) {
        this.category = productCategories;
    }

    public PriceUpdateDetail[] getPrice_update_detail() {
        return price_update_detail;
    }

    public void setPrice_update_detail(PriceUpdateDetail[] price_update_detail) {
        this.price_update_detail = price_update_detail;
    }

    public String getProductID() {
        return product_id;
    }

    public void setProductID(String productID) {
        this.product_id = productID;
    }

    public String getProductName() {
        return product_name;
    }

    public void setProductName(String productName) {
        this.product_name = productName;
    }


    public String getProductImg() {
        return image;
    }

    public void setProductImg(String productImg) {
        this.image = productImg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
