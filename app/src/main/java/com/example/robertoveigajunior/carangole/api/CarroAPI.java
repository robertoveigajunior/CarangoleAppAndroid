package com.example.robertoveigajunior.carangole.api;

import com.example.robertoveigajunior.carangole.model.Carro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by robertoveigajunior on 19/11/16.
 */

public interface CarroAPI {
    @GET("/carros/tipo/{tipo}")
    Call<List<Carro>> findBy(@Path("tipo") String tipo);

}
