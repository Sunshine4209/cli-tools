package io.busybusy.cli.tools

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.choice
import javax.inject.Inject


class Cli
@Inject constructor(private val createDbAccess: CreateDbAccess):  CliktCommand(name = "create-db-access")  {

    val access by option("--access-level", help="each choice includes access level of the previous one. Default `read`")
            .choice("read", "write", "alter")
            .default("read")

    val username by argument(name = "username", help="username you want to use")

    override fun run() {
        createDbAccess.run(access, username)
    }
}