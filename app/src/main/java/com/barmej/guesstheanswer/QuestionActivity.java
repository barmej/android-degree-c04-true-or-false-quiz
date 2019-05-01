package com.barmej.guesstheanswer;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QuestionActivity extends AppCompatActivity {

    private String[] QUESTIONS;

    private static final boolean[] ANSWERS = {
            false,
            true,
            true,
            false};

    private String[] ANSWERS_DETAILS;

    private TextView mTextViewQuestion;

    private String mCurrentQuestion, mCurrentAnswerDetail;
    private boolean mCurrentAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE);
        String appLang = sharedPreferences.getString("app_lang", "");
        if (!appLang.equals(""))
            LocaleHelper.setLocale(this, appLang);

        setContentView(R.layout.activity_question);

        QUESTIONS = getResources().getStringArray(R.array.questions);
        ANSWERS_DETAILS = getResources().getStringArray(R.array.answers_details);

        mTextViewQuestion = findViewById(R.id.text_view_question);
        showNewQuestion();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_change_lang) {
            showLanguagesDialog();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showLanguagesDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.change_lang_text)
                .setItems(R.array.languages, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String language = "ar";
                        switch (which) {
                            case 0:
                                language = "ar";
                                break;
                            case 1:
                                language = "en";
                                break;
                            case 2:
                                language = "fr";
                                break;
                        }

                        saveLanguage(language);
                        LocaleHelper.setLocale(QuestionActivity.this, language);
//                        recreate();
                        Intent i = new Intent(getApplicationContext(),
                               QuestionActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }
                }).create();
        alertDialog.show();
    }

    private void saveLanguage(String lang) {
        SharedPreferences sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("app_lang", lang);
        editor.apply();
    }

    private void showNewQuestion() {
        Random random = new Random();
        int randomQuestionIndex = random.nextInt(QUESTIONS.length);
        mCurrentQuestion = QUESTIONS[randomQuestionIndex];
        mCurrentAnswer = ANSWERS[randomQuestionIndex];
        mCurrentAnswerDetail = ANSWERS_DETAILS[randomQuestionIndex];
        mTextViewQuestion.setText(mCurrentQuestion);
    }

    public void onChangeQuestionClicked(View view) {
        showNewQuestion();
    }

    public void onShareQuestionClicked(View view) {
        Intent intent = new Intent(QuestionActivity.this, ShareActivity.class);
        intent.putExtra("question_text_extra", mCurrentQuestion);
        startActivity(intent);
    }

    public void onTrueClicked(View view) {
        if (mCurrentAnswer == true) {
            Toast.makeText(this, getString(R.string.true_text), Toast.LENGTH_SHORT).show();
            showNewQuestion();
        } else {
            Toast.makeText(this, getString(R.string.false_text), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(QuestionActivity.this, AnswerActivity.class);
            intent.putExtra("question_answer", mCurrentAnswerDetail);
            startActivity(intent);
        }
    }

    public void onFalseClicked(View view) {
        if (mCurrentAnswer == false) {
            Toast.makeText(this, getString(R.string.true_text), Toast.LENGTH_SHORT).show();
            showNewQuestion();
        } else {
            Toast.makeText(this, getString(R.string.false_text), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(QuestionActivity.this, AnswerActivity.class);
            intent.putExtra("question_answer", mCurrentAnswerDetail);
            startActivity(intent);
        }
    }
}
