package io.busybusy.cli.tools

import com.nhaarman.mockito_kotlin.*
import org.junit.jupiter.api.Test

internal class CreateDbAccessTest {

    @Test
    fun `create read user`() {
        val createSqlUser = mock<CreateSQLUser>()

        val createDbAccess = CreateDbAccess(createSqlUser)

        createDbAccess.run("read", "username")

        verify(createSqlUser, times(1)).read("username")
        verify(createSqlUser, never()).write(any())
        verify(createSqlUser, never()).alter(any())
    }

    @Test
    fun `create write user`() {
        val createSqlUser = mock<CreateSQLUser>()

        val createDbAccess = CreateDbAccess(createSqlUser)

        createDbAccess.run("write", "username")

        verify(createSqlUser, never()).read("username")
        verify(createSqlUser, times(1)).write(any())
        verify(createSqlUser, never()).alter(any())
    }

    @Test
    fun `create alter user`() {
        val createSqlUser = mock<CreateSQLUser>()

        val createDbAccess = CreateDbAccess(createSqlUser)

        createDbAccess.run("alter", "username")

        verify(createSqlUser, never()).read("username")
        verify(createSqlUser, never()).write(any())
        verify(createSqlUser, times(1)).alter(any())
    }
}