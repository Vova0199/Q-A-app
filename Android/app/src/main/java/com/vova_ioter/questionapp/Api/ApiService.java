package com.vova_ioter.questionapp.Api;

/**
 * Created by Vova0199 on 07.05.2018.
 */

import com.vova_ioter.questionapp.Model.AnswerList;
import com.vova_ioter.questionapp.Model.AnswerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    /*
    Retrofit get annotation with our URL
    And our method that will return us the List of ContactList
    */
    @GET("/api/news")
    Call<AnswerList> getMyJSON();

    @POST("/api/news")
    @FormUrlEncoded
    Call<AnswerModel> addQuestion(@Field("name") String name,
                                  @Field("surname") String surname,
                                  @Field("phone") String phone,
                                  @Field("email") String email,
                                  @Field("question") String question);
    @POST("/api/news")
    Call<AnswerModel> savePost(@Body AnswerModel post);
}