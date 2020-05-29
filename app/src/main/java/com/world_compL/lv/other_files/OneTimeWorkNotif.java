package com.world_compL.lv.other_files;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class OneTimeWorkNotif extends Worker {


    private NotificationHandler notificationHandler = new NotificationHandler();

    public OneTimeWorkNotif(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        notificationHandler.createNotification(this.getApplicationContext());
        return Result.success();
    }
}
