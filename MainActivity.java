package com.example.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.time);
        Thread thread = new Thread() {
            @NonNull
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Calendar calendar = Calendar.getInstance();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
                                String currenttime = simpleDateFormat.format(calendar.getTime());
                                textView.setText(currenttime);
                            }
                        });
                    }
                } catch (Exception e) {
                    textView.setText(R.string.app_name);
                }
            }
        };
        thread.start();

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.date);
        textViewDate.setText(currentDate);

        findViewById(R.id.Mainsettings).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Password1.class);
                        startActivity(intent);
                    }
                }
        );
        findViewById(R.id.button1).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, ConfirmationActivity.class);
                        startActivity(intent);
                    }
                }
        );


    }
}