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

public class Main2Activity extends Activity implements Runnable, SensorEventListener {
    SensorManager manager;
    Ball ball;
    Hole hole;
    BadHole bhole1,bhole2,bhole3,bhole4,bhole5;
    Wall wall1,wall2,wall3,wall4;
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
        framelayout.setBackgroundColor(Color.CYAN);
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
        ball.x = width-ball.radius;
        ball.y = height-ball.radius;

        hole = new Hole(this);
        hole.x = 300;
        hole.y = height-hole.r*2;

        bhole1 = new BadHole(this);
        bhole1.x = width-bhole1.r*2;
        bhole1.y = 100;

        bhole2 = new BadHole(this);
        bhole2.x = 1200+bhole2.r*2;
        bhole2.y = 100;

        bhole3 = new BadHole(this);
        bhole3.x = 1600-bhole3.r*2;
        bhole3.y = 1000;

        bhole4 = new BadHole(this);
        bhole4.x = 700+bhole4.r*2;
        bhole4.y = 1000;

        bhole5 = new BadHole(this);
        bhole5.x = 600-bhole5.r*2;
        bhole5.y = 300;

        //壁の描画(横0～2000、縦0～1100)
        wall1 = new Wall(this);
        wall1.l = 1600;
        wall1.t = 200;
        wall1.r = 1700;
        wall1.b = 1100;

        wall2 = new Wall(this);
        wall2.l = 1100;
        wall2.t = 0;
        wall2.r = 1200;
        wall2.b = 600;

        wall3 = new Wall(this);
        wall3.l = 1100;
        wall3.t = 800;
        wall3.r = 1200;
        wall3.b = 1100;

        wall4 = new Wall(this);
        wall4.l = 600;
        wall4.t = 200;
        wall4.r = 700;
        wall4.b = 1100;


        framelayout.addView(hole);
        framelayout.addView(bhole1);
        framelayout.addView(bhole2);
        framelayout.addView(bhole3);
        framelayout.addView(bhole4);
        framelayout.addView(bhole5);
        framelayout.addView(ball);
        framelayout.addView(wall1);
        framelayout.addView(wall2);
        framelayout.addView(wall3);
        framelayout.addView(wall4);
    }

    @Override
    public void run() {
        ball.vx += (float) (gx * time / 10000);
        ball.vy += (float) (gy * time / 10000);
        ball.x += dpi * ball.vx * time / 25.4;
        ball.y += dpi * ball.vy * time / 25.4;

        //壁の反射
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

        //壁１の当たり判定
        if (ball.x < wall1.r + ball.radius && ball.x >= wall1.r && ball.y <= wall1.b && ball.y >= wall1.t) {
            ball.x = wall1.r + ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.x > wall1.l - ball.radius && ball.x <= wall1.l && ball.y <= wall1.b && ball.y >= wall1.t) {
            ball.x = wall1.l - ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.y > wall1.t - ball.radius && ball.y <= wall1.t && ball.x >= wall1.l && ball.x <= wall1.r) {
            ball.y = wall1.t - ball.radius;
            ball.vy = -ball.vy / 3;
        }

        //壁２の当たり判定
        if (ball.x < wall2.r + ball.radius && ball.x >= wall2.r && ball.y <= wall2.b && ball.y >= wall2.t) {
            ball.x = wall2.r + ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.x > wall2.l - ball.radius && ball.x <= wall2.l && ball.y <= wall2.b && ball.y >= wall2.t) {
            ball.x = wall2.l - ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.y < wall2.b + ball.radius && ball.y >= wall2.b && ball.x >= wall2.l && ball.x <= wall2.r) {
            ball.y = wall2.b + ball.radius;
            ball.vy = -ball.vy / 3;
        }

        //壁３の当たり判定
        if (ball.x < wall3.r + ball.radius && ball.x >= wall3.r && ball.y <= wall3.b && ball.y >= wall3.t) {
            ball.x = wall3.r + ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.x > wall3.l - ball.radius && ball.x <= wall3.l && ball.y <= wall3.b && ball.y >= wall3.t) {
            ball.x = wall3.l - ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.y > wall3.t - ball.radius && ball.y <= wall3.t && ball.x >= wall3.l && ball.x <= wall3.r) {
            ball.y = wall3.t - ball.radius;
            ball.vy = -ball.vy / 3;
        }

        if (ball.x < wall4.r + ball.radius && ball.x >= wall4.r && ball.y <= wall4.b && ball.y >= wall4.t) {
            ball.x = wall4.r + ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.x > wall4.l - ball.radius && ball.x <= wall4.l && ball.y <= wall4.b && ball.y >= wall4.t) {
            ball.x = wall4.l - ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.y > wall4.t - ball.radius && ball.y <= wall4.t && ball.x >= wall4.l && ball.x <= wall4.r) {
            ball.y = wall4.t - ball.radius;
            ball.vy = -ball.vy / 3;
        }


//ボールがゴールに吸い込まれる判定
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

        } else if ((bhole1.x - bhole1.r < ball.x &&
                ball.x < bhole1.x + bhole1.r) &&
                (bhole1.y - bhole1.r < ball.y &&
                        ball.y < bhole1.y + bhole1.r)) {
            ball.x = bhole1.x;
            ball.y = bhole1.y;
            ball.vx = ball.vy = 0;
            ball.invalidate();

            // 結果画面へ
            Intent intent = new Intent(getApplicationContext(), GameOverActivity.class);
            startActivity(intent);

        }else if ((bhole2.x - bhole2.r < ball.x &&
                ball.x < bhole2.x + bhole2.r) &&
                (bhole2.y - bhole2.r < ball.y &&
                        ball.y < bhole2.y + bhole2.r)) {
            ball.x = bhole2.x;
            ball.y = bhole2.y;
            ball.vx = ball.vy = 0;
            ball.invalidate();

            // 結果画面へ
            Intent intent = new Intent(getApplicationContext(), GameOverActivity.class);
            startActivity(intent);

        }else if ((bhole3.x - bhole3.r < ball.x &&
                ball.x < bhole3.x + bhole3.r) &&
                (bhole3.y - bhole3.r < ball.y &&
                        ball.y < bhole3.y + bhole3.r)) {
            ball.x = bhole3.x;
            ball.y = bhole3.y;
            ball.vx = ball.vy = 0;
            ball.invalidate();

            // 結果画面へ
            Intent intent = new Intent(getApplicationContext(), GameOverActivity.class);
            startActivity(intent);

        }else if ((bhole4.x - bhole4.r < ball.x &&
                ball.x < bhole4.x + bhole4.r) &&
                (bhole4.y - bhole4.r < ball.y &&
                        ball.y < bhole4.y + bhole4.r)) {
            ball.x = bhole4.x;
            ball.y = bhole4.y;
            ball.vx = ball.vy = 0;
            ball.invalidate();

            // 結果画面へ
            Intent intent = new Intent(getApplicationContext(), GameOverActivity.class);
            startActivity(intent);

        }else if ((bhole5.x - bhole5.r < ball.x &&
                ball.x < bhole5.x + bhole5.r) &&
                (bhole5.y - bhole5.r < ball.y &&
                        ball.y < bhole5.y + bhole5.r)) {
            ball.x = bhole5.x;
            ball.y = bhole5.y;
            ball.vx = ball.vy = 0;
            ball.invalidate();

            // 結果画面へ
            Intent intent = new Intent(getApplicationContext(), GameOverActivity.class);
            startActivity(intent);
        }

        else {
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

