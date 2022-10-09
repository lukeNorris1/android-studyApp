package com.example.myapplication.ui.pomodoro;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentPomodoroBinding;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class PomodoroFragment extends Fragment {

    final private long START_TIME_IN_MILLIS = 1500000;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private final int CARD_SIZE = 5;

    private FragmentPomodoroBinding binding;

    private int counter = 0;

    LinearLayout layout;
    TextView current;
    TextView [] startArray = new TextView[CARD_SIZE];
    TextView [] restArray = new TextView[CARD_SIZE];
    MediaPlayer ding;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Pre-existing code --- needs to be here
        PomodoroViewModel pomodoroViewModel = new ViewModelProvider(this).get(PomodoroViewModel.class);
        binding = FragmentPomodoroBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //------------------------------------------------
        ding = MediaPlayer.create(getContext(), R.raw.ding);


        //Block to add textviews [Card] to linearlayout under pomodoroFragment
        layout = binding.container;
        for (int i = 0; i < CARD_SIZE; i++){
            startArray[i] = addCard(i);
            restArray[i] = addCardRest(i);
        }

        TextView start = startArray[counter];
        current = start;

        start.setText(updateCountDownText());




        // Start button on click event
        binding.startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(mTimerRunning){
                    pauseTimer();
                } else{
                    startTimer(current); // make a variable to store the index of the working/resting timers so It can re-start the correct timer
                }
            }
        });





        pomodoroViewModel.getText().observe(getViewLifecycleOwner(), start::setText);
        return root;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    // END OF DEFAULT METHODS --------------

    private void startTimer(TextView text){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long l) {
                mTimeLeftInMillis = l;
                text.setText(updateCountDownText());
            }

            @Override
            public void onFinish() {
                Log.i("TAG", String.valueOf(text.getText()));
                //If work block was last block change value of rest block
                if (counter < 5){
                    ding.start();
                    if (text == startArray[counter]){
                        restArray[counter].setText("05:00");
                        mTimeLeftInMillis = 300000;
                        startTimer(restArray[counter]);
                        current = restArray[counter];
                        counter += 1;
                    } else{
                        Log.i("TAG", "not start");
                        startArray[counter].setText("25:00");
                        mTimeLeftInMillis = 1500000;
                        startTimer(startArray[counter]);
                        current = startArray[counter];

                    }
                } else binding.startButton.setText("Finished");

            }
        }.start();
        mTimerRunning = true;
        binding.startButton.setText("Pause");
    }

    private void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRunning = false;
        binding.startButton.setText("Start");
    }

    private String updateCountDownText(){
        int minutes = (int)(mTimeLeftInMillis/1000) / 60;
        int seconds = (int)(mTimeLeftInMillis/1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

        return timeLeftFormatted;

    }

    private TextView addCard(int id) {
        final View view = getLayoutInflater().inflate(R.layout.card, null);

        TextView nameView = view.findViewById(R.id.text_pomodoro);
        nameView.setId(id);

        layout.addView(view);
        return nameView;
    }
    private TextView addCardRest(int id) {
        final View view = getLayoutInflater().inflate(R.layout.cardrest, null);

        TextView nameView = view.findViewById(R.id.text_pomodoro_rest);
        nameView.setId(id);

        layout.addView(view);
        return nameView;
    }
}
