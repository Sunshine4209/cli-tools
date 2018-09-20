package io.busybusy.cli.tools


fun main(args: Array<String>)
{

    val databaseModule = DatabaseModule.fromEnv()
    val dataSource = databaseModule.dataSource

    CreateDbAccess(CreateSQLUser(dataSource)).main(args)
}

