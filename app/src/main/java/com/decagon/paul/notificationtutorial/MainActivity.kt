package com.decagon.paul.notificationtutorial

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    var CHANNEL_ID = "channelID"
    var CHANNEL_NAME = "channelNaame"
    var NOTIFICATION_ID = 0
    var notificationTicker = "this is a ticker, the message that passes by on the notification drawer"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()
        var btnnotifyme = findViewById<Button>(R.id.btn_notifyme)


        /*the builder pattern used to set features of the notification like title, text, icon etc*/
        val notifiaction = NotificationCompat.Builder(this, CHANNEL_ID)
        .setContentTitle("Do you want to be a millionair?")
        .setContentText("Millionairs Club")
        .setSmallIcon(R.drawable.ic_baseline_campaign_24)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setTicker(notificationTicker)
        .build()


        val notificationManager = NotificationManagerCompat.from(this)
        btnnotifyme.setOnClickListener {
            notificationManager.notify(NOTIFICATION_ID, notifiaction)

        }

    }

    fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            /*the notification channel which specify the behavior of the notification light vibration, turning on the phone light, color etc*/
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
                enableLights(true)
                enableVibration(true)
                lightColor = Color.RED
                Context.VIBRATOR_SERVICE
                enableLights(true)
            }
            /*the notification manager used to access the getsystemservice method*/
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel((channel))
        }
}



}