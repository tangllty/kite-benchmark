package com.tang.benchmark

import com.tang.benchmark.factory.DataSourceFactory
import com.tang.benchmark.flex.FlexInitializer
import com.tang.benchmark.kite.KiteInitializer
import com.tang.benchmark.plus.PlusInitializer
import com.tang.kite.config.KiteConfig

/**
 * @author Tang
 */
fun main() {
    val dataSource = DataSourceFactory.getDataSource()

    KiteInitializer.init(dataSource)
    FlexInitializer.init(dataSource)
    PlusInitializer.init(dataSource)

    KiteConfig.sql.sqlLogging = false

    printAverageTime("selectById", selectById())
    printAverageTime("paginate", paginate())
    printAverageTime("insert", insert())
    printAverageTime("updateById", updateById())
    printAverageTime("deleteById", deleteById())
}

const val repeatTimes = 50

fun printAverageTime(methodName: String, averageTime: AverageTime) {
    val kiteAverage = String.format("%.1f", averageTime.kiteAverage / 1_000_000.0).toDouble()
    val flexAverage = String.format("%.1f", averageTime.flexAverage / 1_000_000.0).toDouble()
    val plusAverage = String.format("%.1f", averageTime.plusAverage / 1_000_000.0).toDouble()

    println()
    println("[${methodName}] Kite average time: ${kiteAverage}ms")
    println("[${methodName}] Flex average time: ${flexAverage}ms")
    println("[${methodName}] Plus average time: ${plusAverage}ms")
}

class AverageTime(val kiteAverage: Double, val flexAverage: Double, val plusAverage: Double)

fun selectById(): AverageTime {
    KiteInitializer.selectById()
    FlexInitializer.selectById()
    PlusInitializer.selectById()

    val kiteTimes = mutableListOf<Long>()
    val flexTimes = mutableListOf<Long>()
    val plusTimes = mutableListOf<Long>()

    repeat(repeatTimes) {
        val kiteStart = System.nanoTime()
        KiteInitializer.selectById()
        val kiteEnd = System.nanoTime()
        kiteTimes.add(kiteEnd - kiteStart)

        val flexStart = System.nanoTime()
        FlexInitializer.selectById()
        val flexEnd = System.nanoTime()
        flexTimes.add(flexEnd - flexStart)

        val plusStart = System.nanoTime()
        PlusInitializer.selectById()
        val plusEnd = System.nanoTime()
        plusTimes.add(plusEnd - plusStart)
    }

    val kiteAverage = kiteTimes.average()
    val flexAverage = flexTimes.average()
    val plusAverage = plusTimes.average()

    return AverageTime(kiteAverage, flexAverage, plusAverage)
}

fun paginate(): AverageTime {
    KiteInitializer.paginate()
    FlexInitializer.paginate()
    PlusInitializer.paginate()

    val kiteTimes = mutableListOf<Long>()
    val flexTimes = mutableListOf<Long>()
    val plusTimes = mutableListOf<Long>()

    repeat(repeatTimes) {
        val kiteStart = System.nanoTime()
        KiteInitializer.paginate()
        val kiteEnd = System.nanoTime()
        kiteTimes.add(kiteEnd - kiteStart)

        val flexStart = System.nanoTime()
        FlexInitializer.paginate()
        val flexEnd = System.nanoTime()
        flexTimes.add(flexEnd - flexStart)

        val plusStart = System.nanoTime()
        PlusInitializer.paginate()
        val plusEnd = System.nanoTime()
        plusTimes.add(plusEnd - plusStart)
    }

    val kiteAverage = kiteTimes.average()
    val flexAverage = flexTimes.average()
    val plusAverage = plusTimes.average()

    return AverageTime(kiteAverage, flexAverage, plusAverage)
}

fun insert(): AverageTime {
    KiteInitializer.insert()
    FlexInitializer.insert()
    PlusInitializer.insert()

    val kiteTimes = mutableListOf<Long>()
    val flexTimes = mutableListOf<Long>()
    val plusTimes = mutableListOf<Long>()

    repeat(repeatTimes) {
        val kiteStart = System.nanoTime()
        KiteInitializer.insert()
        val kiteEnd = System.nanoTime()
        kiteTimes.add(kiteEnd - kiteStart)

        val flexStart = System.nanoTime()
        FlexInitializer.insert()
        val flexEnd = System.nanoTime()
        flexTimes.add(flexEnd - flexStart)

        val plusStart = System.nanoTime()
        PlusInitializer.insert()
        val plusEnd = System.nanoTime()
        plusTimes.add(plusEnd - plusStart)
    }

    val kiteAverage = kiteTimes.average()
    val flexAverage = flexTimes.average()
    val plusAverage = plusTimes.average()

    return AverageTime(kiteAverage, flexAverage, plusAverage)
}

fun updateById(): AverageTime {
    KiteInitializer.updateById()
    FlexInitializer.updateById()
    PlusInitializer.updateById()

    val kiteTimes = mutableListOf<Long>()
    val flexTimes = mutableListOf<Long>()
    val plusTimes = mutableListOf<Long>()

    repeat(repeatTimes) {
        val kiteStart = System.nanoTime()
        KiteInitializer.updateById()
        val kiteEnd = System.nanoTime()
        kiteTimes.add(kiteEnd - kiteStart)

        val flexStart = System.nanoTime()
        FlexInitializer.updateById()
        val flexEnd = System.nanoTime()
        flexTimes.add(flexEnd - flexStart)

        val plusStart = System.nanoTime()
        PlusInitializer.updateById()
        val plusEnd = System.nanoTime()
        plusTimes.add(plusEnd - plusStart)
    }

    val kiteAverage = kiteTimes.average()
    val flexAverage = flexTimes.average()
    val plusAverage = plusTimes.average()

    return AverageTime(kiteAverage, flexAverage, plusAverage)
}

fun deleteById(): AverageTime {
    KiteInitializer.deleteById()
    FlexInitializer.deleteById()
    PlusInitializer.deleteById()

    val kiteTimes = mutableListOf<Long>()
    val flexTimes = mutableListOf<Long>()
    val plusTimes = mutableListOf<Long>()

    repeat(repeatTimes) {
        val kiteStart = System.nanoTime()
        KiteInitializer.deleteById()
        val kiteEnd = System.nanoTime()
        kiteTimes.add(kiteEnd - kiteStart)

        val flexStart = System.nanoTime()
        FlexInitializer.deleteById()
        val flexEnd = System.nanoTime()
        flexTimes.add(flexEnd - flexStart)

        val plusStart = System.nanoTime()
        PlusInitializer.deleteById()
        val plusEnd = System.nanoTime()
        plusTimes.add(plusEnd - plusStart)
    }

    val kiteAverage = kiteTimes.average()
    val flexAverage = flexTimes.average()
    val plusAverage = plusTimes.average()

    return AverageTime(kiteAverage, flexAverage, plusAverage)
}
