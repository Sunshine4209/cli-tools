package io.busybusy.cli.tools

import com.mysql.cj.jdbc.MysqlDataSource
import java.sql.PreparedStatement
import java.sql.SQLException
import kotlin.system.exitProcess

class CreateSQLUser(val dataSource: MysqlDataSource) {

    private val password = PasswordGenerator().generatePassword()
    private val schema = if (dataSource.databaseName.substringAfter('/') == "") "*" else dataSource.databaseName.substringAfter('/')
    private val host = dataSource.serverName

    fun read (username: String)
    {
        createUser(username)

        val query = "GRANT SELECT ON $schema.* TO ?@?;"
        val parameters = arrayOf(username, host)
        val stmt = prepareStatement(query, parameters)
        executeStatement(stmt, "SELECT permission granted successfully")

    }

    fun write (username: String)
    {
        createUser(username)

        val query = "GRANT SELECT, INSERT, UPDATE, DELETE ON $schema.* TO ?@?;"
        val parameters = arrayOf(username, host)
        val stmt = prepareStatement(query, parameters)
        executeStatement(stmt, "SELECT, INSERT, UPDATE, and DELETE permission granted successfully")
    }

    fun alter (username: String)
    {
        createUser(username)

        val query = "GRANT SELECT, INSERT, UPDATE, DELETE, ALTER, CREATE on $schema.* to ?@?;"
        val parameters = arrayOf(username, host)
        val stmt = prepareStatement(query, parameters)
        executeStatement(stmt, "SELECT, INSERT, UPDATE, DELETE, and ALTER permission granted successfully")
    }

    private fun createUser(username: String)
    {
        val query = "CREATE USER ?@? IDENTIFIED BY ?; "
        val parameters = arrayOf(username, host, password)
        val stmt = prepareStatement(query, parameters)
        val successResponse = "User $username successfully created. Password for this user is $password. Please keep this password for your records"
        executeStatement(stmt, successResponse)
    }

    private fun executeStatement(stmt: PreparedStatement, success: String)
    {
        try {
            stmt.execute()

            stmt.close()
            println(success)

        } catch (ex: SQLException) {
            println("abort! abort! " + ex.message)
        }
    }

    private fun prepareStatement(query: String, parameters: Array<String> = emptyArray()): PreparedStatement
    {
        try {
            val stmt = dataSource.connection.prepareStatement(query)

            parameters.forEachIndexed {
                index, s -> stmt.setString((index+1), s)
            }

            return stmt

        } catch (ex: SQLException) {
            // we are exiting if we are running into an error while preparing the statement.
            println("abort! abort! " + ex.message)
            exitProcess(2)
        }
    }
}