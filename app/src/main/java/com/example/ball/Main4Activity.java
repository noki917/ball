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

public class Main4Activity extends Activity implements Runnable, SensorEventListener {
    SensorManager manager;
    Ball ball;
    Hole hole;
    BadHole bhole1,bhole2,bhole3,bhole4,bhole5,bhole6,bhole7,bhole8,bhole9,bhole10,bhole11;
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
        hole.x = hole.r;
        hole.y = hole.r;

        bhole1 = new BadHole(this);
        bhole1.x = width-bhole1.r;
        bhole1.y = 450;

        bhole2 = new BadHole(this);
        bhole2.x = width-bhole2.r*3;
        bhole2.y = 450;

        bhole3 = new BadHole(this);
        bhole3.x = width-bhole3.r*5;
        bhole3.y = height-bhole3.r*3;

        bhole4 = new BadHole(this);
        bhole4.x = width-bhole4.r*5;
        bhole4.y = height-bhole4.r;

        bhole5 = new BadHole(this);
        bhole5.x = bhole5.r;
        bhole5.y = 450;

        bhole6 = new BadHole(this);
        bhole6.x = bhole6.r*3;
        bhole6.y = 450;

        bhole7 = new BadHole(this);
        bhole7.x = 1175;
        bhole7.y = bhole7.r;

        bhole8 = new BadHole(this);
        bhole8.x = 1175;
        bhole8.y = bhole8.r*3;

        bhole9 = new BadHole(this);
        bhole9.x = 1150-bhole9.r;
        bhole9.y = 475+bhole9.r;

        bhole10 = new BadHole(this);
        bhole10.x = 1050;
        bhole10.y = 550;

        bhole11 = new BadHole(this);
        bhole11.x = 950+bhole11.r;
        bhole11.y = 625-bhole11.r;

        //壁の描画(横0～2000、縦0～1100)
        wall1 = new Wall(this);
        wall1.l = 1150;
        wall1.t = 625;
        wall1.r = 1900;
        wall1.b = 915;

        wall2 = new Wall(this);
        wall2.l = 1150;
        wall2.t = 160;
        wall2.r = 1900;
        wall2.b = 475;

        wall3 = new Wall(this);
        wall3.l = 165;
        wall3.t = 625;
        wall3.r = 950;
        wall3.b = 915;

        wall4 = new Wall(this);
        wall4.l = 165;
        wall4.t = 160;
        wall4.r = 950;
        wall4.b = 475;


        framelayout.addView(hole);

        framelayout.addView(bhole1);
        framelayout.addView(bhole2);
        framelayout.addView(bhole3);
        framelayout.addView(bhole4);
        framelayout.addView(bhole5);
        framelayout.addView(bhole6);
        framelayout.addView(bhole7);
        framelayout.addView(bhole8);
        framelayout.addView(bhole9);
        framelayout.addView(bhole10);
        framelayout.addView(bhole11);

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
        } else if (ball.x >= wall1.l - ball.radius && ball.x < wall1.l && ball.y <= wall1.b && ball.y >= wall1.t) {
            ball.x = wall1.l - ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.y < wall1.b + ball.radius && ball.y >= wall1.b && ball.x >= wall1.l && ball.x <= wall1.r) {
            ball.y = wall1.b + ball.radius;
            ball.vy = -ball.vy / 3;
        }else if (ball.y >= wall1.t - ball.radius && ball.y < wall1.t && ball.x >= wall1.l && ball.x <= wall1.r) {
            ball.y = wall1.t - ball.radius;
            ball.vy = -ball.vy / 3;
        }

        //壁２の当たり判定
        if (ball.x < wall2.r + ball.radius && ball.x >= wall2.r && ball.y <= wall2.b && ball.y >= wall2.t) {
            ball.x = wall2.r + ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.x >= wall2.l - ball.radius && ball.x < wall2.l && ball.y <= wall2.b && ball.y >= wall2.t) {
            ball.x = wall2.l - ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.y < wall2.b + ball.radius && ball.y >= wall2.b && ball.x >= wall2.l && ball.x <= wall2.r) {
            ball.y = wall2.b + ball.radius;
            ball.vy = -ball.vy / 3;
        }else if (ball.y >= wall2.t - ball.radius && ball.y < wall2.t && ball.x >= wall2.l && ball.x <= wall2.r) {
            ball.y = wall2.t - ball.radius;
            ball.vy = -ball.vy / 3;
        }

        //壁３の当たり判定
        if (ball.x < wall3.r + ball.radius && ball.x >= wall3.r && ball.y <= wall3.b && ball.y >= wall3.t) {
            ball.x = wall3.r + ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.x >= wall3.l - ball.radius && ball.x < wall3.l && ball.y <= wall3.b && ball.y >= wall3.t) {
            ball.x = wall3.l - ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.y < wall3.b + ball.radius && ball.y >= wall3.b && ball.x >= wall3.l && ball.x <= wall3.r) {
            ball.y = wall3.b + ball.radius;
            ball.vy = -ball.vy / 3;
        }else if (ball.y >= wall3.t - ball.radius && ball.y < wall3.t && ball.x >= wall3.l && ball.x <= wall3.r) {
            ball.y = wall3.t - ball.radius;
            ball.vy = -ball.vy / 3;
        }

        //壁４の当たり判定
        if (ball.x < wall4.r + ball.radius && ball.x >= wall4.r && ball.y <= wall4.b && ball.y >= wall4.t) {
            ball.x = wall4.r + ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.x >= wall4.l - ball.radius && ball.x < wall4.l && ball.y <= wall4.b && ball.y >= wall4.t) {
            ball.x = wall4.l - ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.y < wall4.b + ball.radius && ball.y >= wall4.b && ball.x >= wall4.l && ball.x <= wall4.r) {
            ball.y = wall4.b + ball.radius;
            ball.vy = -ball.vy / 3;
        }else if (ball.y >= wall4.t - ball.radius && ball.y < wall4.t && ball.x >= wall4.l && ball.x <= wall4.r) {
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
            Intent intent = new Intent(getApplicationContext(), GameOver3Activity.class);
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
            Intent intent = new Intent(getApplicationContext(), GameOver3Activity.class);
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
            Intent intent = new Intent(getApplicationContext(), GameOver3Activity.class);
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
            Intent intent = new Intent(getApplicationContext(), GameOver3Activity.class);
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
            Intent intent = new Intent(getApplicationContext(), GameOver3Activity.class);
            startActivity(intent);

        }else if ((bhole6.x - bhole6.r < ball.x &&
                ball.x < bhole6.x + bhole6.r) &&
                (bhole6.y - bhole6.r < ball.y &&
                        ball.y < bhole6.y + bhole6.r)) {
            ball.x = bhole6.x;
            ball.y = bhole6.y;
            ball.vx = ball.vy = 0;
            ball.invalidate();

            // 結果画面へ
            Intent intent = new Intent(getApplicationContext(), GameOver3Activity.class);
            startActivity(intent);

        }else if ((bhole7.x - bhole7.r < ball.x &&
                ball.x < bhole7.x + bhole7.r) &&
                (bhole7.y - bhole7.r < ball.y &&
                        ball.y < bhole7.y + bhole7.r)) {
            ball.x = bhole7.x;
            ball.y = bhole7.y;
            ball.vx = ball.vy = 0;
            ball.invalidate();

            // 結果画面へ
            Intent intent = new Intent(getApplicationContext(), GameOver3Activity.class);
            startActivity(intent);

        }else if ((bhole8.x - bhole8.r < ball.x &&
                ball.x < bhole8.x + bhole8.r) &&
                (bhole8.y - bhole8.r < ball.y &&
                        ball.y < bhole8.y + bhole8.r)) {
            ball.x = bhole8.x;
            ball.y = bhole8.y;
            ball.vx = ball.vy = 0;
            ball.invalidate();

            // 結果画面へ
            Intent intent = new Intent(getApplicationContext(), GameOver3Activity.class);
            startActivity(intent);

        }else if ((bhole9.x - bhole9.r < ball.x &&
                ball.x < bhole9.x + bhole9.r) &&
                (bhole9.y - bhole9.r < ball.y &&
                        ball.y < bhole9.y + bhole9.r)) {
            ball.x = bhole9.x;
            ball.y = bhole9.y;
            ball.vx = ball.vy = 0;
            ball.invalidate();

            // 結果画面へ
            Intent intent = new Intent(getApplicationContext(), GameOver3Activity.class);
            startActivity(intent);

        }else if ((bhole10.x - bhole10.r < ball.x &&
                ball.x < bhole10.x + bhole10.r) &&
                (bhole10.y - bhole10.r < ball.y &&
                        ball.y < bhole10.y + bhole10.r)) {
            ball.x = bhole10.x;
            ball.y = bhole10.y;
            ball.vx = ball.vy = 0;
            ball.invalidate();

            // 結果画面へ
            Intent intent = new Intent(getApplicationContext(), GameOver3Activity.class);
            startActivity(intent);

        }else if ((bhole11.x - bhole11.r < ball.x &&
                ball.x < bhole11.x + bhole11.r) &&
                (bhole11.y - bhole11.r < ball.y &&
                        ball.y < bhole11.y + bhole11.r)) {
            ball.x = bhole11.x;
            ball.y = bhole11.y;
            ball.vx = ball.vy = 0;
            ball.invalidate();

            // 結果画面へ
            Intent intent = new Intent(getApplicationContext(), GameOver3Activity.class);
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

