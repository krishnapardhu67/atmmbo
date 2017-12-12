package com.example.venusweetgen.atmmobile;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by VENUSWEETGEN on 9/16/2017.
 */

public class GetDirectionsData extends AsyncTask<Object,String,String>{
    GoogleMap mMap;
    String url;
    String GoogleDirectionsData;
    String duration,distance;
    LatLng latLng;
    @Override
    protected String doInBackground(Object... objects) {
        mMap = (GoogleMap)objects[0];
        url = (String)objects[1];
        latLng=(LatLng) objects[2];
        DownloadURL downloadURL = new DownloadURL();
        try {
            GoogleDirectionsData = downloadURL.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return GoogleDirectionsData;
    }


    @Override
    protected void onPostExecute(String s){
        String[] directionsList=null;
        DataParser parser = new DataParser();
        directionsList=parser.parseDirections(s);
        displayDirection(directionsList);
    }

    private void displayDirection(String[] directionsList) {
        int count=directionsList.length;
        for(int i=0;i<count;i++){
            Log.d("List is ggggg",directionsList.toString());
            PolylineOptions options=new PolylineOptions();
            options.color(Color.BLUE);
            options.width(10);
            options.addAll(PolyUtil.decode(directionsList[i]));
            mMap.addPolyline(options);
        }
    }

}
