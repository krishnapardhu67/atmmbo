package com.example.venusweetgen.atmmobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
    }
    public void ulogin(View view)
    {
        Intent intent=new Intent(AdminLogin.this,Login.class);
        startActivity(intent);
    }

    public void alogin(View view)
    {
        EditText unameis=(EditText)findViewById(R.id.uname);
        EditText passis=(EditText)findViewById(R.id.pass);
        if(unameis.getText().length()==0||passis.getText().length()==0)
        {
            Toast.makeText(AdminLogin.this,"Please All Fields",Toast.LENGTH_SHORT).show();
        }else
        {

            if(unameis.getText().toString().equalsIgnoreCase("admin")&&passis.getText().toString().equalsIgnoreCase("admin"))
            {
                Intent intent=new Intent(AdminLogin.this,Admin.class);
                startActivity(intent);
            }else{
                Toast.makeText(AdminLogin.this,"Invalid Login Details",Toast.LENGTH_SHORT).show();
            }

        }
    }
}
