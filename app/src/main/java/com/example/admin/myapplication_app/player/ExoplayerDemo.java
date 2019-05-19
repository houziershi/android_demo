package com.example.admin.myapplication_app.player;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.admin.myapplication_app.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

/**
 * Discription:
 * Created by guokun on 2019/5/19.
 */
public class ExoplayerDemo extends AppCompatActivity {
    private static final String VIDEOURI = "https://lrtest.lrts.me/adv_1558081258150_79901_640x360.mp4";
    private PlayerView playerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_layout);
        playerView = findViewById(R.id.player_view);
        setup();
    }

    private void setup() {
        SimpleExoPlayer player  = createSimpleExoPlayer();
        player.prepare(createVideoSource(VIDEOURI));
        setPlayerToVideoPlayerView(player);
    }

    private void setPlayerToVideoPlayerView(SimpleExoPlayer player) {
        playerView.setKeepContentOnPlayerReset(true);
        playerView.setUseController(false);
        playerView.setPlayer(player);
    }

    private ProgressiveMediaSource  createVideoSource(String videouri) {
        DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(this, "guokun");
        return new ProgressiveMediaSource.Factory(defaultDataSourceFactory).createMediaSource(Uri.parse(videouri));
    }


    private SimpleExoPlayer createSimpleExoPlayer() {
        AdaptiveTrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory();
        DefaultTrackSelector trackSelector  = new DefaultTrackSelector(videoTrackSelectionFactory);
        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
        player.setPlayWhenReady(true);
        player.setRepeatMode(Player.REPEAT_MODE_ONE);
        return player;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (playerView.getPlayer() != null) {
            playerView.getPlayer().release();
        }
    }
}
