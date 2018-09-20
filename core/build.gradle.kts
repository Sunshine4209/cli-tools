plugins {
    application
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("mysql", "mysql-connector-java", "8.0.12")
    compile("com.github.ajalt:clikt:1.5.0")
}


application {
    mainClassName = "io.busybusy.cli.tools.MainKt"
    this.applicationName = "create-db-access"
}
