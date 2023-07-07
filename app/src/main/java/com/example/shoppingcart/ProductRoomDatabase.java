package com.example.shoppingcart;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.shoppingcart.models.Product;

@Database(entities = {Product.class}, version = 3)
public abstract class ProductRoomDatabase extends RoomDatabase {

        public abstract ProductDao productDao();

        private static volatile ProductRoomDatabase INSTANCE;

        public static ProductRoomDatabase getInstance(Context context){
            if (INSTANCE == null){
                synchronized (ProductRoomDatabase.class){
                    if (INSTANCE ==null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                        ProductRoomDatabase.class, "ProductList_Database")
                                .build();
                    }
                }
            }
            return INSTANCE;
        }
}
