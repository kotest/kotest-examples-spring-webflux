import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
   alias(libs.plugins.springBoot)
   alias(libs.plugins.kotlin.jvm)
   alias(libs.plugins.kotlin.plugin.spring)
}

repositories {
   mavenCentral()
}

val javaTarget = JavaVersion.VERSION_17
group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = javaTarget

extra["kotlinx-coroutines.version"] = libs.versions.kotlinxCoroutines

dependencies {
   implementation(platform(libs.springBoot.bom))
   implementation(kotlin("reflect"))
   implementation(libs.springBoot.starterWebflux)
   implementation(libs.jackson.module.kotlin)
   implementation(libs.reactor.kotlinExtensions)
   implementation(libs.kotlinxCoroutines.reactor)

   testImplementation(libs.springmockk)
   testImplementation(libs.kotest.runnerJunit5)
   testImplementation(libs.kotestExtensions.spring)
   testImplementation(libs.reactor.test)
   testImplementation(libs.springBoot.starterTest) {
      exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
      exclude(module = "mockito-core")
   }
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
      jvmTarget = javaTarget.toString()
   }
}
