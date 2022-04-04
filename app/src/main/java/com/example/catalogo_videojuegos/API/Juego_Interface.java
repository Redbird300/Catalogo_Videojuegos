package com.example.catalogo_videojuegos.API;

import com.google.gson.annotations.SerializedName;

public class Juego_Interface {
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("estudio")
    private String estudio;
    @SerializedName("plataforma")
    private String plataforma;
    @SerializedName("clasi")
    private String clasi;
    @SerializedName("fechalan")
    private String fechalan;

    public String getNombre() {
        return nombre;
    }

    public String getEstudio() {
        return estudio;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public String getClasi() {
        return clasi;
    }

    public String getFechalan() {
        return fechalan;
    }
}
