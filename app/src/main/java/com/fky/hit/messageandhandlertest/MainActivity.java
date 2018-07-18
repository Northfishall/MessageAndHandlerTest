package com.fky.hit.messageandhandlertest;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView info;
    private static int count=0;
    private static final int SET = 1 ;

    @SuppressLint("HandlerLeak")
    private Handler myHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch(msg.what)
            {
                case SET:
                    MainActivity.this.info.setText("Message-"+count++);
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info = findViewById(R.id.info);
        Timer timer = new Timer();
        timer.schedule(new Mytask(),0,1000);

    }
    private class Mytask extends TimerTask
    {
        @Override
        public void run()
        {
            Message msg = new Message();
            msg.what=SET;
            MainActivity.this.myHandler.sendMessage(msg);

        }
    }
}
