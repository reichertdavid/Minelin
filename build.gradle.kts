import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
}

group = "de.rumeotech"
version = "1.0.0-ALPHA"

repositories {
    mavenCentral()

    // jitpack
    maven("https://jitpack.io")
}

dependencies {
    // Log4j Implementation
    implementation("org.apache.logging.log4j", "log4j-api", "2.19.0")
    implementation("org.apache.logging.log4j", "log4j-core", "2.19.0")

    // org.json implementation
    implementation("com.google.code.gson", "gson", "2.10")

    // khttp implementation
    implementation("com.github.jkcclemens", "khttp", "-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}