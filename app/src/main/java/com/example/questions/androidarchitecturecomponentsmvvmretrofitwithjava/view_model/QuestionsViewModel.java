package com.example.questions.androidarchitecturecomponentsmvvmretrofitwithjava.view_model;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.questions.androidarchitecturecomponentsmvvmretrofitwithjava.repository.QuestionsRepository;
import com.example.questions.androidarchitecturecomponentsmvvmretrofitwithjava.response.QuestionsResponse;

import java.util.List;


public class QuestionsViewModel extends AndroidViewModel {


    private final MutableLiveData<List<QuestionsResponse>> articleResponseLiveData;

    public QuestionsViewModel(@NonNull Application application) {
        super(application);

        QuestionsRepository questionsRepository = new QuestionsRepository();
        this.articleResponseLiveData = questionsRepository.getList();
    }

    public MutableLiveData<List<QuestionsResponse>> getResponseLiveData() {
        return articleResponseLiveData;
    }
}
