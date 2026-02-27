package com.tang.benchmark.factory

import com.tang.kite.datasource.unpooled.UnpooledDataSourceFactory
import com.tang.kite.io.Resources
import org.yaml.snakeyaml.Yaml
import javax.sql.DataSource

/**
 * @author Tang
 */
object DataSourceFactory {

    const val DATA_LENGTH = 20_000

    private fun createDataSource(): DataSource {
        val inputStream = Resources.getResourceAsStream("kite-config.yml")
        val yaml = Yaml().load<Map<String, Map<String, Map<String, String>>>>(inputStream)
        val kite = yaml["kite"]
        val datasource = kite!!["datasource"] as Map<String, String>
        return UnpooledDataSourceFactory(datasource).getDataSource()
    }

    private fun createDatabase(dataSource: DataSource) {
        val database = Resources.getResourceAsStream("kite-test.sql")
        dataSource.connection.use { connection ->
            connection.createStatement().use { statement ->
                database.bufferedReader().useLines { lines ->
                    val sqlLines = ArrayList<String>()
                    val sqlBuilder = StringBuilder()
                    lines.forEach { line ->
                        if (line.isBlank() || line.startsWith("--")) {
                            return@forEach
                        }
                        sqlBuilder.append(line)
                        if (line.endsWith(";")) {
                            sqlLines.add(sqlBuilder.toString())
                            sqlBuilder.clear()
                        }
                    }
                    sqlLines.addAll(getData())
                    sqlLines.forEach { sql ->
                        try {
                            statement.execute(sql.replace(";", ""))
                        } catch (e: Exception) {e.printStackTrace()}
                    }
                }
            }
        }
    }

    private fun getData(): List<String> {
        val data = ArrayList<String>()
        for (i in 1 until DATA_LENGTH) {
            val username = "user$i"
            val password = "password$i"
            val createTime = "2026-01-01 00:00:00"
            val balance = 1000.00 + i
            data.add("insert into account (username, password, create_time, balance) values ('$username', '$password', '$createTime', $balance);")
        }
        return data
    }

    fun getDataSource(): DataSource {
        val dataSource = createDataSource()
        createDatabase(dataSource)
        return dataSource
    }

}
