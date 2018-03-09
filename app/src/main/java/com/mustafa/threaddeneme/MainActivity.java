package com.mustafa.threaddeneme;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {


    MediaPlayer[] mp        = new MediaPlayer[2];
    String piano         = "Piano";
    String electro        = "Electro";

    PlayThread[] playThreads = new PlayThread[2];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp[0] = MediaPlayer.create(this, R.raw.piano);
        mp[1] = MediaPlayer.create(this, R.raw.electro);


        final Button buttonHello = (Button) findViewById(R.id.idpiano);
        buttonHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managerOfSound(piano);
            }
        });

        final Button buttonGoodBye = (Button) findViewById(R.id.idelectro);
        buttonGoodBye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managerOfSound(electro);
            }
        });

       for (int i = 0; i < 2; i++) {
            playThreads[i] = new PlayThread();
        }
      // for (int i = 0; i < 2; i++)
         //   playThreads[i].executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mp[i]);

    }
        class PlayThread extends AsyncTask<MediaPlayer, Void, Void> {

            @Override
            protected Void doInBackground(MediaPlayer... player) {
                player[0].start();

                return null;
            }
        }
    protected void managerOfSound(String theText) {

        if (theText == piano) {
            mp[0] = MediaPlayer.create(this, R.raw.piano);
            playThreads[0].executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mp[0]);
        }
        else if (theText == electro) {
            mp[1] = MediaPlayer.create(this, R.raw.electro);
            playThreads[1].executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mp[1]);
        }
        //mp.start();
    }
}


