package com.iteso.sesion9;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iteso.sesion9.beans.User;
import com.iteso.sesion9.tools.Constant;

public class ActivityLogin extends AppCompatActivity {

    EditText username,password;
    Button myButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.activity_login_user);
        password = (EditText) findViewById(R.id.activity_login_password);
        myButton = (Button) findViewById(R.id.activity_login_sign);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                switch(v.getId()){
                    case R.id.activity_login_sign:
                        savePreferences();
                        Intent intent = new Intent(ActivityLogin.this , MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        });

    }


    public void savePreferences(){
        User user = new User();
        user.setName(username.getText().toString());
        user.setPassword(password.getText().toString());
        user.setLogged(true);
        SharedPreferences sharedPreferences =
                getSharedPreferences(Constant.USER_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("USER", user.getName());
        editor.putString("PWD", user.getPassword());
        editor.putBoolean("LOGGED", user.isLogged());
        editor.apply();
    }

}
