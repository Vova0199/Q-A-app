package com.vova_ioter.questionapp.Model;

/**
 * Created by Vova0199 on 07.05.2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnswerModel {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("answer")
    @Expose
    private String answer;

    public AnswerModel(String email, String name, String phone, String surname, String question) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.surname = surname;
        this.question = question;
    }

    @SerializedName("question")
    @Expose

    private String question;

    public AnswerModel() {

    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", surname=" + surname +
                '}';
    }
}