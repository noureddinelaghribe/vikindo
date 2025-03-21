package com.example.aslsignlanguage.workers;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.aslsignlanguage.services.FloatingWindowService;

public class WorkerShowPopup extends Worker {

    Context context;

    public WorkerShowPopup(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {

        Log.d("TAG", "doWork: showWindow");
        // استخدام PowerManager للتحقق من حالة الشاشة
        PowerManager powerManager = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
        if (powerManager != null && powerManager.isInteractive()) { // isInteractive تُعيد true إذا كانت الشاشة تعمل
            // تنفيذ المهمة المطلوبة

            showWindow(context);

            return Result.success();
        } else {
            // يمكنك إعادة جدولة المهمة أو التعامل مع الحالة كما تشاء
            return Result.retry();
        }
    }

    public void showWindow(Context context){
        Intent i = new Intent(context, FloatingWindowService.class);
        context.startService(i);
    }


}
