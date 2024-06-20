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
)
