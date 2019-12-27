package com.vova_ioter.questionapp.Model;

/**
 * Created by Vova0199 on 07.05.2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vova_ioter.questionapp.Model.AnswerModel;

import java.util.ArrayList;

public class AnswerList {

    @SerializedName("answer")
    @Expose
    private ArrayList<AnswerModel> answer = new ArrayList<>();

    public ArrayList<AnswerModel> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<AnswerModel> answer) {
        this.answer = answer;
    }
}