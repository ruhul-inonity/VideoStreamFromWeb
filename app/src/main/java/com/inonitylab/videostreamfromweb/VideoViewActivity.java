package com.inonitylab.videostreamfromweb;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewActivity extends Activity {

    // Declare variables
    ProgressDialog pDialog;
    VideoView videoview;

    //Video URL
    String VideoURL = "http://api.inonity.com/fileUp/uploads/VID_20161111_001033.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        videoview = (VideoView) findViewById(R.id.VideoView);
        // Execute StreamVideo AsyncTask

        // Create a progressbar
        pDialog = new ProgressDialog(VideoViewActivity.this);

        // Set progressbar title
        pDialog.setTitle("Video Streaming!!");

        // Set progressbar message
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        // Show progressbar
        pDialog.show();

        try {
            // Start the MediaController
            MediaController mediaController = new MediaController(VideoViewActivity.this);

            mediaController.setAnchorView(videoview);
            // Get the URL from String VideoURL
            Uri video = Uri.parse(VideoURL);
            videoview.setMediaController(mediaController);
            videoview.setVideoURI(video);

        } catch (Exception e) {
            // TODO: handle exception
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoview.requestFocus();
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            @Override
            public void onPrepared(MediaPlayer mp) {
                // TODO Auto-generated method stub
                pDialog.dismiss();
                videoview.start();
            }
        });
    }

}
