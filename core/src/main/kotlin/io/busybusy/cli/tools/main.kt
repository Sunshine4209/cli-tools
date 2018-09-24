package io.busybusy.cli.tools

import java.net.URI


fun main(args: Array<String>)
{
    DaggerCliComponent.builder()
        .databaseModule(
            DatabaseModule(
                URI(System.getenv("DATABASE_URL") ?: error("Could not get: DATABASE_URL"))
            )
        )
        .build().cli().main(args)
}

