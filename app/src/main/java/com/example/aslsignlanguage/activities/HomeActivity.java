package com.example.aslsignlanguage.activities;


import static com.google.common.reflect.Reflection.getPackageName;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.aslsignlanguage.workers.InactivityReminderWorker;
import com.example.aslsignlanguage.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.example.aslsignlanguage.Controlar.AdapterLesson;
import com.example.aslsignlanguage.Model.Lesson;
import com.example.aslsignlanguage.workers.WorkerStartServesBackground;

public class HomeActivity extends AppCompatActivity {

    TextView name,numberHeart,numberDaimand,numberLesson,numberUnit,nameUnit;
    RecyclerView recyclerView;
    ImageView quiz,reels,aiChat,subsecribe,profile;
    List<Object> lessonList = new ArrayList<>();
    AdapterLesson adapterLesson;
    SharedPreferences sharedPreferencesNotification;

    private static final String PREF_NAME = "AppUsagePrefs";
    private static final String LAST_USAGE_KEY = "last_app_usage";



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sharedPreferencesNotification = getSharedPreferences("notification", Context.MODE_PRIVATE);

        name = findViewById(R.id.textView8);
        numberHeart = findViewById(R.id.textView10);
        numberDaimand = findViewById(R.id.textView11);
        numberLesson = findViewById(R.id.textView15);
        numberUnit = findViewById(R.id.textView16);
        nameUnit = findViewById(R.id.textView12);
        recyclerView = findViewById(R.id.recyclerView);
        quiz = findViewById(R.id.imageButton2);
        reels = findViewById(R.id.imageButton3);
        aiChat = findViewById(R.id.imageButton4);
        subsecribe = findViewById(R.id.imageButton5);
        profile = findViewById(R.id.imageButton6);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapterLesson = new AdapterLesson();
        recyclerView.setAdapter(adapterLesson);

        lessonList.add("UNIT : 1");

        lessonList.add(new Lesson(1,1,"Introduction"));
        lessonList.add(new Lesson(2,1,"Introduction"));
        lessonList.add(new Lesson(3,1,"Introduction"));
        lessonList.add(new Lesson(4,1,"Introduction"));
        lessonList.add(new Lesson(5,1,"Introduction"));

        lessonList.add("UNIT : 2");

        lessonList.add(new Lesson(1,2,"Introduction"));
        lessonList.add(new Lesson(2,2,"Introduction"));
        lessonList.add(new Lesson(3,2,"Introduction"));
        lessonList.add(new Lesson(4,2,"Introduction"));
        lessonList.add(new Lesson(5,2,"Introduction"));

        lessonList.add("UNIT : 3");

        lessonList.add(new Lesson(1,3,"Introduction"));
        lessonList.add(new Lesson(2,3,"Introduction"));
        lessonList.add(new Lesson(3,3,"Introduction"));
        lessonList.add(new Lesson(4,3,"Introduction"));
        lessonList.add(new Lesson(5,3,"Introduction"));

        lessonList.add("UNIT : 4");

        lessonList.add(new Lesson(1,4,"Introduction"));
        lessonList.add(new Lesson(2,4,"Introduction"));
        lessonList.add(new Lesson(3,4,"Introduction"));
        lessonList.add(new Lesson(4,4,"Introduction"));
        lessonList.add(new Lesson(5,4,"Introduction"));

        adapterLesson.addLesson(lessonList, new AdapterLesson.OnVideoPlayLessonListener() {
            @Override
            public void playLesson(int position) {
                Intent intent = new Intent(HomeActivity.this, LessonActivity.class);
                startActivity(intent);
            }
        });



        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, QuizActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

            }
        });

        reels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ReelsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        aiChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ChatBotActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        subsecribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SubscriptionActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        Map<String,?> allEntries = sharedPreferencesNotification.getAll();

        if (allEntries.isEmpty()) {
            SharedPreferences.Editor editor = sharedPreferencesNotification.edit();
            editor.putBoolean("showNotification",true);
            editor.apply();
            startPeriodicCheck(this);
            Log.d("TAG", "doWork: true allEntries.isEmpty()");
        }else {
            Log.d("TAG", "doWork: false allEntries.isEmpty()");
        }

        createNotificationChannel();
        updateLastUsageTime(this);

        //runPeriodicWork();

    }


    @Override
    protected void onResume() {
        super.onResume();
        updateLastUsageTime(this);
    }
//
//    private void updateLastUsageTime() {
//        SharedPreferences prefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
//        prefs.edit().putLong(LAST_USAGE_KEY, System.currentTimeMillis()).apply();
//    }
//
//    private void createNotificationChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(
//                    "reminder_channel",
//                    "Lesson Reminders",
//                    NotificationManager.IMPORTANCE_DEFAULT
//            );
//            channel.setDescription("Lesson reminder notifications");
//            NotificationManager manager = getSystemService(NotificationManager.class);
//            manager.createNotificationChannel(channel);
//        }
//    }
//
//    private void startPeriodicCheck() {
//        PeriodicWorkRequest periodicWork = new PeriodicWorkRequest.Builder(
//                InactivityReminderWorker.class,
//                15, // Repeat every 15 minutes
//                TimeUnit.MINUTES
//        ).build();
//
//        WorkManager.getInstance(this).enqueueUniquePeriodicWork("check_inactivity", ExistingPeriodicWorkPolicy.REPLACE, periodicWork);
//    }


    // Call this method from your Activity's onResume() to update last usage time.
    public static void updateLastUsageTime(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().putLong(LAST_USAGE_KEY, System.currentTimeMillis()).apply();
    }

    // Create a notification channel for Android O and above.
    public void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "reminder_channel",
                    "Lesson Reminders",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("Lesson reminder notifications");
            NotificationManager manager = getApplicationContext().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    // Schedule the periodic check to run every 3 minutes.
    public static void startPeriodicCheck(Context context) {
        PeriodicWorkRequest periodicWork = new PeriodicWorkRequest.Builder(InactivityReminderWorker.class, 15, TimeUnit.MINUTES).build();
        WorkManager.getInstance(context).enqueue(periodicWork);
    }

    // run servece in background if its stopped
    public void runPeriodicWork() {
        PeriodicWorkRequest periodicWork = new PeriodicWorkRequest.Builder(WorkerStartServesBackground.class, 15, TimeUnit.MINUTES).build();
        WorkManager.getInstance(getApplicationContext()).enqueue(periodicWork);
    }


}