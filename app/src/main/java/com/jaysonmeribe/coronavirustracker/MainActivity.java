package com.jaysonmeribe.coronavirustracker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ActionBar theToolbar;

    private Typeface font;

    private TextView confrimedNumber;
    private TextView recoveredNumber;
    private TextView  deathsNumber;
    private TextView criticalNumber;
    private TextView activeNumber;

    private Handler statsHandler;
    private StateTextUpdater statsUpdater;
    
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

        statsUpdater = new StateTextUpdater();
        statsHandler = new Handler();

        confrimedNumber = findViewById(R.id.confirmedNumber);
        recoveredNumber = findViewById(R.id.recoveredNumber);
        deathsNumber = findViewById(R.id.deathsNumber);
        criticalNumber = findViewById(R.id.criticalNumber);
        activeNumber = findViewById(R.id.activeNumber);

        new Thread(statsUpdater).start();
    }



    class StateTextUpdater implements Runnable
    {
        private Document webPage;
        private Elements stats;

        @Override
        public void run() {

            try {
                webPage = Jsoup.connect("https://ncov2019.live/data").get();
                stats = webPage.select("div.container--wrap.bg-navy-4");
            } catch(IOException e)
            {
                e.printStackTrace();
            }

            confrimedNumber.setText(stats.select("p:nth-of-type(3)") .text());
            recoveredNumber.setText(stats.select("p:nth-of-type(11)").text());
            deathsNumber.setText(stats.select(" p:nth-of-type(7)").text());
            criticalNumber.setText(stats.select("p:nth-of-type(5)").text());
            activeNumber.setText(stats.select("p:nth-of-type(9)").text());

            try {
                Thread.sleep(9000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            new Thread(statsUpdater).run();

        }

    }
}