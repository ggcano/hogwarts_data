package com.example.hogwarts_data.model


import com.google.gson.annotations.SerializedName

data class Trait(
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String
)