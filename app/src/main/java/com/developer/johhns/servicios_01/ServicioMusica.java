package com.developer.johhns.servicios_01;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ServicioMusica extends Service {

    MediaPlayer  reproductor ;

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,"Servicio creado",Toast.LENGTH_LONG).show();
        reproductor = MediaPlayer.create(this,R.raw.audio);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //return super.onStartCommand(intent, flags, startId);
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
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
