package com.example.hendriksemacbook.braintrainer.Pages;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.service.voice.AlwaysOnHotwordDetector;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hendriksemacbook.braintrainer.R;
import com.example.hendriksemacbook.braintrainer.ui.MainActivity;

public class Page0  extends AppCompatActivity {
    public static final String TAG = Page0.class.getSimpleName();
    private int CORRECT_MAX = 1;
    private int INCORRECT_MAX = 1;
    private int scoreCorrect;
    private int scoreIncorrect;

    TextView questionTextView;
    TextView topLeftTextView;
    TextView topRightTextView;
    TextView bottomLeftTextView;
    TextView bottomRightTextView;

    ImageView incorrectImageView;
    ImageView correctImageView;

    ImageView topLeftImageView;
    ImageView topRightImageView;
    ImageView bottomLeftImageView;
    ImageView bottomRightImageView;

    TextView incorrectTextView;
    TextView correctTextView;


    MediaPlayer mediaPlayer;
    Handler handler = new Handler();
    final static int DELAY = 600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        // find Views
        questionTextView = findViewById(R.id.questionTextView);
        topLeftTextView = findViewById(R.id.topLeftTextView);
        topRightTextView = findViewById(R.id.topRightTextView);
        bottomLeftTextView = findViewById(R.id.bottomLeftTextView);
        bottomRightTextView = findViewById(R.id.bottomRightTextView);

        incorrectImageView = findViewById(R.id.incorrectImageView);
        correctImageView = findViewById(R.id.correctImageView);


        topLeftImageView = findViewById(R.id.topLeftImageView);
        topRightImageView = findViewById(R.id.topRightImageView);
        bottomLeftImageView = findViewById(R.id.bottomLeftImageView);
        bottomRightImageView = findViewById(R.id.bottomRightImageView);

        incorrectTextView = findViewById(R.id.incorrectTextView);
        correctTextView = findViewById(R.id.correctTextView);

        //set Images and views

        questionTextView.setText(R.string.bear);
        questionTextView.setTranslationY(-1000);
        questionTextView.animate().translationYBy(1000).setDuration(150);

        incorrectTextView.setText("0");
        correctTextView.setText("0");

        incorrectTextView.setRotation(-720);
        incorrectTextView.animate().rotation(720).setDuration(400);
        incorrectTextView.setTranslationY(-1000);
        incorrectTextView.animate().translationYBy(1000).setDuration(400);

        correctTextView.setRotation(-720);
        correctTextView.animate().rotation(720).setDuration(400);
        correctTextView.setTranslationY(-1000);
        correctTextView.animate().translationYBy(1000).setDuration(400);

        //Set Images
        topLeftImageView.setBackgroundResource(R.drawable.dog);
        topRightImageView.setBackgroundResource(R.drawable.elk);
        bottomLeftImageView.setBackgroundResource(R.drawable.cat);
        bottomRightImageView.setBackgroundResource(R.drawable.bear);
        //Set Animation

        topLeftImageView.setTranslationX(-1000);
        topLeftImageView.animate().translationXBy(1000).setDuration(300);
        topLeftImageView.setRotation(-360);
        topLeftImageView.animate().rotation(360).setDuration(300);

        topRightImageView.setTranslationX(1000);
        topRightImageView.animate().translationXBy(-1000).setDuration(300);
        topRightImageView.setRotation(-360);
        topRightImageView.animate().rotation(360).setDuration(300);

        bottomLeftImageView.setTranslationX(-1000);
        bottomLeftImageView.animate().translationXBy(1000).setDuration(300);
        bottomLeftImageView.setRotation(-360);
        bottomLeftImageView.animate().rotation(360).setDuration(300);

        bottomRightImageView.setTranslationX(1000);
        bottomRightImageView.animate().translationXBy(-1000).setDuration(300);
        bottomRightImageView.setRotation(-360);
        bottomRightImageView.animate().rotation(360).setDuration(300);

        //Set Image Descriptions for user
        topLeftTextView.setText(R.string.page0_top_left_image_view);
        topRightTextView.setText(R.string.page0_top_right_image_view);
        bottomLeftTextView.setText(R.string.page0_bottom_left_image_view);
        bottomRightTextView.setText(R.string.page0_bottom_right_image_view);

        topLeftImageView.setOnClickListener(loadIncorrectAnswer);
        topRightImageView.setOnClickListener(loadIncorrectAnswer);
        bottomLeftImageView.setOnClickListener(loadIncorrectAnswer);
        bottomRightImageView.setOnClickListener(loadCorrectAnswer);

        mediaPlayer = MediaPlayer.create(Page0.this, R.raw.bear_growl);

        playAudioWithDelay();

    }


    public void playAudioWithDelay() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.start();
            }

        }, DELAY);
    }




    View.OnClickListener loadIncorrectAnswer = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            incorrectImageView.setTranslationY(-10);
            incorrectImageView.animate().translationYBy(10).setDuration(2);

            try{
                mediaPlayer.reset();
                mediaPlayer.prepare();
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e){
                e.printStackTrace();
            }
            if(scoreIncorrect < INCORRECT_MAX) {
                scoreIncorrect += 1;
                incorrectTextView.setText(String.valueOf(scoreIncorrect));
            } else {
                incorrectTextView.setText(String.valueOf(scoreIncorrect));
                Toast.makeText(getApplicationContext(), "Cannot Repeat until game is over", Toast.LENGTH_SHORT).show();
            }
            loadPageCorrect(scoreIncorrect, scoreCorrect);
        }
    };

    View.OnClickListener loadCorrectAnswer = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            correctImageView.setTranslationY(-10);
            correctImageView.animate().translationYBy(10).setDuration(2);

            try{
                mediaPlayer.reset();
                mediaPlayer.prepare();
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e){
                e.printStackTrace();
            }

            if (scoreCorrect < CORRECT_MAX) {
                scoreCorrect += 1;
                correctTextView.setText(String.valueOf(scoreCorrect));
            } else {
                correctTextView.setText(String.valueOf(scoreCorrect));
                Toast.makeText(getApplicationContext(), "Cannot Repeat until game is over", Toast.LENGTH_SHORT).show();
            }
            loadPageCorrect(scoreCorrect, scoreIncorrect);


        }
    };



    private void loadPageCorrect(int correct, int incorrect){
        Intent loadCorrect = new Intent(this, Page1.class);
        loadCorrect.putExtra("correct", scoreCorrect);
        loadCorrect.putExtra("incorrect", scoreIncorrect);
        startActivity(loadCorrect);



    }
    @Override
    public void onBackPressed() {
        Intent startAgain = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(startAgain);
        finish();
    }

}
