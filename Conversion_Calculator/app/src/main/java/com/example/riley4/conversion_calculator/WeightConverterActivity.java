package com.example.riley4.conversion_calculator;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    Weight Converter Activity
    Purpose: Converts common weights, allows the user to save their favourite conversions to a list,
             and make a list of conversions so they can convert multiple volumes and weights and see
             them all.
    Authors: James Haig, Riley Campbell
    Date: October 26, 2014
 */

public class WeightConverterActivity extends Activity {

    // pound, ounce, gram, milligram, kilogram, grain,
    static HashMap<String, Double> conversions = new HashMap<String, Double>();

    public static final String PREFS_NAME = "WeightFavorites1";

    String from = "";
    String to = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_converter);

        conversions.put("poundToGram", 453.59237);
        conversions.put("ounceToGram", 28.349523125);
        conversions.put("milligramToGram",  0.001);
        conversions.put("kilogramToGram", 1000.00);
        conversions.put("grainToGram", 0.06479891);
        conversions.put("centigramToGram", 0.01);
        conversions.put("decigramToGram", 0.1);
        conversions.put("dekagramToGram", 10.0);
        conversions.put("dramToGram", 1.7718451953);
        conversions.put("hectogramToGram", 100.0);
        conversions.put("megagramToGram", 1000000.0);
        conversions.put("microgramToGram", 0.000001);
        conversions.put("hundredweightLongUkToGram", 50802.34544);
        conversions.put("hundredweightShortUsToGram", 45359.237);
        conversions.put("tonMetricToGram", 1000000.0);
        conversions.put("tonLongUkToGram", 1016046.9088);
        conversions.put("tonShortUsToGram", 907184.74);

        final Spinner fromSpinner = (Spinner)findViewById(R.id.spinnerFrom);
        final Spinner toSpinner = (Spinner)findViewById(R.id.spinnerTo);
        final EditText fromText = (EditText)findViewById(R.id.EditTextFrom);
        final EditText toText = (EditText)findViewById(R.id.EditTextTo);

        //set textfields to 1
        fromText.setText("1");
        toText.setText("1");

        //set up listeners on the from spinner
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                from = parent.getItemAtPosition(position).toString();
                calculateConversion();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getBaseContext(), "Please make a selection",
                        Toast.LENGTH_LONG).show();
            }
        });
        //set up listeners on the to spinner
        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                to = parent.getItemAtPosition(position).toString();
                calculateConversion();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getBaseContext(), "Please make a selection",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        SubMenu menu4 = menu.addSubMenu(Menu.NONE, 0, 4,"Your Favorites");
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        int selector = settings.getInt("selector", -1);
        for (int i = 0; i <= selector ; i ++) {
            menu4.add(0, 0, 1, settings.getString("Fav" + Integer.toString(i), ""));
        }
        getMenuInflater().inflate(R.menu.weight_converter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        String title = item.getTitle().toString();
        if (title != "Volume Converter" || title != "Save as Favorite" || title != "About" || title != "Your Favorites" || title != "Conversion List" ) {
            int test = title.indexOf("to");
            if (test > -1){
                String to = title.substring(test + 3) ;
                String from = title.substring(0, test - 1);

                final Spinner fromSpinner = (Spinner)findViewById(R.id.spinnerFrom);
                final Spinner toSpinner = (Spinner)findViewById(R.id.spinnerTo);

                ArrayAdapter myAdap = (ArrayAdapter) fromSpinner.getAdapter(); //cast to an ArrayAdapter
                int spinnerPosition = myAdap.getPosition(from);
                //set the spinner according to value
                fromSpinner.setSelection(spinnerPosition);

                myAdap = (ArrayAdapter) toSpinner.getAdapter(); //cast to an ArrayAdapter
                spinnerPosition = myAdap.getPosition(to);
                //set the spinner according to value
                toSpinner.setSelection(spinnerPosition);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void OnMenuClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.VolumeMenuItem:
                startActivity(new Intent(this,VolumeConverterActivity.class));
                break;
            case R.id.AboutMenuItem:
                startActivity(new Intent(this,AboutActivity.class));
                break;
            case R.id.ConversionList:
                startActivity(new Intent(this,ConversionList.class));
                break;
            default:
                // return super.onOptionsItemSelected(item);
        }
    }

    public void calculateConversion()
    {
        double gramAmount = 0.0;

        EditText fromText = (EditText)findViewById(R.id.EditTextFrom);
        EditText toText = (EditText)findViewById(R.id.EditTextTo);

        double amount = Double.parseDouble(fromText.getText().toString());

        if( from.equals(to.toString()) )
            toText.setText(fromText.getText());

        if( from.equals("Ounce[oz]") ) { gramAmount = conversions.get("ounceToGram") * amount; }
        else if( from.equals("Pound[lb]") ) { gramAmount = conversions.get("poundToGram") * amount; }
        else if( from.equals("Kilogram[kg]") ) { gramAmount = conversions.get("kilogramToGram") * amount; }
        else if( from.equals("Milligram[mg]") ) { gramAmount = conversions.get("milligramToGram") * amount; }
        else if( from.equals("Grain") ) { gramAmount = conversions.get("grainToGram") * amount; }
        else if( from.equals("Centigram") ) { gramAmount = conversions.get("centigramToGram") * amount; }
        else if( from.equals("Decigram") ) { gramAmount = conversions.get("decigramToGram") * amount; }
        else if( from.equals("Dekagram") ) { gramAmount = conversions.get("dekagramToGram") * amount; }
        else if( from.equals("Hectogram") ) { gramAmount = conversions.get("hectogramToGram") * amount; }
        else if( from.equals("Megagram") ) { gramAmount = conversions.get("megagramToGram") * amount; }
        else if( from.equals("Microgram") ) { gramAmount = conversions.get("microgramToGram") * amount; }
        else if( from.equals("Dram") ) { gramAmount = conversions.get("dramToGram") * amount; }
        else if( from.equals("Hundredweight[Long, UK]") ) { gramAmount = conversions.get("hundredweightLongUkToGram") * amount; }
        else if( from.equals("Hundredweight[Short, US]") ) { gramAmount = conversions.get("hundredweightShortUsToGram") * amount; }
        else if( from.equals("Ton[Metric]") ) { gramAmount = conversions.get("tonMetricToGram") * amount; }
        else if( from.equals("Ton[Long, UK]") ) { gramAmount = conversions.get("tonLongUkToGram") * amount; }
        else if( from.equals("Ton[Short, US]") ) { gramAmount = conversions.get("tonShortUsToGram") * amount; }
        else if( from.equals("Gram[g]") ) { gramAmount = amount; }

        if( to.equals("Ounce[oz]") ) { gramAmount /= conversions.get("ounceToGram"); }
        else if( to.equals("Pound[lb]") ) { gramAmount /= conversions.get("poundToGram"); }
        else if( to.equals("Kilogram[kg]") ) { gramAmount /= conversions.get("kilogramToGram"); }
        else if( to.equals("Milligram[mg]") ) { gramAmount /= conversions.get("milligramToGram"); }
        else if( to.equals("Grain") ) { gramAmount /= conversions.get("grainToGram"); }
        else if( to.equals("Centigram") ) { gramAmount /= conversions.get("centigramToGram"); }
        else if( to.equals("Decigram") ) { gramAmount /= conversions.get("decigramToGram"); }
        else if( to.equals("Dekagram") ) { gramAmount /= conversions.get("dekagramToGram"); }
        else if( to.equals("Hectogram") ) { gramAmount /= conversions.get("hectogramToGram"); }
        else if( to.equals("Megagram") ) { gramAmount /= conversions.get("megagramToGram"); }
        else if( to.equals("Microgram") ) { gramAmount /= conversions.get("microgramToGram"); }
        else if( to.equals("Dram") ) { gramAmount /= conversions.get("dramToGram"); }
        else if( to.equals("Hundredweight[Long, UK]") ) { gramAmount /= conversions.get("hundredweightLongUkToGram"); }
        else if( to.equals("Hundredweight[Short, US]") ) { gramAmount /= conversions.get("hundredweightShortUsToGram"); }
        else if( to.equals("Ton[Metric]") ) { gramAmount /= conversions.get("tonMetricToGram"); }
        else if( to.equals("Ton[Long, UK]") ) { gramAmount /= conversions.get("tonLongUkToGram"); }
        else if( to.equals("Ton[Short, US]") ) { gramAmount /= conversions.get("tonShortUsToGram"); }

        DecimalFormat df2 = new DecimalFormat("##.####");
        toText.setText(String.valueOf(df2.format(gramAmount)));
    }

    public void OnCalcClick(View view) {
        calculateConversion();
    }

    public void onAddClick(View view) {
        // Do the conversion, just in case they haven't hit it yet
        calculateConversion();

        // add the conversion and the name (if filled out) to a data structure to send over to the conversion list activity
        EditText fromText = (EditText)findViewById(R.id.EditTextFrom);
        EditText toText = (EditText)findViewById(R.id.EditTextTo);

        EditText etConversionName = (EditText)findViewById(R.id.editTextCalcName);

        String conversionName = etConversionName.getText().toString().isEmpty() ? "No Name"
                                 : etConversionName.getText().toString();

        StringBuilder conversionItem = new StringBuilder();
        conversionItem.append("- ");
        conversionItem.append(conversionName);
        conversionItem.append(" : ");
        conversionItem.append(fromText.getText().toString());
        conversionItem.append(" ");
        conversionItem.append(from);
        conversionItem.append(" = ");
        conversionItem.append(toText.getText().toString());
        conversionItem.append(" ");
        conversionItem.append(to);

        // add the item to the list of items that will be displayed on the ConversionList activity
        ((ConversionCalculator) this.getApplication()).addItem(conversionItem.toString());

        // clear out the conversion name edit text box
        etConversionName.setText("");

        Toast.makeText(getBaseContext(), "Conversion Added!", Toast.LENGTH_LONG).show();
    }

    public void addToFavorites(View view) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        int selector = settings.getInt("selector", 0);
        //push the current conversion to the shared preferences
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("Fav" + selector, from + " to " + to);
        selector = selector + 1;
        editor.putInt("selector", selector);
        editor.commit();
        Toast.makeText(getBaseContext(), "Favorite Added!", Toast.LENGTH_LONG).show();
    }
}
