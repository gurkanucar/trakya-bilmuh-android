package com.gusoft.trakyabilmuh.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class ChannelModel(
    @SerializedName("id") @Expose val id: Long,
    @SerializedName("channelName") @Expose val channelName: String,
    @SerializedName("channelTopic") @Expose val channelTopic: String,
    @SerializedName("channelImageUrl") @Expose val channelImageUrl: String,
    @SerializedName("createdDateTime") @Expose val createdDateTime: String,
    @SerializedName("updatedDateTime") @Expose val updatedDateTime: String,
    @SerializedName("user") @Expose val user: UserModel,
    var isSubscribed: Boolean,
) : Serializable
