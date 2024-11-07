package com.example.android_course.network

import com.example.android_course.BuildConfig
import com.example.android_course.model.CharacterResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Path
import retrofit2.http.Query
import java.security.MessageDigest

@OptIn(ExperimentalStdlibApi::class)
fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(this.toByteArray())
    return digest.toHexString()
}

interface MarvelApiService {
    @GET("characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int = 15,
        @Query("offset") offset: Int = 20
    ): CharacterResponse

    @GET("characters/{characterId}")
    suspend fun getCharacter(
        @Path("characterId") characterId: Int
    ): CharacterResponse
}

object RequestInterceptor : Interceptor {
    private val public_key = BuildConfig.MARVEL_API_PUBLIC_KEY
    private val private_key = BuildConfig.MARVEL_API_PRIVATE_KEY
    private val ts = "1"
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newUrl = originalRequest.url.newBuilder()
            .addQueryParameter("apikey", public_key)
            .addQueryParameter("hash", (ts.toString() + private_key + public_key).md5())
            .addQueryParameter("ts", ts)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        println(newRequest.url)

        return chain.proceed(newRequest)
    }
}

private val okHttpClient = OkHttpClient()
    .newBuilder()
    .addInterceptor(RequestInterceptor)
    .build()

private const val BASE_URL =
    "https://gateway.marvel.com/v1/public/"

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object MarvelApi {
    val retrofitService : MarvelApiService by lazy {
        retrofit.create(MarvelApiService::class.java)
    }
}