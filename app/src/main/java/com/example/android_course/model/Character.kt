package com.example.android_course.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterResponse(
    @Json(name = "data")
    val data: CharacterData
)

@JsonClass(generateAdapter = true)
data class CharacterData(
    @Json(name = "results")
    val results: List<Character>
)

@JsonClass(generateAdapter = true)
data class Thumbnail(
    @Json(name = "path")
    val imageUrl: String,
    @Json(name = "extension")
    val imageExt: String
)

@JsonClass(generateAdapter = true)
data class Character(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "description")
    val description: String ="",
    @Json(name = "thumbnail")
    val thumbnail: Thumbnail = Thumbnail("", "")
){
    val fullImageUrl: String
        get()  = "${thumbnail.imageUrl}.${thumbnail.imageExt}".replace("http://", "https://")
}