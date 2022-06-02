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

    implementation("com.github.mckernant1:kotlin-utils:0.0.25")
    implementation("com.github.mckernant1.lol:esports-api:0.0.11")

    implementation("net.dv8tion:JDA:4.4.0_352")

    implementation("org.slf4j:slf4j-simple:1.7.36")

    implementation(platform("software.amazon.awssdk:bom:2.17.+"))
    implementation("software.amazon.awssdk:cloudwatch")
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
