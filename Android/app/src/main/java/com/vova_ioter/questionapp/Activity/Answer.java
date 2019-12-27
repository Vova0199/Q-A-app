package com.vova_ioter.questionapp.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.vova_ioter.questionapp.AnswerAdapter;
import com.vova_ioter.questionapp.Model.AnswerList;
import com.vova_ioter.questionapp.Model.AnswerModel;
import com.vova_ioter.questionapp.Api.ApiService;
import com.vova_ioter.questionapp.SendQuestionActivity;
import com.vova_ioter.questionapp.Utils.InternetConnection;
import com.vova_ioter.questionapp.R;
import com.vova_ioter.questionapp.Api.RetroClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Answer extends AppCompatActivity {
    private ListView listView;
    private View parentView;
FloatingActionButton myFab;
    private ArrayList<AnswerModel> contactList;
    private AnswerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        contactList = new ArrayList<>();

        parentView = findViewById(R.id.parentLayout);
        listView = (ListView) findViewById(R.id.listView);

        myFab = findViewById(R.id.floatingActionButton);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Answer.this, SendQuestionActivity.class);
                startActivity(i);
            }
        });
        refresh();


    }

    public  void refresh(){

        if (InternetConnection.checkConnection(getApplicationContext())) {
            ApiService api = RetroClient.getApiService();

            Call<AnswerList> call = api.getMyJSON();

            call.enqueue(new Callback<AnswerList>() {
                @Override
                public void onResponse(Call<AnswerList> call, Response<AnswerList> response) {
                    //Dismiss Dialog

                    if (response.isSuccessful()) {
                        contactList = response.body().getAnswer();

                        adapter = new AnswerAdapter(Answer.this, contactList);
                        listView.setAdapter(adapter);

                    } else {
                        System.out.print("Pizso");
                    }
                }

                @Override
                public void onFailure(Call<AnswerList> call, Throwable t) {
                    System.out.print("Pizso");
                }
            });

        } else {
            System.out.print("Pizso");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }
}