import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URI

plugins {
    base
    kotlin("jvm") version "1.2.70" apply false
}

allprojects {
    group = "io.busybusy.cli.tools"
    version = "1.0"

    repositories { 
        jcenter()
    }

    tasks.withType(KotlinCompile::class.java).all {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}
