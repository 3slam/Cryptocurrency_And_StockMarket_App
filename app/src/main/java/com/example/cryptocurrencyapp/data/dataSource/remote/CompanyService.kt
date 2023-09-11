package com.example.cryptocurrencyapp.data.dataSource.remote

import com.example.cryptocurrencyapp.common.API_KEY_FOR_COMPANY
import com.example.cryptocurrencyapp.domain.entity.CompanyInfo
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface CompanyService {

    @GET("query?function=LISTING_STATUS&datatype=csv")
    suspend fun getCompanies(
        @Query("apikey") apiKey: String = API_KEY_FOR_COMPANY
    ): ResponseBody



    @GET("query?function=OVERVIEW")
    suspend fun getLCompanyInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY_FOR_COMPANY
    ): CompanyInfo

    @GET("query?function=TIME_SERIES_INTRADAY&interval=60min&datatype=csv")
    suspend fun getCompanyIntradayInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY_FOR_COMPANY
    ): ResponseBody


}