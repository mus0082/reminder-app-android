package com.group3.finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.group3.finalproject.databinding.ActivityMainBinding;

public class SettingsActivity extends Activity {
    private EditText intervalEditText;
    private EditText repetitionsEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        intervalEditText = findViewById(R.id.interval_edit_text);
        repetitionsEditText = findViewById(R.id.repetitions_edit_text);
    }

    // Method to handle the save button click
    public void saveSettings(View view) {
        // Get the input values using ViewBinding
        String interval = intervalEditText.getText().toString();
        String repetitions = repetitionsEditText.getText().toString();

        // Save to SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("ReminderSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("interval", interval);
        editor.putString("repetitions", repetitions);
        editor.apply();

        // Notify user
        Toast.makeText(this, "Reminder Saved", Toast.LENGTH_SHORT).show();

        // Wait for 4 seconds and then navigate to MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Optional: close the current activity
            }
        }, 4000); // 4000 milliseconds = 4 seconds
    }

    // Method to handle the delete button click
    public void deleteAlarm(View view) {
        // Clear the input fields
        intervalEditText.setText("");
        repetitionsEditText.setText("");

        // Navigate back to the previous activity
        finish();
    }
}
