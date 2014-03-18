package edu.CS463.trafficstats.app;

import android.app.Service;
import android.content.Intent;
import android.net.TrafficStats;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.Timer;
import java.util.TimerTask;
import java.io.File;
import java.io.FileReader;


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
                getTrafficStats();
            }

        };

        timer.scheduleAtFixedRate(timerTask, 1000, 100);

    }

    @Override
    public void onDestroy()
    {
        Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show();
        Log.d(this.toString(), "onDestory");
        timer.cancel();
        timer = null;
    }

    private void getTrafficStats()
    {
        File file = new File("/proc/uid_stat/10071/tcp_snd");
        File file2 = new File ("/proc/uid_stat/10071/tcp_rcv");

        String text = "";

        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedReader br2 = new BufferedReader(new FileReader(file2));
            String line, line2;

            while((line = br.readLine()) != null && (line2 = br2.readLine()) != null)
            {
                text += SystemClock.currentThreadTimeMillis()/1000 + " " + line2 + " " + line + " " + TrafficStats.getTotalRxPackets() + " " + TrafficStats.getTotalTxPackets() + "\n";
            }
        }
        catch (IOException e)
        {
            Log.d(this.toString(), e.toString());
        }

        try {
            FileOutputStream fOut = openFileOutput("1.txt", MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.write(text);
            osw.close();
        }
        catch (FileNotFoundException e)
        {
            Log.d(this.toString(), e.toString());
        }
        catch (IOException e)
        {
            Log.d(this.toString(), e.toString());
        }

    }
}
