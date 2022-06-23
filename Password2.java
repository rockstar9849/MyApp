package com.example.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//------------------画面明るさ------------------
import android.view.WindowManager.LayoutParams;

//---------------------------------------------
public class Password2 extends AppCompatActivity {

    MyGlobals myGlobals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password2);

        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        //-----------画面の明るさを調整---------------------------
        final WindowManager.LayoutParams lp = getWindow().getAttributes();
        //------------------------------------------------------
        myGlobals = (MyGlobals) this.getApplication();

        //---------------画面明るさ------------------
        // 明るさを設定
        lp.screenBrightness = 1.0f;
        getWindow().setAttributes(lp);
        //-------------------------------------------


        findViewById(R.id.buttonNinsyou).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        TextView et2 = findViewById(R.id.editText2);
                        String set2 = et2.getText().toString();

                        //変数名variable1の値を取り出し、i1に代入
                        String s1 = pref.getString("variable1", "0");


                        if (set2.equals(s1)) {
                            System.out.println("認証");


                            //---------------------------------------------------------------
                            stopService(new Intent(com.example.presentation.Password2.this, SoundService.class));

                            finish();
                            moveTaskToBack(true);

                        } else {
                            System.out.println("認証失敗");
                        }
                    }
                }
        );


    }
}
