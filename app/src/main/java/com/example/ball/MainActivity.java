package com.example.ball;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import java.util.List;
import java.util.Random;

public class MainActivity extends Activity implements Runnable, SensorEventListener {
    SensorManager manager;
    Ball ball;
    Hole hole;
    Handler handler;
    int width, height, time;
    float gx, gy, dpi;
    FrameLayout framelayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        framelayout = new FrameLayout(this);
        framelayout.setBackgroundColor(Color.GREEN);
        setContentView(framelayout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        time = 10;
        handler = new Handler();
        handler.postDelayed(this, 3000);

        WindowManager windowManager =
                (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        dpi = getResources().getDisplayMetrics().densityDpi;

        ball = new Ball(this);
        ball.x = width / 2;
        ball.y = height / 2;

        hole = new Hole(this);
        Random rnd = new Random();
        hole.x = rnd.nextInt(
                width - (2 * (hole.r + ball.radius)))
                + hole.r + ball.radius;
        hole.y = rnd.nextInt(
                height - (2 * (hole.r + ball.radius)))
                + hole.r + ball.radius;

        framelayout.addView(hole);
        framelayout.addView(ball);
    }

    @Override
    public void run() {
        ball.vx += (float) (gx * time / 10000);
        ball.vy += (float) (gy * time / 10000);
        ball.x += dpi * ball.vx * time / 25.4;
        ball.y += dpi * ball.vy * time / 25.4;

        //端の壁の当たり判定
        if (ball.x < ball.radius) {
            ball.x = ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.x >= width - ball.radius) {
            ball.x = width - ball.radius;
            ball.vx = -ball.vx / 3;
        }

        if (ball.y < ball.radius) {
            ball.y = ball.radius;
            ball.vy = -ball.vy / 3;
        } else if (ball.y >= height - ball.radius) {
            ball.y = height - ball.radius;
            ball.vy = -ball.vy / 3;
        }

//ボールが穴に吸い込まれる判定
        if ((hole.x - hole.r < ball.x &&
                ball.x < hole.x + hole.r) &&
                (hole.y - hole.r < ball.y &&
                        ball.y < hole.y + hole.r)) {
            ball.x = hole.x;
            ball.y = hole.y;
            ball.vx = ball.vy = 0;
            ball.invalidate();

            // 結果画面へ
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            startActivity(intent);

        } else {
            ball.invalidate();
            handler.postDelayed(this, time);
        }
    }
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        manager = (SensorManager)getSystemService(
                SENSOR_SERVICE);
        List<Sensor> sensors =
                manager.getSensorList(
                        Sensor.TYPE_ACCELEROMETER);
        if (0 < sensors.size()) {
            manager.registerListener(
                    this, sensors.get(0),
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        gy = event.values[0];
        gx = event.values[1];
    }
    @Override
    public void onAccuracyChanged(
            Sensor sensor, int accuracy) {
    }
}
