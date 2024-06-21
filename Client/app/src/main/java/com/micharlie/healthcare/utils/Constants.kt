package com.micharlie.healthcare.utils

object Constants {

    //api service(remplazar con la IP para local)
    const val BASE_URL = "http://192.168.5.199:3500/api/"


    //post
    const val POST_USER_PATH = "/"

    //URL completa para hacer registro
    const val POSTUSERBACEURL = "http://192.168.5.199:3500/api/auth/"

    //URL completa para ahcer login
    const val GETDATAUSER = "http://192.168.31.58:3500/api/auth/data/"
    const val POSTLOGINBACEURL = "http://192.168.31.58:3500/api/auth/login/"
    const val NAME = "name"
    const val EMAIL = "email"
    const val PASSWORD = "password"
    const val GENDER = "gender"
    const val DATEBIRTH = "dateBirth"

    const val DATA = "data"
    const val HEIGHT = "height"
    const val WEIGHT = "weight"
    const val MUSCULARMASS = "muscularMass"
    const val BODYFAT = "bodyFat"
    const val CHOLESTEROL = "cholesterol"
    const val BLOODGLUCOSE = "bloodGlucose"
    const val BLOODPRESSURE = "bloodPressure"
    const val DATE = "date"

    const val UPDATE_DATA_USER = "http://192.168.31.58:3500/api/auth/data/"




    const val VIDEOBACEURLGET = "http://192.168.31.58:3500/api/"

    const val VIDEOBACEURLGET = "http://192.168.5.199:3500/api/"

    const val VIDEOGET = "video"
    const val VIDEOBANER = "videoBanner"
    const val CHANNELNAME = "channelName"
    const val CHANNELPHOTO = "channelPhoto"
    const val VIDEONAME = "videoName"
    const val LINK = "link"
    const val CATEGORY = "category"


    //API response

    const val RESPONSE_SUCCESSFUL = "result"
    const val RESPONSE_ERROR = "message"

}