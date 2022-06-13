import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
   id("org.springframework.boot") version "2.7.0"
   kotlin("jvm") version "1.6.21"
   kotlin("plugin.spring") version "1.6.21"
}

apply(plugin = "io.spring.dependency-management")

repositories {
   mavenCentral()
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

extra["kotlinx-coroutines.version"] = "1.6.2"

dependencies {
   implementation(kotlin("reflect"))
   implementation("org.springframework.boot:spring-boot-starter-webflux:2.7.0")
   testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.0") {
      exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
      exclude(module = "mockito-core")
   }
   testImplementation("com.ninja-squad:springmockk:3.1.1")
   testImplementation("io.kotest:kotest-runner-junit5:5.3.1")
   testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.1")

   // versions handled by spring dependency management plugin
   implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
   implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
   implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
   testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<Test> {
   useJUnitPlatform()
   filter {
      isFailOnNoMatchingTests = false
   }
}

tasks.withType<KotlinCompile> {
   kotlinOptions {
      freeCompilerArgs = listOf("-Xjsr305=strict")
      jvmTarget = "1.8"
   }
}
