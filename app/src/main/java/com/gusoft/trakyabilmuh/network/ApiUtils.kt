package com.gusoft.trakyabilmuh.network

class ApiUtils {

    companion object {
        private const val BASE_URL = "http://192.168.0.10:8080"

        fun getAnnouncementsDAO(): AnnouncementDAO {
            return RetrofitClient.getClient(BASE_URL).create(AnnouncementDAO::class.java)
        }
    }
}