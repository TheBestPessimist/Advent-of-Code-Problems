val javaVersion = JavaVersion.VERSION_16
val kotlinLanguageVersion = "1.6"

java.sourceCompatibility = javaVersion
java.targetCompatibility = javaVersion

plugins {
    kotlin("jvm") version "1.6.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}

tasks {
    wrapper {
        gradleVersion = "7.3"
        distributionType = Wrapper.DistributionType.ALL
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            @Suppress("SuspiciousCollectionReassignment")
            freeCompilerArgs += listOf("-Xjsr305=strict")
            jvmTarget = javaVersion.majorVersion
            languageVersion = kotlinLanguageVersion
            apiVersion = kotlinLanguageVersion
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }
}
