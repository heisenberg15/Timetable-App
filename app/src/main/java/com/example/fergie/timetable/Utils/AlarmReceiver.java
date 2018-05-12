package com.example.fergie.timetable.Utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.fergie.timetable.MainActivity;
import com.example.fergie.timetable.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmReceiver extends BroadcastReceiver
{

    public AlarmReceiver()
    {
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Intent i = new Intent(context, MainActivity.class);
        PendingIntent notificationIntent = PendingIntent.getActivity(context, 1, i, PendingIntent.FLAG_CANCEL_CURRENT);

        String text = intent.getStringExtra("subject");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel_id")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Timetable")
                .setContentIntent(notificationIntent)
                .setContentText(text);

        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());
    }
}
