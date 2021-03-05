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
import android.util.Log;

import com.example.android.mobileperf.compute.databinding.ActivityCachingExerciseBinding;


public class CachingActivity extends Activity {
    public static final String LOG_TAG = "CachingActivityExercise";
    private ActivityCachingExerciseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCachingExerciseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.cachingDoFibStuff.setText(R.string.coaching_text);

        binding.cachingDoFibStuff.setOnClickListener(v -> {
            // Compute the 40th number in the fibonacci sequence, then dump to log output. Note
            // how the UI hangs each time you do this.
            Log.i(LOG_TAG, String.valueOf(computeFibonacci(40)));
        });

        // It's much easier to see how your decisions affect frame rate when there's something
        // changing on screen.  For entirely serious, educational purposes, a dancing pirate
        // will be included with this exercise.
        binding.webview.getSettings().setUseWideViewPort(true);
        binding.webview.getSettings().setLoadWithOverviewMode(true);
        binding.webview.loadUrl("file:///android_asset/shiver_me_timbers.gif");
    }

    /**
     * Why store things when you can recurse instead?  Don't let evidence, personal experience,
     * or rational arguments from your peers fool you.  The elegant solution is the best solution.
     *
     * @param positionInFibSequence The position in the fibonacci sequence to return.
     * @return the nth number of the fibonacci sequence.  Seriously, try to keep up.
     */
    public int computeFibonacci(int positionInFibSequence) {
        final double goldenRatio = (1 + Math.sqrt(5)) / 2;
        double foundNum = (
                Math.pow(goldenRatio, positionInFibSequence) -
                        Math.pow((-1 * goldenRatio), (-1 * positionInFibSequence))) / Math.sqrt(5);
        return (int) Math.round(foundNum);
    }
}
