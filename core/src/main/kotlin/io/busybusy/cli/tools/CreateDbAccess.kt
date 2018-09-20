package io.busybusy.cli.tools

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.choice


//create-db-access --access-level=admin sunshine
class CreateDbAccess (val createUser: CreateSQLUser) : CliktCommand(name = "create-db-access") {

    val access by option("--access-level", help="each choice includes access level of the previous one. Default `read`")
            .choice("read", "write", "alter")
            .default("read")

    val username by argument(name = "username", help="username you want to use")

    override fun run() {

        when (access) {

            "read" -> createUser.read(username)
            "write" -> createUser.write(username)
            "alter" -> createUser.alter(username)
        }
    }
}