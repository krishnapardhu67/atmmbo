package com.example.venusweetgen.atmmobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class History extends Fragment {
    int did=0;
    SharedPreferences sharedPreferences;
    DbController controller;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public History() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View view=inflater.inflate(R.layout.fragment_history, container, false);
        Cursor cursor=User.getAtm();
        ListView mylist=(ListView)view.findViewById(R.id.atm_history);
        ArrayList<String> list=new ArrayList<String>();
        String str=null;

        while (cursor.moveToNext())
        { str="hii";
            str="Atm Name        :"+cursor.getString(cursor.getColumnIndex("aname"))+"\n"+"ATM Address    :"+cursor.getString(cursor.getColumnIndex("address"))+"\n"+"Date :"+cursor.getString(cursor.getColumnIndex("datee"));
            list.add(str);
        }
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,list);
        mylist.setAdapter(adapter);

        return view;
    }

}
