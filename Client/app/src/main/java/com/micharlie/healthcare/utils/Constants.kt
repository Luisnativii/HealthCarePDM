package com.micharlie.healthcare.utils

object Constants {
//T https://healthcare-api-production-60ea.up.railway.app
    // IP
    private const val IP_PORT = "https://healthcare-api-production-46a3.up.railway.app"
    //HTTP
    const val HTTP = "http://"
    //PORT
    const val PORT = "3500"
    //URL
    const val URL = "$IP_PORT"
    //api service(remplazar con la IP para local)
    const val BASE_URL = "$URL/api/"
    //Treaer data
    const val GETDATAUSER = "$URL/api/auth/data/"
    //login
    const val POSTLOGINBACEURL = "https://healthcare-api-production-46a3.up.railway.app/api/auth/login/"
    //update data
    const val UPDATE_DATA_USER = "$URL/api/auth/data/"
    //get video
    const val VIDEOBACEURLGET = "$URL/api/"
    //Register
    const val POSTUSERBACEURL = "auth/"
    //post
    const val POST_USER_PATH = "/"

    //coment
    //https://healthcare-api-production-46a3.up.railway.app/api/community/
    const val POST_COMMENT_PATH = "$URL/api/community/"
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



    //comets

    const val CONTENT = "content"
}