plugins {
    kotlin("jvm")
}

group = "hk.qingke.user-manager"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":domain"))

    testImplementation("org.mockito.kotlin:mockito-kotlin:3.1.0")
}
