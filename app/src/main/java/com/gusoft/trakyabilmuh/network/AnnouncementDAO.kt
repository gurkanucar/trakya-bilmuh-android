package com.gusoft.trakyabilmuh.network

import com.gusoft.trakyabilmuh.model.Announcement
import retrofit2.Call
import retrofit2.http.GET

interface AnnouncementDAO {

    @GET("/api/announcement")
    fun getAnnouncements(): Call<List<Announcement>>

}