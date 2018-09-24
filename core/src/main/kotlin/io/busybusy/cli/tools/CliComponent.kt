package io.busybusy.cli.tools

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class])
interface CliComponent
{
    fun cli(): Cli
}