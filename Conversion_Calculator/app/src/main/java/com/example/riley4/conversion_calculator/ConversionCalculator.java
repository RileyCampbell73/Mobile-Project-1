package com.example.riley4.conversion_calculator;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/*
    Conversion Calculator class
    Purpose: This object just holds our conversion list, so that we can add to it from both volume
             and weight converter activities
    Authors: James Haig, Riley Campbell
    Date: October 26, 2014
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
