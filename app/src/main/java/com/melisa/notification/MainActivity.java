package com.melisa.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button notification;

    //To identify channel
    String CHANNEL_ID = "simple_notification";
    //To differentiate channel by id
    int NOTIFICATION_ID = 001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notification=findViewById(R.id.notification);

        //onClickListener for button
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //android oreo and hire versions you need to create a notification channel
                createNotificationChannel();
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_add_alarm_black_24dp)
                        .setContentTitle("Notification")
                        .setContentText("this is a simple notification")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                //calling notification
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);
                notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
            }
        });

    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //this is the name that appears in android system
            CharSequence name = "channel name";
            String description = "description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
