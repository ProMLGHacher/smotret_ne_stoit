package com.krea.kollege.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GeocodeHttpClientModule {
    @Provides
    @Singleton
    fun provide(): HttpClient {
        return HttpClient(CIO) {
            install(HttpCache)
            install(ContentNegotiation) {
                json(
                    Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
            defaultRequest{
                url("https://geocode-maps.yandex.ru/1.x?apikey=88ad1653-696f-43ff-9627-439ed49eafd8&format=json&kind=house")
            }
        }
    }
}