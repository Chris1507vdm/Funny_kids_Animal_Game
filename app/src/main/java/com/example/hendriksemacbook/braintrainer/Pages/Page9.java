package com.example.hendriksemacbook.braintrainer.Pages;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hendriksemacbook.braintrainer.R;
import com.example.hendriksemacbook.braintrainer.ui.MainActivity;
import com.example.hendriksemacbook.braintrainer.ui.ResultsActivity;

public class Page9 extends AppCompatActivity{
    private int CORRECT_MAX = 10;
    private int INCORRECT_MAX = 10;
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
    final static int DELAY = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        Toast.makeText(this, "This is the final Question", Toast.LENGTH_SHORT).show();

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

        incorrectTextView.setRotation(-720);
        incorrectTextView.animate().rotation(720).setDuration(1000);
        incorrectTextView.setTranslationY(-1000);
        incorrectTextView.animate().translationYBy(1000).setDuration(1000);

        correctTextView.setRotation(-720);
        correctTextView.animate().rotation(720).setDuration(1000);
        correctTextView.setTranslationY(-1000);
        correctTextView.animate().translationYBy(1000).setDuration(1000);

        //set Images and views

        Intent load = getIntent();

        scoreIncorrect = load.getIntExtra("incorrect", 0);

        scoreCorrect = load.getIntExtra("correct", 0);

        scoreCorrect = scoreCorrect;

        scoreIncorrect = scoreIncorrect;

        questionTextView.setText(R.string.elephant);
        questionTextView.setTranslationY(-1000);
        questionTextView.animate().translationYBy(1000).setDuration(1500);

        incorrectTextView.setText(String.valueOf(scoreIncorrect));
        correctTextView.setText(String.valueOf(scoreCorrect));

        topLeftImageView.setBackgroundResource(R.drawable.cow);
        topRightImageView.setBackgroundResource(R.drawable.elephant);
        bottomLeftImageView.setBackgroundResource(R.drawable.horse);
        bottomRightImageView.setBackgroundResource(R.drawable.baby_panda);

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

        topLeftTextView.setText(R.string.page9_top_left_image_view);
        topRightTextView.setText(R.string.page9_top_right_image_view);
        bottomLeftTextView.setText(R.string.page9_bottom_left_image_view);
        bottomRightTextView.setText(R.string.page9_bottom_right_image_view);


        topLeftImageView.setOnClickListener(loadIncorrectAnswer);
        topRightImageView.setOnClickListener(loadCorrectAnswer);
        bottomLeftImageView.setOnClickListener(loadIncorrectAnswer);
        bottomRightImageView.setOnClickListener(loadIncorrectAnswer);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.elephant_trumpet);
        playAudioWithDelay();

    }

    public void playAudioWithDelay() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                mediaPlayer.start();
            }
            //your code start with delay in one second after calling this method

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
            mediaPlayer.stop();
            mediaPlayer.release();
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
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    };

    private void loadPageCorrect(int correct, int incorrect){
        Intent loadCorrect = new Intent(getApplicationContext(), ResultsActivity.class);
        loadCorrect.putExtra("incorrect", scoreIncorrect);
        loadCorrect.putExtra("correct", scoreCorrect);
        startActivity(loadCorrect);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent startAgain = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(startAgain);
        finish();
    }
}
