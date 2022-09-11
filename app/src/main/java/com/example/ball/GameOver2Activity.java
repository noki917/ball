package com.example.ball;
import android.media.MediaPlayer;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameOver2Activity extends AppCompatActivity {

    MediaPlayer p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        p = MediaPlayer.create(getApplicationContext(), R.raw.gameover2);
        // 連続再生設定
        p.setLooping(true);
    }

    // 画面が表示されるたびに実行
    @Override
    protected void onResume() {
        super.onResume();
        p.start(); // 再生
    }

    // 画面が非表示に実行
    @Override
    protected void onPause() {
        super.onPause();
        p.pause(); // 一時停止
    }

    // アプリ終了時に実行
    @Override
    protected void onDestroy() {
        super.onDestroy();
        p.release();// メモリの解放
        p = null; // 音楽プレーヤーを破棄
    }


    public void tryAgain(View view) {
        startActivity(new Intent(getApplicationContext(), Main3Activity.class));
    }

    //タイトル画面への遷移
    public void title(View view) {
        startActivity(new Intent(getApplicationContext(), StartActivity.class));
    }
}

