package com.developer.johhns.servicios_01;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class ServicioMusica extends Service {

    MediaPlayer  reproductor ;
    private NotificationManager notificationManager ;
    static final String CANAL_ID = "mi_canal" ;
    static final int NOTIFICACION_ID = 1 ;

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,"Servicio creado",Toast.LENGTH_LONG).show();
        reproductor = MediaPlayer.create(this,R.raw.audio);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        notificationManager = (NotificationManager) getSystemService( NOTIFICATION_SERVICE ) ;

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ) {
            NotificationChannel notificationChannel = new NotificationChannel( CANAL_ID, "Mis Notificaciones", NotificationManager.IMPORTANCE_DEFAULT ) ;
            notificationChannel.setDescription("Descripcion del canal");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern( new long[] { 0 , 100 , 300 , 100 } );
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel( notificationChannel );
        }

        NotificationCompat.Builder notificacion = new NotificationCompat.Builder( this , CANAL_ID )
                 .setSmallIcon(R.mipmap.ic_launcher)
                 .setContentTitle("Titulo")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_media_play))
                .setWhen(System.currentTimeMillis() + 1000 * 60 * 60 )
                .setDefaults(Notification.DEFAULT_SOUND)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setDefaults(Notification.DEFAULT_LIGHTS)
                .setContentInfo("mas info")
                .setTicker("Texto en la barra de estado")
                 .setContentText("Texto de la notificacion");

        PendingIntent pendingIntent = PendingIntent.getActivity( this , 0 , new Intent( this , MainActivity.class ) , 0 ) ;
        notificacion.setContentIntent( pendingIntent ) ;

        //notificationManager.notify( NOTIFICACION_ID , notificacion.build() );
        startForeground( NOTIFICACION_ID , notificacion.build() ); // para que el servicio permanesca en primer plano



        Toast.makeText(this,"Servicio arrancado",Toast.LENGTH_LONG).show();
        reproductor.start();
        return START_STICKY ;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Servicio detenido",Toast.LENGTH_LONG).show();
        reproductor.stop();
        reproductor.release();
        notificationManager.cancel(NOTIFICACION_ID);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
