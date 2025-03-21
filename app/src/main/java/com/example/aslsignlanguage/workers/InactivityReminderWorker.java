package com.example.aslsignlanguage.workers;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.aslsignlanguage.R;
import com.example.aslsignlanguage.activities.MainActivity;

import java.util.concurrent.TimeUnit;

public class InactivityReminderWorker extends Worker {
    private static final String PREF_NAME = "AppUsagePrefs";
    private static final String LAST_USAGE_KEY = "last_app_usage";
    private static final int INACTIVE_HOURS = 1; // Show notification after 48 hours of inactivity
    private static final int INACTIVE_MINUTES = 60;

    public InactivityReminderWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        if (shouldShowReminder()) {
            SharedPreferences sharedPreferencesNotification = getApplicationContext().getSharedPreferences("notification", Context.MODE_PRIVATE);
            if (sharedPreferencesNotification.getBoolean("showNotification",false)){
                showInactivityNotification();
                Log.d("TAG", "doWork: showInactivityNotification");
            }else {
                Log.d("TAG", "doWork: false sharedPreferencesNotification.getBoolean(\"showNotification\",false)");
            }
        }else {
            Log.d("TAG", "doWork: false shouldShowReminder()");
        }
        return Result.success();
    }

//    private boolean shouldShowReminder() {
//        SharedPreferences prefs = getApplicationContext()
//                .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
//        long lastUsage = prefs.getLong(LAST_USAGE_KEY, System.currentTimeMillis());
//        long hoursSinceLastUsage = TimeUnit.MILLISECONDS.toMinutes(
//                System.currentTimeMillis() - lastUsage
//        );
//
//        return hoursSinceLastUsage >= INACTIVE_HOURS;
//    }

    private boolean shouldShowReminder() {
        SharedPreferences prefs = getApplicationContext()
                .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        long lastUsage = prefs.getLong(LAST_USAGE_KEY, System.currentTimeMillis());
        long minutesSinceLastUsage = TimeUnit.MILLISECONDS.toMinutes(
                System.currentTimeMillis() - lastUsage
        );
        return minutesSinceLastUsage >= INACTIVE_MINUTES;
    }



    private void showInactivityNotification() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                getApplicationContext(),
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                getApplicationContext(),
                "reminder_channel"
        )
                .setSmallIcon(R.drawable.corgi)
                .setContentTitle("We miss you! 😢")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("It's been a while! Let's learn something new today"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setCategory(NotificationCompat.CATEGORY_REMINDER);

        NotificationManagerCompat notificationManager = NotificationManagerCompat
                .from(getApplicationContext());

        // Check notification permission for Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (getApplicationContext().checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                    == PackageManager.PERMISSION_GRANTED) {
                notificationManager.notify(1, builder.build());
            }
        } else {
            notificationManager.notify(1, builder.build());
        }
    }
}