package com.barmej.guesstheanswer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    private TextView mTextViewAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        mTextViewAnswer = findViewById(R.id.text_view_answer);
        String answer = getIntent().getStringExtra(Constants.QUESTION_ANSWER);
        if (answer != null)
            mTextViewAnswer.setText(answer);
    }

    public void onReturnClicked(View view) {
        finish();
    }
}
