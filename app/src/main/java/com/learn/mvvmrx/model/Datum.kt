package com.learn.mvvmrx.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//import javax.annotation.Generated;
class Datum {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @JvmField
    @SerializedName("email")
    @Expose
    var email: String? = null

    @JvmField
    @SerializedName("first_name")
    @Expose
    var firstName: String? = null

    @JvmField
    @SerializedName("last_name")
    @Expose
    var lastName: String? = null

    @JvmField
    @SerializedName("avatar")
    @Expose
    var avatar: String? = null
}