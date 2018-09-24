package io.busybusy.cli.tools

import com.mysql.cj.jdbc.MysqlDataSource
import dagger.Module
import dagger.Provides
import java.net.URI
import javax.inject.Singleton

@Module
class DatabaseModule(private val url: URI) {

    @Provides
    @Singleton
    fun provideDataSource(): MysqlDataSource {
        return MysqlDataSource().apply {
            setUrl("jdbc:$url")
            user = url.userInfo.substringBefore(':')
            setPassword(url.userInfo.substringAfter(':'))
            useSSL = false
            serverTimezone = "UTC"
            databaseName = url.path
            serverName = url.host
        }
    }
}