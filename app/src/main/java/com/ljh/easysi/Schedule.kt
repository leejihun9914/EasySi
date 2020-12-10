package com.ljh.easysi

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Schedule(
    var ClassName: String? = "",
    var Professor: String? = "",
    var Place: String? = "",
    var Day: String? = "",
    var StartTime: Int = 0,
    var EndTime: Int = 0,
    var Color: Int = 0
) {

}