package com.fernando.movilepaytest.feature.main.model.widget

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Widgets(
    @SerializedName("widgets")
    val widgetList: List<Widget>
): Parcelable

@Parcelize
data class Widget(
    val identifier: String? = "",
    val content: WidgetContent,
): Parcelable