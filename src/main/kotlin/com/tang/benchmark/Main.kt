package com.tang.benchmark

import com.tang.benchmark.factory.DataSourceFactory
import com.tang.benchmark.flex.FlexInitializer
import com.tang.benchmark.kite.KiteInitializer
import com.tang.benchmark.plus.PlusInitializer

/**
 * @author Tang
 */
fun main() {
    val dataSource = DataSourceFactory.getDataSource()

    KiteInitializer.init(dataSource)
    FlexInitializer.init(dataSource)
    PlusInitializer.init(dataSource)


    printAverageTime("selectById", selectById())
    printAverageTime("insert", insert())
    printAverageTime("updateById", updateById())
    printAverageTime("deleteById", deleteById())
}

const val repeatTimes = 10

fun printAverageTime(methodName: String, averageTime: AverageTime) {
    println()
    println("[${methodName}] Kite average time: ${averageTime.kiteAverage}ms")
    println("[${methodName}] Flex average time: ${averageTime.flexAverage}ms")
    println("[${methodName}] Plus average time: ${averageTime.plusAverage}ms")
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
        val kiteStart = System.currentTimeMillis()
        KiteInitializer.selectById()
        val kiteEnd = System.currentTimeMillis()
        kiteTimes.add(kiteEnd - kiteStart)

        val flexStart = System.currentTimeMillis()
        FlexInitializer.selectById()
        val flexEnd = System.currentTimeMillis()
        flexTimes.add(flexEnd - flexStart)

        val plusStart = System.currentTimeMillis()
        PlusInitializer.selectById()
        val plusEnd = System.currentTimeMillis()
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
        val kiteStart = System.currentTimeMillis()
        KiteInitializer.insert()
        val kiteEnd = System.currentTimeMillis()
        kiteTimes.add(kiteEnd - kiteStart)

        val flexStart = System.currentTimeMillis()
        FlexInitializer.insert()
        val flexEnd = System.currentTimeMillis()
        flexTimes.add(flexEnd - flexStart)

        val plusStart = System.currentTimeMillis()
        PlusInitializer.insert()
        val plusEnd = System.currentTimeMillis()
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
        val kiteStart = System.currentTimeMillis()
        KiteInitializer.updateById()
        val kiteEnd = System.currentTimeMillis()
        kiteTimes.add(kiteEnd - kiteStart)

        val flexStart = System.currentTimeMillis()
        FlexInitializer.updateById()
        val flexEnd = System.currentTimeMillis()
        flexTimes.add(flexEnd - flexStart)

        val plusStart = System.currentTimeMillis()
        PlusInitializer.updateById()
        val plusEnd = System.currentTimeMillis()
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
        val kiteStart = System.currentTimeMillis()
        KiteInitializer.deleteById()
        val kiteEnd = System.currentTimeMillis()
        kiteTimes.add(kiteEnd - kiteStart)

        val flexStart = System.currentTimeMillis()
        FlexInitializer.deleteById()
        val flexEnd = System.currentTimeMillis()
        flexTimes.add(flexEnd - flexStart)

        val plusStart = System.currentTimeMillis()
        PlusInitializer.deleteById()
        val plusEnd = System.currentTimeMillis()
        plusTimes.add(plusEnd - plusStart)
    }

    val kiteAverage = kiteTimes.average()
    val flexAverage = flexTimes.average()
    val plusAverage = plusTimes.average()

    return AverageTime(kiteAverage, flexAverage, plusAverage)
}
