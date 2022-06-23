package com.example.presentation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

    private static final String TAG = com.example.presentation.AlarmReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        // アラームを再登録
        // 参考 PutExtraは使用できない
        // https://stackoverflow.com/questions/12506391/retrieve-requestcode-from-alarm-broadcastreceiver
        // リクエストコードに紐づくデータを取得
        String requestCode = intent.getData().toString();
        com.example.presentation.DatabaseHelper helper = com.example.presentation.DatabaseHelper.getInstance(context);
        ListItem item = Util.getAlarmsByID(Integer.parseInt(requestCode), helper);

        // アラームを設定
        Util.setAlarm(context, item);

        Intent startActivityIntent = new Intent(context, WakeUpActivity.class);
        startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(startActivityIntent);
    }
}
