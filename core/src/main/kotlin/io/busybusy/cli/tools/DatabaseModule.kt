package io.busybusy.cli.tools

import com.mysql.cj.jdbc.MysqlDataSource
import java.net.URI

class DatabaseModule(val url: URI) {
    public val dataSource by lazy {
        MysqlDataSource().apply {
            setUrl("jdbc:$url")
            user = url.userInfo.substringBefore(':')
            setPassword(url.userInfo.substringAfter(':'))
            useSSL = false
            serverTimezone = "UTC"
            databaseName = url.path
            serverName = url.host
        }
    }

    companion object {
        fun fromEnv() = DatabaseModule(
                url = URI(System.getenv("DATABASE_URL") ?: error("Could not get: DATABASE_URL"))
        )
    }
}