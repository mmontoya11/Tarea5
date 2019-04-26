package com.iteso.sesion9.DatBase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.iteso.sesion9.beans.Category;

import java.util.ArrayList;

public class CategoryControl {

    public static ArrayList<Category> getCategories(DatabaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Category> Categorys = new ArrayList<>();

        String select= "SELECT id, name FROM Category";
        Cursor cursor = db.rawQuery(select, null);

        while(cursor.moveToNext()){
            Category category = new Category();

            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));
            Categorys.add(category);
        }

        try {
            db.close();
        }catch (Exception e){
            Log.e("CategoryControl","No pudo cerrar la BD, metodo getCategories ");
        }


        return Categorys;
    }
}
