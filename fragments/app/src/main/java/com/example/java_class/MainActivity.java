package com.example.java_class;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public BottomNavigationView bottomNav;
    public int lastItem;
    public static int notificationNummer = 0;
    private String CHANNEL_ID = "channel_01";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bottom_nav_bar);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        //einlesen der datein
        //Schnittstelle.load(this);
        //Schnittstelle.load();
        Log.d("einlesen", String.valueOf(Schnittstelle.toDoListe.size()));

        createNotificationChannel(); //Channel für die notification erstellen
        //CreateNotification();
    }


    /*Nav bar onclick funktion*/
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            lastItem = bottomNav.getSelectedItemId();
                            selectedFragment = new Startseite();
                            break;
                        case R.id.navigation_kalender:
                            lastItem = bottomNav.getSelectedItemId();
                            selectedFragment = new Kalender();
                            break;
                        case R.id.navigation_to_do:
                            lastItem = bottomNav.getSelectedItemId();
                            selectedFragment = new ToDo();
                            break;
                        case R.id.navigation_abgabe:
                            lastItem = bottomNav.getSelectedItemId();
                            selectedFragment = new Fristen();
                            break;
                        case R.id.navigation_literatur:
                            lastItem = bottomNav.getSelectedItemId();
                            selectedFragment = new Literatur();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, selectedFragment).commit();
                    return true;
                }
            };

    public void setActionBarTitle(String title) {
        setTitle(title);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Fristen Notification", importance);
            channel.setDescription("Channel für alle Fristen Notifications");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public boolean CreateNotification(String text, Datum erinnerungsDatum) {
        scheduleNotification(getNotification(text), erinnerungsDatum);
        return true;
    }

    public void scheduleNotification(android.app.Notification notification, Datum erinnerungsDatum) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, notificationNummer);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        
        calendar.set(Calendar.YEAR, erinnerungsDatum.jahr);
        calendar.set(Calendar.MONTH, (erinnerungsDatum.monat - 1));
        calendar.set(Calendar.DAY_OF_MONTH, erinnerungsDatum.tag);
        calendar.set(Calendar.HOUR_OF_DAY, 5);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);


        //long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

    }


    public Notification getNotification(String content) {

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Errinerung!")
                .setContentText("TEXT ZUR ERRINERUNG DER FRIST: " + content + "!")
                .setSmallIcon(R.drawable.ic_announcement)
                .setColor(Color.rgb(0, 181, 173))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_DEFAULT);
        return builder.build();

    }

}