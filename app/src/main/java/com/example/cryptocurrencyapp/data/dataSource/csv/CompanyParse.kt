package com.example.cryptocurrencyapp.data.dataSource.csv

import android.util.Log
import com.example.cryptocurrencyapp.domain.entity.Company
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader

object CompanyParse  : CSVParser<Company> {

    override suspend fun parse(stream: InputStream): List<Company> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO) {
            csvReader.readAll().drop(1).mapNotNull { line ->

                    val symbol = line.getOrNull(0)
                    val name = line.getOrNull(1)
                    val exchange = line.getOrNull(2)
                    Log.i("result CompanyRepositoryImpl" ,name.toString())
                    Company(
                        name = name ?: return@mapNotNull null,
                        symbol = symbol ?: return@mapNotNull null,
                        exchange = exchange ?: return@mapNotNull null
                    )
                }
                .also {
                    csvReader.close()
                }
        }
    }
}