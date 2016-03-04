package com.example.dam.actividadesrestretrofit.api;


import com.example.dam.actividadesrestretrofit.pojo.Actividad;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

public interface ApiActividad {
    @POST("/restful/api/actividad")
    Call<Actividad> crearActividad(@Body Actividad actividad);
    @GET("restful/api/actividad/Javier")
    Call<List<Actividad>> getActividades();
}
