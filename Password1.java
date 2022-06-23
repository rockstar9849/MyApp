package com.example.presentation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Password1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password1);

        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        final EditText et1 = (EditText) findViewById(R.id.editText1);

        findViewById(R.id.buttonSave).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (et1.toString() == "0") {
                            //トースト表示
                            Toast.makeText(Password1.this, "0以外を入力してください。", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        //共有プリファレンス準備
                        SharedPreferences.Editor e = pref.edit();
                        //変数名variable1にet1の入力整数を代入。
                        e.putString("variable1", et1.getText().toString());
                        //確定処理
                        e.commit();

                        String s = "";
                        if (v.getId() == R.id.buttonSave) {
                            s = "パスワードを登録しました。";
                        }
                        //トースト表示
                        Toast.makeText(Password1.this, s, Toast.LENGTH_SHORT).show();

                        //----------------------------------------------------------------------------------------------------
                        Intent intent = new Intent(Password1.this, MainActivity.class);
                        startActivity(intent);
                        //----------------------------------------------------------------------------------------------------

                    }
                }
        );
    }

}