package com.example.mymusic;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyService extends Service {
     String st;
    private MediaPlayer mediaPlayer1;
    private MediaPlayer mediaPlayer2;
    private MediaPlayer mediaPlayer3;
    @Nullable
    @Override

    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate(){
        mediaPlayer1 = MediaPlayer.create(getApplicationContext(),R.raw.m);
        mediaPlayer1.setLooping(false);
        mediaPlayer2 = MediaPlayer.create(getApplicationContext(),R.raw.t);
        mediaPlayer2.setLooping(false);
        mediaPlayer3 = MediaPlayer.create(getApplicationContext(),R.raw.s);
        mediaPlayer3.setLooping(false);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel=new NotificationChannel("Channelid1","Demo Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);

        }
        Intent inten=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,inten,0);
        Notification notification=new NotificationCompat.Builder(this,"Channelid1").setContentTitle("Playing Music")
                .setContentText("Music")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1,notification);

        st=intent.getStringExtra("sng");
        if(st.equals("1"))
        { if(mediaPlayer2.isPlaying()) mediaPlayer2.stop();
            if(mediaPlayer3.isPlaying()) mediaPlayer3.stop();
            mediaPlayer1.start();
        }
        else if (st.equals("2"))
        {
            if(mediaPlayer1.isPlaying()) mediaPlayer1.stop();
            if(mediaPlayer3.isPlaying()) mediaPlayer3.stop();
            mediaPlayer2.start();
        }
        else if(st.equals("3"))
        {
            if(mediaPlayer2.isPlaying()) mediaPlayer2.stop();
            if(mediaPlayer1.isPlaying()) mediaPlayer1.stop();
            mediaPlayer3.start();
        }
        return START_NOT_STICKY;
    }

    public void onDestroy(){
        if(st.equals("1")){
            mediaPlayer1.stop();}
        else if (st.equals("2"))
            mediaPlayer2.stop();
        else if(st.equals("3"))
            mediaPlayer3.stop();
    }
}
