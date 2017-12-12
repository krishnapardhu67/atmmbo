package com.example.venusweetgen.atmmobile;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by VENUSWEETGEN on 10/10/2017.
 */

public class FetchData extends AsyncTask<Void,Void,Void> {

    String data ="";
    String dataParsed = "";
    String singleParsed ="";

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            String fromm=Currency_Converter.fromm;
            String too=Currency_Converter.too;
            String myurl="http://free.currencyconverterapi.com/api/v3/convert?q="+fromm+"_"+too+"&compact=y";
            URL url=new URL(myurl);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while (line!=null){
                line=bufferedReader.readLine();
                data=data+line;
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        StringBuffer stringBuffer=new StringBuffer(this.data);

        Currency_Converter.data.setText(this.data.split(":")[2].replace("}}null"," "));
    }
}
