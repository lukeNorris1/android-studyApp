package com.example.myapplication.ui.Custom;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CustomViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CustomViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is custom fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}