package hu.am2.jokedisplay;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;

import hu.am2.jokedisplay.databinding.ActivityJokeDisplayBinding;

public class JokeDisplayActivity extends AppCompatActivity {

    private static final String EXTRA_JOKE_QUESTION = "hu.am2.jokedisplay.extra.JOKE_QUESTION";
    private static final String EXTRA_JOKE_ANSWER = "hu.am2.jokedisplay.extra.JOKE_ANSWER";
    private static final String EXTRA_JOKE_STATE = "hu.am2.jokedisplay.extra.JOKE_STATE";

    private ActivityJokeDisplayBinding binding;

    private boolean jokeState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_joke_display);

        Intent intent = getIntent();

        binding.question.setText(intent.getStringExtra(EXTRA_JOKE_QUESTION));
        binding.answer.setText(intent.getStringExtra(EXTRA_JOKE_ANSWER));

        if (savedInstanceState != null) {
            jokeState = savedInstanceState.getBoolean(EXTRA_JOKE_STATE);
            if (jokeState) {
                badumTs();
            }
        }

        binding.idkButton.setOnClickListener(v -> badumTs());
    }

    private void badumTs() {
        jokeState = true;
        binding.idkButton.setVisibility(View.GONE);
        binding.answer.setVisibility(View.VISIBLE);
        binding.badumts.setVisibility(View.VISIBLE);
        Glide.with(this).load(R.drawable.monkey_badumts).into(binding.badumts);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(EXTRA_JOKE_STATE, jokeState);
        super.onSaveInstanceState(outState);
    }

    public static void launchJokeDisplay(Context context, String question, String answer) {
        Intent intent = new Intent(context, JokeDisplayActivity.class);
        intent.putExtra(EXTRA_JOKE_QUESTION, question);
        intent.putExtra(EXTRA_JOKE_ANSWER, answer);
        context.startActivity(intent);
    }
}
