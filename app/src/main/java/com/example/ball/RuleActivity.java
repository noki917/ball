package com.example.ball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RuleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);
    }

    public void returnStart(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void returnStart2(View view) {
        startActivity(new Intent(getApplicationContext(), StartActivity.class));
    }
}
