plugins {
    application
    kotlin("jvm")
    id("kotlin-kapt")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("mysql", "mysql-connector-java", "8.0.12")
    compile("com.github.ajalt:clikt:1.5.0")
    implementation("com.google.dagger", "dagger", "2.17")
    kapt("com.google.dagger", "dagger-compiler", "2.17")
}

dependencies {
    testImplementation(kotlin("test-junit5"))

    val junit = "5.3.0"
    testImplementation("org.junit.jupiter", "junit-jupiter-api", junit)
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", junit)

    testImplementation("com.nhaarman", "mockito-kotlin", "1.6.0")
}


application {
    mainClassName = "io.busybusy.cli.tools.MainKt"
    this.applicationName = "create-db-access"
}
