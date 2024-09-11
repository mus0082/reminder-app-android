package com.group3.finalproject;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import com.group3.finalproject.databinding.ActivityMainBinding;


public class MainActivity extends Activity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get data from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("ReminderSettings", Context.MODE_PRIVATE);
        String intervalString = sharedPreferences.getString("interval", "Not Set");
        String repetitions = sharedPreferences.getString("repetitions", "Not Set");

        // Display the reminder summary in summaryTextView
        String reminderSummary = "Reminder Interval: " + intervalString + " hours\n" +
                "Repetitions: " + repetitions + " times";
        binding.summaryTextView.setText(reminderSummary);

        Intent intent = new Intent(this, AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        // Set the alarm to start at the specified time and repeat at the specified interval
        long interval = 6000; // 60 seconds
        long triggerTime = SystemClock.elapsedRealtime() + interval;
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, interval, pendingIntent);

        //Create a High Importance notification Channel
        NotificationChannel channel = new NotificationChannel(
                "medication_reminder",
                "medications_channel",
                NotificationManager.IMPORTANCE_HIGH
        );
        channel.setDescription("Notifications to remind you to take your medication");
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        NotificationManager notificationManagerService = getSystemService(NotificationManager.class);
        notificationManagerService.createNotificationChannel(channel);
    }

    public void goToSettings(View view) {
        // Start the SettingsActivity
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}