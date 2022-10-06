package com.gusoft.trakyabilmuh.model

import kotlinx.parcelize.Parcelize
import android.os.Parcel
import android.os.Parcelable

@Parcelize
enum class MessageTypes : Parcelable {
    FIRST_GRADE, SECOND_GRADE, THIRD_GRADE, FOURTH_GRADE, JOB, INTERNSHIP, ANNOUNCEMENT, PRIVATE
}