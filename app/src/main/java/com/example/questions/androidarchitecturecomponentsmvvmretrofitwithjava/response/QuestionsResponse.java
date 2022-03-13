package com.example.questions.androidarchitecturecomponentsmvvmretrofitwithjava.response;

import com.example.questions.androidarchitecturecomponentsmvvmretrofitwithjava.model.QuestionsList;
import com.google.gson.JsonArray;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionsResponse {
    @SerializedName("question_english")
    @Expose
    private  String question;
    @SerializedName("next_question_id")
    @Expose
    private  String nextQuestionId;
    @SerializedName("input_type")
    @Expose
    private  String inputType;
    @SerializedName("option")
    @Expose
    private JsonArray option;



    public String getQuestion() {
        return question;
    }



    public String getInputType() {
        return inputType;
    }


    public JsonArray getOption() {
        return option;
    }




}
