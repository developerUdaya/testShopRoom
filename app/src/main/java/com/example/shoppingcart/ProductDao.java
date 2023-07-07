package com.example.shoppingcart;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.shoppingcart.models.Product;

import java.util.List;

public interface ProductDao {


    @Insert
    void insertProduct(Product product);

    @Insert
    void insertMultipleProducts(List<Product> productList);

    @Query("SELECT * FROM product_table")
    LiveData<List<Product>> getAllProducts();

    @Query("DELETE FROM product_table")
    void  deleteAllItems();

    @Query("UPDATE product_table SET quantity=:quantity WHERE id=:id")
    void updateQuantity(int id , int quantity);

    @Query("SELECT * FROM product_table WHERE category=:category ")
    LiveData<List<Product>> getCategories(String category);

}
