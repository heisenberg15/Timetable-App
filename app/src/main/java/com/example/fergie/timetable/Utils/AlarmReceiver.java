package com.example.fergie.timetable.Utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.fergie.timetable.MainActivity;
import com.example.fergie.timetable.R;
import com.example.fergie.timetable.Settings;

import java.util.Calendar;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmReceiver extends BroadcastReceiver
{

    public AlarmReceiver()
    {
    }

    @Override
    public void onReceive(final Context context, final Intent intent)
    {

        if (Settings.notificationsOn != 1)
        {

            if (Settings.notificationsOn == 3)
            {
                String text = intent.getStringExtra("subject");
                long alarmTime = intent.getLongExtra("alarmTime", 0);
                long testTIme = intent.getLongExtra("testTime", 0);
                Calendar checkCalendar = Calendar.getInstance();
                Calendar testCalendar = Calendar.getInstance();
                checkCalendar.setTimeInMillis(alarmTime);
                testCalendar.setTimeInMillis(testTIme);
                if (alarmTime != 0 && !checkCalendar.before(Calendar.getInstance()))
                {
                    Intent i = new Intent(context, MainActivity.class);
                    PendingIntent notificationIntent = PendingIntent.getActivity(context, 1, i, PendingIntent.FLAG_CANCEL_CURRENT);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel_id")
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("Timetable")
                            .setContentIntent(notificationIntent)
                            .setContentText(text);

                    NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
                    manager.notify(1, builder.build());
                }

                if (alarmTime == 0 && !testCalendar.before(Calendar.getInstance()))
                {
                    Intent i = new Intent(context, MainActivity.class);
                    PendingIntent notificationIntent = PendingIntent.getActivity(context, 1, i, PendingIntent.FLAG_CANCEL_CURRENT);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel_id")
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("Timetable")
                            .setContentIntent(notificationIntent)
                            .setContentText(text);

                    NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
                    manager.notify(1, builder.build());
                }


            }

//            else if (Settings.notificationsOn == 3)
//            {
//
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable()
//                {
//
//                    @Override
//                    public void run()
//                    {
//                        Intent i = new Intent(context, MainActivity.class);
//                        PendingIntent notificationIntent = PendingIntent.getActivity(context, 1, i, PendingIntent.FLAG_CANCEL_CURRENT);
//
//                        String text = intent.getStringExtra("subject");
//
//                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel_id")
//                                .setSmallIcon(R.mipmap.ic_launcher)
//                                .setContentTitle("Timetable")
//                                .setContentIntent(notificationIntent)
//                                .setContentText(text);
//
//                        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
//                        manager.notify(1, builder.build());
//                    }
//
//                }, TimeUnit.MINUTES.toMillis(Settings.delayTIme));
//            }

        }

    }
}
