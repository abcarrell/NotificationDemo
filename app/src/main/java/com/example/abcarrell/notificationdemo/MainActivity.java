package com.example.abcarrell.notificationdemo;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFirst = findViewById(R.id.btnFirstActivity);
        btnFirst.setOnClickListener(this);
        Button btnSecond = findViewById(R.id.btnSecondActivity);
        btnSecond.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent resultIntent = null;
        switch (v.getId()) {
            case R.id.btnFirstActivity:
                resultIntent = new Intent(this, FirstActivity.class);
                resultIntent.putExtra("notificationText", "Go to the first activity in the app");
                break;
            case R.id.btnSecondActivity:
                resultIntent = new Intent(this, SecondActivity.class);
                resultIntent.putExtra("notificationText", "Go to the second activity in the app");
                break;
        }
        if (resultIntent != null) {
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addNextIntentWithParentStack(resultIntent);
            PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, this.getApplicationContext().getPackageName());
            builder.setContentIntent(pendingIntent);
            builder.setSmallIcon(R.drawable.ic_launcher_foreground);
            builder.setContentText(resultIntent.getStringExtra("notificationText"));
            builder.setAutoCancel(true);
            NotificationManagerCompat manager = NotificationManagerCompat.from(this);
            manager.notify(NotificationId.getId(), builder.build());
        }
    }
}
