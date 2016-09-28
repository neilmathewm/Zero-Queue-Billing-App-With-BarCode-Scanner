package com.example.sonyvaio.billapp;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;

import com.example.sonyvaio.billapp.R;

public class offerpage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setTitle(Html.fromHtml("<font color='#ffffff'><b>BACK  </b></font>"));
        getActionBar().setIcon(
                new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_offerpage);
    }

}
