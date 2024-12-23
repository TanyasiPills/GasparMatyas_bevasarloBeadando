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
    @GET("Uo29zQ/termekek")
    Call<List<Termekek>> getTermek();

    @GET("Uo29zQ/termekek/{id}")
    Call <Termekek> getTermekById(@Path("id") int id);

    @POST("Uo29zQ/termekek")
    Call<Termekek> createTermek(@Body Termekek termek);

    @PUT("Uo29zQ/termekek/{id}")
    Call<Termekek> updateTermek(@Path("id") int id, @Body Termekek termek);

    @DELETE("Uo29zQ/termekek/{id}")
    Call<Void> deleteTermek(@Path("id") int id);
}
