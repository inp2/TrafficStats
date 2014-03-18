package edu.CS463.trafficstats.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.widget.ToggleButton;
import android.view.View;
import android.content.Intent;


public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onToggleClicked(View view)
    {
        // Is the toggle on?
        boolean on = ((ToggleButton) view).isChecked();

        if (on)
        {
            //Start the service from here
            startService(new Intent(this, BackgroundService.class));
        }
        else
        {
            //Stop the service from here
            stopService(new Intent(this, BackgroundService.class));
           // finish();
            System.exit(0);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
