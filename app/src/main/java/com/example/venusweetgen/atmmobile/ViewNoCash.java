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

public class ViewNoCash extends Fragment {

    public ViewNoCash() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_view_no_cash, container, false);
        Cursor cursor=User.GetNoCash();
        ListView mylist=(ListView)view.findViewById(R.id.no_cash_atms);
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
