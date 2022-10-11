package com.gusoft.trakyabilmuh.network

import com.gusoft.trakyabilmuh.model.ChannelModel
import retrofit2.Call
import retrofit2.http.GET

interface ChannelDAO {

    @GET("/api/channel")
    fun getChannels(): Call<List<ChannelModel>>

}