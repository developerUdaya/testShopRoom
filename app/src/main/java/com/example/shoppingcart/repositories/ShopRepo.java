package com.example.shoppingcart.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingcart.ProductDao;
import com.example.shoppingcart.ProductRoomDatabase;
import com.example.shoppingcart.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ShopRepo {

    private ProductDao productDao;


    private Executor executor = Executors.newSingleThreadExecutor();
    private MutableLiveData<List<Product>> mutableProductList;

    public ShopRepo(Application application){
        productDao = ProductRoomDatabase.getInstance(application).productDao();
        mutableProductList = (MutableLiveData<List<Product>>) productDao.getAllProducts();
    }

    public void updateQuantity(final int id , final int quantity) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                productDao.updateQuantity(id, quantity);
            }
        });
    }

    public void deleteAllItems(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                productDao.deleteAllItems();
            }
        });
    }

    public void insertMultipleProducts(final List<Product> productList){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                productDao.insertMultipleProducts(productList);
            }
        });
    }

    public void insertProduct(final Product product){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                productDao.insertProduct(product);
            }
        });
    }

    public LiveData<List<Product>> getProducts() {
        if (mutableProductList == null) {
            mutableProductList = new MutableLiveData<>();
            insertMultipleProducts(loadProducts());
        }
        return mutableProductList;
    }

    private List<Product> loadProducts() {
        List<Product> productList = new ArrayList<>();

        productList.add(new Product(UUID.randomUUID().toString(), "iMac 21", 1299, true, "https://firebasestorage.googleapis.com/v0/b/notes-16738.appspot.com/o/products%2Fimac21.jpeg?alt=media&token=e1cf1537-ab30-44a3-91f1-4d6284e79540" ));
        productList.add(new Product(UUID.randomUUID().toString(), "iPad Air", 799, true, "https://firebasestorage.googleapis.com/v0/b/notes-16738.appspot.com/o/products%2Fipadair.jpeg?alt=media&token=da387155-bd8f-4343-954b-e46da7d252ae"));
        productList.add(new Product(UUID.randomUUID().toString(), "iPad Pro", 999, true, "https://firebasestorage.googleapis.com/v0/b/notes-16738.appspot.com/o/products%2Fipadpro.jpeg?alt=media&token=5d433343-f3b3-43eb-8bf2-5298eb5bf11c"));
        productList.add(new Product(UUID.randomUUID().toString(), "iPhone 11", 699, false, "https://firebasestorage.googleapis.com/v0/b/notes-16738.appspot.com/o/products%2Fiphone11.jpeg?alt=media&token=c6874af2-c81e-48eb-96e9-2f1f3fad617f"));
        productList.add(new Product(UUID.randomUUID().toString(), "iPhone 11 Pro", 999, true, "https://firebasestorage.googleapis.com/v0/b/notes-16738.appspot.com/o/products%2Fiphone11pro.jpg?alt=media&token=c4547c4f-7a46-483d-80e5-8f1a93d96a03"));
        productList.add(new Product(UUID.randomUUID().toString(), "iPhone 11 Pro Max", 1099, true, "https://firebasestorage.googleapis.com/v0/b/notes-16738.appspot.com/o/products%2Fiphone11promax.png?alt=media&token=109a89bd-e52b-4b76-91d4-5175aa516a23"));
        productList.add(new Product(UUID.randomUUID().toString(), "iPhone SE", 399, true, "https://firebasestorage.googleapis.com/v0/b/notes-16738.appspot.com/o/products%2Fiphonese.jpeg?alt=media&token=8a3a144d-0cd8-4f6d-94cb-0d81634ea5d0"));
        productList.add(new Product(UUID.randomUUID().toString(), "MacBook Air", 999, true, "https://firebasestorage.googleapis.com/v0/b/notes-16738.appspot.com/o/products%2Fmacbookair.jpeg?alt=media&token=aae96a4a-e86a-4a15-825a-3da9851330c8"));
        productList.add(new Product(UUID.randomUUID().toString(), "MacBook Pro 13", 1299, true, "https://firebasestorage.googleapis.com/v0/b/notes-16738.appspot.com/o/products%2Fmbp13touch.jpeg?alt=media&token=88c2bf8e-e72d-4243-a9ab-4cc32e3aff18"));
        productList.add(new Product(UUID.randomUUID().toString(), "MacBook Pro 16", 2399, true, "https://firebasestorage.googleapis.com/v0/b/notes-16738.appspot.com/o/products%2Fmbp16touch.jpeg?alt=media&token=24498b7f-09b8-42ea-9edb-1bad649902d4"));

        return loadProducts();
    }
}
