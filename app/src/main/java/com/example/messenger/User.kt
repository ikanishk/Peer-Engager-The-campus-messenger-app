package com.example.messenger

import android.net.Uri

data class User (
    val name:String,
    val imageUrl: String,
    val thumbimage:String,
    val uid:String,
    val status:String,
    val onlineStatus:String,
    val deviceToken:String


) {
    constructor():this("","","","","","","")

    constructor(name: String,imageUrl: String,thumbimage: String,uid: String):this(
        name,
        imageUrl,
        thumbimage,
        uid,
        "Hey fellas!, I am on college Engager",
        System.currentTimeMillis().toString(),
        "")
}


