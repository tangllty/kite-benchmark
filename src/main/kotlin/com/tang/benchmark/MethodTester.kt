package com.tang.benchmark

import com.tang.benchmark.Account
import javax.sql.DataSource

/**
 * @author Tang
 */
interface MethodTester {

    fun init(dataSource: DataSource)

    fun selectById(): Account?

    fun insert(): Int

    fun updateById(): Int

    fun deleteById(): Int

}