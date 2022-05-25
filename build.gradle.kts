import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
   id("org.springframework.boot") version "2.7.0"
   kotlin("jvm") version "1.5.30"
   kotlin("plugin.spring") version "1.6.21"
}

repositories {
   mavenCentral()
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

dependencies {
   implementation(kotlin("stdlib"))
   implementation(kotlin("reflect"))
   implementation("org.springframework.boot:spring-boot-starter-webflux:2.7.0")
   implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3")
   implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.5")
   implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.5.2")
   testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.0") {
      exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
      exclude(module = "mockito-core")
   }
   testImplementation("com.ninja-squad:springmockk:3.1.1")
   testImplementation("io.projectreactor:reactor-test:3.4.18")
   testImplementation("io.kotest:kotest-runner-junit5:5.3.0")
   testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.1")
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
