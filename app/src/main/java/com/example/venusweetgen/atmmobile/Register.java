package com.example.venusweetgen.atmmobile;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;


public class Register extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText dobis;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    DbController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        controller=new DbController(this,"",null,1);
        dobis=(EditText)findViewById(R.id.dob);
       dobis.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Calendar cal = Calendar.getInstance();
               int year = cal.get(Calendar.YEAR);
               int month = cal.get(Calendar.MONTH);
               int day = cal.get(Calendar.DAY_OF_MONTH);

               DatePickerDialog dialog = new DatePickerDialog(
                       Register.this,
                       android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                       mDateSetListener,
                       year,month,day);
               dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
               dialog.show();
           }
       });mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                dobis.setText(date);
            }
        };
    }
    public void register2(View view)
    {
        try {
            EditText nameis = (EditText) findViewById(R.id.name);
            EditText unameis = (EditText) findViewById(R.id.uname);
            EditText passis = (EditText) findViewById(R.id.pass);
            EditText emailis = (EditText) findViewById(R.id.email);
            EditText phoneis = (EditText) findViewById(R.id.phone);
            EditText dobis = (EditText) findViewById(R.id.dob);
            String genderis2 = null;
            RadioGroup genderis = (RadioGroup) findViewById(R.id.gender);
            int selectedId=0;
                    selectedId=genderis.getCheckedRadioButtonId();
           RadioButton radioSexButton=(RadioButton)findViewById(selectedId);


            if (nameis.getText().length() == 0 || unameis.getText().length() == 0 || passis.getText().length() == 0 || emailis.getText().length() == 0 || phoneis.getText().length() == 0 || dobis.getText().length() == 0 || selectedId==0) {
                Toast.makeText(Register.this, "Please All Fields", Toast.LENGTH_SHORT).show();
            } else {
                String message = controller.register(nameis.getText().toString(), unameis.getText().toString(), passis.getText().toString(), emailis.getText().toString(), phoneis.getText().toString(), dobis.getText().toString(), radioSexButton.getText().toString());
                Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);

            }
        }catch (Exception e){
            Log.i(TAG,e.toString());
            Toast.makeText(Register.this,e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}

