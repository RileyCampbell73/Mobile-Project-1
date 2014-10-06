package com.example.riley4.conversion_calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//converter app.
//have constants for everything to convert to and from liter?


public class VolumeConverterActivity extends Activity {
    static final double CuptoLiter = 0.2273045;
    static final double LitertoTbspUS = 67.628045405;
    static final double LitertoCup = 4.399384366;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void CalculateConversion(){
        final EditText fromText = (EditText)findViewById(R.id.EditTextFrom);
        final EditText toText = (EditText)findViewById(R.id.EditTextTo);

        double value = Double.parseDouble(fromText.getText().toString());

        if (from.equals("Cup[Canada]")){
            value  = value * CuptoLiter;
        }
        if (to.equals("Tablespoon[US]")){
            value = value * LitertoTbspUS;
        }

        toText.setText(String.valueOf(value));
    }

    public void OnCalcClick(View view) {
        CalculateConversion();
    }
}
