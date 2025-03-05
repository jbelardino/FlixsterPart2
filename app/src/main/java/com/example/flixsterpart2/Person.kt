package com.example.flixsterpart2

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class SearchPersonResponse(
    @SerialName("results")
    val results: List<Person>?
)

@Keep
@Serializable
data class Person (
    @SerialName("name")
    var name: String?,

    @SerialName("profile_path")
    var profile_path: String?,

    @SerialName("known_for")
    var known_for: List<KnownFor>?
) : java.io.Serializable

@Keep
@Serializable
data class KnownFor(
    @SerialName("backdrop_path")
    var backdrop_path: String?,

    @SerialName("title")
    var title: String?,

    @SerialName("overview")
    var overview: String?,

    @SerialName("poster_path")
    var poster_path: String?
) : java.io.Serializable


//import android.support.annotation.Keep
//import kotlinx.serialization.SerialName
//import kotlinx.serialization.Serializable
//
//@Keep
//@Serializable
//data class SearchNewsResponse(
//    @SerialName("response")
//    val response: BaseResponse?
//)
//
//@Keep
//@Serializable
//data class BaseResponse(
//    @SerialName("docs")
//    val docs: List<Article>?
//)
//
//@Keep
//@Serializable
//data class Article(
//    @SerialName("abstract")
//    val abstract: String?,
//    @SerialName("byline")
//    val byline: Byline?,
//    @SerialName("headline")
//    val headline: HeadLine?,
//    @SerialName("multimedia")
//    val multimedia: List<MultiMedia>?,
//) : java.io.Serializable {
//    val mediaImageUrl = "https://www.nytimes.com/${multimedia?.firstOrNull { it.url != null }?.url ?: ""}"
//}
//
//@Keep
//@Serializable
//data class HeadLine(
//    @SerialName("main")
//    val main: String
//) : java.io.Serializable
//
//@Keep
//@Serializable
//data class Byline(
//    @SerialName("original")
//    val original: String? = null
//) : java.io.Serializable
//
//@Keep
//@Serializable
//data class MultiMedia(
//    @SerialName("url")
//    val url: String?
//) : java.io.Serializable