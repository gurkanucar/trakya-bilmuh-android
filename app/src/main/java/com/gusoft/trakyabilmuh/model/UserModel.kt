package com.gusoft.trakyabilmuh.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.time.LocalDateTime

data class UserModel(
    @SerializedName("id") @Expose val id: Long,
    @SerializedName("name") @Expose val name: String,
    @SerializedName("surname") @Expose val surname: String,
    @SerializedName("profileImageUrl") @Expose val profileImageUrl: String,
): Serializable
