plugins {
    id("java")
    id("org.springframework.boot") version "2.7.10" apply false
    id("io.spring.dependency-management") version "1.0.15.RELEASE" apply false
}

allprojects {
    apply(plugin = "java") // Gradle의 Java 플러그인을 적용

    group = "com.heyd"
    version = "1.0-SNAPSHOT"

    java.sourceCompatibility = JavaVersion.VERSION_11

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    tasks.getByName<Test>("test") {
        useJUnitPlatform()
    }

    tasks.register("prepareKotlinBuildScriptModel")
}
