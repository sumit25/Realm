package com.realm.sumit.api;

import com.realm.sumit.dtos.RMTokenDTO;
import com.realm.sumit.dtos.RMUserResponse;
import com.realm.sumit.dtos.RmUserProfileResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by sumit on 26/07/17.
 */

public interface APIInterface {

    @FormUrlEncoded
    @POST("oauth/token.json")
    Call<RMTokenDTO> getAccessToken(@Field("grant_type") String grantType,
                                    @Field("scope") String scope,
                                    @Field("username") String username,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("oauth/token.json")
    Call<RMTokenDTO> refreshAccessToken(@Field("grant_type") String grantType,
                                        @Field("scope") String scope,
                                        @Field("refresh_token") String refreshToken);

    @GET("users/current.json")
    Call<RMUserResponse> getCurrentUser();



    @GET("companies/{companyId}/sq/users/{userId}/user_profile")
    Call<RmUserProfileResponse> getUserProfile(@Path("companyId") String companyId,
                                               @Path("userId") String userId,
                                               @QueryMap HashMap<String, String> queryMap);

    @GET("companies/{companyId}/sq/users/{userId}/user_profile")
    Call<RmUserProfileResponse> getUserProfile(@Path("companyId") String companyId,
                                               @Path("userId") String userId,
                                               @Query ("include[user_lessons][only][]") String q1,
                                               @Query ("include[user_lessons][include][lesson][only]") String q2,
                                               @Query ("include[user_lessons][only][]") String q3,
                                               @Query ("select[]") String q4,
                                               @Query ("select[]") String q5);
}
