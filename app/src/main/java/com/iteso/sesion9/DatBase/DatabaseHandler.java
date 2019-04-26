package com.iteso.sesion9.DatBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static  final String DATABASE_NAME = "ItemProductDataBase.db";
    private static  final int DATABASE_VERSION = 1;

    private static DatabaseHandler databasehandler;

    private DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHandler getInstance(Context context){
        if(databasehandler == null){
            //aqui inicializamos una instancia de la base de datos
            databasehandler = new DatabaseHandler(context);
        }
        return databasehandler;
    }

    public void onCreate(SQLiteDatabase db) {

        //Query para crear una tabla en base de datos
        String TableCity = "CREATE TABLE City (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT)";
        //instancia la tabla declarada arriba
        db.execSQL(TableCity);

        String InsertCity1 = "INSERT INTO  City (name) VALUES ('Guadajara')";
        db.execSQL(InsertCity1);
        String InsertCity2 = "INSERT INTO  City (name) VALUES ('Queretaro')";
        db.execSQL(InsertCity2);
        String InsertCity3 = "INSERT INTO  City (name) VALUES ('Leon')";
        db.execSQL(InsertCity3);


        String TableCategory = "CREATE TABLE Category (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT)";
        db.execSQL(TableCategory);

        String InsertCategoria1 = "INSERT INTO  Category (name) VALUES ('Technology')";
        db.execSQL(InsertCategoria1);
        String InsertCategoria2 = "INSERT INTO  Category (name) VALUES ('Home')";
        db.execSQL(InsertCategoria2);
        String InsertCategoria3 = "INSERT INTO  Category (name) VALUES ('Electronics')";
        db.execSQL(InsertCategoria3);



        String TableStore = "CREATE TABLE Store (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                "name TEXT,"+
                                                "phone TEXT,"+
                                                "idcity INTEGER,"+
                                                "thumbnail INTEGER,"+
                                                "latitude DOUBLE,"+
                                                "longitude DOUBLE)";
        db.execSQL(TableStore);

        String TableProduct = "CREATE TABLE Product (idproduct INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT,"+
                "image INTEGER,"+
                "idcategory INTEGER)";

        db.execSQL(TableProduct);

        String TableStoreProduct = "CREATE TABLE StoreProduct (Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "idproduct INTEGER,"+
                "idstore INTEGER)";

        db.execSQL(TableStoreProduct);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }



}

