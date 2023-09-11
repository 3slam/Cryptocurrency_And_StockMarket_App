package com.example.cryptocurrencyapp.domain.entity

import java.time.LocalDateTime

data class CompanyIntradayChartInfo(
    val timestamp: LocalDateTime,
    val close: Double
)