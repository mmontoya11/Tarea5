package com.iteso.sesion9.DatBase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.iteso.sesion9.beans.Category;
import com.iteso.sesion9.beans.ItemProduct;
import com.iteso.sesion9.beans.Store;

import java.util.ArrayList;

public class ItemProductControl {

    public static void addItemProduct(ItemProduct itemProduct,
                        DatabaseHandler dh){
        try {
        SQLiteDatabase db = dh.getWritableDatabase();
        if(!db.isOpen()){
            Log.e("ItemProductControl", "esta abierta la BD");
        }
        ContentValues contentValues = new ContentValues();
        ContentValues contentValues1 = new ContentValues();

        contentValues.put("title", itemProduct.getTitle());
        contentValues.put("image", itemProduct.getImage());
        contentValues.put("idcategory", itemProduct.getCategory().getId());

        ArrayList<Store> Stores = StoreControl.getStores(dh);
        for(int i =0; i<Stores.size();i++){
            if(Stores.get(i).getName().equalsIgnoreCase(itemProduct.getStore())){
                contentValues1.put("idstore",Stores.get(i).getId());
            }
        }
        contentValues1.put("idproduct", itemProduct.getCode());

        dh.getWritableDatabase().insert("Product",null,contentValues);
        dh.getWritableDatabase().insert("StoreProduct",null,contentValues1);

            db.close();
        }catch (Exception e){
            Log.e("ItemProductControl", "Fallo al insertar y cerrar la BD; Metodo addItemProduct");
        }

    }

    public static ArrayList<ItemProduct> getItemProductsByCategory(int idCategory, DatabaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();

       // que	 devuelva	 todos	 los	 productos	 de	 una                categoría

        ArrayList<ItemProduct> itemProducts = new ArrayList<>();
        String select= "SELECT idproduct,title, image FROM Product WHERE idcategory ="+idCategory;

        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            ItemProduct itemProduct = new ItemProduct();
            itemProduct.setCode(cursor.getInt(0));
            itemProduct.setTitle(cursor.getString(1));
            itemProduct.setImage(cursor.getInt(2));
            itemProducts.add(itemProduct);
        }

        try {
            db.close();
        }catch (Exception e){
            Log.e("ItemProductControl", "Fallo al consultar y cerrar la BD; Metodo getItemProductsByCategory");
        }

        return itemProducts;
    }

}
