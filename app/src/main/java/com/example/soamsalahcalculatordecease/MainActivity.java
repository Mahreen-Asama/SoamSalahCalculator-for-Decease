package com.example.soamsalahcalculatordecease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    Spinner spinner1,spinner2;    //for drop down list
    TextView results;
    Button calculate;
    EditText salah,soam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img=findViewById(R.id.imageView3);
        spinner1=findViewById(R.id.spinner);
        spinner2=findViewById(R.id.spinner2);
        results=findViewById(R.id.result);
        calculate=findViewById(R.id.button3);
        salah=findViewById(R.id.editTextNumber);
        soam=findViewById(R.id.editTextNumber2);

        //adapter required to populate data in spinner
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,
                R.array.time_options, android.R.layout.simple_spinner_item);
        //chooose layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //apply adsapter to spinner
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rzlt="";         // to be shown in that text view
                int fidya=0;

                int SalahCount=0, SoamCount=0;
                String sc=salah.getText().toString();
                String soc=soam.getText().toString();
                if(!sc.matches("")){
                    Log.d("string sc is:",sc);
                    //Toast.makeText(this,"toast",Toast.LENGTH_SHORT).show();
                    SalahCount=Integer.parseInt(sc);
                }
                if(!soc.matches("")){
                    Log.d("string soc is:",sc);
                    SoamCount=Integer.parseInt(soc);
                }

                int pos = spinner1.getSelectedItemPosition();       //get value of choosed option
                String SalahTime= (String) adapter.getItem(pos);
                int poss = spinner2.getSelectedItemPosition();       //get value of choosed option
                String SoamTime= (String) adapter.getItem(pos);

                //wheat rate = 55 rupe per kg
                // so, 1 namaz/roza fidya = 97 rupee
                if(SalahTime.equals("Years")){
                    int totalNmazain = SalahCount*365*6;       //utny salon ki nmazon ki taadad
                    fidya=totalNmazain*97;
                }
                else if(SalahTime.equals("Months")){
                    int totalNmazain = SalahCount*31*6;       //utny months ki nmazon ki taadad
                    fidya=totalNmazain*97;
                }
                else if(SalahTime.equals("Weeks")){
                    int totalNmazain = SalahCount*7*6;       //utny weeks ki nmazon ki taadad
                    fidya=totalNmazain*97;
                }
                else if(SalahTime.equals("Days")){
                    int totalNmazain = SalahCount*6;       //utny days ki nmazon ki taadad
                    fidya=totalNmazain*97;
                }
                else{
                    fidya=0;
                }

                //---- for soam----
                if(SoamTime.equals("Years")){
                    int totalRozy = SoamCount*29;       //utny salon ki nmazon ki taadad
                    fidya+=totalRozy*97;
                }
                else if(SoamTime.equals("Months")){
                    int totalRozy = SoamCount*29;       //utny months ki nmazon ki taadad
                    fidya+=totalRozy*97;
                }
                else if(SoamTime.equals("Weeks")){
                    int totalRozy = SoamCount*7;       //utny weeks ki nmazon ki taadad
                    fidya+=totalRozy*97;
                }
                else if(SoamTime.equals("Days")){
                    int totalRozy = SoamCount;       //utny days ki nmazon ki taadad
                    fidya+=totalRozy*97;
                }
                else{
                    //fidya=0;
                }

                //make result
                rzlt+="Total Fidya to be Paid: "+fidya+" Rs";
                results.setText(rzlt);
            }
        });
    }
}