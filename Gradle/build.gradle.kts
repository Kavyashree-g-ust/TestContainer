plugins {
    java
}

group = "sdet"
version = "0.1.0"


val seleniumVersion = "4.45.0"
val junitVersion = "5.13.4"
val cucumberVersion = "7.31.0"
val testcontainersVersion="2.0.5"
val slf4jVersion = "2.0.17"
var flywayVersion="10.22.0"
var mysqlVersion="8.4.0"

java {
    sourceCompatibility = JavaVersion.VERSION_22
    targetCompatibility = JavaVersion.VERSION_22
}

dependencies {

    // Selenium
    testImplementation("org.seleniumhq.selenium:selenium-java:$seleniumVersion")

    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")

    // Cucumber
    testImplementation("io.cucumber:cucumber-java:$cucumberVersion")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:$cucumberVersion")
    testImplementation("io.cucumber:cucumber-picocontainer:$cucumberVersion")

    // JUnit Platform Suite
    testImplementation("org.junit.platform:junit-platform-suite:1.13.4")
    testImplementation("org.slf4j:slf4j-simple:${slf4jVersion}")
    testImplementation(platform("org.testcontainers:testcontainers-bom:$testcontainersVersion"))
    testImplementation("org.testcontainers:testcontainers-junit-jupiter:$testcontainersVersion")
    testImplementation("org.flywaydb:flyway-core:$flywayVersion")
    testImplementation("org.testcontainers:testcontainers-mysql:${testcontainersVersion}")
    testImplementation("org.flywaydb:flyway-mysql:${flywayVersion}")
    testImplementation("com.mysql:mysql-connector-j:${mysqlVersion}")


    testImplementation("org.assertj:assertj-core:3.27.3")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release.set(22)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()

    systemProperty(
        "baseUrl",
        providers.gradleProperty("baseUrl")
            .orElse("http://localhost:5173")
            .get()
    )

    systemProperty(
        "headless",
        providers.gradleProperty("headless")
            .orElse("false")
            .get()
    )

    systemProperty(
        "browser",
        providers.gradleProperty("browser")
            .orElse("chrome")
            .get()
    )

    systemProperty("cucumber.publish.quiet", "true")

    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.test {
    useJUnitPlatform()
}