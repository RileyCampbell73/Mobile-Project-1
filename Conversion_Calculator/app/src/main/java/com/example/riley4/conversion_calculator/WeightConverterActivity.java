package com.example.riley4.conversion_calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class WeightConverterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_converter);
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
}
