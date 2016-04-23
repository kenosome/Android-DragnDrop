package com.cali.uao.draganddrop;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

    TextView cuadro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cuadro=(TextView) findViewById(R.id.cuadro);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        cuadro.setOnTouchListener(new View.OnTouchListener() {

            float xinicial;
            float yinicial;

            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        cuadro.setBackgroundColor(Color.rgb(0xBB,0x66,0x66));
                        xinicial=event.getX();
                        yinicial=event.getY();

                        return true;
                    case MotionEvent.ACTION_MOVE:

                        cuadro.setText("X: "+event.getX()+" \nY: "+event.getY());
                        cuadro.setX(cuadro.getX()+event.getX()-xinicial);
                        cuadro.setY(cuadro.getY()+event.getY()-yinicial);
                        return true;
                    case MotionEvent.ACTION_UP:
                        cuadro.setBackgroundColor(Color.rgb(0xFF,0xAA,0xAA));

                        float xfinal, yfinal;
                        xfinal=cuadro.getX();
                        yfinal=cuadro.getY();

                        SharedPreferences preferencias= PreferenceManager
                                .getDefaultSharedPreferences(getApplicationContext());

                        SharedPreferences.Editor editor=preferencias.edit();

                        editor.putFloat("X",xfinal);
                        editor.putFloat("Y",yfinal);
                        editor.commit();

                        return true;
                }

                cuadro.setBackgroundColor(Color.rgb(0xEE,0x99,0x99));

                return false;
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        float Xrecuperado=preferencias.getFloat("X",0);
        float Yrecuperado=preferencias.getFloat("Y",0);
        cuadro.setX(Xrecuperado);
        cuadro.setY(Yrecuperado);

    }
}
