package com.ibm.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

/**
 * Page showing two youtube videos relating to carpooling
 */
public class WhyCarpoolActivity extends AppCompatActivity {
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayerView youTubePlayerView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_why_carpool);

        youTubePlayerView = findViewById(R.id.youtubeVideo2_whyCarpoolActivity);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView2 = findViewById(R.id.youtubeVideo_whyCarpoolActivity);
        getLifecycle().addObserver(youTubePlayerView2);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoID = "e-gIHqeNwTY";
                youTubePlayer.cueVideo(videoID, 0);

            }
        });

        youTubePlayerView2.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoID = "5HhLCY-pyWo";
                youTubePlayer.cueVideo(videoID,0);
            }
        });
    }

    public void goBack(View v)
    {
        Intent goBackIntent = new Intent(this, AuthActivity.class);
        startActivity(goBackIntent);
        finish();
    }
}