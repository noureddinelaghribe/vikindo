package com.example.aslsignlanguage.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.aslsignlanguage.R;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;

import java.util.ArrayList;
import java.util.List;

import com.example.aslsignlanguage.Controlar.AdapterReels;
import com.example.aslsignlanguage.Model.Reel;

public class ReelsActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private AdapterReels adapter;
    private List<Reel> videoItems = new ArrayList<>();
    private ExoPlayer exoPlayer;
    ImageView home,quiz,aiChat,subsecribe,profile;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reels);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        home = findViewById(R.id.imageButton1);
        quiz = findViewById(R.id.imageButton2);
        aiChat = findViewById(R.id.imageButton4);
        subsecribe = findViewById(R.id.imageButton5);
        profile = findViewById(R.id.imageButton6);
        viewPager = findViewById(R.id.viewPager);

        viewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);

        // Populate your list of videos (this can come from an API or static data)
        videoItems.add(new Reel("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));
        videoItems.add(new Reel("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        videoItems.add(new Reel("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"));
        videoItems.add(new Reel("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4"));
        videoItems.add(new Reel("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4"));
        // Add more VideoItems as needed...

        // Set up adapter
        adapter = new AdapterReels(videoItems, this, new AdapterReels.OnVideoActionListener() {
            @Override
            public void onVideoPause(int position) {

                if (exoPlayer != null) {
                    if (exoPlayer.isPlaying()) {
                        exoPlayer.pause();
                        //Toast.makeText(ReelsActivity.this, "Paused", Toast.LENGTH_SHORT).show();
                    } else {
                        exoPlayer.play();
                        //Toast.makeText(ReelsActivity.this, "Playing", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onVideoLike(int position) {
                //Toast.makeText(ReelsActivity.this, "Liked!", Toast.LENGTH_SHORT).show();
            }
        });
        viewPager.setAdapter(adapter);

        // Initialize a single shared ExoPlayer instance
        exoPlayer = new ExoPlayer.Builder(this).build();
        exoPlayer.setRepeatMode(Player.REPEAT_MODE_ONE); // Optional: loop the video

        //Set page change callback to update video playback
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                playVideoAtPosition(position);
            }
        });


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReelsActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReelsActivity.this, QuizActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });

        aiChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReelsActivity.this, ChatBotActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        subsecribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReelsActivity.this, SubscriptionActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReelsActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });



    }





    private void playVideoAtPosition(int position) {
        // Retrieve the RecyclerView from the ViewPager2
        RecyclerView recyclerView = (RecyclerView) viewPager.getChildAt(0);
        if (recyclerView == null) {
            // If the RecyclerView isn't attached yet, post a runnable to try again.
            viewPager.post(() -> playVideoAtPosition(position));
            return;
        }

        // Now find the ViewHolder for the current position
        AdapterReels.VideoViewHolder viewHolder = (AdapterReels.VideoViewHolder)
                recyclerView.findViewHolderForAdapterPosition(position);
        if (viewHolder == null) {
            // If the viewHolder isn't ready yet, try again shortly
            viewPager.post(() -> playVideoAtPosition(position));
            return;
        }

        // Attach the shared ExoPlayer instance to the current page's PlayerView
        viewHolder.playerView.setPlayer(exoPlayer);

        // Prepare and play the video
        String videoUrl = adapter.getVideoItem(position).getVideoUrl();
        MediaItem mediaItem = MediaItem.fromUri(videoUrl);
        exoPlayer.setMediaItem(mediaItem);
        exoPlayer.prepare();
        exoPlayer.setPlayWhenReady(true);
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (exoPlayer != null) {
            exoPlayer.setPlayWhenReady(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (exoPlayer != null) {
            exoPlayer.setPlayWhenReady(true);
        }
    }

    @Override
    protected void onDestroy() {
        if (exoPlayer != null) {
            exoPlayer.release();
            exoPlayer = null;
        }
        super.onDestroy();
    }


}