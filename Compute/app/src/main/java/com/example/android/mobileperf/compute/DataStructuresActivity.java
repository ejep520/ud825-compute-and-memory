/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.mobileperf.compute;

import android.app.Activity;
import android.os.Bundle;
import android.os.Trace;
import android.util.Log;
import android.widget.Button;

import com.example.android.mobileperf.compute.databinding.ActivityDataStructuresBinding;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class DataStructuresActivity extends Activity {

    private ActivityDataStructuresBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataStructuresBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button dumpCountriesButton = (Button) findViewById(R.id.ds_button_dostuff);
        binding.dsButtonDostuff.setText("Dump popular numbers to log");

        dumpCountriesButton.setOnClickListener(v -> dumpPopularRandomNumbersByRank());

        // It's much easier to see how your decisions affect frame rate when there's something
        // changing on screen.  For entirely serious, educational purposes, a dancing pirate
        // will be included with this exercise.
        binding.webview.getSettings().setUseWideViewPort(true);
        binding.webview.getSettings().setLoadWithOverviewMode(true);
        binding.webview.loadUrl("file:///android_asset/shiver_me_timbers.gif");
    }

    /**
     * Using the pre-formed array of random numbers ordered by popularity, prints out an ordered
     * list of the random number + rank in the form "(RandomNumber): #(Rank)".
     */
    /*
    public void dumpPopularRandomNumbersByRank() {
        Trace.beginSection("Data Structures");
        // First we need a sorted list of the numbers to iterate through.
        Integer[] sortedNumbers = SampleData.coolestRandomNumbers.clone();
        Arrays.sort(sortedNumbers);

        // Great!  Now because we have no rank lookup in the population-sorted array,
        // take the random number in sorted order, and find its index in the array
        // that's sorted by popularity.  The index is the rank, so report that.  Easy and efficient!
        // Except that it's... you know... It's not.
        for (Integer currentNumber : sortedNumbers) {
            for (int j = 0; j < SampleData.coolestRandomNumbers.length; j++) {
                if (currentNumber.compareTo(SampleData.coolestRandomNumbers[j]) == 0) {
                    Log.i("Popularity Dump", currentNumber + ": #" + j);
                }
            }
        }
        Trace.endSection();
    }
     */

    public void dumpPopularRandomNumbersByRank() {
        Trace.beginSection("Data structures");
        // Make a copy so that we don't accidentally shatter our data structure.
        Map<Integer, Integer> rankedNumbers = new HashMap<>();
        rankedNumbers.putAll(SampleData.coolestRandomNumbers);
        // Then, we need a sorted version of the numbers to iterate through.
        Integer[] sortedNumbers = {};
        sortedNumbers = rankedNumbers.keySet().toArray(sortedNumbers);
        Arrays.sort(sortedNumbers);

        Integer number;
        for (Integer sortedNumber : sortedNumbers) {
            number = sortedNumber;
            Log.i("Popularity Dump", number + ": #" + rankedNumbers.get(number));
        }
        Trace.endSection();
    }}
