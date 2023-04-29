package com.example.iptvstreamapp;

import android.app.ProgressDialog;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class Imedi_TV extends AppCompatActivity {
    private String videoUrl = "https://tv.cdn.xsg.ge/imedihd/index.m3u8?checkedby:iptvcat.com";
    private ProgressDialog pd;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imedi_tv);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        videoView = findViewById(R.id.video);
        pd = new ProgressDialog(this);
        pd.setMessage("Buffering...");
        pd.setCancelable(true);
        playVideo();
    }

    private void playVideo() {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        Uri videoUri = Uri.parse(videoUrl);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(videoUri);
        videoView.requestFocus();
        videoView.setOnPreparedListener(mp -> {
            pd.dismiss();
            videoView.start();
        });
    }
}