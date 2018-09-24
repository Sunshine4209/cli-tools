package io.busybusy.cli.tools

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.choice
import javax.inject.Inject


//create-db-access --access-level=admin sunshine
class CreateDbAccess
@Inject constructor(private val createUser: CreateSQLUser) {

    fun run(access: String, username: String) {

        when (access) {

            "read" -> createUser.read(username)
            "write" -> createUser.write(username)
            "alter" -> createUser.alter(username)
        }
    }
}