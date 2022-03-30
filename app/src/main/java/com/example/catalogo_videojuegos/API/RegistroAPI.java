package com.example.catalogo_videojuegos.API;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RegistroAPI {
    @FormUrlEncoded
    @POST("Insertar.php")
    public Call<ResponseBody> Juego(

            @Field("nombre") String nombre,
            @Field("estudio") String estudio,
            @Field("plataforma") int plataforma,
            @Field("clasi") int clasificacion,
            @Field("fechalan") String fechalan);

    @GET("Consultar.php")
    Call<List<Juego_Interface>> getJuegos();
}

