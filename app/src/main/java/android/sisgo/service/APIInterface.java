package android.sisgo.service;

import android.sisgo.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {

    @FormUrlEncoded
    @POST("login")
    Call<UserResponse> doLogin(@Field("username") String name, @Field("password") String password);
}
