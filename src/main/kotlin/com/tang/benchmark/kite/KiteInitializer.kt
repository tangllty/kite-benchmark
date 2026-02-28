package com.tang.benchmark.kite

import com.tang.benchmark.Account
import com.tang.benchmark.MethodTester
import com.tang.benchmark.factory.DataSourceFactory
import com.tang.kite.session.factory.SqlSessionFactory
import com.tang.kite.session.factory.SqlSessionFactoryBuilder
import java.time.LocalDateTime
import javax.sql.DataSource

/**
 * @author Tang
 */
object KiteInitializer : MethodTester {

    private lateinit var sqlSessionFactory: SqlSessionFactory

    override fun init(dataSource: DataSource) {
        sqlSessionFactory = SqlSessionFactoryBuilder().build(dataSource)
    }

    override fun selectById(): Account? {
        val session = sqlSessionFactory.openSession()
        val mapper = session.getMapper(AccountMapper::class)
        return mapper.selectById((1..DataSourceFactory.DATA_LENGTH).random().toLong())
    }

    override fun paginate(): List<Account> {
        val session = sqlSessionFactory.openSession()
        val mapper = session.getMapper(AccountMapper::class)
        val pageNumber = (1..(DataSourceFactory.DATA_LENGTH - 10)).random().toLong()
        return mapper.paginate(pageNumber, 10).rows
    }

    override fun insert(): Int {
        val session = sqlSessionFactory.openSession()
        val mapper = session.getMapper(AccountMapper::class)
        val account = Account(username = "test", password = "test", createTime = LocalDateTime.now(), balance = 100.0.toBigDecimal())
        val rows = mapper.insert(account)
        session.commit()
        return rows
    }

    override fun updateById(): Int {
        val session = sqlSessionFactory.openSession()
        val mapper = session.getMapper(AccountMapper::class)
        val account = Account(id = (1..DataSourceFactory.DATA_LENGTH).random().toLong(), updateTime = LocalDateTime.now(), balance = 100.0.toBigDecimal())
        val rows = mapper.update(account)
        session.commit()
        return rows
    }

    override fun deleteById(): Int {
        val session = sqlSessionFactory.openSession()
        val mapper = session.getMapper(AccountMapper::class)
        val rows = mapper.deleteById((1..DataSourceFactory.DATA_LENGTH).random().toLong())
        session.commit()
        return rows
    }

}
