package com.example.riley4.conversion_calculator;

import android.app.Activity;
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
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

//converter app.
//Volume Converter done by Riley Campbell
//Finalized Oct 25, 2014


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

    public static final String PREFS_NAME = "VolumeFavorites1";

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
        //set textfields to 1
        fromText.setText("1");
        toText.setText("1");
        //set up listeners on the from spinner
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                from = parent.getItemAtPosition(position).toString();
                CalculateConversion();
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
                CalculateConversion();
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
        for (int i = 0; i <+ selector ; i ++) {
            menu4.add(0, 0, 1, settings.getString("Fav" + Integer.toString(i), ""));
        }
        getMenuInflater().inflate(R.menu.volume_converter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        String title = item.getTitle().toString();
        if (title != "Weight Converter" || title != "Save as Favorite" || title != "About" || title != "Your Favorites" || title != "Conversion List" ) {
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

    public void CalculateConversion(){
        final EditText fromText = (EditText)findViewById(R.id.EditTextFrom);
        final EditText toText = (EditText)findViewById(R.id.EditTextTo);
        //grabs the number from the textbox that the user put in
        double value = Double.parseDouble(fromText.getText().toString());
        DecimalFormat df2 = new DecimalFormat("##.######");

        if (from == to) {
            toText.setText(String.valueOf(df2.format(value)));
        }
        else {
            //convert 'from' number to liters
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

            //convert the converted value to the 'to' value
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
            else if (to.equals("Barrel[US,Dry]")) {  value = value * LitertoBarrelUSD; }
            else if (to.equals("Barrel[US,Liquid]")) { value = value * LitertoBarrelUSL; }
            toText.setText(String.valueOf(df2.format(value)));
        }
    }
//function for the calculate
    public void OnCalcClick(View view) {
        CalculateConversion();
    }
//function for menu clicks
    public void OnMenuClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.WeightMenuItem:
                startActivity(new Intent(this,WeightConverterActivity.class));
                break;
            case R.id.AboutMenuItem:
                startActivity(new Intent(this,AboutActivity.class));
                break;
            case R.id.SaveFavorite:
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                int selector = settings.getInt("selector", 0);
                //push the current conversion to the shared preferences
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("Fav" + selector, from + " to " + to);
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

    public void onAddClick(View view) {
        // Do the conversion, just in case they haven't hit it yet
        CalculateConversion();

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
