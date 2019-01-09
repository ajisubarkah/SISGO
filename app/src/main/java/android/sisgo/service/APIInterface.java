package android.sisgo.service;

import android.sisgo.model.GoodDetailResponse;
import android.sisgo.model.GoodItem;
import android.sisgo.model.GoodResponse;
import android.sisgo.model.HistoryResponse;
import android.sisgo.model.InsertResponse;
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

    @GET("good/listgoods")
    Call<GoodResponse> getAllGoods();

    @FormUrlEncoded
    @POST("good/detailgood")
    Call<GoodDetailResponse> getDetailGood(@Field("id") String id);

    @GET("good/restock/listrestock")
    Call<RestockResponse> getRestock();

    @FormUrlEncoded
    @POST("good/restock/insertrestock")
    Call<InsertResponse> setRestock(@Field("user_id") String id);

    @FormUrlEncoded
    @POST("good/stock/liststock")
    Call<HistoryResponse> getGoodsStockHistory(@Field("id") String id);

    @FormUrlEncoded
    @POST("good/stock/insertstock")
    Call<InsertResponse> setGoodsStock(@Field("restock_id") String idRestock,
                                        @Field("barcode") String barcode,
                                        @Field("add_stock") String addStock);
}
