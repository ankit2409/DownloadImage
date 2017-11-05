package com.example.dell.downloadimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    public void hitMe(View view)
    {
        Bitmap myImage;
        DownloadImg downloadImg = new DownloadImg();
        try {


            myImage= downloadImg.execute("http://im.rediff.com/cricket/2016/oct/19virat1.jpg").get();
            imageView.setImageBitmap(myImage);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView3);

    }
    public class DownloadImg extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... url) {
            URL urls;
            try {
                urls = new URL(url[0]);
                HttpURLConnection connection = (HttpURLConnection) urls.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }

    }



    }
