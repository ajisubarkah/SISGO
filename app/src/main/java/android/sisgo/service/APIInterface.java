package android.sisgo.service;

import android.sisgo.model.GoodDetailResponse;
import android.sisgo.model.GoodItem;
import android.sisgo.model.GoodResponse;
import android.sisgo.model.RestockResponse;
import android.sisgo.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {

    @FormUrlEncoded
    @POST("login")
    Call<UserResponse> doLogin(@Field("username") String name, @Field("password") String password);

    @GET("restock/listrestock")
    Call<RestockResponse> getRestock();

    @GET("good/listgoods")
    Call<GoodResponse> getGoods();

    @FormUrlEncoded
    @POST("good/detailgood")
    Call<GoodDetailResponse> getDetailGood(@Field("id") String id);
}
