package com.example.myapplication.ui.Flowtime;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentFlowtimeBinding;

import java.util.Locale;

public class FlowtimeFragment extends Fragment {

    private FragmentFlowtimeBinding binding;
    private long mTotalTime = 0;
    LinearLayout layout;
    boolean viewToggle = true;

    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FlowtimeViewModel flowtimeViewModel = new ViewModelProvider(this).get(FlowtimeViewModel.class);



        binding = FragmentFlowtimeBinding.inflate(inflater, container, false);
        layout = binding.flowtimeContainer;
        View root = binding.getRoot();

        chronometer = binding.chronometer;
        chronometer.setFormat("%s");
        chronometer.setBase(SystemClock.elapsedRealtime());



        // Start button on click event
        binding.addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText name = binding.nameEdit;
                addCard(name.getText().toString());
                Log.i("TAGS", name.getText().toString());
            }
        });
        binding.startChronometer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(running){
                    binding.startChronometer.setText("Start");
                    pauseChronometer();
                } else{
                    binding.startChronometer.setText("Pause");
                    startChronometer(); // make a variable to store the index of the working/resting timers so It can re-start the correct timer
                }
            }
        });
        binding.resetChronometer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetChronometer();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void addCard(String name) {
        final View view = getLayoutInflater().inflate(R.layout.customcard, null);
        Button delete = view.findViewById(R.id.delete);


        TextView nameView = view.findViewById(R.id.name);

        nameView.setText(name);
        nameView.setTextColor(Color.parseColor("#000000"));


        nameView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (viewToggle){
                    nameView.setBackgroundColor(Color.parseColor("#3da6cc"));
                    viewToggle = false;
                } else{
                    nameView.setBackgroundColor(Color.parseColor("#ffffff"));
                    viewToggle = true;
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeView(view);
            }
        });

        layout.addView(view);
    }

    public void startChronometer() {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;

        }
    }
    public void pauseChronometer() {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

    public void resetChronometer() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }
}