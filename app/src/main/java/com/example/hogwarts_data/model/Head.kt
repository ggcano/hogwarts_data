package com.example.hogwarts_data.model


import com.google.gson.annotations.SerializedName

data class Head(
    @SerializedName("firstName")
    var firstName: String,
    @SerializedName("id")
    var id: String,
    @SerializedName("lastName")
    var lastName: String
)