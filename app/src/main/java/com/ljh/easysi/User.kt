package com.ljh.easysi

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    var Email: String? = "",
    var UserName: String? = "",
    var Password: String? = ""
)
