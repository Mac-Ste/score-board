import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.0"
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("plugin.spring") version "1.8.0"
}

group = "com.macste"
version = "0.0.1-TEST"


repositories {
    mavenCentral()
}

dependencies {
    val logbackVersion = "1.4.11"
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    runtimeOnly("org.springframework.boot:spring-boot-devtools")

    // TEST
    val kotestVersion = "5.8.0"
    val mockkVersion = "1.13.8"
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
    }
}

kotlin {
    jvmToolchain(17)
}
