package com.example.riley4.conversion_calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class ConversionList extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // get the list of conversions
        List<String> listOfConversions = ((ConversionCalculator) this.getApplication()).getList();

//        if (!listOfConversions.isEmpty())
//            Toast.makeText(getBaseContext(), listOfConversions.get(0), Toast.LENGTH_LONG).show();

        // output them to the textview
        TextView tv = (TextView)findViewById(R.id.textViewList);
        StringBuilder sb = new StringBuilder();
        for( int i = 0; i < listOfConversions.size(); ++i ) {
            sb.append(listOfConversions.get(i).toString());
            sb.append("\n");
        }
        tv.setText(sb.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.conversion_list, menu);
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
            case R.id.WeightMenuItem:
                startActivity(new Intent(this,WeightConverterActivity.class));
                break;
            default:
                // return super.onOptionsItemSelected(item);
        }
    }

    public void clearClick(View view) {
        // clear the list of conversion items
        ((ConversionCalculator) this.getApplication()).clearList();
        // clear the text view
        ((TextView)findViewById(R.id.textViewList)).setText("");
    }
}
