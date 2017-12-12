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


public class ViewSafety extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_view_safety, container, false);
        Cursor cursor=User.getSecSafe();
        ListView mylist=(ListView)view.findViewById(R.id.secsafe);
        ArrayList<String> list=new ArrayList<String>();
        String str=null;

        while (cursor.moveToNext())
        { str="hii";
            str="Bank Name        :"+cursor.getString(cursor.getColumnIndex("bname"))+"\n"+"Timings    :"+cursor.getString(cursor.getColumnIndex("timings"))+"\n"+"Description :"+cursor.getString(cursor.getColumnIndex("descr"))+"\n"+"Added Date :"+cursor.getString(cursor.getColumnIndex("datee"));
            list.add(str);
        }
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,list);
        mylist.setAdapter(adapter);
        return view;
    }


}
