package com.group3.finalproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Date;
import java.util.Locale;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        //CREATE THE NOTIFICATION
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "medication_reminder")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Time to take your medication")
                .setContentText("Don't forget to take your medication")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        Date now = new Date();
        int notificationId = Integer.parseInt(new SimpleDateFormat("MMddHHmmss",  Locale.US).format(now));
        notificationManager.notify(notificationId, builder.build());
    }
}
