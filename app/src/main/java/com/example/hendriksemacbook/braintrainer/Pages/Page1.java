package com.example.hendriksemacbook.braintrainer.Pages;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hendriksemacbook.braintrainer.R;
import com.example.hendriksemacbook.braintrainer.ui.MainActivity;

public class Page1 extends AppCompatActivity {


    private int CORRECT_MAX = 2;
    private int INCORRECT_MAX = 2;
    public int scoreIncorrect;
    public int scoreCorrect;

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
        Intent load = getIntent();

        scoreIncorrect = load.getIntExtra("incorrect", 1);

        scoreCorrect = getIntent().getIntExtra("correct", 0);

        scoreCorrect = scoreCorrect;

        scoreIncorrect = scoreIncorrect;

        questionTextView.setText(R.string.horse);
        questionTextView.setTranslationY(-1000);
        questionTextView.animate().translationYBy(1000).setDuration(700);





        incorrectTextView.setText(String.valueOf(scoreIncorrect));
        correctTextView.setText(String.valueOf(scoreCorrect));

        incorrectTextView.setRotation(-720);
        incorrectTextView.animate().rotation(720).setDuration(1000);
        incorrectTextView.setTranslationY(-1000);
        incorrectTextView.animate().translationYBy(1000).setDuration(1000);

        correctTextView.setRotation(-720);
        correctTextView.animate().rotation(720).setDuration(1000);
        correctTextView.setTranslationY(-1000);
        correctTextView.animate().translationYBy(1000).setDuration(1000);


        //Set Images
        topLeftImageView.setBackgroundResource(R.drawable.antelope);
        topRightImageView.setBackgroundResource(R.drawable.chicken);
        bottomLeftImageView.setBackgroundResource(R.drawable.bird);
        bottomRightImageView.setBackgroundResource(R.drawable.horse);

        //Set animation
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

        //Set Image Descriptions for Users
        topLeftTextView.setText(R.string.page1_top_left_image_view);
        topRightTextView.setText(R.string.page1_top_right_image_view);
        bottomLeftTextView.setText(R.string.page1_bottom_left_image_view);
        bottomRightTextView.setText(R.string.page1_bottom_right_image_view);




        topLeftImageView.setOnClickListener(loadIncorrectAnswer);
        topRightImageView.setOnClickListener(loadIncorrectAnswer);
        bottomLeftImageView.setOnClickListener(loadIncorrectAnswer);
        bottomRightImageView.setOnClickListener(loadCorrectAnswer);

        mediaPlayer = MediaPlayer.create(Page1.this, R.raw.horse);
        if(mediaPlayer != null) {
            playAudioWithDelay();
        } else {
            mediaPlayer.reset();;
            mediaPlayer.stop();
            mediaPlayer.release();
        }
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
        Intent loadCorrect = new Intent(this, Page2.class);
        loadCorrect.putExtra("incorrect", scoreIncorrect);
        loadCorrect.putExtra("correct", scoreCorrect);
        startActivity(loadCorrect);

    }

    @Override
    public void onBackPressed() {
        Intent startAgain = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(startAgain);
        finish();
    }
}
