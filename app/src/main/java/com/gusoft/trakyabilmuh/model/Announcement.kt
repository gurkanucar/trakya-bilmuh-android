package com.gusoft.trakyabilmuh.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.time.LocalDateTime

data class Announcement(
    @SerializedName("id") @Expose val id: Long,
    @SerializedName("content") @Expose val content: String,
    @SerializedName("link") @Expose val link: String,
    @SerializedName("title") @Expose val title: String,
    @SerializedName("createdDateTime") @Expose val createdDateTime: String,
    ): Serializable