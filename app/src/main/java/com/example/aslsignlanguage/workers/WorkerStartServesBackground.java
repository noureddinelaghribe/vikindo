package com.example.aslsignlanguage.workers;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.aslsignlanguage.services.MyService;

public class WorkerStartServesBackground extends Worker {
    public WorkerStartServesBackground(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        // تنفيذ المهمة هنا (مثال: إرسال بيانات)
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("PoupLearningDialog", Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("showPopup",false)) {

            if (!isServiceRunning(getApplicationContext(), MyService.class)){
                Intent service = new Intent(getApplicationContext(), MyService.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    getApplicationContext().startForegroundService(service);
                }
            }

        }
//        else {
//
//            if (!isServiceRunning(getApplicationContext(), MyService.class)){
//                Intent service = new Intent(getApplicationContext(), MyService.class);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    getApplicationContext().startForegroundService(service);
//                }
//            }
//
//        }

        return Result.success(); // أو Result.failure() أو Result.retry()
    }

    public static boolean isServiceRunning(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}
