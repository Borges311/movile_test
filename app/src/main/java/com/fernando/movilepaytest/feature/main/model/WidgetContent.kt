package com.fernando.movilepaytest.feature.main.model.widget

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class WidgetContent(
    @SerializedName("title")
    val title: String? = "",
    @SerializedName(value ="cardNumber")
    val cardNumber: String? = "",
    @SerializedName("balance")
    val balance: Balance?,
    @SerializedName("button")
    val button: ButtonWidget? = null,
): Parcelable

@Parcelize
data class Balance(
    @SerializedName("label")
    var label: String,
    @SerializedName("value")
    var value: String
) : Parcelable

@Parcelize
data class ButtonWidget (
    @SerializedName("text")
    val text: String? = "",
    val action: WidgetAction?
) : Parcelable

@Parcelize
data class WidgetAction(
    @SerializedName("identifier")
    val identifier: String? = "",
    val content: ContentWidgetAction): Parcelable

@Parcelize
data class ContentWidgetAction(
    @SerializedName(value="cardId", alternate = ["accountId"])
    val typeId: String? = ""
) : Parcelable