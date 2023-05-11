package com.example.hogwarts_data.model


import com.google.gson.annotations.SerializedName

data class HousesItem(
    @SerializedName("animal")
    var animal: String,
    @SerializedName("commonRoom")
    var commonRoom: String,
    @SerializedName("element")
    var element: String,
    @SerializedName("founder")
    var founder: String,
    @SerializedName("ghost")
    var ghost: String,
    @SerializedName("heads")
    var heads: List<Head>,
    @SerializedName("houseColours")
    var houseColours: String,
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("traits")
    var traits: List<Trait>
)