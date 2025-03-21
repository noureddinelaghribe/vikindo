package com.example.aslsignlanguage.services;


import static com.example.aslsignlanguage.BroadcastReceiver.SecreenReceiver.getFormattedTimeHH;
import static com.example.aslsignlanguage.BroadcastReceiver.SecreenReceiver.getFormattedTimeMM;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aslsignlanguage.R;

public class FloatingWindowService extends Service {

    private WindowManager windowManager;
    private View floatingView;
    private SharedPreferences sharedPreferences;
    private final int ANIMATION_DURATION = 700; // in milliseconds
    int count,now;
    @Override
    public void onCreate() {
        super.onCreate();

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        createFloatingView();

        sharedPreferences = getSharedPreferences("PoupLearningDialog", Context.MODE_PRIVATE);
        now = (Integer.parseInt(getFormattedTimeHH(System.currentTimeMillis()))*60)+Integer.parseInt(getFormattedTimeMM(System.currentTimeMillis()));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("lastTimeShowPopup",now);
        editor.apply();

        count = sharedPreferences.getInt("countTimesShowPopup",0) + 1 ;

    }

    private void createFloatingView() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        floatingView = inflater.inflate(R.layout.window, null);

        // Initially hide the view to animate it later
        floatingView.setAlpha(0f);

        // Set up the layout parameters
        final WindowManager.LayoutParams params;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT);
        } else {
            params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT);
        }

        // Specify the position of the floating window
        params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;
        params.y = 100;

        // Create margins by applying padding to the root view
        //int marginInDp = 0;
        //float density = getResources().getDisplayMetrics().density;
        //int marginInPx = (int) (marginInDp * density + 0.5f);

        // Apply margins to the floating view
        //floatingView.setPadding(marginInPx, 0, marginInPx, 0);

        // Add the view to the window
        windowManager.addView(floatingView, params);

        // Animate the view appearing
        animateIn();

        // Set up the close button
        ImageView doneButton = floatingView.findViewById(R.id.imageView3);
        ImageView againButton = floatingView.findViewById(R.id.imageView4);
        TextView text = floatingView.findViewById(R.id.textView19);

        text.setText("\uD83C\uDF89 Congratulations on completing your learning journey! Celebrate this achievement and look forward to new challenges!");

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Animate out and then stop the service
                animateOut(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {

                        stopSelf();

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("countTimesShowPopup",count);
                        editor.apply();

                    }
                });
            }
        });

        againButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Animate out and then stop the service
                animateOut(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {

                        stopSelf();

                    }
                });
            }
        });
    }

    private void animateIn() {
        // Fade in animation
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(floatingView, "alpha", 0f, 1f);
        fadeIn.setDuration(ANIMATION_DURATION);

        // Scale up animation with overshoot effect (gives a bounce feel)
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(floatingView, "scaleX", 0.5f, 1f);
        scaleX.setDuration(ANIMATION_DURATION);
        scaleX.setInterpolator(new OvershootInterpolator(1.2f));

        ObjectAnimator scaleY = ObjectAnimator.ofFloat(floatingView, "scaleY", 0.5f, 1f);
        scaleY.setDuration(ANIMATION_DURATION);
        scaleY.setInterpolator(new OvershootInterpolator(1.2f));

        // Combine all animations
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(fadeIn, scaleX, scaleY);
        animatorSet.start();
    }

    private void animateOut(AnimatorListenerAdapter listenerAdapter) {
        // Fade out animation
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(floatingView, "alpha", 1f, 0f);
        fadeOut.setDuration(ANIMATION_DURATION - 200);  // Slightly faster

        // Scale down animation
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(floatingView, "scaleX", 1f, 0.8f);
        scaleX.setDuration(ANIMATION_DURATION - 200);

        ObjectAnimator scaleY = ObjectAnimator.ofFloat(floatingView, "scaleY", 1f, 0.8f);
        scaleY.setDuration(ANIMATION_DURATION - 200);

        // Slide up animation
        ObjectAnimator slideUp = ObjectAnimator.ofFloat(floatingView, "translationY", 0f, -50f);
        slideUp.setDuration(ANIMATION_DURATION - 200);
        slideUp.setInterpolator(new AccelerateDecelerateInterpolator());

        // Combine animations
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(fadeOut, scaleX, scaleY, slideUp);

        // Add listener
        if (listenerAdapter != null) {
            animatorSet.addListener(listenerAdapter);
        }

        animatorSet.start();
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (floatingView != null) windowManager.removeView(floatingView);
    }

}