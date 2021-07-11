package com.fernando.creditcard.feature.home.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CreditCardInfo(
    @SerializedName("cardNumber")
    val cardNumber: String?,
    @SerializedName("cardName")
    val cardName: String?,
    @SerializedName("expirationDate")
    val expirationDate: String?,
    @SerializedName("availableLimit")
    val availableLimit: String?,
    @SerializedName("totalLimit")
    val totalLimit: String?
) : Parcelable
