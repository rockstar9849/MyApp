package com.example.presentation;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
//------------------画面明るさ------------------
import android.view.WindowManager.LayoutParams;
//---------------------------------------------

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

// 参考 https://github.com/hiroaki-dev/AlarmSample/blob/master/app/src/main/java/me/hiroaki/alarmsample/PlaySoundActivity.java

public class WakeUpActivity extends AppCompatActivity {

    private Button stopBtn;
    private String McameraID;
    private boolean SW;
    private boolean stopSW = false;
    private CameraManager McameraManager;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        if (vibrator.hasVibrator()) {
            long vibratePattern[] = {1000, 2500, 4000, 1500};
            vibrator.vibrate(vibratePattern, -1);
        }
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //-----------画面の明るさを調整---------------------------
        final WindowManager.LayoutParams lp = getWindow().getAttributes();
        //------------------------------------------------------
        //---------------画面明るさ------------------
        // 明るさを設定
        lp.screenBrightness = 1.0f;
        getWindow().setAttributes(lp);
        //-------------------------------------------

        setContentView(R.layout.activity_wake_up);

        startService(new Intent(this, SoundService.class));


        stopBtn = (Button) findViewById(R.id.stopBtn);


        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WakeUpActivity.this, Face1.class);
                startActivity(intent);
            }
        });


    }
}