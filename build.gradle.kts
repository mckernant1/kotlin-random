import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.20"
    application
}

group = "com.github.mckernant1"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(uri("https://jitpack.io"))
    maven {
        url = uri("https://mvn.mckernant1.com/release")
    }
    maven {
        name = "m2-dv8tion"
        url = uri( "https://m2.dv8tion.net/releases")
    }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")

    // My Libs
    implementation("com.mckernant1.commons:kotlin-utils:0.2.1")
    implementation("com.mckernant1:event-scheduler:0.0.2")
    implementation("com.mckernant1.lol:esports-api:0.1.0")
    implementation("com.github.mckernant1.lol.blitzcrank:lol-predictions-bot-models:0.0.1")

    // Discord
    implementation("net.dv8tion:JDA:4.4.0_352")

    // Sql
    implementation("org.ktorm:ktorm-core:3.6.0")

    // Logging + Utils
    implementation("org.slf4j:slf4j-api:2.0.5")
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.20.0")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.20.0")

    // Utils
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.4")

    // AWS
    implementation(platform("software.amazon.awssdk:bom:2.17.204"))
    implementation("software.amazon.awssdk:cloudwatch")
    implementation("software.amazon.awssdk:cloudwatchlogs")
    implementation("software.amazon.awssdk:dynamodb-enhanced")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}


files("src/main/kotlin")
    .asFileTree
    .files
    .asSequence()
    .filter { it.readText().contains("fun main()") }
    .map { it.path }
    .filter { it.endsWith(".kt") }
    .map { it.removeSuffix(".kt") }
    .map { it.replace("/", ".") }
    .map { it.drop(it.indexOf("src.main.kotlin.") + "src.main.kotlin.".length) }
    .forEach {
        val runnerName = it.takeLastWhile { it != '.' }
        tasks.register(runnerName, JavaExec::class.java) {
            dependsOn(tasks.build)
            classpath = sourceSets["main"].runtimeClasspath
            mainClass.set("${it}Kt")
        }
    }


application {
    mainClass.set("MainKt")
}
