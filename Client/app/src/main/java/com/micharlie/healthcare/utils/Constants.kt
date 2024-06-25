package com.micharlie.healthcare.utils

object Constants {

    // IP
    private const val IP_PORT = "192.168.31.58"
    //HTTP
    const val HTTP = "http://"
    //PORT
    const val PORT = "3500"
    //URL
    const val URL = "$HTTP$IP_PORT:$PORT"
    //api service(remplazar con la IP para local)
    const val BASE_URL = "$URL/api/"
    //Treaer data
    const val GETDATAUSER = "$URL/api/auth/data/"
    //login
    const val POSTLOGINBACEURL = "$URL/api/auth/login/"
    //update data
    const val UPDATE_DATA_USER = "$URL/api/auth/data/"
    //get video
    const val VIDEOBACEURLGET = "$URL/api/"
    //Register
    const val POSTUSERBACEURL = "auth/"
    //post
    const val POST_USER_PATH = "/"


    //User
    const val NAME = "name"
    const val EMAIL = "email"
    const val PASSWORD = "password"
    const val GENDER = "gender"
    const val DATEBIRTH = "dateBirth"

    //DataUser
    const val DATA = "data"
    const val HEIGHT = "height"
    const val WEIGHT = "weight"
    const val MUSCULARMASS = "muscularMass"
    const val BODYFAT = "bodyFat"
    const val CHOLESTEROL = "cholesterol"
    const val BLOODGLUCOSE = "bloodGlucose"
    const val BLOODPRESSURE = "bloodPressure"
    const val DATE = "date"


// video
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