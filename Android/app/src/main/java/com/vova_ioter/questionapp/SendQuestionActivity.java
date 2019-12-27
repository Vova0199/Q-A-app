package com.vova_ioter.questionapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vova_ioter.questionapp.Api.ApiService;
import com.vova_ioter.questionapp.Api.RetroClient;
import com.vova_ioter.questionapp.Model.AnswerList;
import com.vova_ioter.questionapp.Model.AnswerModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static junit.framework.Assert.assertTrue;

public class SendQuestionActivity extends AppCompatActivity {

    EditText et_name, et_surname, et_phone, et_email, et_quest;
    Button save;
    String name, surname, phone, email, question;
    ApiService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_question);
        init();
        mAPIService = RetroClient.getApiService();
        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (et_name.getText().toString().trim().equals("") ||
                                et_surname.getText().toString().trim().equals("")||
                                et_email.getText().toString().trim().equals("")||
                                et_phone.getText().toString().trim().equals("")||
                                et_quest.getText().toString().trim().equals("")) {
                            Toast.makeText(getApplicationContext(), "Заполните все поля", Toast.LENGTH_LONG).show();
                        } else {
                            send();
                            et_name.setText("");
                            et_surname.setText("");
                            et_phone.setText("");
                            et_email.setText("");
                            et_quest.setText("");
                        }
                    }
                });

        findViewById(R.id.relativeLayout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        });

    }

    public void send() {
        AnswerModel res = new AnswerModel();

        name = et_name.getText().toString();
        surname = et_surname.getText().toString();
        phone = et_phone.getText().toString();
        email = et_email.getText().toString();
        question = et_quest.getText().toString();

        res.setQuestion(question);
        res.setEmail(email);
        res.setPhone(phone);
        res.setName(name);
        res.setSurname(surname);

        Call<AnswerModel> val = mAPIService.savePost(res);
        val.enqueue(new Callback<AnswerModel>() {
            @Override
            public void onResponse(Call<AnswerModel> call, Response<AnswerModel> response) {
                AnswerModel res = response.body();
                Toast.makeText(SendQuestionActivity.this, "Norm", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<AnswerModel> call, Throwable t) {
//                Toast.makeText(SendQuestionActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void init() {
        et_name = findViewById(R.id.name);
        et_surname = findViewById(R.id.surname);
        et_phone = findViewById(R.id.phone);
        et_email = findViewById(R.id.email);
        et_quest = findViewById(R.id.question);
        save = findViewById(R.id.save);
    }
}
