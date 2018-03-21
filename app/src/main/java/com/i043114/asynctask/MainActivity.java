package com.i043114.asynctask;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {



    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressBar = (ProgressBar) findViewById(R.id.pgb_1);


onClick();
    }


    private void Unsegundo() {


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

    }


    public void onClick() {


                EjemploAsyncTask ejemploAsyncTask = new EjemploAsyncTask();
                ejemploAsyncTask.execute();

    }


    private class EjemploAsyncTask extends AsyncTask<Void, Integer, Boolean> {


        // Hilo Principal Se ejecuta antes de la actividad  en segundo plano.
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setMax(100);
            progressBar.setProgress(0);


        }

        @Override
        protected Boolean doInBackground(Void... voids) {

            //  Actividad en segundo plano
            for (int i = 0; i <= 10; i++) {
                Unsegundo();
                publishProgress(i * 25); // progreso de la ejecucion
                if (isCancelled()) {
                    break;
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            // Se puede enviar el progreso de la actividad.
            progressBar.setProgress(values[0].intValue());
        }


        @Override
        protected void onCancelled() {
            super.onCancelled();

            //Si se interrumpe la realizacion de la actividad.
            Toast.makeText(getBaseContext(), " tarea no Finalizada", Toast.LENGTH_SHORT).show();
        }
        // Despues de la actividad

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {

                Toast.makeText(getBaseContext(), "Taarea  Finalizada AsyncTask", Toast.LENGTH_SHORT).show();
                Intent ListSong = new Intent(getApplicationContext(), LoginMainActivity.class);
                startActivity(ListSong);

            }

        }


    }


}
