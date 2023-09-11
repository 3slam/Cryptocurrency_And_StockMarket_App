package com.example.cryptocurrencyapp.data.dataSource.csv

import java.io.InputStream

interface CSVParser<T> {
    suspend fun parse(stream: InputStream): List<T>
}