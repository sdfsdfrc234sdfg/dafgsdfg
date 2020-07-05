package com.llav_ad.zv_t.other_files;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WorkManagePr extends Worker {

    static public String TAG_OUTPUT = "NtManager";
    private WorkManager manager = WorkManager.getInstance(getApplicationContext());

    public WorkManagePr(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    public Result doWork() {

        launchWork(10);
        launchWork(13);
        launchWork(16);
        launchWork(19);
        launchWork(22);

        return Result.success();
    }

    public static void launchPeriodicWork(Context context) {
        WorkManager manager = WorkManager.getInstance(context);
        try {

            List<WorkInfo> list = manager.getWorkInfosByTag(WorkManagePr.TAG_OUTPUT).get();
            if (list.size() > 0) {

                Log.d(WorkManagePr.TAG_OUTPUT, "Is Ok");
                return;
            }

            PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(WorkManagePr.class, 1, TimeUnit.DAYS)
                    .addTag(WorkManagePr.TAG_OUTPUT)
                    .build();
            manager.enqueue(request);

        } catch (Throwable e) {
            Log.d(WorkManagePr.TAG_OUTPUT, "Error -> "+e.getMessage());
        }
    }


    public void launchWork(int timeHours) {

        Calendar currentDate = Calendar.getInstance();
        Calendar dueDate = Calendar.getInstance();

        dueDate.set(Calendar.HOUR_OF_DAY, timeHours);
        dueDate.set(Calendar.MINUTE, 0);
        dueDate.set(Calendar.SECOND, 0);
        if (dueDate.before(currentDate)) {
            dueDate.add(Calendar.HOUR_OF_DAY, 24);
        }

        long timeDiff = dueDate.getTimeInMillis() - currentDate.getTimeInMillis();

        OneTimeWorkRequest dailyWorkRequest = new OneTimeWorkRequest.Builder(OneTimeWorkNotif.class)
                .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
                .addTag(TAG_OUTPUT)
                .build();

        manager.enqueue(dailyWorkRequest);
    }
}