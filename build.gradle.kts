plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.json:json:20231013")
    implementation("io.github.cdimascio:dotenv-java:3.0.0")
}

tasks.test {
    useJUnitPlatform()
}