rootProject.name = "kotest-examples-spring-webflux"

if (File("../kotest-extensions-spring").exists() && System.getenv("NO_INCLUDE") == null) {
   println("w: Including [kotest-extensions-spring] build")
   includeBuild("../kotest-extensions-spring")
}

if (File("../kotest").exists() && System.getenv("NO_INCLUDE") == null) {
   println("w: Including Kotest build, will substitute all Kotest stuff for currently checked out implementations")
   includeBuild("../kotest")
}
