package com.red_velvet_cake.dailytodo.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamTodo(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("assignee") val assignee: String?,
    @SerializedName("status") val status: Int,
    @SerializedName("creationTime") val creationTime: String
): Parcelable
