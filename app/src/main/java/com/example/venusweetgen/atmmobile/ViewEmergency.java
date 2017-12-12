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


public class ViewEmergency extends Fragment {

    public ViewEmergency() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_view_emergency, container, false);
        Cursor cursor=Admin.GetEmergency();
        ListView mylist=(ListView)view.findViewById(R.id.view_emergency);
        ArrayList<String> list=new ArrayList<String>();
        String str=null;

        while (cursor.moveToNext())
        { str="hii";
            str="Message        :"+cursor.getString(cursor.getColumnIndex("msg"))+"\n"+"Date :"+cursor.getString(cursor.getColumnIndex("datee"));
            list.add(str);
        }
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,list);
        mylist.setAdapter(adapter);
        return view;
    }


}
