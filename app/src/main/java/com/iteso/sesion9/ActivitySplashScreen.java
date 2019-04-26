package com.iteso.sesion9;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.iteso.sesion9.DatBase.CityControl;
import com.iteso.sesion9.DatBase.DatabaseHandler;
import com.iteso.sesion9.DatBase.StoreControl;
import com.iteso.sesion9.beans.City;
import com.iteso.sesion9.beans.Store;
import com.iteso.sesion9.beans.User;
import com.iteso.sesion9.tools.Constant;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        DatabaseHandler dh = DatabaseHandler.getInstance(ActivitySplashScreen.this);
        ArrayList<Store> ArrayStores = StoreControl.getStores(dh);
        Log.e("item t",ArrayStores.size() + " en total");
        if(ArrayStores.isEmpty()){
            try {
                City city = CityControl.getCity(dh);

                Store store1 = new Store(0, "Muebles America", "331203221", 2, 12312, 43254, city);
                Store store2 = new Store(1, "Dico", "12578811", 12, 87566, 56290, city);
                Store store3 = new Store(2, "Liverpool", "6558122", 21, 3552, 94562, city);
                StoreControl.addStore(store1, dh);
                StoreControl.addStore(store2, dh);
                StoreControl.addStore(store3, dh);
            }catch (SQLiteException s){
                Log.e("error",s.getMessage());
            }
            ArrayStores = StoreControl.getStores(dh);
            Log.e("item t",ArrayStores.size() + " en total 1");

        }

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                User user = loadPreferences();
                Intent mainIntent;
                if(user.isLogged())
                    mainIntent = new Intent().setClass(ActivitySplashScreen.this, MainActivity.class);
                else
                    mainIntent = new Intent().setClass(ActivitySplashScreen.this, ActivityLogin.class);
                startActivity(mainIntent);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 2000);
    }

    public User loadPreferences(){
        SharedPreferences sharedPreferences =
                getSharedPreferences(Constant.USER_PREFERENCES, MODE_PRIVATE);
        User user = new User();
        user.setName(sharedPreferences.getString("USER", null));
        user.setPassword(sharedPreferences.getString("PWD", null));
        user.setLogged(sharedPreferences.getBoolean("LOGGED", false));
        sharedPreferences = null;
        return user;
    }

}
