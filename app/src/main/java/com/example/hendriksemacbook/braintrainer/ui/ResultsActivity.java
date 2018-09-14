package com.example.hendriksemacbook.braintrainer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hendriksemacbook.braintrainer.R;

public class ResultsActivity extends AppCompatActivity {



    ImageView happyFace;
    ImageView sadFace;

    TextView resultsCorrect;
    TextView resultsIncorrect;

    Button tryAgainButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        happyFace = findViewById(R.id.happyFaceImageView);
        sadFace = findViewById(R.id.sadFaceImageView);

        resultsCorrect = findViewById(R.id.resultsPageCorrectTextView);
        resultsIncorrect = findViewById(R.id.resultsPageIncorrectTextView);
        tryAgainButton = findViewById(R.id.playAgainButton);

        Intent load = getIntent();

        int scoreCorrect = load.getIntExtra("correct", 1);

        int scoreIncorrect = load.getIntExtra("incorrect", 1);

        resultsCorrect.setText(String.valueOf(scoreCorrect));
        resultsIncorrect.setText(String.valueOf(scoreIncorrect));

        resultsCorrect.setRotation(-720);
        resultsCorrect.animate().rotation(720).setDuration(1000);
        resultsCorrect.setTranslationY(-1000);
        resultsCorrect.animate().translationYBy(1000).setDuration(1000);

        resultsIncorrect.setRotation(-720);
        resultsIncorrect.animate().rotation(720).setDuration(1000);
        resultsIncorrect.setTranslationY(-1000);
        resultsIncorrect.animate().translationYBy(1000).setDuration(1000);

        happyFace.setBackgroundResource(R.drawable.happyfacefrog);
        happyFace.setTranslationY(-1000f);
        happyFace.animate().translationYBy(1000f).setDuration(800);
        happyFace.setRotationY(-720);
        happyFace.animate().rotation(720).setDuration(800);


        sadFace.setBackgroundResource(R.drawable.sadfacemonkey);
        sadFace.setTranslationY(-2000f);
        sadFace.animate().translationYBy(2000f).setDuration(800);
        sadFace.setRotationY(-720);
        sadFace.animate().rotation(720).setDuration(800);



        tryAgainButton.setOnClickListener(listener);

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            tryAgain();
        }
    };

    public void tryAgain(){
        Intent startOver = new Intent(ResultsActivity.this, MainActivity.class);
        startActivity(startOver);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent startOver = new Intent(ResultsActivity.this, MainActivity.class);
        startActivity(startOver);
        finish();
    }
}
