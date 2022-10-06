package com.gusoft.trakyabilmuh.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.time.LocalDateTime

data class MessageModel(
    @SerializedName("id") @Expose val id: Long,
    @SerializedName("content") @Expose val content: String,
    @SerializedName("link") @Expose val link: String,
    @SerializedName("createdDateTime") @Expose val createdDateTime: String,
    @SerializedName("updatedDateTime") @Expose val updatedDateTime: String,
    @SerializedName("messageType") @Expose val messageType: String,
    @SerializedName("user") @Expose val user: UserModel,
    var expandable: Boolean,
) : Serializable
