package com.iteso.sesion9.DatBase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.iteso.sesion9.beans.City;
import com.iteso.sesion9.beans.Store;

import java.util.ArrayList;

public class StoreControl {

    public static void addStore(Store store, DatabaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", store.getName());
        contentValues.put("phone", store.getPhone());
        contentValues.put("idcity", 0);
        contentValues.put("thumbnail", store.getThumbnail());
        contentValues.put("latitude", store.getLatitude());
        contentValues.put("longitude", store.getLongitude());

        //Este Long devuelve el ID de la ROW creada
        Log.e("tagbd",db.insert("Store", null, contentValues)+ " ");


        try {
            db.close();
        }catch (Exception e){
            Log.e("StoreControl", "Fallo al insertar y cerrar la BD Metodo ADDSTORE");
        }
    }

    /*public static ArrayList<Store> getStores(DatabaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Store> Stores = new ArrayList<>();


        String select= "SELECT a.id, a.name, phone,b.id,b.name, thumbnail,latitude, longitude  FROM Store a" +
                " join City b on a.idcity = b.id where b.name like '%guadalajara%'";

        //String select= "SELECT *  FROM Store a";


        Cursor cursor = db.rawQuery(select, null);

        while(cursor.moveToNext()){
            Store store = new Store();
            City city = new City(cursor.getInt(3),cursor.getString(4));

            store.setId(cursor.getInt(0));
            store.setName(cursor.getString(1));
            store.setPhone(cursor.getString(2));
            store.setCity(city);
            store.setThumbnail(cursor.getInt(5));
            store.setLatitude(cursor.getDouble(6));
            store.setLongitude(cursor.getDouble(7));

            Stores.add(store);
        }

        try {
            db.close();
        }catch (Exception e){
            Log.e("StoreControl", "Fallo al consultar y cerrar la BD Metodo getStores");
        }

        return Stores;
    }*/

    public static ArrayList<Store> getStores(DatabaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Store> Stores = new ArrayList<>();


        String select= "SELECT id, name, phone,idcity, thumbnail,latitude, longitude  FROM Store ";

        //String select= "SELECT *  FROM Store a";


        Cursor cursor = db.rawQuery(select, null);

        while(cursor.moveToNext()){
            Store store = new Store();
            City city = new City(1,"Guadalajara");

            store.setId(cursor.getInt(0));
            store.setName(cursor.getString(1));
            store.setPhone(cursor.getString(2));
            store.setCity(city);
            store.setThumbnail(cursor.getInt(4));
            store.setLatitude(cursor.getDouble(5));
            store.setLongitude(cursor.getDouble(6));

            Stores.add(store);
        }

        try {
            db.close();
        }catch (Exception e){
            Log.e("StoreControl", "Fallo al consultar y cerrar la BD Metodo getStores");
        }

        return Stores;
    }


    public Store getStoreById(int idStore, DatabaseHandler dh){
        ArrayList<Store> Stores =getStores(dh);
        int i;

        for( i = 0; i<Stores.size();i++ ){
            if(Stores.get(i).getId()== idStore)
                return Stores.get(i);
            }
             return null;

        }


    }

