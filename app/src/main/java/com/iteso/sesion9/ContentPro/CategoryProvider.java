package com.iteso.sesion9.ContentPro;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import com.iteso.sesion9.DatBase.DatabaseHandler;

public class CategoryProvider extends ContentProvider {
    private static final String uri =
            "content://com.iteso.sesion9.ContentPro.CategoryProvider";
    public static final Uri CONTENT_URI = Uri.parse(uri);

    private DatabaseHandler dh;
    private static final String BD_NOMBRE = "ItemProductDataBase.db";
    private static final int BD_VERSION = 1;
    private static final String TABLA_CATEGORY = "Category";

    private static final int CATEGORY = 1;
    private static final int CATEGORY_ID = 2;
    private static final UriMatcher uriMatcher;

    //Inicializamos el UriMatcher
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.iteso.sesion9.ContentPro.CategoryProvider", "Category", CATEGORY);
        uriMatcher.addURI("com.iteso.sesion9.ContentPro.CategoryProvider", "Category/#", CATEGORY_ID);
    }

    @Override
    public boolean onCreate() {
        dh = DatabaseHandler.getInstance(getContext());
        if(dh!=null){
            return true;
        } else return false;
    }

    @Override
    public Cursor query(Uri uri,String[] projection,String selection, String[] selectionArgs, String sortOrder) {


        String where = selection;

        if(uriMatcher.match(uri)==CATEGORY_ID){
            //Aqui es el caso de solo un item
            where = "id=" + uri.getLastPathSegment();
        }
        SQLiteDatabase db = dh.getWritableDatabase();

        Cursor c = db.query(TABLA_CATEGORY,
                projection,
                where,
                selectionArgs, null, null, sortOrder);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        int match = uriMatcher.match(uri);

        switch (match)
        {
            case CATEGORY:
                return "vnd.android.cursor.dir/vnd.com.iteso.sesion9.ContentPro.CategoryProvider.Category";
            case CATEGORY_ID:
                return "vnd.android.cursor.item/vnd.com.iteso.sesion9.ContentPro.CategoryProvider.Category";
            default:
                return null;
        }

    }

    @Override
    public Uri insert(Uri uri,ContentValues values) {
        long regId = 1;

        SQLiteDatabase db = dh.getWritableDatabase();

        regId = db.insert(TABLA_CATEGORY, null, values);

        Uri newUri = ContentUris.withAppendedId(CONTENT_URI, regId);

        return newUri;
    }

    @Override
    public int delete(Uri uri,String selection, String[] selectionArgs) {
        int cont;

        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if(uriMatcher.match(uri) == CATEGORY_ID){
            where = "id=" + uri.getLastPathSegment();
        }

        SQLiteDatabase db = dh.getWritableDatabase();

        cont = db.delete(TABLA_CATEGORY, where, selectionArgs);

        return cont;
    }

    @Override
    public int update(Uri uri, ContentValues values,String selection, String[] selectionArgs) {
        int cont;

        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if(uriMatcher.match(uri) == CATEGORY_ID){
            where = "id=" + uri.getLastPathSegment();
        }

        SQLiteDatabase db = dh.getWritableDatabase();

        cont = db.update(TABLA_CATEGORY, values, where, selectionArgs);

        return cont;
    }


    public static final class Categooory implements BaseColumns
    {
        private Categooory() {}

        //Nombres de columnas
        public static final String _ID = "id";
        public static final String NAME = "name";
    }
}
