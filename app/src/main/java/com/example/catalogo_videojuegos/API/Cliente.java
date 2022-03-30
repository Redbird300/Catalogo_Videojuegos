package com.example.catalogo_videojuegos.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cliente {

    private static final String BASE_URL = "https://redbird300.000webhostapp.com/android/";
    private static Cliente cliente;
    private static Retrofit retrofit = null;

    private Cliente(){

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized Cliente getInstance(){

        if(cliente == null){

            cliente = new Cliente();

        }
        return cliente;

    }

    public RegistroAPI getMyApi() {

        return retrofit.create(RegistroAPI.class);

    }

}