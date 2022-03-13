package com.example.questions.androidarchitecturecomponentsmvvmretrofitwithjava.retrofit;


import com.example.questions.androidarchitecturecomponentsmvvmretrofitwithjava.response.QuestionsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequest {

    @GET("customerQuestionnaireMaster")
    Call<List<QuestionsResponse>>customerQuestionnaireMaster();

}
