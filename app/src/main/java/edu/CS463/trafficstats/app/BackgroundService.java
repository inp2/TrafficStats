package edu.CS463.trafficstats.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Imani Palmer on 3/16/14.
 */
public class BackgroundService extends Service
{
    Timer timer;
    TimerTask timerTask;

    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        Toast.makeText(this, "Congrats! Service Created", Toast.LENGTH_SHORT).show();
        Log.d(this.toString(), "onCreate");
    }

    @Override
    public void onStart(Intent intent, int startID)
    {
        Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();
        Log.d(this.toString(), "onStart");
        timer = new Timer();
        timerTask = new TimerTask()
        {
            public void run()
            {
                System.out.println("Yes!");
            }

        };

        timer.scheduleAtFixedRate(timerTask, 0, 1);

    }

    @Override
    public void onDestroy()
    {
        Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show();
        Log.d(this.toString(), "onDestory");
        timer.cancel();
        timer = null;
    }

    
}
