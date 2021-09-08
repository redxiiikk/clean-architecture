plugins {
    kotlin("jvm")
    kotlin("kapt")
}

group = "hk.qingke.user-manager"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":service"))
    implementation(project(":domain"))

    implementation("org.mapstruct:mapstruct:1.4.2.Final")
    kapt("org.mapstruct:mapstruct-processor:1.4.2.Final")

    implementation(kotlin("stdlib"))
}
