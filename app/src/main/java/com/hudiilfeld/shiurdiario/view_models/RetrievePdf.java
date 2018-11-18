package com.hudiilfeld.shiurdiario.view_models;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RetrievePdf extends AsyncTask<String, Void, InputStream> {

    PDFView pdfView;
    ProgressBar progressBar;
    Activity activity;

    public RetrievePdf(Activity activity, PDFView pdfView, ProgressBar progressBar) {
        this.pdfView = pdfView;
        this.progressBar = progressBar;
        this.activity = activity;
    }

    @Override
    protected InputStream doInBackground(String... strings) {
        InputStream inputStream = null;

        try {
            URL url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return inputStream;
    }

    @Override
    protected void onPostExecute(InputStream inputStream) {
        pdfView.fromStream(inputStream).spacing(0).load();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
