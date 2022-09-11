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

public class Main3Activity extends Activity implements Runnable, SensorEventListener {
    SensorManager manager;
    Ball ball;
    Hole hole;
    BadHole bhole1,bhole2,bhole3,bhole4,bhole5,bhole6,bhole7,bhole8,bhole9,bhole10,bhole11,bhole12,bhole13,bhole14,bhole15,bhole16,bhole17,bhole18;
    Wall wall1,wall2,wall3,wall4,wall5,wall6,wall7,wall8,wall9;
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
        hole.x = 1200;
        hole.y = 700;

        bhole1 = new BadHole(this);
        bhole1.x = width-bhole1.r*2;
        bhole1.y = 100;

        bhole2 = new BadHole(this);
        bhole2.x = 1000+bhole2.r*2;
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

        bhole6 = new BadHole(this);
        bhole6.x = 1960;
        bhole6.y = 600;

        bhole7 = new BadHole(this);
        bhole7.x = 140;
        bhole7.y = 100;

        bhole8 = new BadHole(this);
        bhole8.x = 1600;
        bhole8.y = bhole8.r;

        bhole9 = new BadHole(this);
        bhole9.x = 600;
        bhole9.y = bhole9.r;

        bhole10 = new BadHole(this);
        bhole10.x = bhole10.r;
        bhole10.y = 600;

        bhole11 = new BadHole(this);
        bhole11.x = 140;
        bhole11.y = 1000;

        bhole12 = new BadHole(this);
        bhole12.x = 550;
        bhole12.y = 700;

        bhole13 = new BadHole(this);
        bhole13.x = 1000;
        bhole13.y = 350;

        bhole14 = new BadHole(this);
        bhole14.x = 1550;
        bhole14.y = 500;

        bhole15 = new BadHole(this);
        bhole15.x = 1750;
        bhole15.y = 500;

        bhole16 = new BadHole(this);
        bhole16.x = 1650;
        bhole16.y = 750;

        bhole17 = new BadHole(this);
        bhole17.x = 750;
        bhole17.y = 600;

        bhole18 = new BadHole(this);
        bhole18.x = 860;
        bhole18.y = 730;

        //壁の描画(横0～2000、縦0～1100)
        wall1 = new Wall(this);
        wall1.l = 1800;
        wall1.t = 150;
        wall1.r = 1900;
        wall1.b = 1100;

        wall2 = new Wall(this);
        wall2.l = 200;
        wall2.t = 150;
        wall2.r = 1900;
        wall2.b = 250;

        wall3 = new Wall(this);
        wall3.l = 200;
        wall3.t = 150;
        wall3.r = 300;
        wall3.b = 900;

        wall4 = new Wall(this);
        wall4.l = 600;
        wall4.t = 450;
        wall4.r = 700;
        wall4.b = 1100;

        wall5 = new Wall(this);
        wall5.l = 600;
        wall5.t = 450;
        wall5.r = 1500;
        wall5.b = 550;

        wall6 = new Wall(this);
        wall6.l = 1400;
        wall6.t = 450;
        wall6.r = 1500;
        wall6.b = 900;

        wall7 = new Wall(this);
        wall7.l = 900;
        wall7.t = 800;
        wall7.r = 1500;
        wall7.b = 900;

        wall8 = new Wall(this);
        wall8.l = 900;
        wall8.t = 700;
        wall8.r = 1000;
        wall8.b = 900;


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
        framelayout.addView(bhole12);
        framelayout.addView(bhole13);
        framelayout.addView(bhole14);
        framelayout.addView(bhole15);
        framelayout.addView(bhole16);
        framelayout.addView(bhole17);
        framelayout.addView(bhole18);

        framelayout.addView(ball);

        framelayout.addView(wall1);
        framelayout.addView(wall2);
        framelayout.addView(wall3);
        framelayout.addView(wall4);
        framelayout.addView(wall5);
        framelayout.addView(wall6);
        framelayout.addView(wall7);
        framelayout.addView(wall8);
    }

    @Override
    public void run() {
        ball.vx += (float) (gx * time / 10000);
        ball.vy += (float) (gy * time / 10000);
        ball.x += dpi * ball.vx * time / 50;
        ball.y += dpi * ball.vy * time / 50;

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
        } else if (ball.y >= wall1.t - ball.radius && ball.y < wall1.t && ball.x >= wall1.l && ball.x <= wall1.r) {
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
        } else if (ball.y >= wall3.t - ball.radius && ball.y < wall3.t && ball.x >= wall3.l && ball.x <= wall3.r) {
            ball.y = wall3.t - ball.radius;
            ball.vy = -ball.vy / 3;
        }else if (ball.y < wall3.b + ball.radius && ball.y >= wall3.b && ball.x >= wall3.l && ball.x <= wall3.r) {
            ball.y = wall3.b + ball.radius;
            ball.vy = -ball.vy / 3;
        }

        //壁４の当たり判定
        if (ball.x < wall4.r + ball.radius && ball.x >= wall4.r && ball.y <= wall4.b && ball.y >= wall4.t) {
            ball.x = wall4.r + ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.x >= wall4.l - ball.radius && ball.x < wall4.l && ball.y <= wall4.b && ball.y >= wall4.t) {
            ball.x = wall4.l - ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.y >= wall4.t - ball.radius && ball.y < wall4.t && ball.x >= wall4.l && ball.x <= wall4.r) {
            ball.y = wall4.t - ball.radius;
            ball.vy = -ball.vy / 3;
        }

        //壁５の当たり判定
        if (ball.x < wall5.r + ball.radius && ball.x >= wall5.r && ball.y <= wall5.b && ball.y >= wall5.t) {
            ball.x = wall5.r + ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.x >= wall5.l - ball.radius && ball.x < wall5.l && ball.y <= wall5.b && ball.y >= wall5.t) {
            ball.x = wall5.l - ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.y < wall5.b + ball.radius && ball.y >= wall5.b && ball.x >= wall5.l && ball.x <= wall5.r) {
            ball.y = wall5.b + ball.radius;
            ball.vy = -ball.vy / 3;
        }else if (ball.y >= wall5.t - ball.radius && ball.y < wall5.t && ball.x >= wall5.l && ball.x <= wall5.r) {
            ball.y = wall5.t - ball.radius;
            ball.vy = -ball.vy / 3;
        }

        //壁６の当たり判定
        if (ball.x < wall6.r + ball.radius && ball.x >= wall6.r && ball.y <= wall6.b && ball.y >= wall6.t) {
            ball.x = wall6.r + ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.x >= wall6.l - ball.radius && ball.x < wall6.l && ball.y <= wall6.b && ball.y >= wall6.t) {
            ball.x = wall6.l - ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.y < wall6.b + ball.radius && ball.y >= wall6.b && ball.x >= wall6.l && ball.x <= wall6.r) {
            ball.y = wall6.b + ball.radius;
            ball.vy = -ball.vy / 3;
        }else if (ball.y >= wall6.t - ball.radius && ball.y < wall6.t && ball.x >= wall6.l && ball.x <= wall6.r) {
            ball.y = wall6.t - ball.radius;
            ball.vy = -ball.vy / 3;
        }

        //壁７の当たり判定
        if (ball.x < wall7.r + ball.radius && ball.x >= wall7.r && ball.y <= wall7.b && ball.y >= wall7.t) {
            ball.x = wall7.r + ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.x >= wall7.l - ball.radius && ball.x < wall7.l && ball.y <= wall7.b && ball.y >= wall7.t) {
            ball.x = wall7.l - ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.y < wall7.b + ball.radius && ball.y >= wall7.b && ball.x >= wall7.l && ball.x <= wall7.r) {
            ball.y = wall7.b + ball.radius;
            ball.vy = -ball.vy / 3;
        }else if (ball.y >= wall7.t - ball.radius && ball.y < wall7.t && ball.x >= wall7.l && ball.x <= wall7.r) {
            ball.y = wall7.t - ball.radius;
            ball.vy = -ball.vy / 3;
        }

        //壁８の当たり判定
        if (ball.x < wall8.r + ball.radius && ball.x >= wall8.r && ball.y <= wall8.b && ball.y >= wall8.t) {
            ball.x = wall8.r + ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.x >= wall8.l - ball.radius && ball.x < wall8.l && ball.y <= wall8.b && ball.y >= wall8.t) {
            ball.x = wall8.l - ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.y < wall8.b + ball.radius && ball.y >= wall8.b && ball.x >= wall8.l && ball.x <= wall8.r) {
            ball.y = wall8.b + ball.radius;
            ball.vy = -ball.vy / 3;
        }else if (ball.y >= wall8.t - ball.radius && ball.y < wall8.t && ball.x >= wall8.l && ball.x <= wall8.r) {
            ball.y = wall8.t - ball.radius;
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
            Intent intent = new Intent(getApplicationContext(), GameOver2Activity.class);
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
            Intent intent = new Intent(getApplicationContext(), GameOver2Activity.class);
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
            Intent intent = new Intent(getApplicationContext(), GameOver2Activity.class);
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
            Intent intent = new Intent(getApplicationContext(), GameOver2Activity.class);
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
            Intent intent = new Intent(getApplicationContext(), GameOver2Activity.class);
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
            Intent intent = new Intent(getApplicationContext(), GameOver2Activity.class);
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
            Intent intent = new Intent(getApplicationContext(), GameOver2Activity.class);
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
            Intent intent = new Intent(getApplicationContext(), GameOver2Activity.class);
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
            Intent intent = new Intent(getApplicationContext(), GameOver2Activity.class);
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
            Intent intent = new Intent(getApplicationContext(), GameOver2Activity.class);
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
            Intent intent = new Intent(getApplicationContext(), GameOver2Activity.class);
            startActivity(intent);

        }else if ((bhole12.x - bhole12.r < ball.x &&
                ball.x < bhole12.x + bhole12.r) &&
                (bhole12.y - bhole12.r < ball.y &&
                        ball.y < bhole12.y + bhole12.r)) {
            ball.x = bhole12.x;
            ball.y = bhole12.y;
            ball.vx = ball.vy = 0;
            ball.invalidate();

            // 結果画面へ
            Intent intent = new Intent(getApplicationContext(), GameOver2Activity.class);
            startActivity(intent);

        }else if ((bhole13.x - bhole13.r < ball.x &&
                ball.x < bhole13.x + bhole13.r) &&
                (bhole13.y - bhole13.r < ball.y &&
                        ball.y < bhole13.y + bhole13.r)) {
            ball.x = bhole13.x;
            ball.y = bhole13.y;
            ball.vx = ball.vy = 0;
            ball.invalidate();

            // 結果画面へ
            Intent intent = new Intent(getApplicationContext(), GameOver2Activity.class);
            startActivity(intent);

        }else if ((bhole14.x - bhole14.r < ball.x &&
                ball.x < bhole14.x + bhole14.r) &&
                (bhole14.y - bhole14.r < ball.y &&
                        ball.y < bhole14.y + bhole14.r)) {
            ball.x = bhole14.x;
            ball.y = bhole14.y;
            ball.vx = ball.vy = 0;
            ball.invalidate();

            // 結果画面へ
            Intent intent = new Intent(getApplicationContext(), GameOver2Activity.class);
            startActivity(intent);

        }else if ((bhole15.x - bhole15.r < ball.x &&
                ball.x < bhole15.x + bhole15.r) &&
                (bhole15.y - bhole15.r < ball.y &&
                        ball.y < bhole15.y + bhole15.r)) {
            ball.x = bhole15.x;
            ball.y = bhole15.y;
            ball.vx = ball.vy = 0;
            ball.invalidate();

            // 結果画面へ
            Intent intent = new Intent(getApplicationContext(), GameOver2Activity.class);
            startActivity(intent);

        }else if ((bhole16.x - bhole16.r < ball.x &&
                ball.x < bhole16.x + bhole16.r) &&
                (bhole16.y - bhole16.r < ball.y &&
                        ball.y < bhole16.y + bhole16.r)) {
            ball.x = bhole16.x;
            ball.y = bhole16.y;
            ball.vx = ball.vy = 0;
            ball.invalidate();

            // 結果画面へ
            Intent intent = new Intent(getApplicationContext(), GameOver2Activity.class);
            startActivity(intent);

        }else if ((bhole17.x - bhole17.r < ball.x &&
                ball.x < bhole17.x + bhole17.r) &&
                (bhole17.y - bhole17.r < ball.y &&
                        ball.y < bhole17.y + bhole17.r)) {
            ball.x = bhole17.x;
            ball.y = bhole17.y;
            ball.vx = ball.vy = 0;
            ball.invalidate();

            // 結果画面へ
            Intent intent = new Intent(getApplicationContext(), GameOver2Activity.class);
            startActivity(intent);

        }else if ((bhole18.x - bhole18.r < ball.x &&
                ball.x < bhole18.x + bhole18.r) &&
                (bhole18.y - bhole18.r < ball.y &&
                        ball.y < bhole18.y + bhole18.r)) {
            ball.x = bhole18.x;
            ball.y = bhole18.y;
            ball.vx = ball.vy = 0;
            ball.invalidate();

            // 結果画面へ
            Intent intent = new Intent(getApplicationContext(), GameOver2Activity.class);
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

