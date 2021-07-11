package com.fernando.movilepaytest.feature.statement.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Statement(
    @SerializedName("balance")
    val balance: Balance,
    @SerializedName("transactions")
    val transactions: List<Transactions>
) : Parcelable

@Parcelize
data class Balance(
    @SerializedName("label")
    val label: String,
    @SerializedName("value")
    val value: String
) : Parcelable

@Parcelize
data class Transactions(
    @SerializedName("label")
    val label: String,
    @SerializedName("value")
    val value: String,
    @SerializedName("description")
    val description: String
) : Parcelable