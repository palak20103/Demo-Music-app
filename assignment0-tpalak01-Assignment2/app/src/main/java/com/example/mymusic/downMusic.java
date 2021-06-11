package com.example.mymusic;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.AsyncTask;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;


import android.app.ProgressDialog;
public class downMusic extends AppCompatActivity {
private Button bchck,db,bonusB,bonusS;
private EditText urlt;
public String URL=" ";
    private ProgressDialog pD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_music);
        bchck = findViewById(R.id.chck);
        bchck.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            if (netIsConnected()) {
                Toast.makeText(getApplicationContext(), "Internet Connection is available", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection available", Toast.LENGTH_LONG).show();
            }}});
        db=findViewById(R.id.downB);
        urlt=findViewById(R.id.urlE);
        bonusB=findViewById(R.id.bonus);
        bonusS=findViewById(R.id.bonusstop);
        db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 URL=urlt.getText().toString();
               // new DownloadMusicFile().execute("https://faculty.iiitd.ac.in/~mukulika/s1.mp3");
                new DownloadMusicFile().execute(URL);
            }
        });
        String path = getFilesDir() + "/"+"newsong.mp3";
        MediaPlayer mplayer = new MediaPlayer();
        bonusB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mplayer.setDataSource(path);
                    mplayer.prepare();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                mplayer.start();
            }
        });
        bonusS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mplayer.stop();
            }
        });

    }

    class DownloadMusicFile extends AsyncTask<String, String, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                System.out.println("Starting your download");

                pD = new ProgressDialog(downMusic.this);
                pD.setMessage("Loading...");
                pD.setIndeterminate(false);
                pD.setCancelable(false);
                pD.show();
            }


            @Override
            protected String doInBackground(String... sng_url) {
                int count;
                try {
                    URL url = new URL(sng_url[0]);
                    URLConnection connection = url.openConnection();
                    connection.connect();
                    byte data[] = new byte[1024];
                    InputStream input = new BufferedInputStream(url.openStream(), 8192);
                   // File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC), "filename.mp3");
                    File file = new File(getFilesDir(), "newsong.mp3");
                    OutputStream output = new FileOutputStream(file);
                    while ((count = input.read(data)) != -1) {
                        output.write(data, 0, count);
                    }
                    output.flush();
                    output.close();
                    input.close();

                } catch (Exception e) {
                    Log.e("Error: ", e.getMessage());
                }
                return null;
            }


        @Override
        protected void onPostExecute(String sng_url) {
            System.out.println("Download  Successful");
            Toast.makeText(downMusic.this,"Download Sucessful",Toast.LENGTH_LONG).show();
            pD.dismiss();
        }
            }
        public boolean netIsConnected() {
            boolean connect = false;
                ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = cm.getActiveNetworkInfo();
                connect = info != null && info.isAvailable() && info.isConnected();
                return connect;
        }
}