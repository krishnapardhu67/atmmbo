package com.example.venusweetgen.atmmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Currency_Converter extends AppCompatActivity {
    public static TextView data;
    public static   String fromm;
    public static   String too;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency__converter);

        Button button=(Button)findViewById(R.id.fetch);
        data=(TextView)findViewById(R.id.data);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner from=(Spinner)findViewById(R.id.fromm);
                Spinner to=(Spinner)findViewById(R.id.too);
                fromm=from.getSelectedItem().toString();
                too=to.getSelectedItem().toString();

                FetchData fetchData=new FetchData();
                fetchData.execute();
            }
        });

    }
}
