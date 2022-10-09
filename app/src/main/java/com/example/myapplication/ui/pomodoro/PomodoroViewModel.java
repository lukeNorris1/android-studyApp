package com.example.myapplication.ui.pomodoro;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PomodoroViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PomodoroViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("00:00");
    }

    public LiveData<String> getText() {
        return mText;
    }
}