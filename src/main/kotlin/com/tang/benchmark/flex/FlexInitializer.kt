package com.tang.benchmark.flex

import com.mybatisflex.core.MybatisFlexBootstrap
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.query.QueryWrapper
import com.tang.benchmark.MethodTester
import com.tang.benchmark.factory.DataSourceFactory
import com.tang.benchmark.Account
import java.time.LocalDateTime
import javax.sql.DataSource

/**
 * @author Tang
 */
object FlexInitializer : MethodTester {

    private lateinit var bootstrap: MybatisFlexBootstrap

    override fun init(dataSource: DataSource) {
        bootstrap = MybatisFlexBootstrap.getInstance()
            .setDataSource(dataSource)
            .addMapper(AccountMapper::class.java)
            .start()
    }

    override fun selectById(): Account? {
        val mapper = bootstrap.getMapper(AccountMapper::class.java)
        return mapper.selectOneById((1..DataSourceFactory.DATA_LENGTH).random().toLong())
    }

    override fun paginate(): List<Account> {
        val mapper = bootstrap.getMapper(AccountMapper::class.java)
        val pageNumber = (1..(DataSourceFactory.DATA_LENGTH - 10)).random().toLong()
        return mapper.paginate(Page(pageNumber, 10), QueryWrapper()).records
    }

    override fun insert(): Int {
        val mapper = bootstrap.getMapper(AccountMapper::class.java)
        val account = Account(username = "test", password = "test", createTime = LocalDateTime.now(), balance = 100.0.toBigDecimal())
        return mapper.insert(account)
    }

    override fun updateById(): Int {
        val mapper = bootstrap.getMapper(AccountMapper::class.java)
        val account = Account(id = (1..DataSourceFactory.DATA_LENGTH).random().toLong(), updateTime = LocalDateTime.now(), balance = 100.0.toBigDecimal())
        return mapper.update(account)
    }

    override fun deleteById(): Int {
        val mapper = bootstrap.getMapper(AccountMapper::class.java)
        return mapper.deleteById((1..DataSourceFactory.DATA_LENGTH).random().toLong())
    }
}
