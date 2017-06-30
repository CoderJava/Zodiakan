package com.ysn.zodiakan.api

import com.ysn.zodiakan.internal.model.zodiak.Zodiak
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by root on 30/06/17.
 */
interface ApiService {

    @GET("macros/exec")
    fun getZodiak(
            @Query("service") service: String,
            @Query("nama") nama: String,
            @Query("tanggal") tanggal: String
    ): Call<Zodiak>

}