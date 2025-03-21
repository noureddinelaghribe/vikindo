package com.example.aslsignlanguage.Controlar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aslsignlanguage.R;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.List;

import com.example.aslsignlanguage.Model.Reel;


public class AdapterReels extends RecyclerView.Adapter<AdapterReels.VideoViewHolder> {
    private List<Reel> videoItems;
    private Context context;
    private OnVideoActionListener listener;
    private boolean paused ;
    private boolean liked ;


    // Define an interface for click actions
    public interface OnVideoActionListener {
        void onVideoPause(int position);
        void onVideoLike(int position);
    }

    public AdapterReels(List<Reel> videoItems, Context context,OnVideoActionListener listener) {
        this.videoItems = videoItems;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reel, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {

        int pos = holder.getBindingAdapterPosition();

        paused = false;
        liked = false;
        holder.pause.setVisibility(View.GONE);

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (liked){
                    holder.like.setImageResource(R.drawable.hot_sale);
                    liked = false;
                }else {
                    holder.like.setImageResource(R.drawable.hot_deal);
                    Animation scaleAnimation = AnimationUtils.loadAnimation(context, R.anim.like_scale_animation);
                    holder.like.startAnimation(scaleAnimation);
                    liked = true;
                }

            }
        });

        @SuppressLint("ClickableViewAccessibility")
        GestureDetector gestureDetector = new GestureDetector(holder.playerView.getContext(),
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapConfirmed(MotionEvent e) {

                        if (listener != null) {

                            if (paused){
                                paused = false;
                            }else {
                                paused = true;
                            }

                            if (paused){
                                holder.pause.setVisibility(View.VISIBLE);
                            }else {
                                holder.pause.setVisibility(View.GONE);
                            }

                            listener.onVideoPause(pos);  // Notify the activity
                        }
                        return true;
                    }

                    @Override
                    public boolean onDoubleTap(MotionEvent e) {

                        if (listener != null) {

                            if (!liked){
                                holder.like.setImageResource(R.drawable.hot_deal);
                                Animation scaleAnimation = AnimationUtils.loadAnimation(context, R.anim.like_scale_animation);
                                holder.like.startAnimation(scaleAnimation);
                                liked = true;
                            }else {
                                Animation scaleAnimation = AnimationUtils.loadAnimation(context, R.anim.like_scale_animation);
                                holder.like.startAnimation(scaleAnimation);
                            }

                            listener.onVideoLike(pos);  // Notify the activity
                        }
                        return true;
                    }
                });

        holder.playerView.setUseController(false);
        holder.playerView.setOnTouchListener((v, event) -> {
            boolean result = gestureDetector.onTouchEvent(event);
            Log.d("setupGestureDetector", "Touch event processed: " + result);
            return true;
        });


    }

    @Override
    public int getItemCount() {
        return videoItems.size();
    }

    public Reel getVideoItem(int position) {
        return videoItems.get(position);
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        public PlayerView playerView;
        ImageView pause,like;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            playerView = itemView.findViewById(R.id.playerView);
            playerView.setUseController(false);
            like = itemView.findViewById(R.id.imageView12);
            pause = itemView.findViewById(R.id.imageView13);

        }

//        public void bind(int position,Context context,OnVideoActionListener listener,boolean paused,boolean liked) {
//            setupGestureDetector(playerView, position,context,listener,paused,liked);
//        }
//
//        @SuppressLint("ClickableViewAccessibility")
//        private void setupGestureDetector(PlayerView playerView, int position,Context context,OnVideoActionListener listener,boolean paused,boolean liked) {
//            GestureDetector gestureDetector = new GestureDetector(playerView.getContext(),
//                    new GestureDetector.SimpleOnGestureListener() {
//                        @Override
//                        public boolean onSingleTapConfirmed(MotionEvent e) {
//                            //Toast.makeText(context, "Paused", Toast.LENGTH_SHORT).show();
//
//                            if (paused){
//                                pause.setVisibility(View.VISIBLE);
//                            }else {
//                                pause.setVisibility(View.GONE);
//                            }
//
//                            if (listener != null) {
//                                listener.onVideoPause(position);  // Notify the activity
//                            }
//                            return true;
//                        }
//
//                        @Override
//                        public boolean onDoubleTap(MotionEvent e) {
//                            //Toast.makeText(context, "Liked!", Toast.LENGTH_SHORT).show();
//
//                            like.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    if (liked){
//                                        like.setImageResource(R.drawable.hot_sale);
//                                    }else {
//                                        like.setImageResource(R.drawable.hot_deal);
//                                    }
//                                }
//                            });
//
//                            if (listener != null) {
//                                listener.onVideoLike(position);  // Notify the activity
//                            }
//                            return true;
//                        }
//                    });
//
//            playerView.setUseController(false);
//            playerView.setOnTouchListener((v, event) -> {
//                 boolean result = gestureDetector.onTouchEvent(event);
//                Log.d("setupGestureDetector", "Touch event processed: " + result);
//                return true;
//            });
//
//        }


//        @SuppressLint("ClickableViewAccessibility")
//        private void setupGestureDetector(PlayerView playerView, int position, OnVideoActionListener listener) {
//            GestureDetector gestureDetector = new GestureDetector(playerView.getContext(),
//                    new GestureDetector.SimpleOnGestureListener() {
//                        @Override
//                        public boolean onSingleTapConfirmed(MotionEvent e) {
//                            if (listener != null) {
//                                listener.onVideoPause(position);  // Notify activity to pause video
//
//                            }
//                            return true;
//                        }
//
//                        @Override
//                        public boolean onDoubleTap(MotionEvent e) {
//                            if (listener != null) {
//                                listener.onVideoLike(position); // Notify activity to like video
//                                like
//                            }
//                            return true;
//                        }
//                    });
//
//            playerView.setUseController(false);
//            playerView.setOnTouchListener((v, event) -> {
//                boolean result = gestureDetector.onTouchEvent(event);
//                Log.d("setupGestureDetector", "Touch event processed: " + result);
//                return true;
//            });
//
////            playerView.setOnTouchListener((v, event) -> {
////                return gestureDetector.onTouchEvent(event);
////            });
//        }

    }
}

