package com.example.questions.androidarchitecturecomponentsmvvmretrofitwithjava.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;



import com.example.questions.R;

import com.example.questions.androidarchitecturecomponentsmvvmretrofitwithjava.response.QuestionsResponse;
import com.example.questions.androidarchitecturecomponentsmvvmretrofitwithjava.view_model.QuestionsViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;


public class MainActivity extends AppCompatActivity {


    private TextView questions;
    private RadioGroup radioGroup;
    private LinearLayout chechBoxLayout;
    private TextInputEditText number;
    int num=0;
    List<QuestionsResponse> list;
    QuestionsViewModel questionsViewModel;
    String inputType,questionsVal;
    JsonArray option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();

        getMovieArticles();
    }

    private void initialization() {
        questions=findViewById(R.id.questions);
        radioGroup=findViewById(R.id.radioGroup);
        chechBoxLayout=findViewById(R.id.chechBoxLayout);
        number=findViewById(R.id.number);
        Button previous = findViewById(R.id.previous);
        Button next = findViewById(R.id.next);
        previous.setOnClickListener(view -> {
            num--;
            if(num<0){
                num=0;
            }
            if(num>=0 && num<=list.size()-1) {

                radioGroup.setVisibility(View.GONE);
                radioGroup.removeAllViews();
                chechBoxLayout.removeAllViews();
                chechBoxLayout.setVisibility(View.GONE);
                number.setVisibility(View.GONE);
                inputType = list.get(num).getInputType();
                questionsVal = list.get(num).getQuestion();
                option = list.get(num).getOption();
                questions.setText(questionsVal);
                if (inputType.equals("option")) {
                    chechBoxLayout.setVisibility(View.VISIBLE);
                    for (int i = 0; i < option.size(); i++) {
                        JsonObject jsonObject = (JsonObject) option.get(i);
                        CheckBox checkBox = new CheckBox(this);
                        checkBox.setText(jsonObject.get("option_value").toString().replaceAll("\"", ""));
                        chechBoxLayout.addView(checkBox);

                    }
                } else if (inputType.equals("radio")) {
                    radioGroup.setVisibility(View.VISIBLE);
                    for (int i = 0; i < option.size(); i++) {
                        JsonObject jsonObject = (JsonObject) option.get(i);
                        RadioButton radioButton = new RadioButton(this);
                        radioButton.setText(jsonObject.get("option_value").toString().replaceAll("\"", ""));
                        radioGroup.addView(radioButton);

                    }

                } else {
                    number.setVisibility(View.VISIBLE);
                }
            }

        });
        next.setOnClickListener(view -> {
            num++;
            if(num>list.size()-1)
            {
                num=list.size()-1;
            }

            if(num<=list.size()-1 && num>=0) {

                chechBoxLayout.setVisibility(View.GONE);
                number.setVisibility(View.GONE);
                chechBoxLayout.removeAllViews();
                radioGroup.removeAllViews();
                inputType = list.get(num).getInputType();
                questionsVal = list.get(num).getQuestion();
                option = list.get(num).getOption();
                questions.setText(questionsVal);
                if (inputType.equals("option")) {
                    chechBoxLayout.setVisibility(View.VISIBLE);
                    for (int i = 0; i < option.size(); i++) {
                        JsonObject jsonObject = (JsonObject) option.get(i);
                        CheckBox checkBox = new CheckBox(this);
                        checkBox.setText(jsonObject.get("option_value").toString().replaceAll("\"", ""));
                        chechBoxLayout.addView(checkBox);


                    }
                } else if (inputType.equals("radio")) {
                    radioGroup.setVisibility(View.VISIBLE);
                    for (int i = 0; i < option.size(); i++) {
                        JsonObject jsonObject = (JsonObject) option.get(i);
                        RadioButton radioButton = new RadioButton(this);
                        radioButton.setText(jsonObject.get("option_value").toString().replaceAll("\"", ""));
                        radioGroup.addView(radioButton);

                    }

                } else {
                    number.setVisibility(View.VISIBLE);
                }
            }

        });

        questionsViewModel = ViewModelProviders.of(this).get(QuestionsViewModel.class);
    }


    private void getMovieArticles() {
        questionsViewModel.getResponseLiveData().observe(this, articleResponse -> {
            if (articleResponse != null) {

                 list=articleResponse;
                 inputType = articleResponse.get(num).getInputType();
                 questionsVal=articleResponse.get(num).getQuestion();
                 option=articleResponse.get(num).getOption();
                 questions.setText(questionsVal);
                 if(inputType.equals("option")){
                     chechBoxLayout.setVisibility(View.VISIBLE);
                     for(int i=0;i<option.size();i++){
                         JsonObject jsonObject=(JsonObject) option.get(i);
                         CheckBox checkBox=new CheckBox(this);
                         checkBox.setText(jsonObject.get("option_value").toString().replaceAll("\"", ""));
                         chechBoxLayout.addView(checkBox);

                     }
                 }
                 else if(inputType.equals("radio")){
                     radioGroup.setVisibility(View.VISIBLE);
                     for(int i=0;i<option.size();i++){
                         JsonObject jsonObject=(JsonObject) option.get(i);
                         RadioButton radioButton=new RadioButton(this);
                         radioButton.setText(jsonObject.get("option_value").toString().replaceAll("\"", ""));
                         radioGroup.addView(radioButton);

                     }

                 }
                 else{
                     number.setVisibility(View.VISIBLE);
                 }
                num++;
            }
        });
    }
}
