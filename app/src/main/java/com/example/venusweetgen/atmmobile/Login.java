package com.example.venusweetgen.atmmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    DbController controller;
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        controller=new DbController(this,"",null,1);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }
    public void back(View view)
    {
        Intent intent=new Intent(Login.this,MainActivity.class);
        startActivity(intent);
    }
    public void login(View view)
    {
        EditText unameis=(EditText)findViewById(R.id.uname);
        EditText passis=(EditText)findViewById(R.id.pass);
        if(unameis.getText().length()==0||passis.getText().length()==0)
        {
            Toast.makeText(Login.this,"Please All Fields",Toast.LENGTH_SHORT).show();
        }else
        {
             Cursor cursor=controller.login(unameis.getText().toString(),passis.getText().toString());
            if(cursor.moveToNext())
            {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("uuname",cursor.getString(cursor.getColumnIndex("uname")));
                editor.putString("upass", cursor.getString(cursor.getColumnIndex("pass")));
                editor.putString("name", cursor.getString(cursor.getColumnIndex("name")));
                editor.commit();
                Intent intent=new Intent(Login.this,User.class);
                startActivity(intent);
            }else{
                Toast.makeText(Login.this,"Invalid Login Details",Toast.LENGTH_SHORT).show();
            }

        }
    }
    public void alogin(View view)
    {
        Intent intent=new Intent(Login.this,AdminLogin.class);
        startActivity(intent);
    }
}
