package com.example.cryptocurrencyapp.data.dataSource.csv

import android.util.Log
import com.example.cryptocurrencyapp.domain.entity.Company
import com.example.cryptocurrencyapp.domain.entity.CompanyIntradayChartInfo
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.log



object CompanyIntradayInfoParse : CSVParser<CompanyIntradayChartInfo> {

    override suspend fun parse(stream: InputStream): List<CompanyIntradayChartInfo> {

        val csvReader = CSVReader(InputStreamReader(stream))

        return withContext(Dispatchers.IO) {
            csvReader.readAll().drop(1).mapNotNull { line ->

                val timestamp = line.getOrNull(0) ?: ""
//
                val pattern = "yyyy-MM-dd HH:mm:ss"
                val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
                val localDateTime = LocalDateTime.parse(timestamp, formatter)
                
                val close = line.getOrNull(4) ?: "0"

               CompanyIntradayChartInfo(timestamp = localDateTime, close = close.toDouble())


            }.filter {
                it.timestamp.dayOfMonth == LocalDate.now().minusDays(4).dayOfMonth
            }
                .sortedBy {
                    it.timestamp.hour
                }.also {
                    csvReader.close()
                }
        }
    }
}