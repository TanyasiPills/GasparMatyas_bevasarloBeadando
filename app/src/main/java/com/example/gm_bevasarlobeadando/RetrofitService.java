package com.example.gm_bevasarlobeadando;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
public interface RetrofitService {
    @GET("e9gd95/termekek")
    Call<List<Termekek>> getTermek();

    @GET("e9gd95/termekek/{id}")
    Call <Termekek> getTermekById(@Path("id") int id);

    @POST("e9gd95/termekek")
    Call<Termekek> createTermek(@Body Termekek termek);

    @PUT("e9gd95/termekek/{id}")
    Call<Termekek> updateTermek(@Path("id") int id, @Body Termekek termek);

    @DELETE("e9gd95/termekek/{id}")
    Call<Void> deleteTermek(@Path("id") int id);
}
