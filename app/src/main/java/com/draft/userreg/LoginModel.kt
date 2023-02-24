package com.draft.userreg

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize;


data class LoginModel (

    var name:String,
    var password:String,
    var username:String

) : java.io.Serializable