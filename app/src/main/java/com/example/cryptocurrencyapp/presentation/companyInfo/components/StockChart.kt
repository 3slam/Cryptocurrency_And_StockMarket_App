package com.example.cryptocurrencyapp.presentation.companyInfo.components

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptocurrencyapp.domain.entity.CompanyIntradayChartInfo
import kotlin.math.round
import kotlin.math.roundToInt


val yellow  = Color(0xFFFFC107)
@Composable
fun StockChart(
    infos: List<CompanyIntradayChartInfo> = emptyList(),
    modifier: Modifier = Modifier,
    graphColor: Color = yellow
) {
    val spacing = 100f
    val transparentGraphColor = remember {
        graphColor.copy(alpha = 0.5f)
    }
    val upperValue = remember(infos) {
        (infos.maxOfOrNull { it.close }?.plus(1))?.roundToInt() ?: 0
    }
    val lowerValue = remember(infos) {
        infos.minOfOrNull { it.close }?.toInt() ?: 0
    }
    val density = LocalDensity.current
    val textPaint = remember(density) {
        Paint().apply {
            color = android.graphics.Color.WHITE
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }
    Canvas(modifier = modifier) {
        val spacePerHour = (size.width - spacing) / infos.size
        (0 until infos.size - 1 step 2).forEach { i ->
            val info = infos[i]
            val hour = info.timestamp.hour
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    hour.toString(),
                    spacing + i * spacePerHour,
                    size.height - 5,
                    textPaint
                )
            }
        }
        val priceStep = (upperValue - lowerValue) / 5f
        (0..4).forEach { i ->
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    round(lowerValue + priceStep * i).toString(),
                    30f,
                    size.height - spacing - i * size.height / 5f,
                    textPaint
                )
            }
        }
        var lastX = 0f
        val strokePath = Path().apply {
            val height = size.height
            for(i in infos.indices) {
                val info = infos[i]
                val nextInfo = infos.getOrNull(i + 1) ?: infos.last()
                val leftRatio = (info.close - lowerValue) / (upperValue - lowerValue)
                val rightRatio = (nextInfo.close - lowerValue) / (upperValue - lowerValue)

                val x1 = spacing + i * spacePerHour
                val y1 = height - spacing - (leftRatio * height).toFloat()
                val x2 = spacing + (i + 1) * spacePerHour
                val y2 = height - spacing - (rightRatio * height).toFloat()
                if(i == 0) {
                    moveTo(x1, y1)
                }
                lastX = (x1 + x2) / 2f
                quadraticBezierTo(
                    x1, y1, lastX, (y1 + y2) / 2f
                )
            }
        }
        val fillPath = android.graphics.Path(strokePath.asAndroidPath())
            .asComposePath()
            .apply {
                lineTo(lastX, size.height - spacing)
                lineTo(spacing, size.height - spacing)
                close()
            }
        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(
                    transparentGraphColor,
                    Color.Transparent
                ),
                endY = size.height - spacing
            )
        )
        drawPath(
            path = strokePath,
            color = graphColor,
            style = Stroke(
                width = 3.dp.toPx(),
                cap = StrokeCap.Round
            )
        )
    }
}




//val yellow  = Color(0xFFFFC107)
//
//@Composable
//fun StockChart(
//    infos: List<CompanyIntradayChartInfo> = emptyList()
//){
//
//
//    val upperValue = remember(infos) {
//        (infos.maxOfOrNull { it.close }?.plus(1))?.roundToInt() ?: 0
//    }
//
//    val lowerValue = remember(infos) {
//        infos.minOfOrNull { it.close }?.toInt() ?: 0
//    }
//
//    val priceStep = (upperValue - lowerValue) / 5f
//
//    val steps=5
//
//    val pointsData: List<Point> =
//        listOf(
//            Point(0f, 40f),
//            Point(1f, 90f),
//            Point(2f, 0f),
//            Point(3f, 60f),
//            Point(4f, 10f))
//
//    val xAxisData = AxisData.Builder()
//        .axisStepSize(100.dp)
//        .backgroundColor( MaterialTheme.colorScheme.background)
//        .steps(infos.size)
//        .labelData { i->
//          infos[i].timestamp.hour.toString()
//        }
//        .axisLineColor( yellow)
//        .axisLabelColor( yellow)
//        .build()
//
//    val yAxisData = AxisData.Builder()
//        .steps(5)
//        .backgroundColor( MaterialTheme.colorScheme.background)
//        .labelData { i ->
//            round(lowerValue + priceStep * i).toString()
//        }
//        .axisLineColor(yellow)
//        .axisLabelColor(yellow)
//        .build()
//
//
//    val lineChartData = LineChartData(
//        linePlotData = LinePlotData(
//            lines = listOf(
//                Line(
//                    dataPoints = pointsData,
//                    LineStyle(
//                        color = Color.Red,
//                        lineType = LineType.SmoothCurve(isDotted = false)
//                    ),
//                    IntersectionPoint(
//                        color = Color.Transparent,
//                    ),
//                    SelectionHighlightPoint(
//                      //  color = MaterialTheme.colorScheme.tertiary,
//                    ),
//                    ShadowUnderLine(
//                        alpha = .5f ,
//                        brush = Brush.Companion.verticalGradient(
//                            listOf(
//                                yellow,
//                                 Color.Transparent
//                            )
//
//                        )
//                    ),
//                    SelectionHighlightPopUp()
//                )
//            ),
//        ),
//        xAxisData = xAxisData,
//        yAxisData = yAxisData,
//        backgroundColor = MaterialTheme.colorScheme.background
//    )
//
//   Box (
//       modifier = Modifier
//           .fillMaxWidth()
//           .height(300.dp).background(
//             MaterialTheme.colorScheme.background
//           )
//   ) {
//    LineChart(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(300.dp),
//        lineChartData = lineChartData
//    )
//   }
//
//}
//
