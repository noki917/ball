package com.example.ball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void startGame(View view) {
        startActivity(new Intent(getApplicationContext(), Main2Activity.class));
    }

    public void startGame2(View view) {
        startActivity(new Intent(getApplicationContext(), Main4Activity.class));
    }

    public void startGame3(View view) {
        startActivity(new Intent(getApplicationContext(), Main3Activity.class));
    }

    public void returnStart(View view) {
        startActivity(new Intent(getApplicationContext(), RuleActivity.class));
    }
}
