package com.johntsai.superv2ex.api;

import com.johntsai.superv2ex.data.Topic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface V2exService {

    @GET("topics/hot.json")
    Call<List<Topic>> hotTopic();

    @GET("topics/latest.json")
    Call<List<Topic>> latestTopic();

    @GET("show.json")
    Call nodeInfo(@Query("name") String nodeName);

    @GET("members/show.json")
    Call userPage(@Query("username") String userName, @Query("id") Integer id);

}
