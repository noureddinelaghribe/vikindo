package com.example.aslsignlanguage.workers;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class DailyResetWorker extends Worker {
    private static final String PREF_NAME = "PoupLearningDialog";
    private static final String COUNT_KEY = "countTimesShowPopup";

    public DailyResetWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        // تصفير العداد
        resetPopupCount();

        // جدولة التصفير التالي
        scheduleNextReset();

        return Result.success();
    }

    private void resetPopupCount() {
        SharedPreferences sharedPreferences = getApplicationContext()
                .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        int currentCount = sharedPreferences.getInt(COUNT_KEY, 0);
        if (currentCount != 0) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(COUNT_KEY, 0);
            editor.apply();
        }
    }

    private void scheduleNextReset() {
        Calendar midnight = Calendar.getInstance();
        midnight.add(Calendar.DAY_OF_YEAR, 1);
        midnight.set(Calendar.HOUR_OF_DAY, 0);
        midnight.set(Calendar.MINUTE, 0);
        midnight.set(Calendar.SECOND, 0);

        long delayInMillis = midnight.getTimeInMillis() - System.currentTimeMillis();

        OneTimeWorkRequest resetWork = new OneTimeWorkRequest.Builder(DailyResetWorker.class)
                .setInitialDelay(delayInMillis, TimeUnit.MILLISECONDS)
                .build();

        WorkManager.getInstance(getApplicationContext())
                .enqueueUniqueWork(
                        "reset_popup_count",
                        ExistingWorkPolicy.REPLACE,
                        resetWork
                );
    }
}