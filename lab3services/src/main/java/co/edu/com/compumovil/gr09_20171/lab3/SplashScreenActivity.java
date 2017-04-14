package co.edu.com.compumovil.gr09_20171.lab3;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {
    private static final long SPLASH_SCREEN_DELAY = 1000;
    private final String filename = "registro.txt";
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            BufferedReader fin = new BufferedReader(new InputStreamReader(
                    openFileInput(filename)));

            username = fin.readLine();
            fin.close();
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
        }
        // Set portair orientacion
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //esconder barra de titulo
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.splash_screen);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //iniciar siguiente actividad
                if (username == null || username.equals("")) {
                    Intent mainIntent = new Intent().setClass(
                            SplashScreenActivity.this, Login.class);
                    startActivity(mainIntent);
                } else {
                    Intent mainIntent = new Intent().setClass(SplashScreenActivity.this, Navegacion.class);
                    startActivity(mainIntent);
                }


                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
}
