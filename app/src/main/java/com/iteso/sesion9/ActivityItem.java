package com.iteso.sesion9;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.iteso.sesion9.DatBase.CategoryControl;
import com.iteso.sesion9.DatBase.DatabaseHandler;
import com.iteso.sesion9.DatBase.ItemProductControl;
import com.iteso.sesion9.DatBase.StoreControl;
import com.iteso.sesion9.beans.Category;
import com.iteso.sesion9.beans.City;
import com.iteso.sesion9.beans.ItemProduct;
import com.iteso.sesion9.beans.Store;

import java.util.ArrayList;


public class ActivityItem     extends AppCompatActivity  {

    Spinner SpinnerCategory, SpinnerStore, SpinnerDrawable;
    Button guardarItem;
    EditText titulo;
    private String idS="";
    private Category c = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        DatabaseHandler dh = DatabaseHandler.getInstance(this);


        SpinnerCategory = findViewById(R.id.item_activity_spinner_categorias);
        final ArrayList<Category>  ArrayCategorys = CategoryControl.getCategories(dh);
        ArrayList<String> ArrayCategorysString = new ArrayList<>();

        for(int i = 0 ; i<ArrayCategorys.size();i++){
            ArrayCategorysString.add(ArrayCategorys.get(i).getName());
        }
        ArrayAdapter<String> arrayAdapterCategory = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,ArrayCategorysString);
        SpinnerCategory.setAdapter(arrayAdapterCategory);

        SpinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                c = new Category(ArrayCategorys.get(position).getId(),ArrayCategorys.get(position).getName());
                /*c.setId(ArrayCategorys.get(position).getId());
                c.setName(ArrayCategorys.get(position).getName());*/
                Log.e("city",c.getId() + " " + c.getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        SpinnerStore = findViewById(R.id.item_activity_spinner_tiendas);
        final ArrayList<Store> ArrayStores = StoreControl.getStores(dh);
        ArrayList<String> ArrayStoreString = new ArrayList<>();


        for(int i = 0 ; i<ArrayStores.size();i++){
            ArrayStoreString.add(ArrayStores.get(i).getName());
        }

        SpinnerStore.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idS = ArrayStores.get(position).getName();
                Log.e("Contenido IDS",idS);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> arrayAdapterStore = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,ArrayStoreString);
        SpinnerStore.setAdapter(arrayAdapterStore);

        titulo = findViewById(R.id.item_activity_edit_titulo);
        guardarItem = findViewById(R.id.activity_item_Button);
        SpinnerDrawable = findViewById(R.id.item_activity_spinner_drawable);

        guardarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nombre = titulo.getText().toString().trim();
                String Drawable = SpinnerDrawable.getSelectedItem().toString();
                String Categoria = SpinnerCategory.getSelectedItem().toString();
                DatabaseHandler dh = DatabaseHandler.getInstance(ActivityItem.this);
                ArrayList<Category>  ArrayCategorys = CategoryControl.getCategories(dh);
                Category myCategory = new Category();

                for(int i = 0 ; i<ArrayCategorys.size();i++){
                    if(Drawable.equals(ArrayCategorys.get(i).getName())){
                         myCategory = ArrayCategorys.get(i);
                    }
                }

                ItemProduct itemProduct = new ItemProduct(Nombre ,idS,0,c,"Guadalajara","4412344");


                ItemProductControl.addItemProduct(itemProduct,dh);
                finish();
                Intent intent = new Intent(ActivityItem.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
