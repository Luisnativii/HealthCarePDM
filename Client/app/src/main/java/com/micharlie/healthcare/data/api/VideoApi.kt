package com.micharlie.healthcare.data.api

import com.google.gson.annotations.SerializedName
import com.micharlie.healthcare.utils.Constants

class VideoApi (
    @SerializedName(value = Constants.VIDEOBANER)
    val videoBanner: String? = "",

    @SerializedName(value = Constants.CHANNELNAME)
    val channelName: String? = "",

    @SerializedName(value = Constants.CHANNELPHOTO)
    val channelPhoto: String? = "",

    @SerializedName(value = Constants.VIDEONAME)
    val videoName: String? = "",

    @SerializedName(value = Constants.LINK)
    val link: String? = "",

    @SerializedName(value = Constants.CATEGORY)
    val category: String? = "",

    val createdAt: String? = "",

    val updatedAt: String? = "",

    ){
    override fun toString(): String {
        return "VideoApi(videoBanner=$videoBanner, channelName=$channelName, channelPhoto=$channelPhoto, videoName=$videoName, link=$link, category=$category, createdAt=$createdAt, updatedAt=$updatedAt)"
    }
}



data class CommentApi(
    @SerializedName("_id") val id: String? = "",
    @SerializedName("user") val user: String? = "",
    @SerializedName("userName") val userName: String? = "",
    @SerializedName("content") val content: String? = "",
    @SerializedName("date") val date: String? = "",
    @SerializedName("createdAt") val createdAt: String? = "",
    @SerializedName("updatedAt") val updatedAt: String? = "",
)
