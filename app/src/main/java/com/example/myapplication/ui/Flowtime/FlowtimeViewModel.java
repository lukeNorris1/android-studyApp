package com.example.myapplication.ui.Flowtime;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FlowtimeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FlowtimeViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("This is flowtime fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}