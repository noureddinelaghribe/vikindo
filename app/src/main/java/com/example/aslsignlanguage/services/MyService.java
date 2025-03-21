package com.example.aslsignlanguage.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.example.aslsignlanguage.R;
import com.example.aslsignlanguage.BroadcastReceiver.SecreenReceiver;

import java.util.Random;

public class MyService extends Service {

    SecreenReceiver secreenReceiver;

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
        startForeground(1, getNotification());

        secreenReceiver = new SecreenReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(secreenReceiver, intentFilter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(secreenReceiver);
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }



    private Notification getNotification() {
        return new NotificationCompat.Builder(this, "CHANNEL_ID")
                .setContentTitle("ASL Sign Language Assistant")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(getMotivationalSentences()))
                .setSmallIcon(R.drawable.corgi) // Replace with your notification icon
                .build();
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    "CHANNEL_ID",
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    public static String getMotivationalSentences() {
        Random random = new Random();
        int randomNumber = random.nextInt(10);
        String[] sentences = {
        "Learning ASL opens the door to a new world of communication.",
        "Every sign you learn brings you closer to connection and understanding.",
        "Your journey in ASL is a path to empowerment and self-expression.",
        "With each gesture, you build bridges of empathy and inclusion.",
        "Keep practicing ASL and watch your confidence soar.",
        "The language of hands is a beautiful way to share your story.",
        "Embrace every challenge in learning ASL as a chance to grow.",
        "Mastering ASL means unlocking a new form of art and expression.",
        "Every sign you master tells a story of perseverance and passion.",
        "Your dedication to learning ASL can transform lives around you."
        };
        return sentences[randomNumber];
    }


}