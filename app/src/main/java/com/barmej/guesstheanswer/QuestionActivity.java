package com.barmej.guesstheanswer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QuestionActivity extends AppCompatActivity {

    private static final String[] QUESTIONS = {
            "العملة الرسمية لدولة الكويت هي الريال الكويتي؟",
            "توبقال هي أعلى قمة جبلية في العالم العربي؟",
            "الجزائر هي أكبر دولة عربية من حيث المساحة؟",
            "الدار البيضاء هي عاصمة المغرب؟"};

    private static final boolean[] ANSWERS = {
            false,
            true,
            true,
            false};

    private static final String[] ANSWERS_DETAILS = {
            "العملة الرسمية لدولة الكويت هي الدينار الكويتي",
            "توبقال هي أعلى قمة جبلية في العالم العربي و تقع في المغرب",
            "الجزائر هلي أكبر دولة عربية و إفريقية من حيث المساحة",
            "الرباط هي عاصمة المغرب"};

    private TextView tvQuestion;

    private String mCurrentQuestion, mCurrentAnswerDetail;
    private boolean mCurrentAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        tvQuestion = findViewById(R.id.text_view_question);
        showNewQuestion();
    }

    private void showNewQuestion() {
        Random random = new Random();
        int randomQuestionIndex = random.nextInt(QUESTIONS.length);
        mCurrentQuestion = QUESTIONS[randomQuestionIndex];
        mCurrentAnswer = ANSWERS[randomQuestionIndex];
        mCurrentAnswerDetail = ANSWERS_DETAILS[randomQuestionIndex];
        tvQuestion.setText(mCurrentQuestion);
    }

    public void onChangeQuestionClicked(View view) {
        showNewQuestion();
    }

    public void onShareQuestionClicked(View view) {
        Intent intent = new Intent(QuestionActivity.this, ShareActivity.class);
        intent.putExtra(Constants.QUESTION_TEXT_EXTRA, mCurrentQuestion);
        startActivity(intent);
    }

    public void onTrueClicked(View view) {
        if (mCurrentAnswer == true) {
            Toast.makeText(this, "إجابة صحيحة", Toast.LENGTH_SHORT).show();
            showNewQuestion();
        } else {
            Toast.makeText(this, "إجابة خاطئة", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(QuestionActivity.this, AnswerActivity.class);
            intent.putExtra(Constants.QUESTION_ANSWER, mCurrentAnswerDetail);
            startActivity(intent);
        }
    }

    public void onFalseClicked(View view) {
        if (mCurrentAnswer == false) {
            Toast.makeText(this, "إجابة صحيحة", Toast.LENGTH_SHORT).show();
            showNewQuestion();
        } else {
            Toast.makeText(this, "إجابة خاطئة", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(QuestionActivity.this, AnswerActivity.class);
            intent.putExtra(Constants.QUESTION_ANSWER, mCurrentAnswerDetail);
            startActivity(intent);
        }
    }
}
