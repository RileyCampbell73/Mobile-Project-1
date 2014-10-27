package com.example.riley4.conversion_calculator;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightConverterActivity extends Activity {

    // pound, ounce, gram, milligram, kilogram, grain,
    static HashMap<String, Double> conversions = new HashMap<String, Double>();

    public static final String PREFS_NAME = "WeightFavorites1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_converter);

        conversions.put("poundToGram", 453.59237);
        conversions.put("ounceToGram", 28.349523125);
        conversions.put("milligramToGram",  0.001);
        conversions.put("kilogramToGram", 1000.00);
        conversions.put("grainToGram", 0.06479891);

//        conversions.put("gramToPound", 0.0022046226218);
//        conversions.put("gramToOunce", 0.03527396195);
//        conversions.put("gramToMilligram", 1000.0);
//        conversions.put("gramToKilogram", 0.001);
//        conversions.put("gramToGrain", 15.432358353);
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
            case R.id.SaveFavorite:
                // pull the names of the conversions
                Spinner fromSpinner = (Spinner)findViewById(R.id.spinnerFrom);
                Spinner toSpinner = (Spinner)findViewById(R.id.spinnerTo);

                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                int selector = settings.getInt("selector", 0);
                //push the current conversion to the shared preferences
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("Fav" + selector, fromSpinner.getSelectedItem().toString() + " to " + toSpinner.getSelectedItem().toString());
                selector = selector + 1;
                editor.putInt("selector", selector);
                editor.commit();
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

        Spinner fromSpinner = (Spinner)findViewById(R.id.spinnerFrom);
        Spinner toSpinner = (Spinner)findViewById(R.id.spinnerTo);

        String to = toSpinner.getSelectedItem().toString();
        String from = fromSpinner.getSelectedItem().toString();

        double amount = Double.parseDouble(fromText.getText().toString());

        if( from.equals(to.toString()) )
            toText.setText(fromText.getText());

        if( from.equals("Ounce(oz)") ) { gramAmount = conversions.get("ounceToGram") * amount; }
        else if( from.equals("Pound(lb)") ) { gramAmount = conversions.get("poundToGram") * amount; }
        else if( from.equals("Kilogram(kg)") ) { gramAmount = conversions.get("kilogramToGram") * amount; }
        else if( from.equals("Milligram(mg)") ) { gramAmount = conversions.get("milligramToGram") * amount; }
        else if( from.equals("Grain") ) { gramAmount = conversions.get("grainToGram") * amount; }
        else if( from.equals("Gram(g)") ) { gramAmount = amount; }

        if( to.equals("Ounce(oz)") ) { gramAmount /= conversions.get("ounceToGram"); }
        else if( to.equals("Pound(lb)") ) { gramAmount /= conversions.get("poundToGram"); }
        else if( to.equals("Kilogram(kg)") ) { gramAmount /= conversions.get("kilogramToGram"); }
        else if( to.equals("Milligram(mg)") ) { gramAmount /= conversions.get("milligramToGram"); }
        else if( to.equals("Grain") ) { gramAmount /= conversions.get("grainToGram"); }

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

        Spinner fromSpinner = (Spinner)findViewById(R.id.spinnerFrom);
        Spinner toSpinner = (Spinner)findViewById(R.id.spinnerTo);

        String to = toSpinner.getSelectedItem().toString();
        String from = fromSpinner.getSelectedItem().toString();

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
    }
}
