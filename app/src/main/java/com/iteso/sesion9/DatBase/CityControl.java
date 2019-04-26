package com.iteso.sesion9.DatBase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.iteso.sesion9.beans.City;

import java.util.ArrayList;

public class CityControl {

    public static City getCity(DatabaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();

        String select = "SELECT * FROM City WHERE name LIKE '%guadalajara%'";

        Cursor cursor = db.rawQuery(select, null);
        City city = null;

        while(cursor.moveToNext()){
            city = new City();
            city.setId(cursor.getInt(0));
            city.setName(cursor.getString(1));
        }

        try {
            db.close();
        }catch (Exception e){
            Log.e("CityControl", "Fallo al consultar y cerrar la BD Metodo getCity");
        }

        return city;

    }
}
