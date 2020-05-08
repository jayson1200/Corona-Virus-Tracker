package com.jaysonmeribe.coronavirustracker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ActionBar theToolbar;

    private Typeface font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        font = ResourcesCompat.getFont(this, R.font.pt_sans_narrow_bold);

        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(toolbar);

        theToolbar = getSupportActionBar();

        theToolbar.setTitle("World Covid-19 Info");



    }
}
