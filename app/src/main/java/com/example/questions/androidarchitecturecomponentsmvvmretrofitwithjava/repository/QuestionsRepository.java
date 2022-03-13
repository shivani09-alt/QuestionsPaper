package com.example.questions.androidarchitecturecomponentsmvvmretrofitwithjava.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;


import com.example.questions.androidarchitecturecomponentsmvvmretrofitwithjava.response.QuestionsResponse;
import com.example.questions.androidarchitecturecomponentsmvvmretrofitwithjava.retrofit.ApiRequest;
import com.example.questions.androidarchitecturecomponentsmvvmretrofitwithjava.retrofit.RetrofitRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsRepository {
    private static final String TAG = QuestionsRepository.class.getSimpleName();
    private final ApiRequest apiRequest;

    public QuestionsRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public MutableLiveData<List<QuestionsResponse>> getList() {
        final MutableLiveData<List<QuestionsResponse>> data = new MutableLiveData<>();
        apiRequest.customerQuestionnaireMaster().enqueue(new Callback<List<QuestionsResponse>>() {


                    @Override
                    public void onResponse(@NonNull Call<List<QuestionsResponse>> call, @NonNull Response<List<QuestionsResponse>> response) {
                        Log.d(TAG, "onResponse response:: " + response);

                      List<QuestionsResponse> questionsResponse =response.body();

                        if (questionsResponse != null) {
                            data.postValue(questionsResponse);


                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<QuestionsResponse>> call, @NonNull Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}
