package com.example.aslsignlanguage.BroadcastReceiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.aslsignlanguage.workers.WorkerShowPopup;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class SecreenReceiver extends BroadcastReceiver{

    public SecreenReceiver() {}

    @Override
    public void onReceive(Context context, Intent intent) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("PoupLearningDialog", Context.MODE_PRIVATE);
        int start = (sharedPreferences.getInt("startHour", 0)*60)+sharedPreferences.getInt("startMinute", 0);
        int end = (sharedPreferences.getInt("endHour", 0)*60)+sharedPreferences.getInt("endMinute", 0);
        int now = (Integer.parseInt(getFormattedTimeHH(System.currentTimeMillis()))*60)+Integer.parseInt(getFormattedTimeMM(System.currentTimeMillis()));
        int delay = sharedPreferences.getInt("numberTimesShowPopupSecreenOn",1);
        int duration = sharedPreferences.getInt("duration",0);
        int lastTimeShowPopup = sharedPreferences.getInt("lastTimeShowPopup", 0);
        int nextTimeShowPopup =  lastTimeShowPopup+30;

        if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
            if (nextTimeShowPopup < now ){
                int numberTimesShowPopup = sharedPreferences.getInt("numberTimesShowPopup",1);
                int countTimesShowPopup = sharedPreferences.getInt("countTimesShowPopup",0);
                if ( numberTimesShowPopup > countTimesShowPopup ){
                    boolean specificTimesShowPopup = sharedPreferences.getBoolean("specificTimesShowPopup",true) ;
                    if (specificTimesShowPopup){
                        if (start <= now && end >= now){

                            // delay to show popup like 3 seconds
                            OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(WorkerShowPopup.class)
                                    .setInitialDelay(delay, duration == 0 ? TimeUnit.SECONDS : TimeUnit.MINUTES)
                                    .build();
                            WorkManager.getInstance(context).enqueue(workRequest);

                        }
                    }else {

                        // delay to show popup like 3 seconds
                        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(WorkerShowPopup.class)
                                .setInitialDelay(delay, duration == 0 ? TimeUnit.SECONDS : TimeUnit.MINUTES)
                                .build();
                        WorkManager.getInstance(context).enqueue(workRequest);

                    }
                }

            }

        }

    }


    public static String getFormattedTimeHH(long timeInMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH", Locale.getDefault()); // 24-hour
        return sdf.format(new Date(timeInMillis));
    }

    public static String getFormattedTimeMM(long timeInMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm", Locale.getDefault()); // 24-hour
        return sdf.format(new Date(timeInMillis));
    }



















}
