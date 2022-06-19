import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    application
}

group = "com.github.mckernant1"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
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

    implementation("com.github.mckernant1:kotlin-utils:0.0.30")
    implementation("com.github.mckernant1.lol:esports-api:0.0.11")
    implementation("com.github.mckernant1.lol.blitzcrank:lol-predictions-bot-models:0.0.1")

    implementation("net.dv8tion:JDA:4.4.0_352")

    implementation("org.slf4j:slf4j-simple:1.7.36")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.3")

    implementation(platform("software.amazon.awssdk:bom:2.17.204"))
    implementation("software.amazon.awssdk:cloudwatch")
    implementation("software.amazon.awssdk:cloudwatchlogs")
    implementation("software.amazon.awssdk:dynamodb-enhanced")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
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
