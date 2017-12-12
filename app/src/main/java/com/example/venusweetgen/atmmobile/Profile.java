package com.example.venusweetgen.atmmobile;

import android.content.Context;
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


public class Profile extends Fragment {

    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        Cursor cursor=User.GetUser();
        ListView mylist=(ListView)view.findViewById(R.id.profile);
        ArrayList<String> list=new ArrayList<String>();
        String str=null;

        while (cursor.moveToNext())
        { str="hii";
            str="Name             :"+cursor.getString(cursor.getColumnIndex("name"))+"\n\n"+"Username      :"+cursor.getString(cursor.getColumnIndex("uname"))+"\n\n"+"Password       :"+cursor.getString(cursor.getColumnIndex("pass"))+"\n\n"+"Email               :"+cursor.getString(cursor.getColumnIndex("email"))+"\n\n"+"Phone              :"+cursor.getString(cursor.getColumnIndex("phone"))+"\n\n"+"Date of Birth   :"+cursor.getString(cursor.getColumnIndex("dob"))+"\n\n"+"Gender             :"+cursor.getString(cursor.getColumnIndex("gender"));
            list.add(str);
        }
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,list);
        mylist.setAdapter(adapter);
        return view;
    }

}
