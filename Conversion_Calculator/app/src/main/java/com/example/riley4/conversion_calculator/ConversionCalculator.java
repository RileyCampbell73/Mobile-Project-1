package com.example.riley4.conversion_calculator;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HaggyLappy on 25/10/2014.
 */
public class ConversionCalculator extends Application {
    //private HashMap<String, String> conversionList = new HashMap<String, String>();
    private List<String> conversionList = new ArrayList<String>();

    public void clearList()
    {
        conversionList.clear();
    }

    public void addItem(String conversionString )
    {
        conversionList.add(conversionString);
    }

    public List<String> getList()
    {
        return conversionList;
    }
}
