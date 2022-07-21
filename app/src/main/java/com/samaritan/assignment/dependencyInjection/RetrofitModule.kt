package com.samaritan.assignment.dependencyInjection

import com.samaritan.assignment.model.api.PokemonApi
import com.samaritan.assignment.repository.DefaultMainRepository
import com.samaritan.assignment.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


private const val BASE_URL = "https://pokeapi.co/api/v2/"
const val API_KEY = "028a1f1f57c61cfd6d7038d5fe9013b3"

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule{

    @Singleton
    @Provides
    fun provideKanyeWestApi():PokemonApi {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(loggingInterceptor)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(PokemonApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(pokemonApi: PokemonApi) : MainRepository = DefaultMainRepository(pokemonApi)

}