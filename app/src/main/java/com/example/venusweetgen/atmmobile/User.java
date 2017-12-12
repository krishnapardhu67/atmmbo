package com.example.venusweetgen.atmmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class User extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    static SharedPreferences sharedPreferences;
    static   DbController controller;
    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        controller=new DbController(this,"",null,1);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);



    }
    public void sendMsg(View view)
    {
        EditText msg=(EditText)findViewById(R.id.msg);
        if(msg.getText().length()==0)
        {
            Toast.makeText(User.this, "Please Fill All the Fields", Toast.LENGTH_SHORT).show();
        }else{
            String uuname= sharedPreferences.getString("uuname","");
            String message = controller.sendMsg(msg.getText().toString(),uuname);
            Toast.makeText(User.this, message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(User.this, User.class);
            startActivity(intent);
        }
    }
public void addnotwork(View view)
{
    Spinner a_category=(Spinner)findViewById(R.id.atm_category);
    EditText a_name=(EditText)findViewById(R.id.a_name);
    EditText a_address=(EditText)findViewById(R.id.a_address);

    if(a_name.getText().length()==0 || a_address.getText().length()==0||a_category.getSelectedItem().toString().length()==0)
    {
        Toast.makeText(User.this, "Please Fill All the Fields", Toast.LENGTH_SHORT).show();
    }else{
        String uuname= sharedPreferences.getString("uuname","");
        String message = controller.addnotwork(a_name.getText().toString(),a_address.getText().toString(),a_category.getSelectedItem().toString(),uuname);
        Toast.makeText(User.this, message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(User.this, User.class);
        startActivity(intent);
    }
}
    public static Cursor getAtm()
    {   String uuname=sharedPreferences.getString("uuname","");
        Cursor cursor=controller.getAtm(uuname);
      return cursor;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user, menu);
        return true;
    }

    public void addatm(View view)
    {
        EditText v_name=(EditText)findViewById(R.id.v_name);
        EditText v_address=(EditText)findViewById(R.id.v_address);
        String v_name2=v_name.getText().toString();
        String v_address2=v_address.getText().toString();
        if(v_name2.length()==0||v_address2.length()==0)
        {
            Toast.makeText(User.this, "Please Fill All the Fields", Toast.LENGTH_SHORT).show();
        }else{
           String uuname= sharedPreferences.getString("uuname","");
            String message = controller.addatm(v_name2,v_address2,uuname);
            Toast.makeText(User.this, message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(User.this, User.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.findatm) {
            Intent intent=new Intent(User.this,MapsActivity.class);
            startActivity(intent);
        } else if (id == R.id.emergency) {
            Emergency fragment=new Emergency();
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();
        }else if (id == R.id.profile) {
            Profile fragment=new Profile();
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();
        }else if (id == R.id.visited) {
            Visited fragment=new Visited();
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();
        }else if (id == R.id.history) {
            History fragment=new History();
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.other) {
            AddNotWork fragment=new AddNotWork();
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();

        }else if (id == R.id.currency) {
           Intent intent=new Intent(User.this,Currency_Converter.class);
            startActivity(intent);
        } else if (id == R.id.nocash) {
            ViewNoCash fragment=new ViewNoCash();
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.notworking) {
            AddNotWork fragment=new AddNotWork();
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.safety) {
            AddSafety fragment=new AddSafety();
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.viewsafety) {
            ViewSafety fragment=new ViewSafety();
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.logout) {
            Intent intent = new Intent(User.this, MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addSecSafe(View view){
        EditText bname=(EditText)findViewById(R.id.bname);
        EditText timings=(EditText)findViewById(R.id.timings);
        EditText location=(EditText)findViewById(R.id.locatoin);
        EditText descr=(EditText)findViewById(R.id.descr);
        if(bname.getText().length()==0||timings.getText().length()==0||location.getText().length()==0||descr.getText().length()==0)
        {
            Toast.makeText(User.this, "Please Fill All the Fields", Toast.LENGTH_SHORT).show();
        }else{
            String uuname=sharedPreferences.getString("uuname","");
            String message=controller.addSecSafe(bname.getText().toString(),timings.getText().toString(),location.getText().toString(),descr.getText().toString(),uuname);
            Toast.makeText(User.this, message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(User.this, User.class);
            startActivity(intent);
        }
    }

    public static Cursor GetNotWork() {
        String uuname=sharedPreferences.getString("uuname","");
        Cursor cursor=controller.GetNotWork(uuname);
        return cursor;
    }

    public static Cursor GetNoCash() {
        String uuname=sharedPreferences.getString("uuname","");
        Cursor cursor=controller.GetCash(uuname);
        return cursor;
    }

    public static Cursor GetUser() {
        String uuname=sharedPreferences.getString("uuname","");
        Cursor cursor=controller.GetUser(uuname);
        return cursor;
    }


    public static Cursor getSecSafe() {
        Cursor cursor=controller.getSecSafe();
        return cursor;
    }
}
