package com.example.cryptocurrencyapp.di


import com.example.cryptocurrencyapp.common.BASE_URL_FOR_COINS
import com.example.cryptocurrencyapp.common.BASE_URL_FOR_COMPANY
import com.example.cryptocurrencyapp.data.dataSource.remote.CoinService
import com.example.cryptocurrencyapp.data.dataSource.remote.CompanyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttp():OkHttpClient {
       return OkHttpClient.Builder()
           .connectTimeout(20, TimeUnit.SECONDS)
           .readTimeout(20, TimeUnit.SECONDS)
           .build()

    }

    @Provides
    @Singleton
    @Named("provideRetrofitForCoins")
    fun provideRetrofitForCoins(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL_FOR_COINS)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiServiceForCoin(@Named("provideRetrofitForCoins") retrofit: Retrofit): CoinService {
        return  retrofit.create(CoinService::class.java)
    }


    @Provides
    @Singleton
    @Named("provideRetrofitForCompanies")
    fun provideRetrofitForCompanies(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL_FOR_COMPANY)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiServiceCompanies(@Named("provideRetrofitForCompanies") retrofit: Retrofit): CompanyService {
        return  retrofit.create(CompanyService::class.java)
    }



}