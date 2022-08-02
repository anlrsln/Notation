package com.example.notebookexample2

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.notebookexample2.activities.NoteActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage



const val channelId = "notification_chanel"
const val channelName = "com.example.notebookexample2"

class MyFirebaseMessagingService  : FirebaseMessagingService(){


    // Generate the notification
    // Attach the notification created with the custom layout
    // Show the notification


    override fun onMessageReceived(message: RemoteMessage) {

        if(message.notification != null){
            generateNotification(message.notification!!.title!!, message.notification!!.body!!)
        }
    }


    @SuppressLint("RemoteViewLayout")
    fun getRemoteView(title:String, message: String) : RemoteViews{
        val remoteView = RemoteViews("com.example.notebookexample2",R.layout.notification)

        remoteView.setTextViewText(R.id.notificationTitle,title)
        remoteView.setTextViewText(R.id.notificationText,message)
        remoteView.setImageViewResource(R.id.notificationLogo,R.drawable.n_logo)

        return remoteView
    }



    fun generateNotification(title:String, message:String){
        val intent = Intent(this,NoteActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT)


        // channel id, channel name
        var builder:NotificationCompat.Builder = NotificationCompat.Builder(applicationContext,
            channelId).setSmallIcon(R.drawable.n_logo)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000,1000,1000,1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        builder = builder.setContent(getRemoteView(title,message))


        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            val notificationChanel = NotificationChannel(channelId, channelName,NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChanel)
        }

        notificationManager.notify(0,builder.build())


    }


}