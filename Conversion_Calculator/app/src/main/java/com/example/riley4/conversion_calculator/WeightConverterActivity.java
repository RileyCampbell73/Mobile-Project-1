package com.example.riley4.conversion_calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


public class WeightConverterActivity extends Activity {

    // pound, ounce, gram, milligram, kilogram, grain,
    static HashMap<String, Double> conversions = new HashMap<String, Double>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_converter);

        conversions.put("poundToGram", 453.59237);
        conversions.put("ounceToGram", 28.349523125);
        conversions.put("milligramToGram",  0.001);
        conversions.put("kilogramToGram", 1000.00);
        conversions.put("grainToGram", 0.06479891);

        conversions.put("gramToPound", 0.0022046226218);
        conversions.put("gramToOunce", 0.03527396195);
        conversions.put("gramToMilligram", 1000.0);
        conversions.put("gramToKilogram", 0.001);
        conversions.put("gramToGrain", 15.432358353);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.weight_converter, menu);
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

    public void OnMenuClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.VolumeMenuItem:
                startActivity(new Intent(this,VolumeConverterActivity.class));
                break;
            case R.id.AboutMenuItem:
                startActivity(new Intent(this,AboutActivity.class));
                break;
            default:
                // return super.onOptionsItemSelected(item);
        }
    }

    public void calculateConversion(String from, String to, double amount)
    {
        double gramAmount = 0.0;

        EditText fromText = (EditText)findViewById(R.id.EditTextFrom);
        EditText toText = (EditText)findViewById(R.id.EditTextTo);

        if( from.equals(to.toString()) )
            toText.setText(fromText.getText());

        if( from.equals("Ounce(oz)") ) { gramAmount = conversions.get("ounceToGram") * amount; }
        else if( from.equals("Pound(lb)") ) { gramAmount = conversions.get("poundToGram") * amount; }
        else if( from.equals("Kilogram(kg)") ) { gramAmount = conversions.get("kilogramToGram") * amount; }
        else if( from.equals("Milligram(mg)") ) { gramAmount = conversions.get("milligramToGram") * amount; }
        else if( from.equals("Grain") ) { gramAmount = conversions.get("grainToGram") * amount; }
        else if( from.equals("Gram(g)") ) { gramAmount = 1.0 * amount; }

        if( to.equals("Ounce(oz)") ) { gramAmount *= conversions.get("gramToOunce"); }
        else if( to.equals("Pound(lb)") ) { gramAmount *= conversions.get("gramToPound"); }
        else if( to.equals("Kilogram(kg)") ) { gramAmount *= conversions.get("gramToKilogram"); }
        else if( to.equals("Milligram(mg)") ) { gramAmount *= conversions.get("gramToMilligram"); }
        else if( to.equals("Grain") ) { gramAmount *= conversions.get("gramToGrain"); }

        DecimalFormat df2 = new DecimalFormat("##.####");
        toText.setText(String.valueOf(df2.format(gramAmount)));
    }

    public void OnCalcClick(View view) {
        // get which types of conversions to do
        EditText fromText = (EditText)findViewById(R.id.EditTextFrom);
        EditText toText = (EditText)findViewById(R.id.EditTextTo);

        Spinner fromSpinner = (Spinner)findViewById(R.id.spinnerFrom);
        Spinner toSpinner = (Spinner)findViewById(R.id.spinnerTo);

        double amount = Double.parseDouble(fromText.getText().toString());

        // pass data to calculateConversion
        calculateConversion(fromSpinner.getSelectedItem().toString(), toSpinner.getSelectedItem().toString(), amount);
    }

    public void onAddClick(View view) {
        // add the conversion and the name (if filled out) to a data structure to send over to the conversion list activity
        
    }
}
