package com.gusoft.trakyabilmuh.network

class ApiUtils {

    companion object {
        private const val BASE_URL = "http://192.168.0.16:8080"

        fun getAnnouncementsDAO(): AnnouncementDAO {
            return RetrofitClient.getClient(BASE_URL).create(AnnouncementDAO::class.java)
        }


        fun getMessageDAO(): MessageDAO {
            return RetrofitClient.getClient(BASE_URL).create(MessageDAO::class.java)
        }


        fun getChannelDAO(): ChannelDAO {
            return RetrofitClient.getClient(BASE_URL).create(ChannelDAO::class.java)
        }
    }
}