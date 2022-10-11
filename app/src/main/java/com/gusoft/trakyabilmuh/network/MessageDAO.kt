package com.gusoft.trakyabilmuh.network

import com.gusoft.trakyabilmuh.model.MessageModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MessageDAO {

    @GET("/api/message")
    fun getMessages(@Query(value = "channelId", encoded = true) type: Long): Call<List<MessageModel>>

}