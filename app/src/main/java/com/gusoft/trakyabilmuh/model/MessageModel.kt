package com.gusoft.trakyabilmuh.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.time.LocalDateTime

data class MessageModel(
    @SerializedName("id") @Expose val id: Long,
): Serializable
