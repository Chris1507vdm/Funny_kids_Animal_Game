package com.example.hendriksemacbook.braintrainer.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hendriksemacbook.braintrainer.Pages.Page0;
import com.example.hendriksemacbook.braintrainer.R;

public class MainActivity extends AppCompatActivity {
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.startButton);



        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        };

        startButton.setOnClickListener(listener);
    }

    public void startGame(){
        Intent start = new Intent(MainActivity.this, Page0.class);
        startActivity(start);
        finishActivity(0);
    }
}
