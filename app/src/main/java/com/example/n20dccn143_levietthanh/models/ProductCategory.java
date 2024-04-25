package com.example.n20dccn143_levietthanh.models;

public class ProductCategory {
    private String category_name;
    private String slug;

    public ProductCategory() {
    }

    public ProductCategory(String category_name, String slug) {
        this.category_name = category_name;
        this.slug = slug;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
