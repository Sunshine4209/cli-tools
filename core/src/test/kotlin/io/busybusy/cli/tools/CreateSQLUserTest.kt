package io.busybusy.cli.tools

import com.mysql.cj.jdbc.MysqlDataSource
import com.nhaarman.mockito_kotlin.*
import org.junit.jupiter.api.Test
import java.sql.Connection
import java.sql.PreparedStatement

internal class CreateSQLUserTest {

    @Test
    fun `create read user`() {

        val stmt = mock<PreparedStatement>()

        val connectionMock = mock<Connection>()
        {
            this.on { prepareStatement("CREATE USER ?@? IDENTIFIED BY ?;")}.thenReturn(stmt)
            this.on { prepareStatement("GRANT SELECT ON fml.* TO ?@?;")}.thenReturn(stmt)
        }

        val dataSource = mock<MysqlDataSource>()
        {
            this.on { databaseName } doReturn "fml"
            this.on { serverName } doReturn "localhost"
            this.on { connection } doReturn connectionMock
        }

        val createSQLUser = CreateSQLUser(dataSource)

        createSQLUser.read("username")
    }
}