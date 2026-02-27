package com.tang.benchmark.plus

import com.baomidou.mybatisplus.core.MybatisConfiguration
import com.tang.benchmark.Account
import com.tang.benchmark.MethodTester
import com.tang.benchmark.factory.DataSourceFactory
import org.apache.ibatis.mapping.Environment
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.apache.ibatis.transaction.TransactionFactory
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory
import java.time.LocalDateTime
import javax.sql.DataSource

/**
 * @author Tang
 */
object PlusInitializer : MethodTester {

    private lateinit var sqlSessionFactory: SqlSessionFactory

    override fun init(dataSource: DataSource) {
        val transactionFactory: TransactionFactory = JdbcTransactionFactory()
        val environment = Environment("Production", transactionFactory, dataSource)
        val configuration = MybatisConfiguration(environment)
        configuration.addMapper(AccountMapper::class.java)
        sqlSessionFactory = SqlSessionFactoryBuilder().build(configuration)
    }

    override fun selectById(): Account? {
        val session = sqlSessionFactory.openSession()
        val mapper = session.getMapper(AccountMapper::class.java)
        return mapper.selectById((1..DataSourceFactory.DATA_LENGTH).random().toLong())
    }

    override fun insert(): Int {
        val session = sqlSessionFactory.openSession()
        val mapper = session.getMapper(AccountMapper::class.java)
        val account = Account(username = "test", password = "test", createTime = LocalDateTime.now(), balance = 100.0.toBigDecimal())
        val rows = mapper.insert(account)
        session.commit()
        return rows
    }

    override fun updateById(): Int {
        val session = sqlSessionFactory.openSession()
        val mapper = session.getMapper(AccountMapper::class.java)
        val account = Account(id = (1..DataSourceFactory.DATA_LENGTH).random().toLong(), updateTime = LocalDateTime.now(), balance = 100.0.toBigDecimal())
        val rows = mapper.updateById(account)
        session.commit()
        return rows
    }

    override fun deleteById(): Int {
        val session = sqlSessionFactory.openSession()
        val mapper = session.getMapper(AccountMapper::class.java)
        val rows = mapper.deleteById((1..DataSourceFactory.DATA_LENGTH).random().toLong())
        session.commit()
        return rows
    }

}
