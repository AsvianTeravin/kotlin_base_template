package com.teravin.kotlin_base_template.ui.sample

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sample(
    @SerializedName("id")
    val id : String,

    @SerializedName("sample")
    val sample:String
):Parcelable