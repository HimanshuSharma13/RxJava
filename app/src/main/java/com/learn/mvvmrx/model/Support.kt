package com.learn.mvvmrx.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//import javax.annotation.Generated;
class Support {
    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("text")
    @Expose
    var text: String? = null
}