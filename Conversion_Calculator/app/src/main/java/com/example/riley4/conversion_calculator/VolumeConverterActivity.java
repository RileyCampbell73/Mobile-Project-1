package com.example.riley4.conversion_calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

//converter app.
//have constants for everything to convert to and from liter?


public class VolumeConverterActivity extends Activity {
    static final double CupCANtoLiter = 0.2273045;
    static final double CupUStoLiter = 0.2365882365;
    static final double CupMETtoLiter = 0.25;
    static final double TablespoonMETtoLiter = 0.015;
    static final double TablespoonUStoLiter = 0.01420653125;
    static final double TablespoonUKtoLiter = 0.014786764781;
    static final double TeaspoonMETtoLiter = 0.005;
    static final double TeaspoonUStoLiter = 0.0049289215938;
    static final double TeaspoonUKtoLiter = 0.0035516328125;
    static final double MillilitertoLiter = 0.001;
    static final double PintUKtoLiter = 0.56826125;
    static final double PintUSDtoLiter = 0.550610475;
    static final double PintUSLtoLiter = 0.473176473;
    static final double BarrelUKtoLiter = 163.65924;
    static final double BarrelUSDtoLiter = 115.62712358;
    static final double BarrelUSLtoLiter = 119.2404712 ;

    static final double LitertoCupCAN = 4.399384366;
    static final double LitertoCupUS = 4.2267528377;
    static final double LitertoCupMET = 4.0;
    static final double LitertoTablespoonMET = 66.666666667;
    static final double LitertoTablespoonUS = 67.628045405;
    static final double LitertoTablespoonUK = 70.390159456;
    static final double LitertoTeaspoonMET = 200.0;
    static final double LitertoTeaspoonUS = 202.88413621;
    static final double LitertoTeaspoonUK = 281.56063782;
    static final double LitertoMilliliter = 1000.0;
    static final double LitertoPintUK = 1.7597539864;
    static final double LitertoPintUSD = 1.8161659565;
    static final double LitertoPintUSL = 2.1133764189;
    static final double LitertoBarrelUK = 0.0061102568972;
    static final double LitertoBarrelUSD = 0.0086484898096;
    static final double LitertoBarrelUSL = 0.0083864143603;


    String from = "";
    String to = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_converter);
        final Spinner fromSpinner = (Spinner)findViewById(R.id.spinnerFrom);
        final Spinner toSpinner = (Spinner)findViewById(R.id.spinnerTo);
        final EditText fromText = (EditText)findViewById(R.id.EditTextFrom);
        final EditText toText = (EditText)findViewById(R.id.EditTextTo);
        fromText.setText("0");
        toText.setText("0");
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                from = parent.getItemAtPosition(position).toString();
                Toast.makeText(getBaseContext(), from,
                        Toast.LENGTH_LONG).show();
                CalculateConversion();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getBaseContext(), "Please make a selection",
                        Toast.LENGTH_LONG).show();
            }
        });
        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                to = parent.getItemAtPosition(position).toString();
                Toast.makeText(getBaseContext(),to,
                        Toast.LENGTH_LONG).show();
                        //CalculateConversion();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getBaseContext(),"Please make a selection",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.volume_converter, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    public void CalculateConversion(){
        final EditText fromText = (EditText)findViewById(R.id.EditTextFrom);
        final EditText toText = (EditText)findViewById(R.id.EditTextTo);

        double value = Double.parseDouble(fromText.getText().toString());
        DecimalFormat df2 = new DecimalFormat("##.##");

        if (from == to) {
            toText.setText(String.valueOf(df2.format(value)));
        }
        else {
            //convert first thing to liters
            if (from.equals("Cup[Canada]")) {value = value * CupCANtoLiter;}
            else if (from.equals("Cup[US]")) { value = value * CupUStoLiter;}
            else if (from.equals("Cup[Metric]")) {value = value * CupMETtoLiter;}
            else if (from.equals("Tablespoon-Metric[Tbsp]")) {value = value * TablespoonMETtoLiter;}
            else if (from.equals("Tablespoon-US[Tbsp]")) {value = value * TablespoonUKtoLiter; }
            else if (from.equals("Tablespoon-UK[Tbsp]")) { value = value * TablespoonUStoLiter; }
            else if (from.equals("Teaspoon-Metric[Tsp]")) { value = value * TeaspoonMETtoLiter; }
            else if (from.equals("Teaspoon-US[Tsp]")) { value = value * TeaspoonUStoLiter; }
            else if (from.equals("Teaspoon-UK[Tsp]")) { value = value * TeaspoonUKtoLiter; }
            else if (from.equals("Milliliter[ml]")) { value = value * MillilitertoLiter; }
            else if (from.equals("Pint[UK]")) {  value = value * PintUKtoLiter; }
            else if (from.equals("Pint[US,dry]")) { value = value * PintUSDtoLiter; }
            else if (from.equals("Pint[US,Liquid]")) {value = value * PintUSLtoLiter; }
            else if (from.equals("Barrel[UK]")) {   value = value * BarrelUKtoLiter; }
            else if (from.equals("Barrel[US, Dry]")) { value = value * BarrelUSDtoLiter;  }
            else if (from.equals("Barrel[US, Liquid]")) {  value = value * BarrelUSLtoLiter;  }

            //convert second thing from liters to second thing
            if (to.equals("Tablespoon[US]")) { value = value * LitertoTablespoonUS;}
            else if (to.equals("Cup[Canada]")) {    value = value * LitertoCupCAN; }
            else if (to.equals("Cup[US]")) {  value = value * LitertoCupUS; }
            else if (to.equals("Cup[Metric]")) {  value = value * LitertoCupMET; }
            else if (to.equals("Tablespoon-Metric[Tbsp]")) {    value = value * LitertoTablespoonMET; }
            else if (to.equals("Tablespoon-US[Tbsp]")) {   value = value * LitertoTablespoonUS;  }
            else if (to.equals("Tablespoon-UK[Tbsp]")) { value = value * LitertoTablespoonUK;  }
            else if (to.equals("Teaspoon-Metric[Tsp]")) {value = value * LitertoTeaspoonMET;   }
            else if (to.equals("Teaspoon-US[Tsp]")) { value = value * LitertoTeaspoonUS;   }
            else if (to.equals("Teaspoon-UK[Tsp]")) { value = value * LitertoTeaspoonUK;   }
            else if (to.equals("Milliliter[ml]")) {  value = value * LitertoMilliliter;  }
            else if (to.equals("Pint[UK]")) { value = value * LitertoPintUK;     }
            else if (to.equals("Pint[US,dry]")) { value = value * LitertoPintUSD;  }
            else if (to.equals("Pint[US,Liquid]")) { value = value * LitertoPintUSL; }
            else if (to.equals("Barrel[UK]")) {value = value * LitertoBarrelUK;  }
            else if (to.equals("Barrel[US, Dry]")) {  value = value * LitertoBarrelUSD; }
            else if (to.equals("Barrel[US, Liquid]")) { value = value * LitertoBarrelUSL; }
            toText.setText(String.valueOf(df2.format(value)));
        }
    }

    public void OnCalcClick(View view) {
        CalculateConversion();
    }

    public void OnMenuClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.WeightMenuItem:
                startActivity(new Intent(this,WeightConverterActivity.class));
                break;
            case R.id.AboutMenuItem:
                startActivity(new Intent(this,AboutActivity.class));
                break;
            default:
               // return super.onOptionsItemSelected(item);
        }
    }
}
